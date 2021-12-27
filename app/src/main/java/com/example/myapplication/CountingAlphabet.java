package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class CountingAlphabet extends Activity implements OnClickListener {

    EditText stringET;
    TextView totalVocalTV, totalConsonantTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        stringET = (EditText) findViewById(R.id.stringEditText);
        totalVocalTV = (TextView) findViewById(R.id.totalVocalTextView);
        totalConsonantTV = (TextView) findViewById(R.id.totalConsonantTextView);

        findViewById(R.id.countingButton).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_couting_alphabet, menu);
        return true;

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.countingButton) {
            String teks = stringET.getText().toString();

            String[] listVokal = {"A", "E", "I", "O", "U"};
            int totalVocal = 0, totalConsonant = 0;
            int i, j;
            boolean vocal;

            for (i = 0; i < teks.length(); i++) {
                int keyCode = teks.charAt(i);
                if (((keyCode >= 65) && (keyCode <= 90)) || ((keyCode >= 97) && (keyCode <= 122))) {
                    vocal = false;
                    j = 0;
                    while ((j < listVokal.length) && !vocal) {
                        if (teks.substring(i, i + 1).equalsIgnoreCase(listVokal[j++])) {
                            vocal = true;
                        }
                    }

                    if (vocal) {
                        totalVocal++;
                    } else {
                        totalConsonant++;
                    }
                }
            }

            totalVocalTV.setText(Integer.toString(totalVocal));
            totalConsonantTV.setText(Integer.toString(totalConsonant));
        }
    }
}
