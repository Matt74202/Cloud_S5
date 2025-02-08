// Portfolio.js
import React,{ useState, useEffect } from 'react';
import { View, Text, FlatList, Button, StyleSheet, Alert } from 'react-native';
import * as Notifications from 'expo-notifications';

export default function Portfolio() {
  const [portfolio, setPortfolio] = useState([]);

  const notifyTransaction = () => {
    Notifications.scheduleNotificationAsync({
      content: {
        title: 'Transaction Alert',
        body: 'A transaction has been made for your favorite crypto.',
      },
      trigger: { seconds: 2 },
    });
  };

  return (
    <View style={styles.portfolioSection}>
      <Text style={styles.sectionTitle}>Portfolio</Text>
      <FlatList
        data={portfolio}
        keyExtractor={(item, index) => index.toString()}
        renderItem={({ item }) => (
          <Text>{item.name} - {item.price}</Text>
        )}
      />
      <Button title="Simulate Transaction" onPress={notifyTransaction} />
    </View>
  );
}

const styles = StyleSheet.create({
  portfolioSection: {
    marginBottom: 20,
  },
});
