/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.tp.entities;

/**
 *
 * @author Patrice Torguet
 */
public class Compte {
    private int idCompte;
    private Position pos;

    public Compte(int idCompte, double somme_init) {
        this.idCompte = idCompte;
        this.pos = new Position(somme_init);
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void crediter(double somme) {
        this.pos.setSomme(somme + this.pos.getSomme());
    }

    public void debiter(double somme) {
        this.pos.setSomme(this.pos.getSomme() - somme);
    }
}
