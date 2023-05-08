/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import entities.User;

/**
 *
 * @author habac
 */
public interface UserService {
    public void ajouter(User u);
    public void modifier(User u);
    void supprimer(User p);
    List< User> afficher();
    
}
