package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnItemClickListener, OnItemLongClickListener {
    private final java.util.ArrayList<String> items = new java.util.ArrayList<String>();
    private final String[] listSubject = {
            "Algorithma Pemrograman I", "Algorithma Pemrograman II", "Struktur Data I",
            "Struktur Data II", "Mobile Programming", "Pemrograman I", "Pemrograman II",
            "Bahasa Indonesia", "Agama", "PKN", "Bahasa Inggris", "Basis Data I", "Basis DataII",
            "Kalkulus", "Aljabar Linier", "Matematika Diskrit", "Fisika", "Etika Profesi"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Collections.addAll(items, listSubject);
        ListView subjectListView = (ListView) findViewById(R.id.subjectListView);
        subjectListView.setOnItemClickListener(this);
        subjectListView.setOnItemLongClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        subjectListView.setAdapter(adapter);
        findViewById(R.id.xButton).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.xButton) {
            System.exit(0);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, ChosenActivity.class);
        intent.putExtra("subject", items.get(i));
        startActivity(intent);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Informasi");
        alert.setMessage("Mata kuliah " + items.get(i));
        alert.setIcon(R.drawable.ic_launcher);

        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(), "Tombol Ya di klik", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(), "Tombol Tidak di klik", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();

        return false;
    }
}
