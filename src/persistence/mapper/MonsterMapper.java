package persistence.mapper;

import domain.Chest;
import domain.Monster;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author A1
 */
public class MonsterMapper extends Mapper {

    ChestMapper chestMapper;

    private final String qryGetMonsters = "SELECT * FROM monster";
    private final String qryGetGuardedChestByMonster = "SELECT * FROM guardedchest WHERE monsterId = ?";
    private final String qryGetMonster = "SELECT * FROM monster where monsterId = ?";

    private final String qryDeleteMonster = "DELETE FROM monster WHERE monsterId = ?";

    public MonsterMapper(String dbLink) {
	super(dbLink);
    }

    public void setChestMapper(ChestMapper mapper) {
	this.chestMapper = mapper;
    }

    public ArrayList<Monster> loadMonsters() {
	ArrayList<Monster> loadedMonsters = new ArrayList<>();

	loadedMonsters.clear();
	openConnection();
	try {
	    ResultSet rs = prepareStatement(qryGetMonsters).executeQuery();
	    while (rs.next()) {
		int monsterId = rs.getInt("monsterId");

		PreparedStatement ps = prepareStatement(qryGetGuardedChestByMonster);
		ps.setInt(1, monsterId);
		ResultSet chestResults = ps.executeQuery();

		ArrayList<Chest> guardedChests = new ArrayList<>();

		while (chestResults.next()) {
		    Chest c = chestMapper.loadChest(chestResults.getInt("treasureId"));
		    guardedChests.add(c);
		}

		Monster m = new Monster(rs.getInt("monsterId"), rs.getString("name"), rs.getFloat("power"),
			rs.getFloat("defence"), rs.getFloat("speed"), rs.getInt("awareness"), rs.getString("avatarDir"), null);
		m.setGuardedChests(guardedChests);

		loadedMonsters.add(m);
	    }
	} catch (SQLException ex) {
	    return null;
	} finally {
	    closeConnection();
	}
	return loadedMonsters;
    }

    public boolean saveMonster(Monster monster) {
	super.openConnection();
	if(exists(monster)){
	    updateMonster(monster);
	}else{
	    // code voor INSERT
	}
	
	super.closeConnection();
	return false; // maak proper
    }

    public boolean updateMonster(Monster monster) {
	super.openConnection();
	if(!exists(monster)){
	    saveMonster(monster);
	}else{
	    // code voor UPDATE
	}
	
	super.closeConnection();
	return false; // maak proper
    }

    public boolean deleteMonster(Monster monster) {
	openConnection();
	if (exists(monster)) {
	    PreparedStatement ps = prepareStatement(qryDeleteMonster);
	    try {
		ps.setInt(1, monster.getMonsterId());
		ps.executeUpdate();
		return true;
	    } catch (SQLException ex) {
		ex.printStackTrace();
	    } finally {
		closeConnection();
	    }
	}
	return false;
    }

    public boolean exists(Monster monster) {
	try {
	    PreparedStatement ps = prepareStatement(qryGetMonster);
	    ps.setInt(1, monster.getMonsterId());
	    ResultSet rs = ps.executeQuery();

	    if (rs.next()) {
		return true;
	    }
	} catch (SQLException se) {
	    se.printStackTrace();
	}

	return false;
    }

    public Monster loadMonster(int id) {
	try {
	    openConnection();
	    PreparedStatement ps = prepareStatement(qryGetMonster);
	    ps.setInt(1, id);

	    ResultSet rs = ps.executeQuery();

	    Monster m = null;
	    if (rs.next()) {
		m = new Monster(rs.getInt("monsterId"), rs.getString("name"), rs.getFloat("power"),
			rs.getFloat("defence"), rs.getFloat("speed"), rs.getInt("awareness"), rs.getString("avatarDir"), null);
		
	    }
	    return m;
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    return null;
	}
    }
}
