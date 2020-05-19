package com.example.tpandroid;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton realizarPedido = findViewById(R.id.realizarPedido);
        ImageButton canjearPuntos = findViewById(R.id.canjearPuntos);

        //Seteo la funcion al hacer click en los botones
        realizarPedido.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent pedidoActivityIntent = new Intent(getApplicationContext(), PedidoActivity.class);
                startActivity(pedidoActivityIntent);
            }
        });

        canjearPuntos.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent puntosActivityIntent = new Intent(getApplicationContext(), PuntosActivity.class);
                startActivity(puntosActivityIntent);
            }
        });

    }

}
