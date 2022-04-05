/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package mg.mitia.tpbanquemitia.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import mg.mitia.tpbanquemitia.entities.CompteBancaire;

/**
 *
 * @author Raoel
 */
@Singleton
@Startup
public class Init {

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    @PostConstruct
    public void initCompte() {
        CompteBancaire cp1 = new CompteBancaire(150000, "John Lennon");
        CompteBancaire cp2 = new CompteBancaire(950000, "Paul McCartney");
        CompteBancaire cp3 = new CompteBancaire(20000, "Ringo Starr");
        CompteBancaire cp4 = new CompteBancaire(100000, "Georges Harrisson");
        if (gestionnaireCompte.nbComptes() != 0) {
            return;
        }
        gestionnaireCompte.creerCompte(cp1);
        gestionnaireCompte.creerCompte(cp2);
        gestionnaireCompte.creerCompte(cp3);
        gestionnaireCompte.creerCompte(cp4);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
