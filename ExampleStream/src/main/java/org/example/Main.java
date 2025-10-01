package org.example;

import org.example.class_anidadas.OuterClass;

import java.util.*;
import java.util.stream.IntStream;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {


      // EJEMPLO DE CLASE ANIDADA
      // PARA CREAR UNA INSTANCIA DE LA CLASE INTERNA, PRIMERO HAY QUE CREAR UNA INSTANCIA DE LA CLASE EXTERNA
      // Y LUEGO USAR ESA INSTANCIA PARA CREAR LA INSTANCIA DE LA CLASE INTERNA
      // SI LA CLASE INTERNA ES ESTATICA, NO ES NECESARIO CREAR UNA INSTANCIA DE LA CLASE EXTERNA
      // SI LA CLASE INTERNA ES PRIVADA, NO SE PUEDE ACCEDER DESDE FUERA DE LA CLASE EXTERNA
       OuterClass outer = new OuterClass();
       OuterClass.InnerClass inner = outer.new InnerClass();


       inner.display();

    // EXAMPLE CODE FOR LIST
    // PUEDE TENER ELEMENTOS DUPLICADOS
    // MANTIENE EL ORDEN DE INSERCION
    // PERMITE ELEMENTOS NULOS
    // ES DINAMICA, NO HAY QUE DEFINIR TAMAÑO INICIAL
    // ES INDEXADA, PERMITE ACCESO POR INDICE

    List<String> list = new ArrayList<>();

    list.add("one");
    list.add("two");
    list.add("three");
    list.add("four");
    list.add("five");
    list.add(5, "Test Add by index");
    list.set(2, "Test Set by index");

    //        list.remove("two");
    //        list.remove(3);

    //        for (String s : list) {
    //            System.out.println(s);
    //        }

    //        Iterator<String> iterator = list.iterator();

    // CON ESTA FORMA PODEMOS ELIMINAR ELEMENTOS MIENTRAS ITERAMOS

    //        while (iterator.hasNext()) {
    //            String s = iterator.next();
    //            System.out.println(s);
    //        }

    System.out.println("List = " + list);

    // EXAMPLE CODE FOR SET
    // NO PUEDE TENER ELEMENTOS DUPLICADOS
    // NO MANTIENE EL ORDEN DE INSERCION
    // NO PERMITE ELEMENTOS NULOS
    // ES DINAMICA, NO HAY QUE DEFINIR TAMAÑO INICIAL
    // NO ES INDEXADA, NO PERMITE ACCESO POR INDICE

    Set<String> set = Set.of("one", "two", "three", "four", "five");

    // set.add("one"); //NO PERMITE ELEMENTOS DUPLICADOS
    // set.add(null); //NO PERMITE ELEMENTOS NULOS
    // set.remove("two"); //NO PERMITE MODIFICAR EL TAMAÑO
    // set.remove(3); //NO ES INDEXADA, NO PERMITE ACCESO POR INDICE
    System.out.println("Set = " + set);

    // EXAMPLE CODE FOR MAP
    // NO PUEDE TENER CLAVES DUPLICADAS
    // MANTIENE EL ORDEN DE INSERCION
    // PERMITE VALORES NULOS
    // ES DINAMICA, NO HAY QUE DEFINIR TAMAÑO INICIAL
    // NO ES INDEXADA, NO PERMITE ACCESO POR INDICE

    Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);

    // map.put("one", 1); //NO PERMITE CLAVES DUPLICADAS
    // map.put(null, 0); //NO PERMITE CLAVES NULAS
    // map.remove("two"); //NO PERMITE MODIFICAR EL TAMAÑO
    // map.remove(3); //NO ES INDEXADA, NO PERMITE ACCESO POR INDICE

    System.out.println("Map = " + map);

    //        for (Map.Entry<String, Integer> entry : map.entrySet()) {
    //            System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
    //        }
    // EXAMPLE CODE FOR SEQUENCED SET
    // NO PUEDE TENER ELEMENTOS DUPLICADOS
    // MANTIENE EL ORDEN DE INSERCION
    // PERMITE ELEMENTOS NULOS
    // ES DINAMICA, NO HAY QUE DEFINIR TAMAÑO INICIAL
    // NO ES INDEXADA, NO PERMITE ACCESO POR INDICE

    SequencedSet<String> sequencedSet = new java.util.LinkedHashSet<>();
    sequencedSet.add("one");
    sequencedSet.add("two");
    sequencedSet.add("three");
    sequencedSet.add("four");
    sequencedSet.add("five");

    System.out.println("SequencedSet = " + sequencedSet);

    // EXAMPLE CODE FOR STREAM
    //
    //        IntStream.generate(()-> (int)(Math.random()*100))
    //                .peek(i-> System.out.print("I = " + i))
    //                .limit(10)
    //                .forEach(val -> System.out.println(" VAl =  " + val));
  }
}
