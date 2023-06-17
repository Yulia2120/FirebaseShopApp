package com.obushko.firebaseshopapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.obushko.firebaseshopapp.R;

public class CategoryActivity extends AppCompatActivity {

    ImageView imageViewEcco, imageViewAdidas, imageViewBalance, imageViewScechers,
            imageViewArmani, imageViewConverse,imageViewGeox, imageViewNike, imageViewPuma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initView();

        imageViewEcco.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AdminActivity.class);
            intent.putExtra("brands", "ecco");
            startActivity(intent);
        });
        imageViewArmani.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AdminActivity.class);
            intent.putExtra("brands", "armani");
            startActivity(intent);
        });
        imageViewAdidas.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AdminActivity.class);
            intent.putExtra("brands", "adidas");
            startActivity(intent);
        });
        imageViewConverse.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AdminActivity.class);
            intent.putExtra("brands", "converse");
            startActivity(intent);
        });
        imageViewGeox.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AdminActivity.class);
            intent.putExtra("brands", "geox");
            startActivity(intent);
        });
        imageViewBalance.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AdminActivity.class);
            intent.putExtra("brands", "new_balance");
            startActivity(intent);
        });
        imageViewNike.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AdminActivity.class);
            intent.putExtra("brands", "nike");
            startActivity(intent);
        });
        imageViewPuma.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AdminActivity.class);
            intent.putExtra("brands", "puma");
            startActivity(intent);
        });
        imageViewScechers.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AdminActivity.class);
            intent.putExtra("brands", "scechers");
            startActivity(intent);
        });
    }

    private void initView() {
        imageViewAdidas = findViewById(R.id.imageViewAdidas);
        imageViewEcco = findViewById(R.id.imageViewEcco);
        imageViewArmani = findViewById(R.id.imageViewArmani);

        imageViewBalance = findViewById(R.id.imageViewBalance);
        imageViewConverse = findViewById(R.id.imageViewConverse);
        imageViewGeox = findViewById(R.id.imageViewGeox);

        imageViewNike = findViewById(R.id.imageViewNike);
        imageViewPuma = findViewById(R.id.imageViewPuma);
        imageViewScechers = findViewById(R.id.imageViewScechers);
    }
}