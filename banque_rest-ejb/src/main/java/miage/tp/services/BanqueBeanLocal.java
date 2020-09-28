/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.tp.services;

import javax.ejb.Local;
import miage.tp.entities.Compte;
import miage.tp.entities.Position;

/**
 *
 * @author Patrice Torguet
 */
@Local
public interface BanqueBeanLocal {
    /**
     * ajoute un nouveau compte
     *
     * @param somme_init somme initiale du compte
     *
     * @return
     */
    public Compte ajouterCompte(double somme_init);

    /**
     * récupère la position d'un compte
     *
     * @param idCompte l'id du compte
     *
     * @return la position du compte
     */
    public Position getPosition(int idCompte);

    /**
     * Retourne un compte
     *
     * @param idCompte l'id du compte
     *
     * @return le compte correspondant
     */
    public Compte getCompte(int idCompte);

    /**
     * Crédite un compte
     *
     * @param idCompte id du compte
     * @param somme    somme à ajouter
     *
     * @return la nouvelle position du compte
     */
    public Position crediter(int idCompte, double somme);

    /**
     * Débite un compte
     *
     * @param idCompte id du compte
     * @param somme    somme à ajouter
     *
     * @return la nouvelle position du compte
     */
    public Position debiter(int idCompte, double somme);
 
}
