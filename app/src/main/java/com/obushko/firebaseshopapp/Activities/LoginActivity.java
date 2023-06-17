package com.obushko.firebaseshopapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.obushko.firebaseshopapp.Model.Users;
import com.obushko.firebaseshopapp.Prevalent.Prevalent;
import com.obushko.firebaseshopapp.R;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText editTextPhone, editTextPassword;
   private String parentDbName = "Users";
   private CheckBox checkBox;
   private TextView textViewAdmin, textViewClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewAdmin = findViewById(R.id.textViewAdmin);
        textViewClient = findViewById(R.id.textViewClient);
        checkBox = findViewById(R.id.checkboxLogin);
        Paper.init(this);

        loginBtn.setOnClickListener(v -> loginUser());

        textViewAdmin.setOnClickListener(v -> {
            textViewAdmin.setVisibility(View.INVISIBLE);
            textViewClient.setVisibility(View.VISIBLE);
            loginBtn.setText("Login to Admin");
            parentDbName="Admins";
        });
        textViewClient.setOnClickListener(v->{
            textViewAdmin.setVisibility(View.VISIBLE);
            textViewClient.setVisibility(View.INVISIBLE);
            loginBtn.setText("Login");
            parentDbName="Users";
        });

    }

    private void loginUser() {
        String phone = editTextPhone.getText().toString();
        String password = editTextPassword.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Enter Your Number of Phone", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter Your Password", Toast.LENGTH_SHORT).show();
        }else {
            ValidateUser(phone, password);
        }
    }

    private void ValidateUser(String phone, String password) {
        //Paper записывает в телефон данные для входа
        if(checkBox.isChecked()){
            Paper.book().write(Prevalent.userPhoneKey, phone);
            Paper.book().write(Prevalent.userPasswordKey, password);
        }


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(parentDbName).child(phone).exists()) {

                    Users usersData = snapshot.child(parentDbName).child(phone).getValue(Users.class);

                                 assert usersData != null;
                                if (usersData.getPhone().equals(phone)) {
                                if (usersData.getPassword().equals(password)) {

                                    if (parentDbName.equals("Users")) {
                                        Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(homeIntent);
                                    }
                                     if (parentDbName.equals("Admins")) {
                                        Toast.makeText(LoginActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                        Intent adminIntent = new Intent(LoginActivity.this, AdminActivity.class);
                                        startActivity(adminIntent);
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                                }
                            }

                    } else {
                        Toast.makeText(LoginActivity.this, "Account " + phone + " is already registered", Toast.LENGTH_SHORT).show();
                        Intent registerIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                        startActivity(registerIntent);
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}