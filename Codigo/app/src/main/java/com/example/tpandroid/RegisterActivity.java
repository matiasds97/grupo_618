package com.example.tpandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.argb(255,115,113,86));
        toolbar.setTitle("Registro");
        setSupportActionBar(toolbar);

        try
        {
            View contenidoRegistro = getLayoutInflater().inflate(R.layout.content_register, null);
            AppCompatImageButton botonLogin = contenidoRegistro.findViewById(R.id.backButton);
            botonLogin.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
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
