package com.example.appb;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {




    /**
     * 1 Hacer una actividad inicial para que con un SPINNER para que se elija la modalidad que quieres jugar
     * 2 Crear una actividad previa a la modalidad "FER" para seleccionar el número de toques antes de entrar. Usar un NumberPicker
     * 3 Hacer un botón de pausa para que el juego se detenga y en ese momento no se pueda seguir (salga una pantalla botón, o imagen superpuesta o cualquier tipo de aviso) y que permita reanudar la partida cuando el usuario vuelva a darle
     * 4 Definir una opción en el menú para que se pueda reiniciar la partida (y con ello el tiempo)
     *
     * EXTRA: PENSAR SOBRE LA APP DEL REGISTRO DE HORAS DE TRABAJO. Definir un nombre, REQUISITOS logo...
     */
    //private static final String [] NOMBRES = {"AppB", "INFINITO"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        //le decimos que cree el spiner cuando arranque y le ponga las opciones del arrayspinner
        Spinner spinner = (Spinner) findViewById(R.id.mispinner);
        ArrayAdapter spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// es el proveedor de datos
        spinner.setAdapter(spinnerArrayAdapter);

        //spinner.setSelected(false);
        //spinner.setSelection(0,false); //esto se pone para que no entre en la primera opcion de spinner

        spinner.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String text = parent.getItemAtPosition(pos).toString();
        switch (pos){
            case 1:
                Intent intent1 = new Intent(this,NumberPickerActivity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(this,CajaColor.class);
                startActivity(intent2);
                break;

        }

        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.d("MIAPP", "TOCADO " + pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}