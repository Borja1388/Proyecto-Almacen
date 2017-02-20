package Modelo;

public class Televisor extends Electrodomestico {

    protected double pulgadas;
    protected tele tipo;

    public enum tele {

        plasma, led, lcd, oled
    };

    public Televisor() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {
        if (tipo == tele.plasma && pulgadas > 40) {
            precio = precioBase;
        } else if (tipo == tele.plasma) {
            precio = precioBase / 1.10;
        } else if (pulgadas > 40) {
            precio = precioBase * 1.10;
        } else {
            precio = precioBase;
        }

    }

    @Override
    public String imprimirProducto() {
        String res = super.imprimirProducto() + "tipo de TV: " + this.tipo + "con " + this.pulgadas + " pulgadas";
        return res;

    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public tele getTipo() {
        return tipo;
    }

    public void setTipo(tele tipo) {
        this.tipo = tipo;
    }

}
