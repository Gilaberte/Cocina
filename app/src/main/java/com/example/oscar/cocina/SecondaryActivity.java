package com.example.oscar.cocina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondaryActivity extends Activity {

    TextView tvSecondary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        tvSecondary = (TextView) findViewById(R.id.tvSecondary);


        Intent intent = getIntent();

        String dato = intent.getStringExtra("dato");

        tvSecondary.setText(dato);


    }

}
