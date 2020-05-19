package com.example.tpandroid;

import android.content.Intent;
import android.os.Bundle;

import com.example.tpandroid.models.SaboresHelado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PuntosActivity extends AppCompatActivity {

    Button botonCucurucho;
    Button botonCuarto;
    Button botonMedio;
    Button botonKilo;
    Spinner sabores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botonCucurucho = findViewById(R.id.button6);
        botonCuarto = findViewById(R.id.button7);
        botonMedio = findViewById(R.id.button8);
        botonKilo = findViewById(R.id.button9);

        sabores = findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, new SaboresHelado().getSabores());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sabores.setAdapter(adapter);



        botonCucurucho.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                startActivity(confirmarActivityIntent);
            }
        });

        botonCuarto.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                startActivity(confirmarActivityIntent);
            }
        });

        botonMedio.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                startActivity(confirmarActivityIntent);
            }
        });

        botonKilo.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                startActivity(confirmarActivityIntent);
            }
        });

    }

}
