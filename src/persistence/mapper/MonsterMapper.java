package persistence.mapper;

import domain.Chest;
import domain.Monster;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A1
 */
public class MonsterMapper extends Mapper {

    ChestMapper chestMapper;

    private final String qryGetMonsters = "SELECT * FROM monster";
    private final String qryGetGuardedChestByMonster = "SELECT treasureId FROM guardedchest WHERE monsterId = ?";
    private final String qryGetMonster = "SELECT * FROM monster where monsterId = ?";

    private final String qryDeleteMonster = "DELETE FROM monster WHERE monsterId = ?; DELETE FROM guardedchest WHERE monsterId = ?;";

    private ArrayList<Monster> loadedMonsters = new ArrayList<>();

    public MonsterMapper(String dbLink) {
        super(dbLink);
        chestMapper = new ChestMapper(dbLink);
    }

    public ArrayList<Monster> loadMonsters() {
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
                        rs.getFloat("defence"), rs.getFloat("speed"), rs.getInt("awareness"), rs.getString("avatarDir"));
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
        throw new UnsupportedOperationException();
    }

    public boolean updateMonster(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public boolean deleteMonster(Monster monster) {
        openConnection();
        if (exists(monster)) {
            PreparedStatement ps = prepareStatement(qryDeleteMonster);
            try {
                ps.setInt(1, monster.getMonsterId());
                ps.setInt(2, monster.getMonsterId());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                return false;
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

        }

        return false;
    }

    public Monster loadMonster(int id) {
        //not sure if good like this or should directly load from database
        loadMonsters();
        for (Monster m : loadedMonsters) {
            if (m.getMonsterId() == id) {
                return m;
            }
        }
        return null;
    }
}
