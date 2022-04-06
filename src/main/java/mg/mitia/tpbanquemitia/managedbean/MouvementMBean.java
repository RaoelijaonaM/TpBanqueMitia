/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.mitia.tpbanquemitia.managedbean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import mg.mitia.tpbanquemitia.ejb.GestionnaireCompte;
import mg.mitia.tpbanquemitia.entities.CompteBancaire;

/**
 *
 * @author Raoel
 */
@Named(value = "mouvement")
@ViewScoped
public class MouvementMBean implements Serializable {

    private int idCompte;
    private CompteBancaire compteBancaire;
    private String typeMouvement;
    private int montant;
    @EJB
    GestionnaireCompte gestionnaireCompte;

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of MouvementMBean
     */
    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public MouvementMBean() {
    }

    public void loadCompte() {
        this.compteBancaire = gestionnaireCompte.getCompteById(idCompte);
    }

    public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
        UIInput composantTypeMouvement = (UIInput) composant.findComponent("typeMouvement");
        String valeurTypeMouvement = (String) composantTypeMouvement.getLocalValue();
        if (valeurTypeMouvement.equals("retrait")) {
            int retrait = (int) valeur;
            if (compteBancaire.getSolde() < retrait) {
                FacesMessage message
                        = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Ce montant est indisponible",
                                "Le retrait doit être inférieur au solde du compte");
                throw new ValidatorException(message);
            }
        }
    }
    public String enregistrerMouvement(){
        if(typeMouvement.equals("ajout")){
            gestionnaireCompte.ajoutArgent(compteBancaire, montant);
        }
        if(typeMouvement.equals("retrait")){
            gestionnaireCompte.retraitArgent(compteBancaire, montant);
        }
         return "listeComptes?faces-redirect=true";
    }
}
