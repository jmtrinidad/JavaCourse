package com.coursojava.sesion13.hilos;

import java.util.LinkedList;
import java.util.List;

public class MyHiloApp {

    public static void main(String[] args) {
//        var myHilo = new MyHilo("hilo1");
//        var myHilo2 = new MyHilo("hilo2");
//
//        myHilo.start();
//        myHilo2.start();

        Integer numHilo=2;

        List<MyHilo> litadoHilos = new LinkedList<>();

        for (int i = 0; i < 10; i++) {

            var myHilo = new MyHilo("hilo" + i);

            litadoHilos.add(myHilo);

        }

        for (int i = 0; i < numHilo; i++) {
            litadoHilos.get(i).start();
        }
    }

}
