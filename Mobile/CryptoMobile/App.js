import React, { useState } from 'react';
import { View, StyleSheet, ScrollView } from 'react-native';
import UserProfile from './components/UserProfile';
import CryptoList from './components/CryptoList';
import Portfolio from './components/Portfolio';
import TransactionActions from './components/TransactionActions';
import LoginScreen from './components/LoginScreen';
import * as Notifications from 'expo-notifications';

export default function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true); // Passe à la page principale après login
  };

  return (
    <View style={styles.container}>
      {!isLoggedIn ? (
        <LoginScreen onLogin={handleLogin} />
      ) : (
        <>
        <ScrollView contentContainerStyle={styles.scrollViewContainer}>
          <UserProfile />
          <CryptoList />
          <Portfolio />
          <TransactionActions />
      </ScrollView>
        </>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
});


Notifications.setNotificationHandler({
  handleNotification: async () => ({
    shouldShowAlert: true,
    shouldPlaySound: false,
    shouldSetBadge: false,
  }),
});




// TransactionActions.js
