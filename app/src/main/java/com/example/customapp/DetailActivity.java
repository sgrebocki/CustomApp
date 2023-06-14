package com.example.customapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView tvDetailTtile, tvDetailDesc;
    ImageView ivDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailTtile = findViewById(R.id.tvDetailTtile);
        tvDetailDesc = findViewById(R.id.tvDetailDesc);
        ivDetailImage = findViewById(R.id.ivDetailImage);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            tvDetailTtile.setText(bundle.getString("Title"));
            tvDetailDesc.setText(bundle.getString("Description"));
            Glide.with(this).load(bundle.getString("Image")).into(ivDetailImage);
        }
    }
}