package com.ayc.regex.entidad;

public class Hilo {

    private String id;
    private Estado estadoActual;
    private String caracteresRestantes;


    public Hilo(String id,Estado e,String restantes){
        this.id = id;
        this.estadoActual = e;
        this.caracteresRestantes = restantes;
    }

    public String getId() {
        return id;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public String getCaracteresRestantes() {
        return caracteresRestantes;
    }


}
