/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import entities.Participation;

/**
 *
 * @author ZC-Lenovo
 */
public interface ParticipationInterface {
    public void delete(int id);
    public List<Participation> fetchParticiation();
    public int Count(int id);
    
    
}
