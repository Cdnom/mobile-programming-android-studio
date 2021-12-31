package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    SharedPreferences preferences;
    EditText valueTextView;
    EditText valueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        valueTextView = (EditText) findViewById(R.id.valueTextEditorText);
        valueButton = (EditText) findViewById(R.id.valueButtonEditText);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        valueTextView.setText(preferences.getString("valuetextview", "TextView (default)"));
        valueButton.setText(preferences.getString("valuebutton", "Button (default)"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exitMenuItem) {
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_setting, menu);
        return true;
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("valuetextview", valueTextView.getText().toString());
        edit.putString("valuebutton", valueButton.getText().toString());
        edit.commit();
        super.onPause();
    }

}
