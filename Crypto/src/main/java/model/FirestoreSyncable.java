package model;
import java.util.Map;

public interface FirestoreSyncable {
    Map<String, Object> toFirestoreMap();
    String getFirestoreCollectionName();
    Long getId();
}
