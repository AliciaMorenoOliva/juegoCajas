package com.example.appb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Random;

public class Fernando extends AppCompatActivity {

    private static final int COLORES[] = {R.color.aguamarina, R.color.cian, R.color.dukeBlue, R.color.UCLABlue, R.color.verdeEsmeralda, R.color.black, R.color.white};
    private int indexColor = 0;
    private static Random r = new Random();
    private int contador = 0;
    private static final int MAX_HIJOS = 5; //creamos constante para el numero de hijos en que se divide
    private int ntoques;
    private String usuario; //creo la variable para recoger el usuario
    private long tiempo_inicial;
    private long tiempo_final;
    private String puntuacion;

    public static final String NOMBRE_FICHERO_PREFRENCES = "prefs";//le damos un nombre al fichero de xml



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fernando);
        this.indexColor = 0;
        this.r = new Random();
        this.contador = 0;
        Intent intent_creador = getIntent();
        this.ntoques = intent_creador.getIntExtra("NTOKES", 3);
        this.usuario = intent_creador.getStringExtra("NOMBRE");
        //recogemos los datos de usuario y numero de tokes



        this.tiempo_inicial = System.currentTimeMillis();//nos da el tiempo en milisegundos

        //recojo los datos del usuario

    }

    //dibujo el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        /*menu.add ("fulanito"); //para añadir en el menu*/

        return super.onCreateOptionsMenu(menu);
    }

    //recibir el evento del menu pulsado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.version_infinito:
                //lanzar la version infinito: ya estoy
                Log.d("MIAPP", "toco v infinito");
                break;
            case R.id.version_original:
                //lanzar la version original
                Intent intent = new Intent(this, CajaColor.class);
                startActivity(intent);// transito a la actividad CajaColor
                Log.d("MIAPP", "toco v original");
                this.finish();
                break;
            case R.id.pausa:

                //TODO para la actividad
                Button button_play = findViewById(R.id.botonplay);
                button_play.setVisibility(View.VISIBLE);


            default:
                Log.d("MIAPP", "No sé que toco");
        }
        return super.onOptionsItemSelected(item);
    }

    //metodo para crear un numero aleatorio y recoger un color del array COLORES
    public int colorAleatorio() {
        int color_aleatorio = -1;
        int posicion = -1;

        Random random = null;

        random = new Random();
        posicion = random.nextInt(COLORES.length); //recojo el numero de elentos que tiene el array de colores
        color_aleatorio = getResources().getColor(COLORES[posicion]);//aqui dice que recoja el id y lo ponga de la carpeta R.color


        return color_aleatorio;

    } //metodo para crear hijos de las cajas

    private LinearLayout crearHijo(int orientacion) {
        LinearLayout nueva_caja = null;
        LinearLayout.LayoutParams parametros = null;

        nueva_caja = new LinearLayout(this);
        nueva_caja.setId(newId());

        if (orientacion == LinearLayout.VERTICAL) {
            nueva_caja.setOrientation(LinearLayout.HORIZONTAL);
            parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1F);
        } else //padre en horizontal
        {
            nueva_caja.setOrientation(LinearLayout.VERTICAL);
            parametros = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1F);
        }
        nueva_caja.setLayoutParams(parametros);
        nueva_caja.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dividir(v);
            }
        });
        nueva_caja.setBackgroundColor(colorAleatorio());
        nueva_caja.setVisibility(View.VISIBLE);

        return nueva_caja;
    }


    public void dividir(View view) {

        LinearLayout padre = (LinearLayout) view;
        LinearLayout nuevo_hijo = null;

        int naleatorio_hijos = this.r.nextInt(MAX_HIJOS) + 2; //creamos un numero aleatorio para dividirse

        for (int contador = 2; contador < naleatorio_hijos; contador++) {
            nuevo_hijo = crearHijo(padre.getOrientation());
            padre.addView(nuevo_hijo);
        }
        this.contador++;
        if (this.contador == this.ntoques) {
            salir();
        }

    }


    /**
     * Es el ID calculado
     * Va generando números aleatorios. Comprueba que el valor generado
     * no coincide con un ID existente y si no existe devuelvo el nuevo
     * valor.
     *
     * @return
     */
    private int newId() {
        int resultado = -1;
        do {
            resultado = r.nextInt(Integer.MAX_VALUE);
        } while (findViewById(resultado) != null);
        return resultado;
    }

    private void guardar(String nombre, long tiempo) {
        SharedPreferences sp = getSharedPreferences(NOMBRE_FICHERO_PREFRENCES, MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("usuario", nombre);
        ed.putLong("tiempo_final", tiempo);

        //guardar el json
        Puntuacion p = new Puntuacion(nombre,tiempo);
        Gson gson = new Gson();
        String objeto_json = gson.toJson(p);
        ed.putString("record_json",objeto_json);

        ed.commit();//GUARDO

        //abrir el preferencs y leerlo
        String puntuacion_str = sp.getString("record_json",null);
        gson.fromJson(puntuacion_str, puntuacion.getClass());
    }

    private void salir() {

        this.tiempo_final = System.currentTimeMillis();
        long ms_total = this.tiempo_final - this.tiempo_inicial;
        long segundos = ms_total / 1000;

        //el Toast es el mensajito pequeño negro abajo que sale por ejemplo cuando pones una alarma
        // y te indica las horas que te quedan para que suene
        Toast mensaje_toast = Toast.makeText(this, "has superado las " + ntoques + " cajas en un tiempo de " + segundos + " segundos", Toast.LENGTH_LONG);//creando el mensaje donde nos dice el tiempo que hemos tardado
        mensaje_toast.show(); //informo
        guardar(this.usuario, segundos);

        //llamo a la funcion de puntuacion
        Puntuacion puntuacion = new Puntuacion(this.usuario, segundos);
        long fp = puntuacion.getTiempo();

        String str_puntuacion = puntuacion.toString();//recojo el metodo de String de la clase puntuacion
        Log.d("MIAPP",str_puntuacion);

        //convertir el objeto puntuacion a un json
        //primero hay que descargar la libreria gson

        Gson gson = new Gson();//declaro el objeto json
       String puntuacion_json = gson.toJson(puntuacion);
        Log.d("MIAPP","JSON= " + puntuacion_json);

        //convertir el objeto json en puntuacion (Deserializar)
        Puntuacion p2 = gson.fromJson(str_puntuacion,Puntuacion.class);

       MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.mariobros);
       mediaPlayer.setLooping(false);
       mediaPlayer.setVolume(100, 100);
       mediaPlayer.start();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();//este metodo es para salir completamente de la aplicacion
        } else {
            this.finish();
        }
    }

    /**
     * @param view Reprsenta el botón tocado
     */
    public void play(View view) {

        //Button boton_play = (Button)view;//podría hacer el casting
        view.setVisibility(View.INVISIBLE);

    }

}
