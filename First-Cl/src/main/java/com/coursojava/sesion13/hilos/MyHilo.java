package com.coursojava.sesion13.hilos;

public class MyHilo extends Thread {

private  String name;

    MyHilo(String name){
        this.name=name;
    }

    public  void run(){
        for (int i = 0; i < 10; i++) {

            System.out.println(this.name + " : " + i);

        }
    }

}
