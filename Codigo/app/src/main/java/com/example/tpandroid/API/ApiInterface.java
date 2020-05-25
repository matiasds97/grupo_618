package com.example.tpandroid.API;

import com.example.tpandroid.models.Event.Event;
import com.example.tpandroid.models.Event.EventSuccessResponse;
import com.example.tpandroid.models.Login.LoginSuccessResponse;
import com.example.tpandroid.models.Register.RegisterSuccessResponse;
import com.example.tpandroid.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface
{
    //Defino los Endpoints
    //Call<RespuestaAPI>

    @POST("register")
    Call<RegisterSuccessResponse> registrarUsuario(@Body Usuario user);

    @POST("login")
    Call<LoginSuccessResponse> loginUsuario(@Body Usuario user);

    @Headers("token: $2y$10$FHkKBS/E37cMyf6N9lTCkuaJJoJH4Z1hy5o.fb8ZSNzuBvEsRSIKC")
    @POST("event")
    Call<EventSuccessResponse> registrarEvento(@Body Event ev);
}
