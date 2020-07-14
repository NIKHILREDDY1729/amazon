package com.example.amazon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
private EditText email,password;
private Button login;
private TextView adminlink;
private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
email=findViewById(R.id.login_email_input);
password=findViewById(R.id.login_password_input);
login=findViewById(R.id.login_btn);
adminlink=findViewById(R.id.admin_panel_link);
auth=FirebaseAuth.getInstance();
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       String txt_email=email.getText().toString();
       String txt_password=password.getText().toString() ;
loginuser(txt_email,txt_password);


    }
});
adminlink.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        login.setText("Admin Login");
        adminlink.setVisibility(View.INVISIBLE);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String txt_email=email.getText().toString();
                 String txt_password=password.getText().toString() ;
                adminuser(txt_email,txt_password);
            }
        });

    }
});




    }

    private void adminuser(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "adminloginSucccess", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,Addcategory.class));
                finish();

            }
        });

    }


    private void loginuser(String email, String password) {

auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
    @Override
    public void onSuccess(AuthResult authResult) {
        Toast.makeText(LoginActivity.this, "Succcess", Toast.LENGTH_SHORT).show();
       // startActivity(new Intent(LoginActivity.this,HomeActivity1.class));
        finish();

    }
});

    }
}