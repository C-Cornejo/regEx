package com.ayc.regex.entidad;

import java.util.ArrayList;

public class Estado {

    public boolean esInicial;
    public boolean esFinal;
    public ArrayList<Transicion> listaTransicion;

    public Estado( boolean esInicial,boolean esFinal)
    {
        this.esInicial = esInicial;
        this.esFinal = esFinal;
        this.listaTransicion = new ArrayList<>();
    }

    public void agregarTransicion(Transicion t){
        this.listaTransicion.add(t);
    }

}
