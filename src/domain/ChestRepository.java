/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.util.ArrayList;
import persistence.PersistenceController;

/**
 *
 * @author Jens
 */
public class ChestRepository {
    
    private Chest chest;
    private PersistenceController persistenceController;

    public ChestRepository() {
    }

    public ArrayList<Chest> getChest(){
        
        throw new UnsupportedOperationException();
    }
    
    public boolean linkChestToMonster(Chest chest, Monster monster){
        
        throw new UnsupportedOperationException();
    }
    public boolean saveChest(Chest chest){
        
        throw new UnsupportedOperationException();
    }
    
    public boolean deleteChest(Chest chest){
        
        throw new UnsupportedOperationException();
    }
    public boolean updateChest(Chest chest){
        
        throw new UnsupportedOperationException();
    }
    public boolean chestExists(Chest chest){
        
        throw new UnsupportedOperationException();
    }
}
