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

		while (monsterResult.next()) {
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
	if (exists(chest)) {
	    this.updateChest(chest);
	} else {
	    // TODO: Add code for INSERT-statement (don't forget guardedchest)
	}

	throw new UnsupportedOperationException();
    }

    public boolean updateChest(Chest chest) {
	if (!exists(chest)) {
	    this.saveChest(chest);
	} else {
	    // TODO: Add code for UPDATE-statement (don't forget guardedchest)
	}

	throw new UnsupportedOperationException();
    }

    public boolean deleteChest(Chest chest) {
	super.openConnection();
	if (exists(chest)) {
	    String queryDeleteChest = "DELETE FROM treasure WHERE treasureId = ?; DELETE FROM guardedchest WHERE idTreasure = ?;";
	    try{
		PreparedStatement statement = super.prepareStatement(queryDeleteChest);
		statement.executeUpdate();
		return true;
	    }catch(SQLException ex){
		return false;
	    }finally{
		super.closeConnection();
	    }
	}
	
	return false;
    }

    public boolean exists(Chest chest) {
	String queryGetChest = "SELECT * FROM treasure WHERE treasureId = ?";
	PreparedStatement statement = super.prepareStatement(queryGetChest);
	try {
	    statement.setInt(1, chest.getTreasureId());
	    return statement.executeQuery().next();
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	// Does not open or close connection! Because exists() is most of the
	// time called in another method of the Mapper-class, this means that it
	// would open a connection while it has already been opened by the
	// caller, it can also not be closed because then the caller-method
	// will throw an error because the connection has been closed.
	return false;
    }

    public Chest loadChest(int id) throws SQLException {
	String queryGetChest = "SELECT goldAmount FROM treasure WHERE treasureId = ?";
	String queryGetMonstersLinkedToTreasure = "SELECT idMonster FROM guardedchest WHERE idTreasure = ?";

	super.openConnection();
	PreparedStatement getChest = super.prepareStatement(queryGetChest);

	try {
	    getChest.setInt(1, id);
	    ResultSet chest = getChest.executeQuery();
	    if (chest.next()) {
		int goldAmount = chest.getInt("goldAmount");

		PreparedStatement monsters = super.prepareStatement(queryGetMonstersLinkedToTreasure);
		monsters.setInt(1, id);

		ArrayList<Monster> monsterList = new ArrayList<>();
		ResultSet monsterResult = monsters.executeQuery();

		while (monsterResult.next()) {
		    monsterList.add(monsterMapper.loadMonster(monsterResult.getInt("idMonster")));
		}
		
		return new Chest(id, goldAmount, monsterList);
		
	    } else {
		return null;
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}

	return null;
    }
}
