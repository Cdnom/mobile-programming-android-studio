package com.example.myapplication;


import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChosenActivity  extends AppCompatActivity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen);
        TextView mataKuliahTV = (TextView) findViewById(R.id.subjectTextView);

        mataKuliahTV.setText(getIntent().getExtras().getString("subject"));

        findViewById(R.id.backButton).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_chosen, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            finish();
        }
    }
}
