package com.coursojava.sesion13.hilos.complejos;

public class HiloProductorConsumidorApp {

    public static void main(String[] args) {

        var contenedor= new HIloContenedor();

        var produce= new HiloProductor(contenedor);
        var consume= new HiloConsumidor(contenedor);

        produce.start();
        consume.start();

    }
}
