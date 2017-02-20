/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;



/**
 *
 * @author Borja
 */
public class FormatoFechaErroneo extends RuntimeException {
    public FormatoFechaErroneo(String fecha) {
        super("El formato de la fecha es incorrecto" + fecha);
    }
}
