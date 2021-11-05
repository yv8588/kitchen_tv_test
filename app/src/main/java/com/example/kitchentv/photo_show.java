package com.example.kitchentv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import static com.example.kitchentv.FBref.AUTH;
import static com.example.kitchentv.FBref.STORAGEREF;

public class photo_show extends AppCompatActivity {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().toString().equals("register")){
            Intent si=new Intent(this,MainActivity.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}
