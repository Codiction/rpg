package persistence;

import domain.Chest;
import java.util.ArrayList;
import persistence.mappers.ChestMapper;
import persistence.mappers.MonsterMapper;

/**
 *
 * @author A1
 */
public class PersistenceController {

    private static PersistenceController INSTANCE = null;
    private static final String DB_LINK = "jdbc:mysql://localhost:3306/rpg?user=rpg&password=rpg";
    
    private ChestMapper chestMapper = new ChestMapper(DB_LINK);
    private MonsterMapper monsterMapper = new MonsterMapper(DB_LINK);
    

    private PersistenceController() {
        
    }

    public static PersistenceController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersistenceController();
        }
        return INSTANCE;
    }
    
    public ArrayList<Chest> loadChests() {
        return chestMapper.loadChests();
    }
}
