package com.ayc.regex.entidad;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Automata {

    private ArrayList<Hilo> listaHilo;
    private Set<Character> alfabeto;

    private ArrayList<Estado> listaEstado;
    private Estado inicial;
    private int contadorHilos;
    private static final String EPSILON = "ε";

    public Automata() {
    }

    public boolean definirAutomata(ArrayList<Estado> listaEstado, Set<Character> alfabeto) {
        this.alfabeto = alfabeto;
        this.listaEstado = listaEstado;
        listaHilo = new ArrayList<>();
        contadorHilos = 0;

        // Inicializar el contador de estados iniciales
        int inicial = 0;
        int finales = 0;
        // Verificación de que la lista de estados no esté vacía
        if (listaEstado == null || listaEstado.isEmpty()) {
            return false; // La lista de estados no puede estar vacía
        }
        for (Estado e : listaEstado) {
            if (e.isEsInicial()) {
                inicial++;
                this.inicial = e;
            }
            if (e.isEsFinal()) {
                finales++;
            }
        }
        // Verificación de si el autómata está correctamente definido
        return !listaEstado.isEmpty() && this.inicial != null && finales != 0 && inicial == 1 && !alfabeto.isEmpty();
    }


    public char evaluar(String palabra, boolean estadoAutomata) {
        contadorHilos = 0;

        for (char simbolo : palabra.toCharArray()) {
            boolean encontrado = false;
            for (Character caracter : alfabeto) {
                if (caracter == simbolo) {
                    encontrado = true;
                    break; // Salimos del bucle si encontramos el símbolo
                }
            }
            if (!encontrado) {
                return 'p'; // Símbolo no en alfabeto
            }
        }
        if (estadoAutomata == false) {
            return 'e'; // Error, autómata no definido
        }
        reiniciarHilos();
        return evaluarRegEx(palabra) ? 's' : 'n';
    }

    public void reiniciarHilos() {
        listaHilo = new ArrayList<>();
    }

    private boolean evaluarRegEx(String palabra) {
        contadorHilos++;  // Incrementa el contador de hilos (instancias de evaluación) cada vez que se inicia una evaluación.
        listaHilo.add(new Hilo(String.valueOf(contadorHilos), this.inicial, palabra));  // Crea y agrega un nuevo hilo de evaluación a la lista de hilos, iniciando desde el estado inicial y con la palabra completa a evaluar.

        while (!listaHilo.isEmpty()) {  // Continua mientras haya hilos en la lista, es decir, mientras haya evaluaciones pendientes.
            Hilo actual = listaHilo.remove(0);  // Toma y elimina el primer hilo de la lista para procesarlo.
            transicionVacio(actual);  // Aplica todas las transiciones vacías (epsilon) desde el estado actual del hilo.

            if (actual.getCaracteresRestantes().isEmpty()) {  // Verifica si ya no quedan caracteres por consumir en la palabra actual.
                if (actual.getEstadoActual().isEsFinal()) {  // Si no quedan caracteres y el estado actual es un estado final:
                    return true;  // La palabra es aceptada por el autómata, devuelve `true`.
                }
            } else {
                consumirCaracter(actual);  // Si quedan caracteres, intenta consumir el siguiente carácter con las transiciones válidas.
            }
        }
        return false;  // Si se procesaron todos los hilos y ninguno alcanzó un estado final con la palabra completa, devuelve `false`.
    }

    private void transicionVacio(Hilo hilo) {
        Estado estadoActual = hilo.getEstadoActual();  // Obtiene el estado actual del hilo que se está evaluando.
        for (int i = 0; i < estadoActual.getListaTransicion().size(); i++) {  // Itera sobre la lista de transiciones del estado actual.
            Transicion t = estadoActual.getListaTransicion().get(i);  // Obtiene la transición actual.
            if (t.getSimbolo().equals(EPSILON)) {  // Si el símbolo de la transición es una epsilon (transición vacía):
                // System.out.println("..Se encontró transicion vacía...");
                //System.out.println("Se pasará al estado: "+t.destino.id+" y aun se tiene: " +hilo.caracteresRestantes);
                contadorHilos++;  // Incrementa el contador de hilos.
                listaHilo.add(new Hilo(String.valueOf(contadorHilos), t.getDestino(), hilo.getCaracteresRestantes()));  // Crea un nuevo hilo en el estado de destino de la transición epsilon y agrega este hilo a la lista de hilos.
            }
        }
    }

    public String imprimirEstados(List<Estado> estados) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(String.format("%-2s %-10s%n", "", "Estados"));
        resultado.append("--------------------\n");

        for (Estado estado : estados) {
            String indicador = "";
            if (estado.isEsInicial()) {
                indicador = ">";
            }
            if (estado.isEsFinal()) {
                indicador += "*";
            }
            resultado.append(String.format("%-2s %-10s%n", indicador, estado.getNombre()));
        }

        return resultado.toString();
    }

    private void consumirCaracter(Hilo hilo) {
        String caracterAConsumir = hilo.getCaracteresRestantes().substring(0, 1);  // Obtiene el primer carácter de los caracteres restantes de la palabra en el hilo actual.

        Estado estadoActual = hilo.getEstadoActual();  // Obtiene el estado actual del hilo que se está evaluando.

        //System.out.println("Hilo " + hilo.getId() + ": El estado actual es:" + estadoActual.getNombre());
        //System.out.println("El simbolo a leer es: " + caracterAConsumir);

        for (int i = 0; i < estadoActual.getListaTransicion().size(); i++) {  // Itera sobre la lista de transiciones del estado actual.
            String restantes = "";  // Variable para almacenar los caracteres restantes de la palabra después de consumir el carácter actual.
            Transicion t = estadoActual.getListaTransicion().get(i);  // Obtiene la transición actual.

            if (!t.getSimbolo().equals(EPSILON)) {  // Ignora las transiciones epsilon, ya que este método solo trata de consumir un carácter.
                if (t.getSimbolo().equals(caracterAConsumir)) {  // Verifica si el símbolo de la transición coincide con el carácter a consumir.
                    if (hilo.getCaracteresRestantes().length() > 1) {  // Si hay más de un carácter restante:
                        restantes = hilo.getCaracteresRestantes().substring(1);  // Remueve el primer carácter y obtiene los caracteres restantes.
                    } else {
                        restantes = "";  // Si no hay más caracteres, los restantes quedan como una cadena vacía.
                    }
                    contadorHilos++;  // Incrementa el contador de hilos (trayectorias) para gestionar el nuevo estado generado por la transición.

                    //  System.out.println("Existe transicion... se pasará al estado: " + t.getDestino().getNombre() + " y aun se tiene: " + restantes);
                    listaHilo.add(new Hilo(String.valueOf(contadorHilos), t.getDestino(), restantes));  // Crea y agrega un nuevo hilo que parte del estado destino de la transición y con los caracteres restantes.
                }
            }
        }
    }

    public void reiniciarAutomata() {
        // Reinicia la lista de hilos
        listaHilo = new ArrayList<>();

        // Reinicia el conjunto de caracteres del alfabeto
        alfabeto = new HashSet<>();

        // Reinicia la lista de estados
        listaEstado = new ArrayList<>();

        // Reinicia el estado inicial (suponiendo que quieras dejarlo null)
        inicial = null;

        // Reinicia el contador de hilos
        contadorHilos = 0;
    }


}
