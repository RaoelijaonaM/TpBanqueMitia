/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.mitia.tpbanquemitia.managedbean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import mg.mitia.tpbanquemitia.ejb.GestionnaireCompte;
import mg.mitia.tpbanquemitia.entities.CompteBancaire;
import mg.mitia.tpbanquemitia.util.Util;

/**
 *
 * @author Raoel
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    public List<CompteBancaire> getAllComptes(){
        return gestionnaireCompte.getAllComptes();
    }
    public String supprimerCompte(CompteBancaire cb){
        gestionnaireCompte.deleteCompte(cb);
        Util.addFlashInfoMessage("Compte supprimé");
        return "listeComptes?faces-redirect=true";
    }
}
