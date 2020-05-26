package com.example.tpandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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
import android.widget.TextView;
import android.widget.Toast;

public class PuntosActivity extends AppCompatActivity {

    Context context;
    TextView P;
    Button botonCucurucho;
    Button botonCuarto;
    Button botonMedio;
    Button botonKilo;
    Spinner sabores;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        int puntos = leerPuntos();

        botonCucurucho = findViewById(R.id.button6);
        botonCuarto = findViewById(R.id.button7);
        botonMedio = findViewById(R.id.button8);
        botonKilo = findViewById(R.id.button9);
        P = findViewById(R.id.textView8);

        P.setText("Puntos: " + Integer.toString(puntos));

        sabores = findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, new SaboresHelado().getSabores());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sabores.setAdapter(adapter);



        botonCucurucho.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(puntos >= 100)
                {
                    restarPuntos(100);
                    Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                    startActivity(confirmarActivityIntent);
                }
                else
                {
                    Toast.makeText(PuntosActivity.this,
                            String.format("Se necesitan mas puntos.")
                            , Toast.LENGTH_LONG).show();
                }


            }
        });

        botonCuarto.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(puntos >= 170)
                {
                    restarPuntos(170);
                    Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                    startActivity(confirmarActivityIntent);
                }
                else
                {
                    Toast.makeText(PuntosActivity.this,
                            String.format("Se necesitan mas puntos.")
                            , Toast.LENGTH_LONG).show();
                }

            }
        });

        botonMedio.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(puntos >= 250)
                {
                    restarPuntos(250);
                    Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                    startActivity(confirmarActivityIntent);
                }
                else
                {
                    Toast.makeText(PuntosActivity.this,
                            String.format("Se necesitan mas puntos.")
                            , Toast.LENGTH_LONG).show();
                }
            }
        });

        botonKilo.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(puntos >= 400)
                {
                    restarPuntos(400);
                    Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                    startActivity(confirmarActivityIntent);
                }
                else
                {
                    Toast.makeText(PuntosActivity.this,
                            String.format("Se necesitan mas puntos.")
                            , Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private int leerPuntos()
    {
        SharedPreferences preferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        return preferences.getInt("Puntos", 0);
    }

    private void restarPuntos(int c)
    {
        SharedPreferences preferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        int total = preferences.getInt("Puntos", 0);

        total -= c;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Puntos", total);
        editor.commit();
    }

}
