package com.ayc.regex.entidad;

public class Transicion {

    public Estado destino;
    public String simbolo;


    public Transicion(Estado destino,String simbolo){
        this.destino = destino;
        this.simbolo = simbolo;
    }
}
