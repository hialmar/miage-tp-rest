/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.tp.services;

import java.util.HashMap;
import javax.ejb.Singleton;
import miage.tp.entities.Compte;
import miage.tp.entities.Position;

/**
 *
 * @author Patrice Torguet
 */
@Singleton
public class BanqueBean implements BanqueBeanLocal {

    private HashMap<Integer, Compte> lescomptes;
    private int lastid;

    public BanqueBean() {
        this.lescomptes = new HashMap<>();
        this.lastid = 0;
    }

    /**
     * ajoute un nouveau compte
     *
     * @param somme_init somme initiale du compte
     *
     * @return
     */
    @Override
    public Compte ajouterCompte(double somme_init) {
        Compte c = new Compte(lastid, somme_init);
        this.lescomptes.put(lastid, c);
        lastid++;
        return c;
    }

    /**
     * récupère la position d'un compte
     *
     * @param idCompte l'id du compte
     *
     * @return la position du compte
     */
    @Override
    public Position getPosition(int idCompte) {
        return this.lescomptes.get(idCompte).getPos();
    }

    /**
     * Crédite un compte
     *
     * @param idCompte id du compte
     * @param somme    somme à ajouter
     *
     * @return la nouvelle position du compte
     */
    @Override
    public Position crediter(int idCompte, double somme) {
        this.lescomptes.get(idCompte).crediter(somme);
        return this.lescomptes.get(idCompte).getPos();
    }

    /**
     * Débite un compte
     *
     * @param idCompte id du compte
     * @param somme    somme à ajouter
     *
     * @return la nouvelle position du compte
     */
    @Override
    public Position debiter(int idCompte, double somme) {
        this.lescomptes.get(idCompte).debiter(somme);
        return this.lescomptes.get(idCompte).getPos();
    }

    /**
     * Retourne un compte
     *
     * @param idCompte l'id du compte
     *
     * @return le compte correspondant
     */
    @Override
    public Compte getCompte(int idCompte) {
        return this.lescomptes.get(idCompte);
    }
}
