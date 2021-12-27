package com.example.myapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent;
        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;
        intent = new Intent(this, CountingAlphabet.class);
        tabSpec = getTabHost().newTabSpec("tab1").setIndicator(
                "Menghitung Huruf").setContent(intent);
        tabHost.addTab(tabSpec);
        intent = new Intent(this, CountingAlphabetVocal.class);
        tabSpec = getTabHost().newTabSpec("tab2").setIndicator(
                "Menghitung Vokal").setContent(intent);
        tabHost.addTab(tabSpec);

    }
}