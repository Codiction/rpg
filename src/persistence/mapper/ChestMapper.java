package persistence.mapper;

import domain.Chest;
import domain.Monster;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jens
 */
public class ChestMapper extends Mapper {
    
    MonsterMapper monsterMapper;

    /**
     * 
     * @param dbLink 
     */
    public ChestMapper(String dbLink) {
	super(dbLink);
	monsterMapper = new MonsterMapper(dbLink);
    }

    public ArrayList<Chest> loadChests() {
	String queryGetTreasures = "SELECT * FROM treasure";
	String queryGetMonstersLinkedToTreasure = "SELECT idMonster FROM guardedchest WHERE idTreasure = ?";

	ArrayList<Chest> chests = new ArrayList<>();
	super.openConnection();
	try {
	    ResultSet chestResult = super.prepareStatement(queryGetTreasures).executeQuery();
	    while (chestResult.next()) {
		int id = chestResult.getInt("treasureId");
		int goldAmount = chestResult.getInt("goldAmount");
		PreparedStatement monsters = super.prepareStatement(queryGetMonstersLinkedToTreasure);
		monsters.setInt(1, id);
		ArrayList<Monster> monsterList = new ArrayList<>();
		ResultSet monsterResult = monsters.executeQuery();
		while(monsterResult.next()){
		    monsterList.add(monsterMapper.loadMonster(monsterResult.getInt("idMonster")));
		}
		chests.add(new Chest(id, goldAmount, monsterList));
	    }
	} catch (SQLException ex) {
	    return null;
	} finally {
	    super.closeConnection();
	}

	return chests;
    }

    public boolean saveChest(Chest chest) {
	if(exists(chest)){
	    this.updateChest(chest);
	}else{
	    // TODO: Add code for INSERT-statement (don't forget guardedchest)
	}
	
	throw new UnsupportedOperationException();
    }

    public boolean updateChest(Chest chest) {
	if(!exists(chest)){
	    this.saveChest(chest);
	}else{
	    // TODO: Add code for UPDATE-statement (don't forget guardedchest)
	}
	
	throw new UnsupportedOperationException();
    }

    public boolean deleteChest(Chest chest) {
	if(!exists(chest)){
	    // TODO: Add code for DELETE-statement (don't forget guardedchest)
	}
	
	throw new UnsupportedOperationException();
    }

    public boolean exists(Chest chest) {
	// TODO: Add code for SELECT-statement based on id of Chest-class
	// and check if this result is empty
	
	throw new UnsupportedOperationException();
    }

    public Chest loadChest(int id){
	// TODO: Add code for SELECT-statement based on id
	
	throw new UnsupportedOperationException();
    }
}
