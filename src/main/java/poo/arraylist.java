package poo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class arraylist {


    public static void main(String[] args) {
//        ArrayList<String> lista = new ArrayList<>();
//        lista.add("Carlos");
//        // ["Carlos"]
//        lista.add("Marcos");
//        lista.add("Pepita");
//        lista.add("Luciana");
//
//        System.out.println(lista);
//
//        //List<>
//        List<Alumnos> nombreDeAlumnos2 = new ArrayList<>();
//        nombreDeAlumnos2.add(new Alumnos("Carlos","Nina",26));
//        nombreDeAlumnos2.add(new Alumnos("Marcos","Nina",26));
//
//
//
//
//        System.out.println("Alumnos actuales:");
//        for (Alumnos alumnoAuxiliar : listaDeAlumnos1) {
//        // por la cantidad de objetos
//            alumnoAuxiliar.mostrarInformacionAlumno();
//        }
//        System.out.println("Operacion GET:");
//
//        listaDeAlumnos1.get(0).mostrarInformacionAlumno();
//        listaDeAlumnos1.get(2).mostrarInformacionAlumno();
//        listaDeAlumnos1.get(1).mostrarInformacionAlumno();
//
//        listaDeAlumnos1.equals(nombreDeAlumnos2);
//        System.out.println("Alumnos actuales despues de ser eliminados:");
//        for (Alumnos alumnoAuxiliar : listaDeAlumnos1) {
//            // por la cantidad de objetos
//            alumnoAuxiliar.mostrarInformacionAlumno();
//        }
//
//
//        ArrayList<Alumnos> lista = new ArrayList<>();
//
//        lista.forEach(elemento -> {
//            // acción con cada elemento
//        });
//
//
//        List<String> nombres = List.of("Ana", "Carlos", "Luis");
//
//        nombres.forEach(nombre -> System.out.println(nombre));

// ejemplos
//
        ArrayList<Alumnos> listaDeAlumnos1 = new ArrayList<>();
        listaDeAlumnos1.add(new Alumnos("Carlos","Nina",26)); //0
        listaDeAlumnos1.add(new Alumnos("Ana","kolio",26)); //1
        listaDeAlumnos1.add(new Alumnos("Sebastian","Ropelio",26)); //2
        listaDeAlumnos1.add(new Alumnos("Marcos","Moya",26)); //3
//
//
// Listado funcional de todos los alumnos con forEach
        System.out.println("Listado funcional:");
        listaDeAlumnos1.stream() // Creamos un flujo (stream) desde la lista
                .forEach(alumno -> alumno.mostrarInformacionAlumno()); // Recorremos y mostramos cada alumno con una lambda

// Map: obtener solo los nombres de los alumnos
        System.out.println("\n======================================================");
        System.out.println("\nNombres de alumnos funcion MAP:");
        List<String> nombres = listaDeAlumnos1.stream()
                //.map(Alumnos::getNombre) // Transformamos cada alumno a su nombre (String)
                .map(alumno -> alumno.getNombre())
                .collect(Collectors.toList()); // Lo recolectamos en una nueva lista de Strings
        System.out.println(nombres);

//// Mostrar cada nombre
//        nombres.forEach(System.out::println); // Otra forma de lambda: método de referencia
//
//// Filter: filtrar alumnos con nombre 'Carlos'
//        System.out.println("\nFiltrar alumnos llamados 'Carlos':");
//        List<Alumnos> filtrados = listaDeAlumnos1.stream()
//                .filter(alumno -> alumno.getNombre().equalsIgnoreCase("Carlos")) // Condición: nombre igual a 'Carlos'
//                .collect(Collectors.toList()); // Recolectamos los que cumplan la condición
//
//// Mostrar alumnos filtrados
//        filtrados.forEach(Alumnos::mostrarInformacionAlumno);
//
//
//        // MAP
//
////        put(K, V)	Agrega o reemplaza un valor con la clave dada
////        get(K)	Recupera el valor asociado a una clave
////        containsKey(K)	Verifica si existe una clave
////        remove(K)	Elimina una entrada
////        keySet() / values()	Accede solo a claves o valores
////        Claves únicas	No puede haber claves duplicadas (sobrescriben valor)
//
//        Map<String, Integer> edades = new HashMap<>();
//        edades.put("Carlos", 26);
//        edades.put("Ana", 22);
//
//        System.out.println(edades.get("Carlos")); // 26
//
//
//
//        Map<String, Alumnos> mapaAlumnos = new HashMap<>();
//        mapaAlumnos.put("Carlos", new Alumnos("Carlos", "Nina", 26));
//
//        Alumnos alumno = mapaAlumnos.get("Carlos");
//        alumno.mostrarInformacionAlumno();
//
//
//// Collect to Map: convertir lista en mapa clave-valor (nombre → alumno)
//        System.out.println("\nMap de alumnos por nombre:");
//        Map<String, Alumnos> mapa = listaDeAlumnos1.stream()
//                .collect(Collectors.toMap(
//                        Alumnos::getNombre, // Clave del mapa: nombre
//                        alumno -> alumno,   // Valor del mapa: el objeto alumno
//                        (a, b) -> a         // En caso de nombres duplicados, dejamos el primero
//                ));
//
//// Acceso rápido desde el Map usando el nombre como clave
//        System.out.println("Acceder a 'Carlos':");
//        mapa.get("Carlos").mostrarInformacionAlumno();

























    }
}