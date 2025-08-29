package com.coursojava.sesion11;

import java.util.*;

public class ExampleListAndSetMap {

    public static void main(String[] args) {

        //ES IGUAL QUE LA LISTA PERO EN ESTE CASO ES UN CONJUNTO DE DATOS NO
        //ORDENADOS
        //LAS LISTAS SON UN CONJUNTO DE DATOS ORDENADOS
        Set<String> conjunto = new HashSet<String>();

        //ESTA ES LA FORMA DE INICIALIZAR UNA LISTA
        List<String> Listado = new ArrayList<>();

        //LOS MAPAS SON PARECIDOS O PRACTICAMENTE ES LO MISMO QUE UN Dictionary EN C#
        //CLAVE VALOR
        Map<Integer, String> mapa= new HashMap<>();

        mapa.put(1, "Test");

        for (Map.Entry<Integer, String> entry : mapa.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }


    }
}
