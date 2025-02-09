// CryptoList.js
import React, { useState, useEffect } from 'react';
import { View, Text, FlatList, Button, StyleSheet, Alert } from 'react-native';
import { collection, getDocs } from 'firebase/firestore';
import { db } from '../firebase'; // Assure-toi que ce chemin est correct

export default function CryptoList() {
  const [cryptos, setCryptos] = useState([]);
  const [favorite, setFavorite] = useState(null);

  useEffect(() => {
    // Fonction pour récupérer les cryptos depuis Firestore
    const fetchCryptos = async () => {
      try {
        const querySnapshot = await getDocs(collection(db, 'Crypto'));
        const cryptoList = querySnapshot.docs.map((doc) => ({
          id: doc.id,
          ...doc.data(),
        }));
        setCryptos(cryptoList);
      } catch (error) {
        Alert.alert('Error', 'Unable to fetch cryptos.');
      }
    };

    fetchCryptos(); // Appel de la fonction pour récupérer les cryptos
  }, []);

  const addToFavorite = (crypto) => {
    setFavorite(crypto);
    Alert.alert('Favorite added', `${crypto.nom} is now your favorite.`);
  };

  return (
    <View style={styles.cryptoSection}>
      <Text style={styles.sectionTitle}>Cryptos</Text>
      <FlatList
        data={cryptos}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <View style={styles.cryptoItem}>
            <Text>{item.nom} </Text>
            <Button title="Favorite" onPress={() => addToFavorite(item)} />
          </View>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  cryptoSection: {
    marginBottom: 20,
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  cryptoItem: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 10,
    backgroundColor: '#fff',
    marginBottom: 10,
    borderRadius: 5,
  },
});
