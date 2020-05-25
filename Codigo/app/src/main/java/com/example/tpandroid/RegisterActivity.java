package com.example.tpandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tpandroid.API.ApiClient;

public class RegisterActivity extends AppCompatActivity {

    EditText nombre;
    EditText apellido;
    EditText dni;
    EditText mail;
    EditText contra;
    EditText comision;
    EditText grupo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.argb(255,115,113,86));
        toolbar.setTitle("Registro");
        setSupportActionBar(toolbar);

        View contenidoRegistro = getLayoutInflater().inflate(R.layout.content_register, null);
        Button registrarse;

        registrarse = contenidoRegistro.findViewById(R.id.button2);

        nombre = contenidoRegistro.findViewById(R.id.editText3);
        apellido = contenidoRegistro.findViewById(R.id.editText4);
        dni = contenidoRegistro.findViewById(R.id.editText5);
        mail = contenidoRegistro.findViewById(R.id.editText6);
        contra = contenidoRegistro.findViewById(R.id.editText7);
        comision = contenidoRegistro.findViewById(R.id.editText8);
        grupo = contenidoRegistro.findViewById(R.id.editText9);

        registrarse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent homeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
                //ApiClient.Register(nombre.getText().toString(), apellido.getText().toString(), Integer.parseInt(dni.getText().toString()), mail.getText().toString(), contra.getText().toString(), Integer.parseInt(comision.getText().toString()), Integer.parseInt(grupo.getText().toString()));
                startActivity(homeActivityIntent);
            }
        });

        try
        {
            AppCompatImageButton botonLogin = contenidoRegistro.findViewById(R.id.backButton);
            botonLogin.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    //ApiClient.Register(nombre.getText().toString(), apellido.getText().toString(), Integer.parseInt(dni.getText().toString()), mail.getText().toString(), contra.getText().toString(), Integer.parseInt(comision.getText().toString()), Integer.parseInt(grupo.getText().toString()));
                    startActivity(loginActivityIntent);
                    //System.out.println("Botón presionado");
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


}
