/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.mitia.tpbanquemitia.managedbean;

import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import mg.mitia.tpbanquemitia.ejb.GestionnaireCompte;
import mg.mitia.tpbanquemitia.entities.CompteBancaire;
import mg.mitia.tpbanquemitia.util.Util;

/**
 *
 * @author Raoel
 */
@Named(value = "transfertMBean")
@RequestScoped
public class TransfertMBean implements Serializable {

    /**
     * Creates a new instance of TransfertMBean
     */
    private int idSource;
    private int idDestination;
    private int montant;

    @EJB
    GestionnaireCompte gestionnaireCompte;

    public int getIdSource() {
        return idSource;
    }

    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public TransfertMBean() {
    }

    public String transfert() {
        CompteBancaire cp1 = gestionnaireCompte.getCompteById(idSource);
        CompteBancaire cp2 = gestionnaireCompte.getCompteById(idDestination);
        Boolean erreur = false;
        if (cp1 == null) {
            Util.messageErreur("Le compte source n'existe pas", "Vous avez inscrit un id de compte qui n'existe pas", "transfert:source");
            erreur = true;

        } else {
            if ((cp1.getSolde() < montant)) {
                Util.messageErreur("Votre montant est insuffisant pour effectuer cette transaction", "montant insuffisant", "transfert:montant");
                erreur = true;
            }
        }
        if (cp2 == null) {
            Util.messageErreur("Le compte de destination n'existe pas", "Vous avez inscrit un id de compte qui n'existe pas", "transfert:destination");
            erreur = true;

        }
        if (erreur) {
            return null;
        }
        gestionnaireCompte.transfertArgent(cp1, cp2, montant);
        Util.addFlashInfoMessage("Mr/Mme "+cp1.getNom()+" a transféré un montant de "+montant+" à Mr/Mme "+cp2.getNom());
        return "listeComptes?faces-redirect=true";
    }

}
