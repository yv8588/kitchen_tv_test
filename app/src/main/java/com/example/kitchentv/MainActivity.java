package com.example.kitchentv;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import static com.example.kitchentv.FBref.AUTH;
import static com.example.kitchentv.FBref.STORAGEREF;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends Activity {
    ImageView photo;
    Bitmap image;
    StorageReference pathReference;
    String userid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo=(ImageView)findViewById(R.id.photo);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = AUTH.getCurrentUser();
        if(user!=null) {
            userid = user.getUid();
            pathReference = STORAGEREF.child("images/" + userid + ".jpg");

            final long ONE_MEGABYTE = 1024 * 1024;
            pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes , 0, bytes .length);
                    photo.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }

        //read photo from db and upload on image view.
    }
}