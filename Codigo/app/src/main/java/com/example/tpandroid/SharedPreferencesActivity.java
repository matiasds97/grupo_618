package com.example.tpandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SharedPreferencesActivity extends AppCompatActivity {

    Context context;
    TextView puntosText;
    TextView acelText;
    TextView proxText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();

        puntosText = findViewById(R.id.textView15);
        acelText = findViewById(R.id.textView16);
        proxText = findViewById(R.id.textView17);

        //Cargo los datos de SharedPreferences
        cargarPreferencias();

        Button home = findViewById(R.id.button12);
        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent homeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(homeActivityIntent);
            }
        });

    }

    private void cargarPreferencias()
    {
        SharedPreferences preferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        int puntos = preferences.getInt("Puntos", 0);
        float acelerometro = preferences.getFloat("Acelerometro", 0);
        float prox = preferences.getFloat("Proximidad", 0);

        puntosText.setText("Puntos: " + Integer.toString(puntos));
        acelText.setText("Acelerómetro: " + Float.toString(acelerometro));
        proxText.setText("Proximidad: " + Float.toString(prox));
    }

}
