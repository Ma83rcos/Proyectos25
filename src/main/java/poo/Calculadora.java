package poo;

public class Calculadora {


    // encapsulamiento == public
    // externa o interna == static
    // tipo de dato == int
    // nombre de la funcion == sumar
    // parametros (tipo_de_dato parametro)

    public static int sumar(int numero1, int numero2) {
        return numero1 + numero2;
    }


    public static void main(String[] args) {
        int numero1 = 10;
        int numero2 = 20;

        System.out.println(sumar(numero1, numero2));


    }



}
