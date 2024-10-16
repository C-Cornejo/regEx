package com.ayc.regex.entidad;

import java.util.ArrayList;

public class Estado {

    private String nombre;
    private boolean esInicial;
    private boolean esFinal;
    private ArrayList<Transicion> listaTransicion;

    public Estado(String nombre, boolean esInicial,boolean esFinal)
    {
        this.nombre  = nombre;
        this.esInicial = esInicial;
        this.esFinal = esFinal;
        this.listaTransicion = new ArrayList<>();
    }

    public void agregarTransicion(Transicion t){
        this.listaTransicion.add(t);
    }


    public String getNombre() {
        return nombre;
    }

    public boolean isEsInicial() {
        return esInicial;
    }

    public boolean isEsFinal() {
        return esFinal;
    }

    public ArrayList<Transicion> getListaTransicion() {
        return listaTransicion;
    }

}
