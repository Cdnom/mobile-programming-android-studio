package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText utsET, uasET, nilaiAkhirET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utsET = (EditText) findViewById(R.id.utsEditText);
        uasET = (EditText) findViewById(R.id.uasEditText);
        nilaiAkhirET = (EditText) findViewById(R.id.nilaiAkhirEditTextText);

        findViewById(R.id.hitungButton).setOnClickListener(this);
        findViewById(R.id.tutupButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hitungButton:
                double uts = 0, uas = 0, nilaiAkhir = 0;

                try {
                    uts = Double.parseDouble(utsET.getText().toString());
                } catch (Exception e) {
                }

                try {
                    uas = Double.parseDouble(uasET.getText().toString());
                } catch (Exception e) {
                }

                nilaiAkhir = (uts + uas) / 2;

                if (nilaiAkhir >= 60) {

                    nilaiAkhirET.setBackgroundResource(R.color.teal_200);
                } else {

                    nilaiAkhirET.setBackgroundResource(R.color.purple_200);
                }

                nilaiAkhirET.setText(Double.toString(nilaiAkhir));
                break;
            case R.id.tutupButton:
                System.exit(0);
        }
    }
}