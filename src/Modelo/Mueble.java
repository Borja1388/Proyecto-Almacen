package Modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Mueble extends Producto {

    public enum Madera {

        PINO, ROBLE, HAYA
    };

    private LocalDate anyoFab;
    private Madera tipoMadera;
    private String estilo;

    public Mueble() {
        super();
    }

    @Override
    public String imprimirProducto() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd-MMMM-yy");
        String fecha=sdf.format(anyoFab);
        String res = super.imprimirProducto() + "el año de fabricación: " + fecha + " el tipo de madera: " + this.tipoMadera + "el estilo: " + getEstilo();
        return res;

    }

    @Override
    public void setPrecio(double precioBase) {
        if (tipoMadera == Madera.PINO) {
            precio = precioBase / 1.10;
        } else if (tipoMadera == Madera.ROBLE) {
            precio = precioBase * 1.10;
        } else if (tipoMadera == Madera.HAYA) {
            precio = precioBase;
        }
    }

    public String getAnyoFab() {
        String finalanyo;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MMMM-yy",Locale.ENGLISH);
        finalanyo=anyoFab.format(formateador);
        return finalanyo;
    }

    public void setAnyoFab(LocalDate anyoFab) {
        this.anyoFab = anyoFab;
    }

    public Madera getTipoMadera() {
        return tipoMadera;
    }

    public void setTipoMadera(Madera madera) {
        this.tipoMadera = madera;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

}
