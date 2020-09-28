/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.tp.expo_rest;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import miage.tp.entities.Position;
import miage.tp.services.BanqueBeanLocal;

/**
 * REST Web Service
 *
 * @author Patrice Torguet
 */
@Path("comptes")
@RequestScoped
public class ComptesResource {

    // Accès BackOffice
    BanqueBeanLocal banqueBean = lookupBanqueBeanLocal();
    

    @Context
    private UriInfo context;
    
    // Convertisseur JSON
    private Gson gson;

    /**
     * Creates a new instance of ComptesResource
     */
    public ComptesResource() {
        this.gson = new Gson();
        this.banqueBean = this.lookupBanqueBeanLocal();
    }

    /**
     * Pas d'export de la liste des comptes
     *
     * @return une réponse HTTP avec le code d'erreur 403
     */
    @GET
    public Response getJson() {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    /**
     * Creation d'un nouveau compte. Le solde est facultatif (défaut = 0.0). Pour appeler cette méthode on doit utiliser l'URL :
     * http://localhost:8080/BanqueREST/webresources/comptes/?solde=1000
     *
     * @param solde le solde du compte
     *
     * @return le compte en notation JSON de la forme {"idCompte":0,"pos":{"date":1475757207292,"somme":1000.0}}
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postJson(@QueryParam("solde") String solde) {
        if ((solde == null) || (solde.isEmpty())) {
            solde = "0.0";
        }
        return this.gson.toJson(this.banqueBean.ajouterCompte(Double.parseDouble(solde)));
    }

    /**
     * Renvoie la représentation JSON d'un compte Pour l'appeler on doit utiliser l'URL :
     * http://localhost:8080/BanqueREST/webresources/comptes/0
     *
     * @param idCompte id du compte
     *
     * @return le compte en notation JSON de la forme {"idCompte":0,"pos":{"date":1475757207292,"somme":1000.0}}
     */
    @GET
    @Path("{idCompte}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("idCompte") String idCompte) {
        return this.gson.toJson(this.banqueBean.getCompte(Integer.parseInt(idCompte)));
    }
    
    /**
     * Permet de créditer ou de débiter (en fonction du signe de la somme) un compte Pour l'appeler on doit utiliser une URL du type :
     * http://localhost:8080/BanqueREST/webresources/comptes/0/position?somme=100
     *
     * @param idCompte le compte
     * @param somme    la somme à créditer (si > 0) ou à débiter (si < 0)
     *
     * @return la nouvelle position en notation JSON du type : {"date":1475757403726,"somme":1100.0}
     */
    @PUT
    @Path("{idCompte}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(@PathParam("idCompte") String idCompte, @QueryParam("somme") String somme) {
        int idcpt = Integer.parseInt(idCompte);
        double som = Double.parseDouble(somme);
        Position pos;
        if (som > 0) {
            pos = this.banqueBean.crediter(idcpt, som);
        } else {
            pos = this.banqueBean.debiter(idcpt, -som);
        }
        return Response.ok(this.gson.toJson(pos)).build();
    }

    private BanqueBeanLocal lookupBanqueBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (BanqueBeanLocal) c.lookup("java:global/banque_rest-ear/banque_rest-ejb-1.0-SNAPSHOT/BanqueBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
