package com.example.appb;

import android.content.Context;
import android.content.SharedPreferences;



public class PreferenciasUsuario {

    private static final String USUARIO = "fichero_usuario";
    private static final String TIEMPO = "fichero_tiempo";





    /*private static final String NOMBRE_FICHERO = "fichero_prefs";
    private static final String CLAVE_CADENA = "texto_ultimo";

    public String leerTexto(Context context) {
        String cadena_leida = null;
        SharedPreferences fichero = null;
        fichero = context.getSharedPreferences(NOMBRE_FICHERO, context.MODE_PRIVATE);
        cadena_leida = fichero.getString(CLAVE_CADENA, null);
        return cadena_leida;
    }

    public static void guardarTexto(String texto, Context context) {
        //tomar el fichero
        SharedPreferences fichero = null;
        fichero = context.getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = fichero.edit();
        editor.putString(CLAVE_CADENA, texto);
        editor.commit(); // lo guardo
    }*/


}
