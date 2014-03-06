package persistence.mapper;

import domain.Chest;
import domain.Monster;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author A1
 */
public class ChestMapper extends Mapper {

    MonsterMapper monsterMapper;

    /**
     *
     * @param dbLink
     */
    public ChestMapper(String dbLink) {
	super(dbLink);
    }

    public void setMonsterMapper(MonsterMapper mapper) {
	this.monsterMapper = mapper;
    }

    public ArrayList<Chest> loadChests() {
	String queryGetTreasures = "SELECT * FROM treasure";
	String queryGetMonstersLinkedToTreasure = "SELECT monsterId FROM guardedchest WHERE treasureId = ?";

	ArrayList<Chest> chests = new ArrayList<>();
	super.openConnection();
	try {
	    PreparedStatement chestPs = super.prepareStatement(queryGetTreasures);
	    ResultSet chestResult = chestPs.executeQuery();
	    while (chestResult.next()) {
		int id = chestResult.getInt("treasureId");
		int goldAmount = chestResult.getInt("goldAmount");

		PreparedStatement monsters = super.prepareStatement(queryGetMonstersLinkedToTreasure);
		monsters.setInt(1, id);

		ArrayList<Monster> monsterList = new ArrayList<>();
		ResultSet monsterResult = monsters.executeQuery();

		while (monsterResult.next()) {
		    monsterList.add(monsterMapper.loadMonster(monsterResult.getInt("monsterId")));
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
	super.openConnection();
	if (exists(chest)) {
	    this.updateChest(chest);
	} else {
	    String queryInsertChest = "INSERT INTO treasure (goldAmount) VALUES (?)";
	    String queryLinkChestToMonster = "INSERT INTO guardedchest VALUES (?, ?)";
	    PreparedStatement insertChest = prepareStatement(queryInsertChest, Statement.RETURN_GENERATED_KEYS);
	    PreparedStatement linkChestToMonster = super.prepareStatement(queryLinkChestToMonster);
	    try {
		insertChest.setInt(1, chest.getGoldAmount());
		insertChest.executeUpdate();
		int id = 0;
		ResultSet keys = insertChest.getGeneratedKeys();
		if(keys.next()){
		    id = keys.getInt(1);
		}
		
		chest.setTreasureId(id);
		linkChestToMonster.setInt(2, id);
		for (Monster m : chest.getGuardingMonsters()) {
		    monsterMapper.saveMonster(m);
		    linkChestToMonster.setInt(1, m.getMonsterId());
		    linkChestToMonster.executeUpdate();
		}

		return true;
	    } catch (SQLException ex) {
		ex.printStackTrace();
	    } finally {
		super.closeConnection();
	    }
	}

	return false;
    }

    public boolean updateChest(Chest chest) {
	super.openConnection();
	if (!exists(chest)) {
	    this.saveChest(chest);
	} else {
	    // Update Chest in treasure-table (UPDATE-statement)
	    // Delete all rows with corresponding id in guardedchest and insert
	    // monsters with corresponding id in guardedchest

	    String queryUpdateTreasure = "UPDATE treasure SET goldAmount = ? WHERE treasureId = ?;";
	    String queryDeleteGuardedTreasure = "DELETE FROM guardedchest WHERE treasureId = ?;";
	    String queryLinkChestToMonster = "INSERT INTO guardedchest(?, ?)";
	    PreparedStatement update = super.prepareStatement(queryUpdateTreasure);
	    PreparedStatement delete = super.prepareStatement(queryDeleteGuardedTreasure);
	    try {
		update.setInt(1, chest.getGoldAmount());
		update.setInt(2, chest.getTreasureId());
		update.executeUpdate();
		
		delete.setInt(1, chest.getTreasureId());
		delete.executeUpdate();

		PreparedStatement insert = super.prepareStatement(queryLinkChestToMonster);
		for (Monster m : chest.getGuardingMonsters()) {
		    monsterMapper.saveMonster(m);
		    insert.setInt(1, m.getMonsterId());
		    insert.setInt(2, chest.getTreasureId());
		    insert.executeUpdate();
		}

		return true;
	    } catch (SQLException ex) {
	    }finally{
		super.closeConnection();
	    }
	}

	return false;
    }

    public boolean deleteChest(Chest chest) {
	super.openConnection();
	if (exists(chest)) {
	    String queryDeleteChest = "DELETE FROM treasure WHERE treasureId = ?";
	    try {
		PreparedStatement statement = super.prepareStatement(queryDeleteChest);
		statement.setInt(1, chest.getTreasureId());
		statement.executeUpdate();
		return true;
	    } catch (SQLException ex) {
	    } finally {
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

	super.openConnection();
	PreparedStatement getChest = super.prepareStatement(queryGetChest);

	try {
	    getChest.setInt(1, id);
	    ResultSet chest = getChest.executeQuery();
	    if (chest.next()) {
		int goldAmount = chest.getInt("goldAmount");
		
		return new Chest(id, goldAmount, null);

	    } else {
		return null;
	    }
	} catch (SQLException ex) {
	}

	return null;
    }
}
