package poo;

public class Principal {
    public static void main(String[] args) {
        Parqueo parqueoSanDiego = new Parqueo(3);
        // Creamos un parqueo con 3 espacios

        // Clase - nombre variable
        Auto autoCarlos = new Auto("123ABC", "Toyota", "Rojo");
        Auto autoMarcos = new Auto("456DEF", "Honda", "Azul");

        parqueoSanDiego.mostrarEstadoParqueo();

        parqueoSanDiego.ingresarAuto(autoCarlos);
        parqueoSanDiego.mostrarEstadoParqueo();

        parqueoSanDiego.ingresarAuto(autoMarcos);
        parqueoSanDiego.mostrarEstadoParqueo();

        // Podemos liberar un espacio, por ejemplo el primero:
        //parqueo.ingresarAuto(new Auto("789XYZ", "Nissan", "Negro"));
    }
}
