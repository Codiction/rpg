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
	    super.openConnection();
	    String queryInsertChest = "INSERT INTO treasure (null, ?)";
	    String queryLinkChestToMonster = "INSERT INTO guardedchest (?, ?)";
	    PreparedStatement insertChest = prepareStatement(queryInsertChest, Statement.RETURN_GENERATED_KEYS);
	    PreparedStatement linkChestToMonster = super.prepareStatement(queryLinkChestToMonster);
	    try {
		insertChest.setInt(1, chest.getGoldAmount());
		int id = insertChest.executeUpdate();
		linkChestToMonster.setInt(2, id);
		for (Monster m : chest.getGuardingMonsters()) {
		    linkChestToMonster.setInt(1, m.getMonsterId());
		    linkChestToMonster.executeUpdate();
		}

		return true;
	    } catch (SQLException ex) {
		return false;
	    } finally {
		super.closeConnection();
	    }
	}

	return false;
    }

    public boolean updateChest(Chest chest) {
	if (!exists(chest)) {
	    this.saveChest(chest);
	} else {
	    // Update Chest in treasure-table (UPDATE-statement)
	    // Delete all rows with corresponding id in guardedchest and insert
	    // monsters with corresponding id in guardedchest

	    String queryInsertTreasure = "UPDATE treasure SET goldAmount = ? WHERE treasureId = ?; DELETE FROM guardedchest WHERE idTreasure = ?;";
	    String queryLinkChestToMonster = "INSERT INTO guardedchest(?, ?)";
	    PreparedStatement update = super.prepareStatement(queryInsertTreasure);
	    try {
		update.setInt(1, chest.getGoldAmount());
		update.setInt(2, chest.getTreasureId());
		update.setInt(3, chest.getTreasureId());
		update.executeUpdate();

		PreparedStatement insert = super.prepareStatement(queryLinkChestToMonster);
		for (Monster m : chest.getGuardingMonsters()) {
		    insert.setInt(1, m.getMonsterId());
		    insert.setInt(2, chest.getTreasureId());
		    insert.executeUpdate();
		}

		return true;
	    } catch (SQLException ex) {
		return false;
	    }
	}

	return false;
    }

    public boolean deleteChest(Chest chest) {
	super.openConnection();
	if (exists(chest)) {
	    String queryDeleteChest = "DELETE FROM treasure WHERE treasureId = ?; DELETE FROM guardedchest WHERE idTreasure = ?;";
	    try {
		PreparedStatement statement = super.prepareStatement(queryDeleteChest);
		statement.executeUpdate();
		return true;
	    } catch (SQLException ex) {
		return false;
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
