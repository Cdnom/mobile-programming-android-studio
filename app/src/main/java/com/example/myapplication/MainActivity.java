package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    static final int SETTING = 1;
    SharedPreferences preferences;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        textView.setText(preferences.getString("valuetextview", "TextView (default)"));
        button.setText(preferences.getString("valuebutton", "Button (default)"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            Toast.makeText(this, preferences.getString("valuebutton", "Button (default)") + " diklik", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSettingMenuItem:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivityForResult(settingIntent, SETTING);
                return true;
            case R.id.exitMenuItem:
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING) {
            textView.setText(preferences.getString("valuetextview", "TextView (default)"));
            button.setText(preferences.getString("valuebutton", "Button (default)"));
        }
    }
}