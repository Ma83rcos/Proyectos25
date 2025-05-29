package poo;

public class EspacioParqueo {
    private int numero;
    private boolean ocupado;
    private Auto auto; // relación con la clase Auto

    public EspacioParqueo(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.auto = null;
    }

    public boolean estacionarAuto(Auto auto) {
        if (!ocupado) {
            this.auto = auto;
            this.ocupado = true;
            return true;
        }
        return false;
    }

    public void liberarEspacio() {
        this.auto = null;
        this.ocupado = false;
    }

    public void mostrarEstado() {
        if (ocupado) {
            System.out.print("Espacio " + numero + " está ocupado por: ");
            auto.mostrarInfo();
        } else {
            System.out.println("Espacio " + numero + " está libre.");
        }
    }
}
