package com.example.appb;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class CajaColor extends AppCompatActivity {

    private int color;
    private int veces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja_color);
        this.color = getResources().getColor(R.color.negro);
        this.veces = 0;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        } else {
            this.finish();
        }
    }
}
