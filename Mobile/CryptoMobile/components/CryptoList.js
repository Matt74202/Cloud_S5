// CryptoList.js
import React, { useState, useEffect } from 'react';
import { View, Text, FlatList, Button, StyleSheet, Alert } from 'react-native';

export default function CryptoList() {
  const [cryptos, setCryptos] = useState([]);
  const [favorite, setFavorite] = useState(null);

  useEffect(() => {
    setCryptos([
      { id: '1', name: 'Bitcoin', price: '$23,000' },
      { id: '2', name: 'Ethereum', price: '$1,500' },
      { id: '3', name: 'Ripple', price: '$0.50' },
    ]);
  }, []);

  const addToFavorite = (crypto) => {
    setFavorite(crypto);
    Alert.alert('Favorite added', `${crypto.name} is now your favorite.`);
  };

  return (
    <View style={styles.cryptoSection}>
      <Text style={styles.sectionTitle}>Cryptos</Text>
      <FlatList
        data={cryptos}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <View style={styles.cryptoItem}>
            <Text>{item.name} - {item.price}</Text>
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
