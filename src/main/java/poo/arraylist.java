package poo;


import java.util.ArrayList;
import java.util.List;

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

        //List<>
        List<Alumnos> nombreDeAlumnos2 = new ArrayList<>();
        nombreDeAlumnos2.add(new Alumnos("Carlos","Nina",26));
        nombreDeAlumnos2.add(new Alumnos("Marcos","Nina",26));

        ArrayList<Alumnos> listaDeAlumnos1 = new ArrayList<>();
        listaDeAlumnos1.add(new Alumnos("Carlos","Nina",26)); //0
        listaDeAlumnos1.add(new Alumnos("Ana","Ascarturisa",26)); //1
        listaDeAlumnos1.add(new Alumnos("Sebastian","Ropelio",26)); //2
        listaDeAlumnos1.add(new Alumnos("Marcos","Moya",26)); //3


        System.out.println("Alumnos actuales:");
        for (Alumnos alumnoAuxiliar : listaDeAlumnos1) {
        // por la cantidad de objetos
            alumnoAuxiliar.mostrarInformacionAlumno();
        }
        System.out.println("Operacion GET:");

        listaDeAlumnos1.get(0).mostrarInformacionAlumno();
        listaDeAlumnos1.get(2).mostrarInformacionAlumno();
        listaDeAlumnos1.get(1).mostrarInformacionAlumno();

        listaDeAlumnos1.equals(nombreDeAlumnos2);
        System.out.println("Alumnos actuales despues de ser eliminados:");
        for (Alumnos alumnoAuxiliar : listaDeAlumnos1) {
            // por la cantidad de objetos
            alumnoAuxiliar.mostrarInformacionAlumno();
        }


    }
}