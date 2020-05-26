package com.example.tpandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.os.Bundle;

import com.example.tpandroid.models.Event.Event;
import com.example.tpandroid.models.Event.EventSuccessResponse;
import com.example.tpandroid.models.SaboresHelado;
import com.example.tpandroid.models.CantidadHelado;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoActivity extends AppCompatActivity implements SensorEventListener
{

    private static Context context;
    // Se usan para detectar el shake del acelerómetro
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private PedidoActivity mShakeDetector;

    // Para detectar el sensor de proximidad
    private Sensor proxSensor;

    /*
     * The gForce that is necessary to register as shake.
     * Must be greater than 1G (one earth gravity unit).
     * You can install "G-Force", by Blake La Pierre
     * from the Google Play Store and run it to see how
     *  many G's it takes to register a shake
     */
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private OnShakeListener mListener;
    private long mShakeTimestamp;
    private int mShakeCount;

    public void setOnShakeListener(OnShakeListener listener) {
        this.mListener = listener;
    }

    public interface OnShakeListener {
        public void onShake(int count);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ignore
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY)
        {
                if(cantidad.getSelectedItemPosition() == 3)
                {
                    cantidad.setSelection(0);
                }
                else
                {
                    cantidad.setSelection(cantidad.getSelectedItemPosition() + 1);
                }
            guardarPreferenciasProximidad(event.values[0]);
            registrarEventoProximidad();
        }
        if (mListener != null) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                final long now = System.currentTimeMillis();
                // ignore shake events too close to each other (500ms)
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }

                // reset the shake count after 3 seconds of no shakes
                if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                    mShakeCount = 0;
                }

                mShakeTimestamp = now;
                mShakeCount++;

                guardarPreferenciasAcelerometro(gForce);
                mListener.onShake(mShakeCount);
            }
        }
    }

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
        context = getApplicationContext();

        precio = findViewById(R.id.textView7);
        puntos = findViewById(R.id.textView6);
        confirmar = findViewById(R.id.button5);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proxSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorManager.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);

        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new PedidoActivity();
        mShakeDetector.setOnShakeListener(new OnShakeListener() {

            @Override
            public void onShake(int count) {

                if(gustos.getSelectedItemPosition() == 23)
                    gustos.setSelection(0);
                else
                    gustos.setSelection(gustos.getSelectedItemPosition() + 1);
                registrarEventoAcelerometro();
            }
        });

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
                int puntosASumar = 0;
                switch (cantidad.getSelectedItem().toString()) {
                    case "Cucurucho":
                        puntosASumar = 10;
                        break;
                    case "1/4 Kg":
                        puntosASumar = 20;
                        break;
                    case "1/2 Kg":
                        puntosASumar = 40;
                        break;
                    case "1 Kg":
                        puntosASumar = 60;
                        break;
                    default:
                        puntosASumar = 0;
                }
                guardarPuntos(puntosASumar);
                Intent confirmarActivityIntent = new Intent(getApplicationContext(), ConfirmarActivity.class);
                startActivity(confirmarActivityIntent);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
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

    private void registrarEventoAcelerometro()
    {
        Event e = new Event("Sensor", "ACTIVO", "Se ha realizado un shake.");
        LoginActivity.apiClient.RegistrarEvento(e, new Callback<EventSuccessResponse>() {
            @Override
            public void onResponse(Call<EventSuccessResponse> call, Response<EventSuccessResponse> response) {
                Log.i("Evento", "Se realizó un Shake.");
            }

            @Override
            public void onFailure(Call<EventSuccessResponse> call, Throwable t) {
                Log.i("Evento", "Falló el envio del Evento.");
            }
        });
    }

    private void registrarEventoProximidad()
    {
        Event e = new Event("Sensor", "ACTIVO", "Se detectó proximidad con un sensor.");
        LoginActivity.apiClient.RegistrarEvento(e, new Callback<EventSuccessResponse>() {
            @Override
            public void onResponse(Call<EventSuccessResponse> call, Response<EventSuccessResponse> response) {
                Log.i("Evento", "Se detectó un cambio en el sensor de proximidad.");
            }

            @Override
            public void onFailure(Call<EventSuccessResponse> call, Throwable t) {
                Log.i("Evento", "Falló el envio del Evento.");
            }
        });
    }

    private void guardarPreferenciasAcelerometro(float gForce)
    {
        try {

            SharedPreferences preferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat("Acelerometro", gForce);
            editor.apply();
        }
        catch (Exception e)
        {
            Log.i("DEBUG", e.getMessage());
        }
    }

    private void guardarPreferenciasProximidad(float cm)
    {
        SharedPreferences preferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("Proximidad", cm);
        editor.commit();
    }

    private void guardarPuntos(int puntos)
    {
        SharedPreferences preferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        int puntostotales = preferences.getInt("Puntos", 0);
        editor.putInt("Puntos", puntos + puntostotales);
        editor.commit();
    }

}
