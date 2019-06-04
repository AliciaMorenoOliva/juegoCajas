package com.example.appb;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class LinearLayoutCustom extends LinearLayout { //se genera una nueva clase que hereda
    // de la clase LinearLayout por eso le ponemos extends
    private boolean usado; //creamos la variable usado de tipo booleano y es privada

    public LinearLayoutCustom(Context context) { //este es un constructor de la clase donde le decimos
        // que la variable usado es falsa
        super(context);
        this.usado = false;
    }

    public LinearLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.usado = false;
    }

    public LinearLayoutCustom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.usado = false;
    }
    public boolean isUsado() {
        return this.usado;
    } //creamos un metodo que nos devuelve usado

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
}
