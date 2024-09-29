package com.ayc.regex.entidad;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Automata {

    public ArrayList<Hilo> listaHilo;
    public String alfabeto;
    public String palabra;
    public ArrayList<Estado> listaEstado;
    public Estado inicial;

    public Automata(ArrayList<Estado> estados,String alfabeto,String palabra){
        this.listaEstado = estados;
        this.alfabeto    = alfabeto;
        this.palabra     = palabra;
        listaHilo = new ArrayList<>();

    }

    private boolean validarEstados()
    {
        int iniciales = 0;
        int finales   = 0;
        for(Estado e: listaEstado)
        {
            if(e.esInicial)
            {
                iniciales++;
                this.inicial = e;
            }
            if(e.esFinal)
            {
                finales++;
            }
        }
        if(iniciales == 1 && finales >= 1)
        {
            return true;
        }
        return false;
    }

    private boolean validarAlfabeto(){

        for(int i =0;i< palabra.length();i++)
        {
            if(!alfabeto.contains(palabra.substring(i,i+1)))
            {
                return false;
            }
        }
        return true;
    }

    public String evaluar(){
        boolean r = false;
        String response ="";
        if (validarAlfabeto())
        {
            if(validarEstados()) {
               r = evaluarRegEx(this.inicial, this.palabra);
            }
        }
        if(r)
        {
            response = "s";
        }else
        {
            response = "n";
        }
        reset();
        return response; //
    }
    public void reset()
    {
        listaHilo = new ArrayList<Hilo>();
    }

    private boolean evaluarRegEx(Estado inicial,String palabra)
    {
        listaHilo.add(new Hilo(inicial,palabra));

        while(!listaHilo.isEmpty())
        {
            Hilo actual = listaHilo.removeFirst();

            if(actual.caracteresRestantes.isEmpty())
            {
                if(actual.estadoActual.esFinal)
                {
                    return true;
                }
            }else {
                consumirCaracter(actual);
            }
        }
        return false;
    }

    private void consumirCaracter(Hilo hilo)
    {
        String caracterAConsumir = hilo.caracteresRestantes.substring(0,1);

        Estado estadoActual = hilo.estadoActual;
        for(int i=0;i < estadoActual.listaTransicion.size();i++)
        {
            Transicion t = estadoActual.listaTransicion.get(i);
            if(t.simbolo == null)
            {
                listaHilo.add(new Hilo(t.destino,hilo.caracteresRestantes));
            }
            else if(t.simbolo.equals(caracterAConsumir))
            {
                listaHilo.add(new Hilo(t.destino,hilo.caracteresRestantes.substring(0,hilo.caracteresRestantes.length() - 1)));
            }
        }
    }
}
