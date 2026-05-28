package com.example.zakatgoldapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    TextView tvGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarAbout);
        setSupportActionBar(toolbar);

        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Github link
        tvGithub = findViewById(R.id.tvGithub);

        tvGithub.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/ird00/ZakaTracking.git")
            );

            startActivity(intent);

        });
    }

    // Back button function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}