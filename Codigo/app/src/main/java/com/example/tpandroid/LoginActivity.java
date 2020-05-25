package com.example.tpandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.tpandroid.API.ApiClient;
import com.example.tpandroid.models.Login.LoginSuccessResponse;
import com.example.tpandroid.models.Register.RegisterSuccessResponse;
import com.example.tpandroid.models.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity
{

    public static ApiClient apiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button botonRegistro = findViewById(R.id.button3);
        Button botonHome = findViewById(R.id.button);
        TextView mail = findViewById(R.id.email);
        TextView contra = findViewById(R.id.contrasenaLogin);
        apiClient = ApiClient.getInstance();


        botonHome.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Usuario user = null;
                try {
                    user = new Usuario(
                            mail.getText().toString(),
                            contra.getText().toString());

                    LoginActivity.apiClient.LoginUsuario(user, new Callback<LoginSuccessResponse>() {
                        @Override
                        public void onResponse(Call<LoginSuccessResponse> call, Response<LoginSuccessResponse> response) {
                            LoginSuccessResponse responseUser = response.body();
                            if (response.isSuccessful() && responseUser != null) {
                                /*Toast.makeText(RegisterActivity.this,
                                        String.format("Usuario %s - %s fue registrado.",
                                                responseUser.getToken(),
                                                responseUser.getState()),
                                        Toast.LENGTH_LONG).show(); */
                                //Si el registro fue exitoso, que me rediriga al Home.
                                Intent homeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(homeActivityIntent);
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        String.format("Datos incorrectos.")
                                        , Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginSuccessResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this,
                                    "No se encontró al usuario."
                                    , Toast.LENGTH_LONG).show();
                        }

                    });
                }
                catch (Exception e)
                {
                    Toast.makeText(LoginActivity.this,
                            String.format(e.getMessage())
                            ,Toast.LENGTH_LONG).show();
                }

            }
        });

        botonRegistro.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent registroActivityIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registroActivityIntent);
            }
        });

    }



}
