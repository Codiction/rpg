/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence.mapper;

import domain.Chest;
import java.util.ArrayList;

/**
 *
 * @author Jens
 */
public class ChestMapper extends Mapper{
    
    public ChestMapper(String dbLink){
        super(dbLink);
    }
    public ArrayList<Chest> loadChests(){
         throw new UnsupportedOperationException();
         
    }
    public boolean saveChest(Chest chest){
         throw new UnsupportedOperationException();
    }
    public boolean updateChest(Chest original, Chest updated){
         throw new UnsupportedOperationException();
         
    }
    public boolean deleteChest(Chest chest){
         throw new UnsupportedOperationException();
    }
    public boolean exists(Chest chest){
         throw new UnsupportedOperationException();
    }
    
}
