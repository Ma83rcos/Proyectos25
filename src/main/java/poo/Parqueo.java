package poo;

public class Parqueo {
    private EspacioParqueo[] espacios;


    public Parqueo(int cantidad) {
        espacios = new EspacioParqueo[cantidad];

        for (int i = 0; i < cantidad; i++) {
            espacios[i] = new EspacioParqueo(i + 1);
        }
    }

    public void mostrarEstadoParqueo() {
        for (EspacioParqueo espacio : espacios) {
            espacio.mostrarEstado();
        }
    }

    public boolean ingresarAuto(Auto auto) {
        for (EspacioParqueo espacio : espacios) {
            if (espacio.estacionarAuto(auto)) {
                System.out.println("Auto estacionado en espacio #" + espacio);
                return true;
            }
        }
        System.out.println("No hay espacios disponibles.");
        return false;
    }
}
