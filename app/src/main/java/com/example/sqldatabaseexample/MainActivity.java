package com.example.sqldatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etName, etCell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etCell = findViewById(R.id.etNumber);
    }

    public void btnEdit(View view) {
    }

    public void btnDelete(View view) {
    }

    public void btnShow(View view) {
        startActivity(new Intent(MainActivity.this, Data.class));
    }

    public void btnSubmit(View view) {
    }
}
