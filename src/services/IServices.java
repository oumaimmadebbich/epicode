/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entites.Categorie;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Imen
 */
public interface IServices <T>{
      void insert(T t);
    void delete(int id);
    void update(T t);
    List <T>realAll();
    T readById(int id);
}
