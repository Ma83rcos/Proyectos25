package poo;

public class Auto {

    private String placa;
    private String marca;
    private String color;

    public String dato_publico;

    protected String dato_protegido;

    public Auto(String placa, String marca, String color) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
    }

    public Auto(String placa, String marca) {
        this.placa = placa;
        this.marca = marca;
        this.color = "Añadir color urgentemente";
    }

    public Auto() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {

        // placa no sea repetiva
        // placa no tenga 0 inicial
        // placa no seamas de 5 digitos
        // matricula ...
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDato_publico() {
        return dato_publico;
    }

    public void setDato_publico(String dato_publico) {
        this.dato_publico = dato_publico;
    }

    public String getDato_protegido() {
        return dato_protegido;
    }

    public void setDato_protegido(String dato_protegido) {
        this.dato_protegido = dato_protegido;
    }

    public void mostrarInfo() {
        System.out.println("Placa: " + placa + ", Marca: " + marca + ", Color: " + color);
    }
}

