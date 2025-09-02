package com.coursojava.sesion13.hilos.complejos;

public class HiloConsumidor extends Thread{

    private  HIloContenedor contenedor;

    public HiloConsumidor(HIloContenedor c){
        this.contenedor=c;
    }

    public void run (){
        Integer value=0;

        for (int i = 0; i < 10; i++) {
            value=(Integer) contenedor.get();

            System.out.println("Consumidor. get: " + value);
        }
    }
}
