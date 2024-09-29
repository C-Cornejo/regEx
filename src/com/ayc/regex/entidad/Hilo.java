package com.ayc.regex.entidad;

public class Hilo {


    public Estado estadoActual;
    public String caracteresRestantes;


    public Hilo(Estado e,String restantes){
        this.estadoActual = e;
        this.caracteresRestantes = restantes;
    }
}
