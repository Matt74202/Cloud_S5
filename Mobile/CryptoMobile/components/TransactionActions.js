import React from 'react';
import { View, Button, Alert, StyleSheet } from 'react-native';

export default function TransactionActions() {
  return (
    <View style={styles.transactionSection}>
      <Button title="Deposit" onPress={() => Alert.alert('Deposit', 'Deposit action triggered')} />
      <Button title="Withdraw" onPress={() => Alert.alert('Withdraw', 'Withdraw action triggered')} />
    </View>
  );
}

const styles = StyleSheet.create({
  transactionSection: {
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
});
