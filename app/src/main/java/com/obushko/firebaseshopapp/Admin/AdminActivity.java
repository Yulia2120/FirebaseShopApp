package com.obushko.firebaseshopapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.obushko.firebaseshopapp.R;

public class AdminActivity extends AppCompatActivity {

    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        categoryName = getIntent().getExtras().get("brands").toString();

        Toast.makeText(this, "Brands " +categoryName, Toast.LENGTH_SHORT).show();
    }
}