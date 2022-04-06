/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.mitia.tpbanquemitia.managedbean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import mg.mitia.tpbanquemitia.ejb.GestionnaireCompte;
import mg.mitia.tpbanquemitia.entities.CompteBancaire;
import mg.mitia.tpbanquemitia.util.Util;

/**
 *
 * @author Raoel
 */
@Named(value = "updateCompteMBean")
@ViewScoped
public class UpdateCompteMBean implements Serializable {

    private int idCompte;
    private CompteBancaire compteBancaire;
    @EJB
    GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of UpdateCompteMBean
     */
    public UpdateCompteMBean() {
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public void loadCompte() {
        this.compteBancaire = gestionnaireCompte.getCompteById(idCompte);
    }
    public String modifier(){
        this.gestionnaireCompte.update(compteBancaire);
        Util.addFlashInfoMessage("Compte modifié");
        return "listeComptes?faces-redirect=true";
    }
}
