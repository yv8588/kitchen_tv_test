package com.example.kitchentv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import static com.example.kitchentv.FBref.AUTH;

public class register extends AppCompatActivity {
    EditText password,mail;
    String p,m, userid;
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
            }
            else {
                prove = false;
            }

        });
    }

    public void register(View view) {
        p=password.getText().toString();
        m=mail.getText().toString();
        if(m==null){
            Toast.makeText(register.this, "enter mail", Toast.LENGTH_SHORT).show();
        }
        else if(p==null){
            Toast.makeText(register.this, "enter password", Toast.LENGTH_SHORT).show();
        }
        else if(Patterns.EMAIL_ADDRESS.matcher(m).matches()){
            createUserAuthWithEmailAndPassword(m,p);
            Toast.makeText(register.this,"user registered",Toast.LENGTH_SHORT).show();
            FirebaseUser user = AUTH.getCurrentUser();
            if(user!=null) {
                userid  = user.getUid();
            }
            Toast.makeText(this, userid, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(register.this,"email does not exist",Toast.LENGTH_SHORT).show();
        }
        mail.setText("");
        password.setText("");
        mail.setHint("mail");
        password.setHint("password");
    }
    public boolean sign(String mail,String password){
        AUTH.signInWithEmailAndPassword(mail, password).addOnCompleteListener((@NonNull Task<AuthResult> task) -> {

            if (task.isSuccessful()) {
                prove = true;
            }
            else {
                prove = false;
            }

        });
        return prove;

    }

    public void signIn(View view) {
        p=password.getText().toString();
        m=mail.getText().toString();
        if(m==null){
            Toast.makeText(register.this, "enter mail", Toast.LENGTH_SHORT).show();
        }
        else if(p==null){
            Toast.makeText(register.this, "enter password", Toast.LENGTH_SHORT).show();
        }
        else {
            boolean b=sign(m,p);
            if(b){
                Toast.makeText(register.this,"user signed in",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(register.this,"sign in failed",Toast.LENGTH_SHORT).show();
            }
        }
        mail.setText("");
        password.setText("");
        mail.setHint("mail");
        password.setHint("password");
    }
}
