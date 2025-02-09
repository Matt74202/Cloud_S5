package com.cloud.Crypto.service;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import model.FirestoreSyncable;
import model.FirestoreTimeConverter;

@SuppressWarnings("unused")
@Service
public class FirestoreService {
    private static final Logger logger = LoggerFactory.getLogger(FirestoreService.class);
    private Firestore db;
    
    @Value("${firebase.config.path}")
    private String serviceAccountKeyPath;

    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    

//     public void initialize() {
//     try {
//         FileInputStream serviceAccount = new FileInputStream(serviceAccountKeyPath);
//         FirebaseOptions options = FirebaseOptions.builder()
//                 .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                 .build();
        
//         if (FirebaseApp.getApps().isEmpty()) {
//             FirebaseApp.initializeApp(options);
//         }
//         db = FirestoreClient.getFirestore();
//         listenToFirestoreChanges();
//         logger.info("HUHUHU");
//     } catch (IOException e) {
//         logger.error("Error initializing Firestore", e);
//     }
// }
@PostConstruct
public void initialize() {
    try {
        System.out.println("FirestoreService initialized"); // Check if this appears
        FileInputStream serviceAccount = new FileInputStream(serviceAccountKeyPath);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
        db = FirestoreClient.getFirestore();
        listenToFirestoreChanges();
        logger.info("HUHUHU");
    } catch (IOException e) {
        logger.error("Error initializing Firestore", e);
    }
}


public <T extends FirestoreSyncable> void syncToFirestore(T entity) {
    try {
        // Remove manual ID mapping and let Firestore generate the ID
        db.collection(entity.getFirestoreCollectionName())
          .add(entity.toFirestoreMap()) // This generates a unique Firestore document ID
          .get();
    } catch (Exception e) {
        logger.error("Error syncing to Firestore", e);
    }
}


    public <T extends FirestoreSyncable> void deleteFromFirestore(T entity) {
        try {
            db.collection(entity.getFirestoreCollectionName())
                    .document(String.valueOf(entity.getId()))
                    .delete()
                    .get();
        } catch (Exception e) {
            logger.error("Error deleting from Firestore", e);
        }
    }

    private void listenToFirestoreChanges() {
        listenToCollectionChanges("MouvementCrypto");
        listenToCollectionChanges("MouvementFond");

    }

    private void listenToCollectionChanges(String collectionName) {
        db.collection(collectionName).addSnapshotListener((snapshots, e) -> {
            if (e != null) {
                logger.error("Listen failed for collection: " + collectionName, e);
                return;
            }
            for (DocumentChange dc : snapshots.getDocumentChanges()) {
                switch (dc.getType()) {
                    case ADDED:
                    case MODIFIED:
                        handleDocumentUpsert(dc.getDocument(), collectionName);
                        break;
                    case REMOVED:
                        handleDocumentRemoved(dc.getDocument(), collectionName);
                        break;
                }
            }
        });
    }
    

    private void handleDocumentUpsert(DocumentSnapshot document, String tableName) {
        try {
            Map<String, Object> data = document.getData();
            if (data != null) {
                adjustTimestamps(data);
                String sql = generateUpsertSQL(tableName, data);
                jdbcTemplate.update(sql, data.values().toArray());
                logger.info("Document upserted: " + document.getId());
            }
        } catch (Exception e) {
            logger.error("Failed to handle document upsert: " + document.getId(), e);
        }
    }

    private void handleDocumentRemoved(DocumentSnapshot document, String tableName) {
        try {
            Long id = document.getLong("id");
            jdbcTemplate.update("DELETE FROM " + tableName + " WHERE id = ?", id);
            logger.info("Document removed: " + document.getId());
        } catch (Exception e) {
            logger.error("Failed to handle document removal: " + document.getId(), e);
        }
    }
    
    private void adjustTimestamps(Map<String, Object> data) {
        if (data.containsKey("timestamp")) {
            data.put("timestamp", convertToLocalDateTime(data.get("timestamp")));
        }
        if (data.containsKey("validationTimestamp")) {
            data.put("validationTimestamp", convertToLocalDateTime(data.get("validationTimestamp")));
        }
    }

    private LocalDateTime convertToLocalDateTime(Object timestampObj) {
        if (timestampObj instanceof Timestamp) {
            return FirestoreTimeConverter.toLocalDateTime((Timestamp) timestampObj);
        } else if (timestampObj instanceof String) {
            return LocalDateTime.parse((String) timestampObj);
        }
        throw new IllegalArgumentException("Unknown timestamp format: " + timestampObj);
    }

    private String generateUpsertSQL(String tableName, Map<String, Object> data) {
        String columns = String.join(", ", data.keySet());
        String placeholders = String.join(", ", data.keySet().stream().map(k -> "?").toArray(String[]::new));
        return "REPLACE INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";
    }
    
}
