// firebase.js
import { initializeApp } from 'firebase/app';
import { getFirestore } from 'firebase/firestore';

const firebaseConfig = {
  apiKey: "AIzaSyC9MmvvF2pECwDg4NR460MKL1nTMeWfxsI",
  authDomain: "crypto-4f318.firebaseapp.com",
  projectId: "crypto-4f318",
  storageBucket: "crypto-4f318.appspot.com",
  messagingSenderId: "374425747666",
  appId: "1:374425747666:android:90d4f3b458054eac82ed0b"
};

const app = initializeApp(firebaseConfig);
const db = getFirestore(app); // Pour utiliser Firestore

export { db };
