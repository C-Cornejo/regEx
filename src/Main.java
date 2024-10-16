import com.ayc.regex.entidad.Automata;
import com.ayc.regex.entidad.Estado;
import com.ayc.regex.entidad.Transicion;

import java.util.*;

//
public class Main {
    private static final String EPSILON = "ε";

    public static void main(String[] args) {

        Estado q8 = new Estado("q8", true, false); // Estado inicial
        Estado q0 = new Estado("q0", false, false);
        Estado q1 = new Estado("q1", false, false);
        Estado q5 = new Estado("q5", false, false);
        Estado q6 = new Estado("q6", false, false);
        Estado q7 = new Estado("q7", false, false);
        Estado q2 = new Estado("q2", false, false);
        Estado q3 = new Estado("q3", false, false);
        Estado q4 = new Estado("q4", false, false);
        Estado q9 = new Estado("q9", false, true); // Estado final


        q0.agregarTransicion(new Transicion(q1, "b"));

        q1.agregarTransicion(new Transicion(q1, "a"));
        q1.agregarTransicion(new Transicion(q9, EPSILON));

        q2.agregarTransicion(new Transicion(q3, "c"));

        q3.agregarTransicion(new Transicion(q3, "c"));
        q3.agregarTransicion(new Transicion(q4, "d"));

        q4.agregarTransicion(new Transicion(q9, EPSILON));

        q5.agregarTransicion(new Transicion(q6, "a"));

        q6.agregarTransicion(new Transicion(q7, "a"));

        q7.agregarTransicion(new Transicion(q9, EPSILON));

        q8.agregarTransicion(new Transicion(q0, EPSILON));
        q8.agregarTransicion(new Transicion(q5, EPSILON));
        q8.agregarTransicion(new Transicion(q2, EPSILON));

        ArrayList<Estado> estados = new ArrayList<>();
        estados.add(q0);
        estados.add(q1);
        estados.add(q2);
        estados.add(q3);
        estados.add(q5);
        estados.add(q6);
        estados.add(q7);
        estados.add(q8);
        estados.add(q9);
        Set<Character> alfabeto = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd'));


        Automata regex = new Automata();
        boolean automataEstado = regex.definirAutomata(estados, alfabeto);
        System.out.println("Evaluador de expresiones regulares mediante AFN-E");
        System.out.println("Expresión regular a utilizar : ba*|c^+d|aa");
        System.out.println("Lista de estados del automata");
        System.out.println(regex.imprimirEstados(estados));
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        System.out.println("Proceda a probar la expresión regular ingresando " + 5 + " palabras:");
        while (i < 5) {
            String palabra = scanner.nextLine();
            // Evaluar la palabra inmediatamente después de ingresarla
            char resultado = regex.evaluar(palabra, automataEstado);
            System.out.println("Palabra: " + palabra + " -> " + resultado);
            i++; // Incrementar el contador de palabras ingresadas
        }


        regex.reiniciarAutomata();


        Estado a0 = new Estado("a0", true, false); // Estado inicial
        Estado a1 = new Estado("a1", false, false);
        Estado a2 = new Estado("a2", false, true);

        ArrayList<Estado> estados2 = new ArrayList<>();
        estados2.add(a0);
        estados2.add(a1);
        estados2.add(a2);
        Set<Character> alfabeto2 = new HashSet<>(Arrays.asList('a', 'b', 'c'));

        a0.agregarTransicion(new Transicion(a0, "a"));
        a0.agregarTransicion(new Transicion(a1, EPSILON));
        a1.agregarTransicion(new Transicion(a1, "b"));
        a1.agregarTransicion(new Transicion(a2, "c"));
        a2.agregarTransicion(new Transicion(a2, "c"));

        boolean automataEstado2 = regex.definirAutomata(estados2, alfabeto2);

        System.out.println("Evaluador de expresiones regulares mediante AFN-E");
        System.out.println("Expresión regular a utilizar : a*b*c");
        System.out.println("Lista de estados del automata");
        System.out.println(regex.imprimirEstados(estados2));


        int j = 0;
        System.out.println("Proceda a probar la expresión regular ingresando " + 5 + " palabras:");
        while (j < 5) {
            String palabra = scanner.nextLine();
            // Evaluar la palabra inmediatamente después de ingresarla
            char resultado = regex.evaluar(palabra, automataEstado2);
            System.out.println("Palabra: " + palabra + " -> " + resultado);
            j++; // Incrementar el contador de palabras ingresadas
        }
        scanner.close(); // Cerrar el scanner

    }
}