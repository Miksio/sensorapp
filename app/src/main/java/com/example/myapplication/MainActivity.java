package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    private TextView wilgotnosc;

    private TextView cisnienie;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private boolean isTemperatureSensoreAvaiable;
    private Sensor humiditySensor;
    private Sensor pressureSensor;
    private boolean isHumiditySensorAvaiable;
    private boolean isPressureAvaiable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null)
        {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemperatureSensoreAvaiable = true;
        } else {
            textView.setText("Czujnik temperatury jest niedostępny");
            isTemperatureSensoreAvaiable = false;
        }
//CzujnikWilgotności
        wilgotnosc = findViewById(R.id.wilgotnosc);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) !=null)
        {
            humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            isHumiditySensorAvaiable = true;
        }else {
            wilgotnosc.setText("Czujnik wilgotności jest niedostępny");
            isHumiditySensorAvaiable = false;
        }

        //Czujnik ciśnienia
        cisnienie = findViewById(R.id.cisnienie);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) !=null)
        {
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            isPressureAvaiable = true;
        }else {
            wilgotnosc.setText("Czujnik ciśnienia jest niedostępny");
            isPressureAvaiable = false;
        }


    }
//czujnik temperatury
    @Override
    public void onSensorChanged(SensorEvent Event) {
        if (Event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float tempSensor = Event.values[0];
            textView.setText("Temperatura wynosi: "+tempSensor + " °C");
        }
        if (Event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            float humiditySensor = Event.values[0];
            wilgotnosc.setText("Wilgotność wynosi: " +humiditySensor + " %");
        }
        if (Event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float pressureSensor = Event.values[0];
            cisnienie.setText("Cieśnienie wynosi: " +pressureSensor + " hPa");
        }


    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isTemperatureSensoreAvaiable);
        {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (isHumiditySensorAvaiable);{
            sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (isPressureAvaiable);{
            sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isTemperatureSensoreAvaiable) {
            sensorManager.unregisterListener(this);
        }
        if (isHumiditySensorAvaiable){
            sensorManager.unregisterListener(this);
        }
        if (isPressureAvaiable){
            sensorManager.unregisterListener(this);
        }
    }
}