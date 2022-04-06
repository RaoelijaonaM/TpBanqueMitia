/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package mg.mitia.tpbanquemitia.ejb;

import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import mg.mitia.tpbanquemitia.entities.CompteBancaire;

/**
 *
 * @author Raoel
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3307,
        user = "root", // nom et
        password = "root", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

    @PersistenceContext
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery query = em.createQuery("SELECT c FROM CompteBancaire c", CompteBancaire.class);
        return query.getResultList();
    }
    public long nbComptes(){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM CompteBancaire c", Long.class);
        return query.getSingleResult();
    }
    public CompteBancaire update(CompteBancaire cb) {
       return em.merge(cb);
    }
    public CompteBancaire getCompteById(long idCompte){
       return em.find(CompteBancaire.class,idCompte);
    }
   public void transfertArgent(CompteBancaire destinataire, CompteBancaire destination, int montant){
       destinataire.retirer(montant);
       destination.deposer(montant);
       update(destinataire);
       update(destination);
       
   }
}
