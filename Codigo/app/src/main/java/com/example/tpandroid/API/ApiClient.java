package com.example.tpandroid.API;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    private static final String BASE_URL = "http://so-unlam.net.ar/api/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    //T. Ceejeetah
    public static String Register(String nombre, String apellido, int dni, String email, String contra, int comision, int grupo)
    {
        String result;
        result = null;

        try {
            URL url = new URL ("http://so-unlam.net.ar/api/api/register");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            httpURLConnection.connect();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("env", "TEST");
            jsonObject.put("name", nombre);
            jsonObject.put("lastname", apellido);
            jsonObject.put("dni", dni);
            jsonObject.put("email", email);
            jsonObject.put("password", contra);
            jsonObject.put("comission", comision);
            jsonObject.put("group", grupo);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.write(jsonObject.toString().getBytes("UTF-8"));

            Log.i("LOGUEO_SERVICE", "Se va a enviar al servidor" + jsonObject.toString());

            wr.flush();
            wr.close();

            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();

            if((responseCode == HttpURLConnection.HTTP_OK) || (responseCode == HttpURLConnection.HTTP_CREATED))
               result = convertInputStreamToString(httpURLConnection.getInputStream());
            else
                result = "NO_OK";

            httpURLConnection.disconnect();

            Log.i("TAG", result);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());

    }
}
