package com.example.appb;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CajaColor extends AppCompatActivity implements View.OnClickListener { //implementamos
    //la clase OnClickListener

    private int color;
    private int veces;
    private long tinicial;
    private Button boton_empezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja_color);
        this.color = getResources().getColor(R.color.negro);
        this.veces = 0;
        this.boton_empezar = findViewById(R.id.boton_empezar);
    /*   this.tinicial = System.currentTimeMillis(); //crea tiempo en milisegundos

        //programo el listener del boton
        Button button = findViewById(R.id.boton_empezar);
        this.boton_empezar = findViewById(R.id.boton_empezar);
        this.boton_empezar.setOnClickListener(this);//metodo setOnClickListener escucha
        this.boton_empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        /*menu.add ("fulanito"); //para añadir en el menu*/

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.version_original:
                //lanzar la version infinito: ya estoy
                Log.d("MIAPP", "toco v infinito");
                break;
            case R.id.version_infinito:
                //lanzar la version original
                Intent intent = new Intent(this, Fernando.class);
                startActivity(intent);// transito a la actividad CajaColor
                Log.d("MIAPP", "toco v original");
                this.finish();
                break;
            default:
                Log.d("MIAPP", "No sé que toco");
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        Log.d("MIAPP","toco el boton");
    }

    public void cambiaColor(View v){
        LinearLayoutCustom caja = (LinearLayoutCustom) v;
       if (caja.isUsado() ){
           Log.d("MIAPP", "Ya ha sido tocada");
       } else {
           caja.setBackgroundColor(this.color);
           caja.setUsado(true);
           this.veces += 1;
           if(this.veces == 6) {
             salir();

           }
       }

    }

    private void salir(){
        long tiempo_final = System.currentTimeMillis(); // creamos variable con el contador System.currentTimeMillis
        long tiempo_total = tiempo_final - this.tinicial; //creamos variable con el resultado del tiempo_final

        double tDuracion = tiempo_total/1000d; //dividimos los milisegundos entre 1000 para pasar a segundos
        String texto_informativo = String.format("Duracion : %1$.3f segundos",tDuracion);
        Log.i("MYAPP", texto_informativo);

        //el Toast es el mensajito pequeño negro abajo que sale por ejemplo cuando pones una alarma
        // y te indica las horas que te quedan para que suene
        Toast mensaje_toast = Toast.makeText(this,texto_informativo,Toast.LENGTH_LONG);//creando el mensaje donde nos dice el tiempo que hemos tardado
        mensaje_toast.show(); //informo

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();//este metodo es para salir completamente de la aplicacion
        } else {
            this.finish();
        }
    }
    public void empezarPartida (View view)
    {
        Log.d("MIAPP", "empezar la partida");

        this.tinicial= System.currentTimeMillis();
        this.boton_empezar.setVisibility(View.INVISIBLE);

    }
}
