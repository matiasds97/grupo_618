package com.example.tpandroid.API;

import com.example.tpandroid.models.Event.Event;
import com.example.tpandroid.models.Event.EventSuccessResponse;
import com.example.tpandroid.models.Login.LoginSuccessResponse;
import com.example.tpandroid.models.Register.RegisterSuccessResponse;
import com.example.tpandroid.models.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    private static ApiInterface service;
    private static ApiClient apiClient;

    private static final String BASE_URL = "http://so-unlam.net.ar/api/api/";
    private static Retrofit retrofit = null;

    private ApiClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         service = retrofit.create(ApiInterface.class);
    }

    //Singleton
    public static ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public void RegistrarUsuario(Usuario user, Callback<RegisterSuccessResponse> callback)
    {
        Call<RegisterSuccessResponse> userCall = service.registrarUsuario(user);
        userCall.enqueue(callback);
    }

    public void LoginUsuario(Usuario user, Callback<LoginSuccessResponse> callback)
    {
        Call<LoginSuccessResponse> userCall = service.loginUsuario(user);
        userCall.enqueue(callback);
    }

    public void RegistrarEvento(Event ev, Callback<EventSuccessResponse> callback)
    {
        Call<EventSuccessResponse> userCall = service.registrarEvento(ev);
        userCall.enqueue(callback);
    }

}
