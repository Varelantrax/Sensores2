package com.example.edgar.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity implements SensorEventListener {

    private TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        salida = (TextView) findViewById(R.id.salida);
// aqui se hiso un areglo para mostrar todos los censores
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor sensor: listaSensores) {
            log(sensor.getName());
        }
    }
// este es el metodo para iniciar todos los sensores
    public void iniciarSensores(){

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
// estas son condiciones para iniciar los sensores que existen en el dospisitivo movil
        if (!listaSensores.isEmpty()) {
            Sensor orientationSensor = listaSensores.get(0);
            sensorManager.registerListener(this, orientationSensor,
                    SensorManager.SENSOR_DELAY_UI);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listaSensores.isEmpty()) {
            Sensor acelerometerSensor = listaSensores.get(0);
            sensorManager.registerListener(this, acelerometerSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);

        if (!listaSensores.isEmpty()) {
            Sensor giroscopioSensor = listaSensores.get(0);
            sensorManager.registerListener(this, giroscopioSensor,
                    SensorManager.SENSOR_DELAY_UI);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);

        if (!listaSensores.isEmpty()) {
            Sensor magneticSensor = listaSensores.get(0);
            sensorManager.registerListener(this, magneticSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);

        if (!listaSensores.isEmpty()) {
            Sensor temperatureSensor = listaSensores.get(0);
            sensorManager.registerListener(this, temperatureSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);

        if (!listaSensores.isEmpty()) {
            Sensor proximitySensor = listaSensores.get(0);
            sensorManager.registerListener(this, proximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_GRAVITY);

        if (!listaSensores.isEmpty()) {
            Sensor gravedadSensor = listaSensores.get(0);
            sensorManager.registerListener(this, gravedadSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);}

// en esta parte fue donde le pusimos pausa para ver el manga nuevo de naruto donde aparece un nuevo huchiha
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_LIGHT);

        if (!listaSensores.isEmpty()) {
            Sensor luzSensor = listaSensores.get(0);
            sensorManager.registerListener(this, luzSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);}
    }
//Es el metodo para detener los sensores
    public void detenerSensores(){
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch(event.sensor.getType()) {
                case Sensor.TYPE_ORIENTATION:
// aqui es masomenos donde se le da el valor a los ejes de los sensores o algo asi no me acuerdo
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    log("Acelerometro X: "+event.values[0]);
                    log("Acelerometro Y: "+event.values[1]);
                    log("Acelerometro Z: "+event.values[2]);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    log("Eje X: "+event.values[0]);
                    log("Eje Y: "+event.values[1]);
                    log("Eje Z: "+event.values[2]);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    log("Eje X: "+event.values[0]);
                    log("Eje Y: "+event.values[1]);
                    log("Eje Z: "+event.values[2]);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    log("Proximidad: "+event.values[0]);
                    break;
                case Sensor.TYPE_LIGHT:

                    break;
                case Sensor.TYPE_GRAVITY:

                    break;
                default:
                    for (int i=0 ; i<event.values.length ; i++) {
                        log("Temperatura "+i+": "+event.values[i]);
                    }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

// este es el metodo para mostrar los sensores que existen en el celular en el textview salida
    private void log (String string) {
        salida.append(string + "\n");
    }
// este es el metodo para limpiar el textview
    private void limpiar(){
        salida.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //se modifico el action bar
        switch (item.getItemId()) {
            case R.id.iniciar:
                iniciarSensores();
                return true;
            case R.id.detener:
                detenerSensores();
                return true;
            case R.id.limpiar:
                limpiar();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }
}
