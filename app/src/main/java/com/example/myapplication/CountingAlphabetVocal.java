package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class CountingAlphabetVocal extends Activity implements OnClickListener {

    EditText stringET;
    TextView totalStringTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_vocal);

        stringET = (EditText) findViewById(R.id.stringVocalEditText);
        totalStringTV = (TextView) findViewById(R.id.totalVocalTextView);

        findViewById(R.id.coutingVocalButton).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_couting_alphabet_vocal, menu);
        return true;

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.coutingVocalButton) {
            String stringVocal = stringET.getText().toString();

            boolean word = false, alphabet;
            int i, totalString = 0, keyCode;

            for (i = 0; i < stringVocal.length(); i++) {
                alphabet = false;
                keyCode = stringVocal.charAt(i);
                if (((keyCode >= 65) && (keyCode <= 90)) || ((keyCode >= 97) && (keyCode <= 122))) {
                    alphabet = true;
                }
                if (alphabet) {
                    if (!word) {
                        word = true;
                        totalString++;
                    }
                } else {
                    word = false;
                }
            }
            totalStringTV.setText(Integer.toString(totalString));
        }
    }
}
