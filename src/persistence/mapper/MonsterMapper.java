package persistence.mapper;

import domain.Monster;
import java.util.ArrayList;

/**
 *
 * @author Jens
 */
public class MonsterMapper extends Mapper {
    
    ChestMapper chestMapper;

    public MonsterMapper(String dbLink) {
        super(dbLink);
	chestMapper = new ChestMapper(dbLink);
    }

    public ArrayList<Monster> loadMonster() {
        throw new UnsupportedOperationException();
    }

    public boolean saveMonster(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public boolean updateMonster(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public boolean deleteMonster(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public boolean exists(Monster monster) {
        throw new UnsupportedOperationException();
    }
    
    public Monster loadMonster(int id){
	throw new UnsupportedOperationException();
    }
}
