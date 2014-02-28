package persistence;

import domain.Chest;
import domain.Monster;
import java.util.ArrayList;
import persistence.mapper.ChestMapper;
import persistence.mapper.MonsterMapper;

/**
 *
 * @author Jens
 */
public class PersistenceController {
    
    private ChestMapper chestMapper;
    private MonsterMapper monsterMapper;
    private static PersistenceController INSTANCE;
    
    
    private PersistenceController(){
        
    }
    public static PersistenceController getInstance(){
        
        throw new UnsupportedOperationException();
        
    }
    public ArrayList<Chest> loadChests(){
        
        throw new UnsupportedOperationException();
    }
    public ArrayList<Monster> loadMonsters(){
        
        throw new UnsupportedOperationException();
    }
    public boolean saveChest(Chest chest){
        
        throw new UnsupportedOperationException();
    }
    public boolean saveMonster(Monster monster){
        
        throw new UnsupportedOperationException();
    }
    public boolean updateChest(Chest chest){
        
        throw new UnsupportedOperationException();
    }
    public boolean updateMonster(Monster monster){
        
        throw new UnsupportedOperationException();
    
    }
    public boolean deleteChest(Chest chest){
        
        throw new UnsupportedOperationException();
    }
    public boolean deleteMonster(Monster monster){
        
        throw new UnsupportedOperationException();
    }
    public boolean exists(Monster monster){
        
         throw new UnsupportedOperationException();
    }
    public boolean exists(Chest chest){
         throw new UnsupportedOperationException();
    }
    public boolean link(Monster monster, Chest chest){
         throw new UnsupportedOperationException();
    }
}
