/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Offre;
import java.util.List;

/**
 *
 * @author trabe
 */
public interface IoffreController {
     public void ajouterOffre(Offre f);
    public void supprimerOffre(int id_offre);
    public void modifierOffre(Offre f , int id_offre);
    public List<Offre> afficherOffre();
    public Offre rechProduit(int id_offre);
}
