package com.example.zakatgoldapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etValue;
    Spinner spinnerType;
    Button btnCalculate;

    TextView tvTotalGold, tvZakatPayable, tvTotalZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etWeight = findViewById(R.id.etWeight);
        etValue = findViewById(R.id.etValue);
        spinnerType = findViewById(R.id.spinnerType);
        btnCalculate = findViewById(R.id.btnCalculate);

        tvTotalGold = findViewById(R.id.tvTotalGold);
        tvZakatPayable = findViewById(R.id.tvZakatPayable);
        tvTotalZakat = findViewById(R.id.tvTotalZakat);

        String[] type = {"Keep", "Wear"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                type
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerType.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etWeight.getText().toString().isEmpty() ||
                        etValue.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this,
                            "Please enter all inputs",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                double weight = Double.parseDouble(
                        etWeight.getText().toString()
                );

                double value = Double.parseDouble(
                        etValue.getText().toString()
                );

                String selectedType =
                        spinnerType.getSelectedItem().toString();

                double uruf;

                if (selectedType.equals("Keep")) {
                    uruf = 85;
                }
                else {
                    uruf = 200;
                }

                double totalGoldValue = weight * value;

                double zakatWeight = weight - uruf;

                if (zakatWeight < 0) {
                    zakatWeight = 0;
                }

                double zakatPayable = zakatWeight * value;

                double totalZakat = zakatPayable * 0.025;

                tvTotalGold.setText(
                        "Total Gold Value: RM " + totalGoldValue
                );

                tvZakatPayable.setText(
                        "Zakat Payable: RM " + zakatPayable
                );

                tvTotalZakat.setText(
                        "Total Zakat: RM " + totalZakat
                );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_about) {

            Intent intent = new Intent(
                    MainActivity.this,
                    AboutActivity.class
            );

            startActivity(intent);

            return true;
        }

        else if (item.getItemId() == R.id.menu_share) {

            Intent shareIntent =
                    new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Download my Gold Zakat App:\nhttps://github.com/yourusername"
            );

            startActivity(
                    Intent.createChooser(
                            shareIntent,
                            "Share App"
                    )
            );

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}