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

    public ArrayList<Monster> loadMonsters() {
	return monsterMapper.loadMonster();
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
	return monsterMapper.exists(monster);
    }

    public boolean exists(Chest chest) {
	return chestMapper.exists(chest);
    }

    public boolean link(Monster monster, Chest chest) {
	monster.getGuardedChests().add(chest);
	return monsterMapper.updateMonster(monster);
    }
}