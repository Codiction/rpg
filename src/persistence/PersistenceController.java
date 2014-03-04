package persistence;

import domain.Chest;
import domain.Monster;
import java.util.ArrayList;
import persistence.mapper.ChestMapper;
import persistence.mapper.MonsterMapper;

/**
 *
 * @author A1
 */
public class PersistenceController {

    private ChestMapper chestMapper;
    private MonsterMapper monsterMapper;
    private static PersistenceController INSTANCE;
    public static final String DB_LINK = "jdbc:mysql:bitcode.io/user=rpg&password=rpg";

    private PersistenceController() {
	// TODO: Get DB_LINK from config-file, so the database connection can be changed
	
	chestMapper = new ChestMapper(DB_LINK);
	monsterMapper = new MonsterMapper(DB_LINK);
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

    public ArrayList<Monster> loadMonsters() {
	return monsterMapper.loadMonsters();
    }

    public boolean saveChest(Chest chest) {
	return chestMapper.saveChest(chest);
    }

    public boolean saveMonster(Monster monster) {
	return monsterMapper.saveMonster(monster);
    }

    public boolean updateChest(Chest chest) {
	return chestMapper.updateChest(chest);
    }

    public boolean updateMonster(Monster monster) {
	return monsterMapper.updateMonster(monster);
    }

    public boolean deleteChest(Chest chest) {
	return chestMapper.deleteChest(chest);
    }

    public boolean deleteMonster(Monster monster) {
	return monsterMapper.deleteMonster(monster);
    }

    public boolean exists(Monster monster) {
	monsterMapper.openConnection();
	boolean exists = monsterMapper.exists(monster);
	monsterMapper.closeConnection();
	return exists;
    }

    public boolean exists(Chest chest) {
	chestMapper.openConnection();
	boolean exists = chestMapper.exists(chest);
	chestMapper.closeConnection();
	return exists;
    }

    public boolean link(Monster monster, Chest chest) {
	monster.getGuardedChests().add(chest);
	return monsterMapper.updateMonster(monster);
    }
}
