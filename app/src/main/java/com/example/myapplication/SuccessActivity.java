package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        TextView usernameTE = (TextView) findViewById(R.id.usernameAuthView);
        usernameTE.setText(getIntent().getExtras().getString("username"));

        findViewById(R.id.logoutButton).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_success, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logoutButton) {
            finish();
        }
    }
}
