import { getFirestore, collection, getDocs, query, where } from 'firebase/firestore';
import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, Alert } from 'react-native';
import { db } from '../firebase'; // Assure-toi que tu as bien configuré Firebase

export default function LoginScreen({ onLogin }) {
  const [email, setEmail] = useState('user1@gmail.com'); // Valeur par défaut pour l'email
  const [password, setPassword] = useState('123'); // Valeur par défaut pour le mot de passe

  const handleLogin = async () => {
    try {
      const usersRef = collection(db, 'Utilisateur');
      
      const q = query(usersRef, where('email', '==', email));
      const querySnapshot = await getDocs(q);
      
      if (querySnapshot.empty) {
        Alert.alert('Login Failed', 'No user found with this email.');
        return;
      }

      const userDoc = querySnapshot.docs[0];
      const userData = userDoc.data();
      
      if (userData.mdp === password) {
        Alert.alert('Login Successful', 'You are logged in!');
        onLogin(); 
      } else {
        Alert.alert('Login Failed', 'Incorrect password.');
      }
    } catch (error) {
      Alert.alert('Error', 'Something went wrong.');
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Login</Text>
      <TextInput
        style={styles.input}
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
        keyboardType="email-address"
        autoCapitalize="none"
      />
      <TextInput
        style={styles.input}
        placeholder="Password"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />
      <Button title="Login" onPress={handleLogin} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
    backgroundColor: '#f5f5f5',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  input: {
    width: '100%',
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 5,
    padding: 10,
    marginBottom: 10,
    backgroundColor: '#fff',
  },
});
