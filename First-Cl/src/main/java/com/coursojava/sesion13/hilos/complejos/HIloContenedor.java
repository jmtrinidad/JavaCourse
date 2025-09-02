package com.coursojava.sesion13.hilos.complejos;

public class HIloContenedor {

    private  int dato;

    private boolean hayDato=false;

    public  synchronized int get(){
        while (hayDato == false){
            try{
                //Espera a que el productor coloque valor
                wait();

            }catch (InterruptedException e){ }
        }
        hayDato=false;

        //Notfica que el valor ha sido consumido
        notifyAll();
        return dato;
    }

    public  synchronized void put(int valor){
        while (hayDato == true){
            try{
                //Espera a que se consuma dato
                wait();

            }catch (InterruptedException e){ }
        }

        dato=valor;

        hayDato=true;

        //Notfica que el valor ha sido consumido
        notifyAll();

    }

}
