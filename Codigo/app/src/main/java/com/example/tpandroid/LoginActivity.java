package com.example.tpandroid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tpandroid.API.ApiClient;
import com.example.tpandroid.models.Event.Event;
import com.example.tpandroid.models.Event.EventSuccessResponse;
import com.example.tpandroid.models.Login.LoginSuccessResponse;
import com.example.tpandroid.models.Usuario;

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
                if(!hayConexion())
                {
                    Toast.makeText(LoginActivity.this, String.format("No hay conexión a Internet."), Toast.LENGTH_LONG).show();
                    return;
                }
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
                                //Si el registro fue exitoso, que me redirija al Home.
                                registrarEventoLogin();
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

    private void registrarEventoLogin()
    {
        Event e = new Event("Login", "ACTIVO", "Se ha realizado con exito un login.");
        LoginActivity.apiClient.RegistrarEvento(e, new Callback<EventSuccessResponse>() {
            @Override
            public void onResponse(Call<EventSuccessResponse> call, Response<EventSuccessResponse> response) {
                Log.i("Evento", "Se realizó un Login.");
            }

            @Override
            public void onFailure(Call<EventSuccessResponse> call, Throwable t) {
                Log.i("Evento", "Falló el envio del Evento.");
            }
        });
    }

    private boolean hayConexion()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

}
