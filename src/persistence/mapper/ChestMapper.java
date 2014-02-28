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
         ArrayList<Chest> chests = new ArrayList<>();
	 
	 
	 
	 return chests;
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
