/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.tp.entities;

import java.util.Calendar;

/**
 *
 * @author Patrice Torguet
 */
public class Position {
    private long date;
    private double somme;

    public Position(double somme) {
        this.somme = somme;
        this.setDate();
    }

    public long getDate() {
        return date;
    }

    private void setDate(long date) {
        this.date = date;
    }

    private void setDate() {
        this.date = Calendar.getInstance().getTimeInMillis();
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
        this.setDate();
    }

}
