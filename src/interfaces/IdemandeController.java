/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Demande;
import java.util.List;

/**
 *
 * @author trabe
 */
public interface IdemandeController {
         public void ajouterOffre(Demande d);
    public void supprimerOffre(int id_demande);
    public void modifierOffre(Demande d , int id_demande);
    public List<Demande> afficherOffre();
    public Demande rechProduit(int id_demande);
}
