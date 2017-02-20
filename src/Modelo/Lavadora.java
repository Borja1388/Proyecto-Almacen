package Modelo;

public class Lavadora extends Electrodomestico {

    int revoluciones;
    double carga;

    public Lavadora() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {
        if (revoluciones >= 500 && carga <= 8.0) {
            precio = precioBase * 1.05;

        } else if (revoluciones >= 500) {
            precio = precioBase * 1.10;
        } else if (carga <= 8.0) {
            precio = precioBase / 1.15;
        } else {
            precio = precioBase;
        }

    }

    public int getRevoluciones() {
        return revoluciones;
    }

    public void setRevoluciones(int revoluciones) {
        this.revoluciones = revoluciones;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }

    @Override
    public String imprimirProducto() {
        String res = super.imprimirProducto() + "de revoluciones: " + this.revoluciones + "con carga: " + this.carga;
        return res;
    }

}
