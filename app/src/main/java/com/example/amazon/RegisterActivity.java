package com.example.amazon;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText phone;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.register_email_input);
        password = findViewById(R.id.register_password_input);
        register = findViewById(R.id.register_btn);

        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txt_email = email.getText().toString();
                final String txt_password = password.getText().toString();
                final String txt_name = name.getText().toString();
                final String txt_phone = phone.getText().toString();
                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(RegisterActivity.this, "Empty credentials", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String,Object> map =new HashMap<>();
                    map.put("name",txt_name);
                    map.put("email",txt_email);
                    map.put("phone",txt_phone);
                    FirebaseDatabase.getInstance().getReference().child("Users").child("values").updateChildren(map);
                  registerUser(txt_email,txt_password);

                }
            }

            private void registerUser(String email, String password) {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}

