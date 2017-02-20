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
public class Errordni extends RuntimeException {
    public Errordni(String dni) {
        super("El Dni es incorrecto " + dni);
    }
}
