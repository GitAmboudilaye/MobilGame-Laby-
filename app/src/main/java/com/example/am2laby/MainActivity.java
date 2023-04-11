package com.example.am2laby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Laby laby;
    private SensorManager manager;
    private Sensor sensor;
    private  Ball setPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setPosition = new Ball();
        this.manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        this.sensor = this.manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.laby = new Laby(this);
        setContentView(laby);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            //float z = sensorEvent.values[2];

            //change Ball postions with Accelerometer
            setPosition.setX(x);
            setPosition.setY(y);



        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}