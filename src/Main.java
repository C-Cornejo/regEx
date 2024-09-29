import com.ayc.regex.entidad.Automata;
import com.ayc.regex.entidad.Estado;
import com.ayc.regex.entidad.Transicion;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String p = "aa";

        Estado q9 = new Estado(false, true);
        Estado q8 = new Estado(true, false);
        Estado q7 = new Estado(false, false);
        Estado q6 = new Estado(false, false);
        Estado q5 = new Estado(false, false);
        Estado q4 = new Estado(false, false);
        Estado q3 = new Estado(false,false);
        Estado q2 = new Estado(false,false);
        Estado q1 = new Estado(false,false);
        Estado q0 = new Estado(false,false);

        q0.agregarTransicion(new Transicion(q1,"b"));
        q1.agregarTransicion(new Transicion(q1,"a"));
        q1.agregarTransicion(new Transicion(q9,null));
        q2.agregarTransicion(new Transicion(q3,"c"));
        q3.agregarTransicion(new Transicion(q3,"c"));
        q3.agregarTransicion(new Transicion(q4,"d"));
        q4.agregarTransicion(new Transicion(q9,null));
        q5.agregarTransicion(new Transicion(q6,"a"));
        q6.agregarTransicion(new Transicion(q7,"a"));
        q7.agregarTransicion(new Transicion(q9,null));
        q8.agregarTransicion(new Transicion(q0,null));
        q8.agregarTransicion(new Transicion(q5,null));
        q8.agregarTransicion(new Transicion(q2,null));




        ArrayList<Estado> lista = new ArrayList<Estado>();
        lista.add(q0);
        lista.add(q1);
        lista.add(q2);
        lista.add(q3);
        lista.add(q5);
        lista.add(q6);
        lista.add(q7);
        lista.add(q8);
        lista.add(q9);

        Automata regex = new Automata(lista,"abc",p);

        String r = regex.evaluar();
        System.out.println("Respuesta para: aa es "+r);

        regex.palabra = "baaaa";
        r = regex.evaluar();
        System.out.println("Respuesta para: baaaa es "+r);

        regex.palabra = "cd";
        r = regex.evaluar();
        System.out.println("Respuesta para: cd es "+r);


        regex.palabra = "aa";
        r = regex.evaluar();
        System.out.println("Respuesta para: aa es "+r);


        regex.palabra = "p";
        r = regex.evaluar();
        System.out.println("Respuesta para: p es "+r);


    }
}