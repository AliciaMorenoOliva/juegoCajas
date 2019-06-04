package com.example.appb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;



public class NumberPickerActivity extends AppCompatActivity {
    NumberPicker numberpicker;
    TextView textview;

    //para crear una preferencs creo estas variables
    private int num_veces;
    private String nombre;
    public static final String NOMBRE_FICHERO_PREFRENCES = "prefs";
    public static final String PUNTUACIONES = "prefs_puntuaciones";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);

        numberpicker = (NumberPicker) findViewById(R.id.numberPickerVeces);

        textview = (TextView) findViewById(R.id.textViewSeleccion);

        numberpicker.setMinValue(1);
        numberpicker.setMaxValue(75);

        textview = (TextView) findViewById(R.id.textViewNombreUsuario);




    }

    public void cambiaPantalla(View view) {

        int valor = this.numberpicker.getValue();

        EditText editText = findViewById(R.id.editTextUsuario);
        String usuario_introducido = editText.getText().toString();
       //recojo el dato de la caja de usuario

        //pasando a la siguiente actividad/(Fernando) los datos valor y usuario_introducido
        Intent intent = new Intent(this, Fernando.class);
        intent.putExtra("NTOKES", valor);
        intent.putExtra("NOMBRE",usuario_introducido);
        intent.putExtra("PUNTUACIONES",usuario_introducido);
        startActivity(intent);


      //  lanzarPantalla(valor,usuario_introducido); llama al metodo lanzarPantalla

    }

    /*private void lanzarPantalla(int veces,String nombre) {

        Intent intent = new Intent(this, Fernando.class);

        intent.putExtra("NTOKES", veces);
        intent.putExtra("NOMBRE",nombre);
        startActivity(intent);

    }*/

}
