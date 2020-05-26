package com.example.tpandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tpandroid.API.ApiClient;
import com.example.tpandroid.API.ApiInterface;
import com.example.tpandroid.models.Register.RegisterSuccessResponse;
import com.example.tpandroid.models.Usuario;

import retrofit2.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity
{

    public static ApiClient apiClient;
    EditText nombre;
    EditText apellido;
    EditText dni;
    EditText mail;
    EditText contra;
    EditText comision;
    EditText grupo;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.argb(255, 115, 113, 86));
        toolbar.setTitle("Registro");
        setSupportActionBar(toolbar);

        View contenidoRegistro = getLayoutInflater().inflate(R.layout.content_register, null);
        Button registrarse;

        registrarse = findViewById(R.id.button2);
        //registrarse = contenidoRegistro.findViewById(R.id.button2);

        nombre = findViewById(R.id.editText3);
        apellido = findViewById(R.id.editText4);
        dni = findViewById(R.id.editText5);
        mail = findViewById(R.id.editText6);
        contra = findViewById(R.id.editText7);
        comision = findViewById(R.id.editText8);
        grupo = findViewById(R.id.editText9);

        apiClient = ApiClient.getInstance();

        registrarse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!hayConexion())
                {
                    Toast.makeText(RegisterActivity.this, String.format("No hay conexión a Internet."), Toast.LENGTH_LONG).show();
                    return;
                }
                if (!camposCorrectos())
                    return;
                Usuario user = null;
                try
                {
                    user = new Usuario(
                            "DEV",
                            nombre.getText().toString(),
                            apellido.getText().toString(),
                            Integer.parseInt(dni.getText().toString()),
                            mail.getText().toString(),
                            contra.getText().toString(),
                            Integer.parseInt(comision.getText().toString()),
                            Integer.parseInt(grupo.getText().toString()));

                    RegisterActivity.apiClient.RegistrarUsuario(user, new Callback<RegisterSuccessResponse>()
                    {
                        @Override
                        public void onResponse(Call<RegisterSuccessResponse> call, Response<RegisterSuccessResponse> response)
                        {
                            RegisterSuccessResponse responseUser = response.body();
                            if (response.isSuccessful() && responseUser != null)
                            {
                                //Si el registro fue exitoso, que me rediriga al Home.
                                Intent HomeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(HomeActivityIntent);
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,
                                        String.format("Datos incorrectos.")
                                        , Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterSuccessResponse> call, Throwable t)
                        {
                            Toast.makeText(RegisterActivity.this,
                                    "No se pudo registrar el usuario."
                                    , Toast.LENGTH_LONG).show();
                        }

                    });
                } catch (Exception e)
                {
                    Toast.makeText(RegisterActivity.this,
                            String.format("Por favor ingrese los valores correctamente.")
                            , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean hayConexion()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
            return true;
        return false;
    }

    private boolean camposCorrectos()
    {
        if (nombre.getText().toString().equals(""))
        {
            Toast.makeText(RegisterActivity.this, String.format("Debe ingresar un nombre."), Toast.LENGTH_LONG).show();
            return false;
        }

        if (apellido.getText().toString().equals(""))
        {
            Toast.makeText(RegisterActivity.this, String.format("Debe ingresar un apellido."), Toast.LENGTH_LONG).show();
            return false;
        }

        if (dni.getText().toString().equals(""))
        {
            Toast.makeText(RegisterActivity.this, String.format("Debe ingresar un DNI."), Toast.LENGTH_LONG).show();
            return false;
        }

        if (mail.getText().toString().equals(""))
        {
            Toast.makeText(RegisterActivity.this, String.format("Debe ingresar un mail."), Toast.LENGTH_LONG).show();
            return false;
        }

        if (contra.getText().toString().equals(""))
        {
            Toast.makeText(RegisterActivity.this, String.format("Debe ingresar una contraseña."), Toast.LENGTH_LONG).show();
            return false;
        }

        if (contra.getText().toString().length() < 8)
        {
            Toast.makeText(RegisterActivity.this, String.format("La contraseña debe contener al menos 8 caracteres."), Toast.LENGTH_LONG).show();
            return false;
        }

        if (comision.getText().toString().equals(""))
        {
            Toast.makeText(RegisterActivity.this, String.format("Debe ingresar un numero de comisión."), Toast.LENGTH_LONG).show();
            return false;
        }

        if (grupo.getText().toString().equals(""))
        {
            Toast.makeText(RegisterActivity.this, String.format("Debe ingresar un numero de grupo."), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


}
