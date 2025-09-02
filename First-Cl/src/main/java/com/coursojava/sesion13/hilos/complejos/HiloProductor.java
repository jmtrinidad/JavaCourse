package com.coursojava.sesion13.hilos.complejos;

public class HiloProductor extends  Thread{

    private  HIloContenedor contenedor;


    public HiloProductor(HIloContenedor c){
        this.contenedor=c;
    }

    public  void  run(){
        for (int i = 0; i < 10; i++) {

            contenedor.put(i);
            System.out.println("Productor. put: " + i);

            try{

                sleep((int)(Math.random() * 1000));

            }catch (InterruptedException e){}

        }
    }
}
