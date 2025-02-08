import React, { useState } from 'react';
import { View, Text, TouchableOpacity, Image, Alert, StyleSheet, TextInput } from 'react-native';
import * as ImagePicker from 'expo-image-picker';

export default function UserProfile() {
  const [user, setUser] = useState({ name: '', profileImage: null });

  // Demander l'accès à l'appareil photo
  const takePhoto = async () => {
    const { status } = await ImagePicker.requestCameraPermissionsAsync();
    if (status !== 'granted') {
      Alert.alert('Permission Required', 'Camera access is required to take a profile picture.');
      return;
    }

    const result = await ImagePicker.launchCameraAsync({
      allowsEditing: true,
      aspect: [1, 1],
      quality: 1,
    });

    if (!result.canceled) {
      setUser({ ...user, profileImage: result.assets[0].uri });
    }
  };

  return (
    <View style={styles.profileSection}>
      <TouchableOpacity onPress={takePhoto}>
        {user.profileImage ? (
          <Image source={{ uri: user.profileImage }} style={styles.profileImage} />
        ) : (
          <View style={styles.profilePlaceholder}>
            <Text>Add Photo</Text>
          </View>
        )}
      </TouchableOpacity>
      <TextInput
        placeholder="Enter your name"
        style={styles.input}
        value={user.name}
        onChangeText={(text) => setUser({ ...user, name: text })}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  profileSection: {
    alignItems: 'center',
    marginBottom: 20,
  },
  profileImage: {
    width: 100,
    height: 100,
    borderRadius: 50,
  },
  profilePlaceholder: {
    width: 100,
    height: 100,
    borderRadius: 50,
    backgroundColor: '#ddd',
    justifyContent: 'center',
    alignItems: 'center',
  },
  input: {
    marginTop: 10,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 5,
    padding: 10,
    width: '80%',
  },
});
