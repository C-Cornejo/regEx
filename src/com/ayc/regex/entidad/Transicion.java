package com.ayc.regex.entidad;

public class Transicion {

    private Estado destino;
    private String simbolo;


    public Transicion(Estado destino,String simbolo){
        this.destino = destino;
        this.simbolo = simbolo;
    }

    public Estado getDestino() {
        return destino;
    }

    public String getSimbolo() {
        return simbolo;
    }


}
