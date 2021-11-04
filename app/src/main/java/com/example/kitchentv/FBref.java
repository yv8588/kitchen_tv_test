package com.example.kitchentv;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FBref {
    public static final StorageReference STORAGEREF = FirebaseStorage.getInstance().getReference();
    public static final  FirebaseAuth AUTH=FirebaseAuth.getInstance();
}
