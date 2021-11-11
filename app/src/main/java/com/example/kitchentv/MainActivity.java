package com.example.kitchentv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.example.kitchentv.FBref.AUTH;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends Activity {
    EditText password,mail;
    String p,m;
    boolean prove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password=(EditText)findViewById(R.id.password);
        mail=(EditText)findViewById(R.id.mail);
    }
    protected final void createUserAuthWithEmailAndPassword(String emailId, String password) {

        AUTH.createUserWithEmailAndPassword(emailId, password).addOnCompleteListener((@NonNull Task<AuthResult> task) -> {

            if (task.isSuccessful()) {
                prove = true;
                Toast.makeText(MainActivity.this, "user registered", Toast.LENGTH_SHORT).show();
                Intent si=new Intent(MainActivity.this,photo_show.class);
                startActivity(si);
            }
            else {
                prove = false;
                Toast.makeText(this, "user registeration failed", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void register(View view) {
        p=password.getText().toString();
        m=mail.getText().toString();
        if(m==null){
            Toast.makeText(MainActivity.this, "enter mail", Toast.LENGTH_SHORT).show();
        }
        else if(p==null){
            Toast.makeText(MainActivity.this, "enter password", Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(m).matches()) {
            Toast.makeText(MainActivity.this,"email does not exist",Toast.LENGTH_SHORT).show();
        }
        else {
            mail.setText("");
            password.setText("");
            mail.setHint("mail");
            password.setHint("password");
            createUserAuthWithEmailAndPassword(m,p);
        }
    }
    public void sign(String mail,String password){
        AUTH.signInWithEmailAndPassword(mail, password).addOnCompleteListener((@NonNull Task<AuthResult> task) -> {

            if (task.isSuccessful()) {
                prove = true;
                Toast.makeText(MainActivity.this,"user signed in",Toast.LENGTH_SHORT).show();
                Intent si=new Intent(MainActivity.this,photo_show.class);
                startActivity(si);
            }
            else {
                prove = false;
                Toast.makeText(MainActivity.this,"sign in failed",Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void signIn(View view) {
        p=password.getText().toString();
        m=mail.getText().toString();
        if(m==null){
            Toast.makeText(MainActivity.this, "enter mail", Toast.LENGTH_SHORT).show();
        }
        else if(p==null){
            Toast.makeText(MainActivity.this, "enter password", Toast.LENGTH_SHORT).show();
        }
        else {
            mail.setText("");
            password.setText("");
            mail.setHint("mail");
            password.setHint("password");
            sign(m,p);
        }
    }
}