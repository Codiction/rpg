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
public class MonsterRepository {
    
    private Monster monster;
    private PersistenceController persistenceController;

    public MonsterRepository() {
    }
    
    

    public ArrayList<Monster> getMonster() {

        throw new UnsupportedOperationException();
    }

    public boolean linkMonster(Monster monster, Chest chest) {

        throw new UnsupportedOperationException();
    }

    public boolean saveMonster(Monster monster) {

        throw new UnsupportedOperationException();
    }
    
    public boolean deleteMonster(Monster monster){
        
        throw new UnsupportedOperationException();
    }
    
    public boolean updateMonster(Monster monster){
        
        throw new UnsupportedOperationException();
    }
    
    public boolean monsterExists(Monster moster){
        
        throw new UnsupportedOperationException();
    }

}