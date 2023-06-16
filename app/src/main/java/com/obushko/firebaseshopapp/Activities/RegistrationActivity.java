package com.obushko.firebaseshopapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.obushko.firebaseshopapp.R;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {
    private Button registrationBtn;
    private EditText editTextNameReg, editTextPhoneReg, editTextPasswordReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationBtn = findViewById(R.id.registrationBtn);
        editTextNameReg = findViewById(R.id.editTextNameReg);
        editTextPhoneReg = findViewById(R.id.editTextPhoneReg);
        editTextPasswordReg = findViewById(R.id.editTextPasswordReg);

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {

        String userName = editTextNameReg.getText().toString();
        String phone = editTextPhoneReg.getText().toString();
        String password = editTextPasswordReg.getText().toString();
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Enter Your Name", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Enter Your Number of Phone", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter Your Password", Toast.LENGTH_SHORT).show();
        }else {
            ValidateAccount(userName, phone, password);
        }
    }

    private void ValidateAccount(String userName, String phone, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(phone).exists())){

                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("name", userName);
                    userDataMap.put("phone", phone);
                    userDataMap.put("password", password);

                    RootRef.child("Users").child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                        startActivity(loginIntent);
                                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(RegistrationActivity.this, "Error", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }else {
                    Toast.makeText(RegistrationActivity.this, "Number "+phone+" is already registered", Toast.LENGTH_LONG).show();

                    Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}