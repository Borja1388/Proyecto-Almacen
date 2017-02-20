package Modelo;

public class Particular extends Cliente {

    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        
        String dniOK = this.comprobarNif(dni);
        this.dni = dniOK;

    }

    public String comprobarNif(String dni) {
        boolean respuesta = false;
        int numero = 0;
        int resto;
        String dnifinal = null;
        String letra = null, letrafinal;
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        if (dni.length() == 9) {
            numero = Integer.parseInt(dni.substring(0, dni.length() - 1));
            if (Character.isLetter(dni.charAt(8))) {
                letra = dni.substring(8);
            } else {
                throw new Errordni(dni);
            }
        } else if (dni.length() == 8) {
            numero = Integer.parseInt(dni.substring(0, dni.length()));
        } else {
            throw new Errordni(dni);
        }
        resto = numero % 23;
        char letrabuena = letras[resto];
        letrafinal = String.valueOf(letrabuena);
        dnifinal = numero + letrafinal;

        return dnifinal;
    }

    @Override
    public String imprimir() {
        String res = super.imprimir() + " DNI: " + this.dni;
        return res;
    }

}
