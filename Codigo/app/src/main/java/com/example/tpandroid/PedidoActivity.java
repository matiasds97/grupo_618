package com.example.tpandroid;

import android.content.Intent;
import android.os.Bundle;
import com.example.tpandroid.models.SaboresHelado;
import com.example.tpandroid.models.CantidadHelado;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PedidoActivity extends AppCompatActivity {

    Spinner cantidad;
    Spinner gustos;
    TextView precio;
    TextView puntos;
    Button confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        precio = findViewById(R.id.textView7);
        puntos = findViewById(R.id.textView6);
        confirmar = findViewById(R.id.button5);

        //Seteo los gustos de helados de forma dinámica.
        gustos = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, new SaboresHelado().getSabores());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gustos.setAdapter(adapter);


        //Seteo la cantidad de helados de forma dinámica.
        cantidad = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, new CantidadHelado().getCantidad());
        adapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cantidad.setAdapter(adapterC);

        confirmar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                startActivity(confirmarActivityIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        cantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (cantidad.getSelectedItem().toString()) {
                    case "Cucurucho":
                        puntos.setText("Puntos obtenidos: 10");
                        precio.setText("Precio: $50");
                        break;
                    case "1/4 Kg":
                        puntos.setText("Puntos obtenidos: 20");
                        precio.setText("Precio $80");
                        break;
                    case "1/2 Kg":
                        puntos.setText("Puntos obtenidos: 40");
                        precio.setText("Precio $120");
                        break;
                    case "1 Kg":
                        puntos.setText("Puntos obtenidos: 60");
                        precio.setText("Precio $200");
                        break;
                    default:
                        puntos.setText("Puntos obtenidos: -");
                        precio.setText("Precio: -");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
