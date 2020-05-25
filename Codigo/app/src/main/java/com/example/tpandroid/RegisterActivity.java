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
import android.widget.Toast;

import com.example.tpandroid.API.ApiClient;
import com.example.tpandroid.API.ApiInterface;
import com.example.tpandroid.models.Register.RegisterSuccessResponse;
import com.example.tpandroid.models.Usuario;

import retrofit2.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity  {

    public static ApiClient apiClient;
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
                Usuario user = null;
                try {
                    user = new Usuario(
                            "DEV",
                            nombre.getText().toString(),
                            apellido.getText().toString(),
                            Integer.parseInt(dni.getText().toString()),
                            mail.getText().toString(),
                            contra.getText().toString(),
                            Integer.parseInt(comision.getText().toString()),
                            Integer.parseInt(grupo.getText().toString()));

                    RegisterActivity.apiClient.RegistrarUsuario(user, new Callback<RegisterSuccessResponse>() {
                        @Override
                        public void onResponse(Call<RegisterSuccessResponse> call, Response<RegisterSuccessResponse> response) {
                            RegisterSuccessResponse responseUser = response.body();
                            if (response.isSuccessful() && responseUser != null) {
                                /*Toast.makeText(RegisterActivity.this,
                                        String.format("Usuario %s - %s fue registrado.",
                                                responseUser.getToken(),
                                                responseUser.getState()),
                                        Toast.LENGTH_LONG).show(); */
                                //Si el registro fue exitoso, que me rediriga al Home.
                                Intent HomeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(HomeActivityIntent);
                            } else {
                                Toast.makeText(RegisterActivity.this,
                                        String.format("Datos incorrectos.")
                                        , Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterSuccessResponse> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this,
                                    "No se pudo registrar el usuario."
                                    , Toast.LENGTH_LONG).show();
                        }

                    });
                }
                catch (Exception e)
                {
                    Toast.makeText(RegisterActivity.this,
                            String.format("Por favor ingrese los valores correctamente.")
                            ,Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
