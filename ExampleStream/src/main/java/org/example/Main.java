package org.example;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import models.Drink;
import models.Food;
import models.Product;
import models.TextFilter;
import org.example.class_anidadas.OuterClass;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {

    // EJEMPLO DE USO DE COLLECTORS.MAPPING

    List<Product> listProducts =
        new ArrayList<>(
            List.of(
                new Product("Monitor", 300.00),
                new Drink("Water", 1200.00),
                new Drink("Pepsi", 1200.00),
                new Food("Pollo", 400.00),
                new Product("Smartphone", 800.00)));

    String productNames =
        listProducts.stream()
            .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(", ")));

    System.out.println(productNames);

    var drinks = listProducts.stream().filter(c -> c instanceof Drink).collect(Collectors.toList());

    System.out.println(drinks);

    //EJEMPLO DE USO DE COLLECTORS PARTITIONINGBY

      var partitionedGetDrink = listProducts.stream()
              .collect(Collectors.partitioningBy(p -> p instanceof Drink));

        System.out.println(partitionedGetDrink);

    // EJEMPLO DE USO DE JOINING

    List<String> words = Arrays.asList("Java", "is", "fun");

    String sentence = words.stream().collect(Collectors.joining(" "));

    System.out.println(sentence);

    // EJEMPLO DE USO DE COLLECTORS
    List<String> listNames =
        new ArrayList<>(List.of("Java", "Python", "JavaScript", "C#", "PHP", "Ruby", "PHP"));

    // EJEMPLO DE COMO AGRUPAR ELEMENTOS DE UNA LISTA USANDO COLLECTORS
    // AGRUPA LOS ELEMENTOS DE LA LISTA SEGUN EL PRIMER CARACTER DE CADA ELEMENTO
    // EL RESULTADO ES UN MAPA DONDE LA CLAVE ES EL PRIMER CARACTER Y EL VALOR ES UNA LISTA DE
    // ELEMENTOS QUE COMPARTEN ESE PRIMER CARACTER

    var group = listNames.stream().collect(Collectors.groupingBy(c -> c.charAt(0)));

    System.out.println(group);

    // EJEMPLO DE USO DE FLATMAP
    List<List<Integer>> listOfLists =
        Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5), Arrays.asList(7, 8, 9, 10));

    // FLATMAP ES UN METODO DE STREAM QUE PERMITE APLANAR UNA LISTA DE LISTAS EN UNA SOLA LISTA
    // ES DECIR, CONVIERTE UNA LISTA DE LISTAS EN UNA LIST

    List<Integer> flattenedList =
        listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());

    System.out.println(flattenedList);

    // EJEMPLO DE REFERENCIA A METODO
    // UNA REFERENCIA A METODO ES UNA FORMA DE REFERIRSE A UN METODO SIN EJECUTARLO
    // SE PUEDE USAR PARA PASAR UN METODO COMO PARAMETRO
    // SE PUEDE USAR PARA REEMPLAZAR UNA EXPRESION LAMBDA CUANDO LA EXPRESION LAMBDA SOLO LLAMA A
    // UN METODO
    // SE PUEDE USAR PARA MEJORAR LA LEGIBILIDAD DEL CODIGO
    // SE PUEDE USAR PARA REDUCIR EL CODIGO BOILERPLATE

    List<String> lenguages =
        new ArrayList<>(List.of("Java", "Python", "JavaScript", "C#", "PHP", "Ruby", "PHP"));

    // PROBANDO EL METODO CON UNA REFERENCIA A METODO ESTATICO
    lenguages.removeIf(TextFilter::remove);

    System.out.println(lenguages);

    // PROBANDO EL METODO CON UNA REFERENCIA A METODO DE INSTANCIA DE UN OBJETO PARTICULAR
    TextFilter textFilter = new TextFilter();

    lenguages.sort(textFilter::compare);

    System.out.println(lenguages);

    // PROBANDO EL METODO CON UNA REFERENCIA A METODO DE INSTANCIA DE UN OBJETO CUALQUIERA
    // EN ESTE CASO, EL OBJETO ES CUALQUIER STRING DE LA LISTA
    // Y EL METODO ES compareToIgnoreCase
    // QUE COMPARA DOS STRINGS IGNORANDO MAYUSCULAS Y MINUSCULAS
    Collections.sort(lenguages, String::compareToIgnoreCase);

    // EJEMPLO DE REFERENCIA A CONSTRUCTOR
    // UNA REFERENCIA A CONSTRUCTOR ES UNA FORMA DE REFERIRSE A UN CONSTRUCTOR SIN EJECUTARLO
    // SE PUEDE USAR PARA PASAR UN CONSTRUCTOR COMO PARAMETRO
    // SE PUEDE USAR PARA REEMPLAZAR UNA EXPRESION LAMBDA CUANDO LA EXPRESION LAMBDA SOLO LLAMA A
    // UN CONSTRUCTOR
    // SE PUEDE USAR PARA MEJORAR LA LEGIBILIDAD DEL CODIGO
    // SE PUEDE USAR PARA REDUCIR EL CODIGO BOILERPLATE
    System.out.println();

    var setLenguages =
        lenguages.stream().map(String::toUpperCase).map(String::new).collect(Collectors.toSet());

    System.out.println(setLenguages);

    System.out.println();

    // EJEMPLO DE EXPRESIONES LAMBDA
    // UNA EXPRESION LAMBDA ES UNA FUNCION ANONIMA QUE SE PUEDE USAR PARA IMPLEMENTAR UNA INTERFAZ
    // FUNCIONAL
    // UNA INTERFAZ FUNCIONAL ES UNA INTERFAZ QUE TIENE UN SOLO METODO ABSTRACTO
    // LAS EXPRESIONES LAMBDA SE USAN PARA PASAR COMPORTAMIENTO COMO PARAMETRO

    List<Product> products =
        new ArrayList<>(
            List.of(
                new Product("Monitor", 300.00),
                new Product("Laptop", 1200.00),
                new Product("Tablet", 400.00),
                new Product("Smartphone", 800.00)));

    System.out.println("Products" + products);

    Collections.sort(products, (p1, p2) -> p1.getName().compareTo(p2.getName()));

    // LAS {} SON OPCIONALES SI SOLO HAY UNA LINEA DE CODIGO
    // SI HAY MAS DE UNA LINEA DE CODIGO, HAY QUE USAR LAS {}
    // SI SE USAN LAS {}, HAY QUE USAR EL RETURN SI EL METODO DEVUELVE UN VALOR

    Collections.sort(
        products,
        (p1, p2) -> {
          return p1.getPrice().compareTo(p2.getPrice());
        });

    System.out.println("Ordenada por nombre");

    System.out.println("Products" + products);

    System.out.println("Aplicando filtros");

    // EJEMPLO DE USO DE PREDICADO
    Predicate<Product> productsFiltered = (p) -> p.getPrice() > 500;

    products.stream()
        .filter(productsFiltered.or(s -> s.getName().contains("M")))
        .forEach(System.out::println);

    // ORDENAR POR PRECIO DE FORMA ASCENDENTE

    // EJEMPLO DE CLASE ANIDADA
    // PARA CREAR UNA INSTANCIA DE LA CLASE INTERNA, PRIMERO HAY QUE CREAR UNA INSTANCIA DE LA CLASE
    // EXTERNA
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
