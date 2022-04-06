/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.mitia.tpbanquemitia.managedbean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import mg.mitia.tpbanquemitia.ejb.GestionnaireCompte;
import mg.mitia.tpbanquemitia.entities.CompteBancaire;
import mg.mitia.tpbanquemitia.util.Util;

/**
 *
 * @author Raoel
 */
@Named(value = "ajoutCompteMBean")
@RequestScoped
public class AjoutCompteMBean {
    private String nom;
    private int montant;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

  

    public AjoutCompteMBean() {
    }
    public String insererCompte(){
        if(montant < 0){
            Util.messageErreur("Le montant doit être positif", "erreur montant", "ajout:montant");
            return null;
        }
        gestionnaireCompte.creerCompte(new CompteBancaire(montant,nom));
        Util.addFlashInfoMessage("Bienvenue Mr/Mme "+nom+" votre compte a été créé avec un montant de "+montant);
        return "listeComptes?faces-redirect=true";
    }
    
}
