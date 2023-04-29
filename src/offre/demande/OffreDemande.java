/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande;

import entities.Demande;
import entities.Offre;
import java.sql.Date;
import services.DemandeService;
import services.OffreService;
import services.UserService;
import utils.connexionDB;

/**
 *
 * @author trabe
 */
public class OffreDemande {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Date datelimite = new Date (System.currentTimeMillis());
        //System.out.println(datelimite);
        
        //connexionDB db1 = connexionDB.getInstance();
        //OffreService f1 = new OffreService();
      //System.out.println(f1.getTitresEtDemandes());
        
        //Offre f = new Offre("xxx",datelimite,"fgefg","dfdzf");
        //f1.ajouterOffre(f);
        //System.out.println(f1.afficherOffre());
        //Offre f2 = new Offre(61,"xxxxxx",datelimite,"fgefg","dfdzf");
        //f1.modifieroffre(f2);
        //f1.supprimerOffre(61);
        DemandeService ds=new DemandeService();
        //Demande d =new Demande(datelimite,"bbbb","aaaa",500,68);
        //ds.ajouterDemande(d);
       // ds.modifieroffre(d1);
        System.out.println(ds.afficherDemande());
       //ds.supprimerDemande(6);
       //UserService us=new UserService();
       //System.out.println(us.afficherUser());
       
        
    }
    
}
