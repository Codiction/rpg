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

    private ArrayList<Chest> chests;
    private PersistenceController persistenceController = PersistenceController.getInstance();
    private static ChestRepository instance;

    private ChestRepository() {
	chests = getChests();
    }

    public static ChestRepository getInstance() {
	if (instance == null) {
	    instance = new ChestRepository();
	}
	return instance;
    }

    public ArrayList<Chest> getChests() {
	return persistenceController.loadChests();
    }

    public boolean linkChestToMonster(Chest chest, Monster monster) {
	return persistenceController.link(monster, chest);
    }

    public boolean saveChest(Chest chest) {
	return persistenceController.saveChest(chest);
    }

    public boolean deleteChest(Chest chest) {
	return persistenceController.deleteChest(chest);
    }

    public boolean updateChest(Chest chest) {
	return persistenceController.updateChest(chest);
    }

    public boolean chestExists(Chest chest) {
	return persistenceController.exists(chest);
    }
}
