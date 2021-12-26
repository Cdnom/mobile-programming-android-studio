package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[][] dataUser = {
            {"admin", "admin", "Administrator"},
            {"user1", "password1", "user 1"},
            {"user2", "password2", "user 2"}
    };
    private EditText usernameET, passwordET;
    private final int SUCCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = (EditText) findViewById(R.id.usernameEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.closeButton).setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                int i = 0;
                boolean success = false;

                while ((i < dataUser.length) && (!success)) {
                    if
                    ((usernameET.getText().toString().equals(dataUser[i][0])) &&

                            (passwordET.getText().toString().equals(dataUser[i][1]))) {
                        success = true;
                    } else {
                        i++;
                    }
                }

                Intent intent;
                if (success) {
                    intent = new Intent(this, SuccessActivity.class);
                    intent.putExtra("username", dataUser[i][2]);

                } else {
                    intent = new Intent(this, FailedActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.closeButton:
                System.exit(0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESS) {
            usernameET.setText("");
            passwordET.setText("");
        }
    }
}