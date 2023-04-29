/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduitoumaima;

import Services.CategorieService;
import Services.ProduitService;
import entites.Categorie;
import entites.Produit;
import utils.MyConnection;

/**
 *
 * @author Imen
 */
public class GestionProduitOumaima {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyConnection conx=  MyConnection.getInstance();
      Categorie C=new Categorie("sondes");
      CategorieService ca =new CategorieService();
      ca.insert(C);
      ca.delete(69);
        Categorie C1=new Categorie(44,"sondes");
      ca.update(C1);
      ca.realAll().forEach(System.out::println);

              Produit p= new Produit("sondes",1,"gbdcjqz","hbcjsz",852,44);
              ProduitService ps= new ProduitService();
              ps.insert(p);
              ps.delete(8);
               ps.realAll().forEach(System.out::println);
                Produit p1= new Produit(40,"sondes",1,"gbdcjqz","hbcjsz",852,44);
               ps.update(p1);


    }

}
