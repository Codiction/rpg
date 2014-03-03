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
public class MonsterMapper extends Mapper {

    ChestMapper chestMapper;

    private final String qryGetMonsters = "SELECT * FROM monster";
    private final String qryGetGuardedChestByMonster = "SELECT treasureId FROM guardedchest WHERE monsterId = ?";

    public MonsterMapper(String dbLink) {
        super(dbLink);
        chestMapper = new ChestMapper(dbLink);
    }

    public ArrayList<Monster> loadMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        openConnection();
        try {
            ResultSet rs = prepareStatement(qryGetMonsters).executeQuery();
            while (rs.next()) {
                int monsterId = rs.getInt("monsterId");
                Chest guardedChest = null;
                PreparedStatement ps = prepareStatement(qryGetGuardedChestByMonster);
                ps.setInt(1, monsterId);
                ResultSet chestResults = ps.executeQuery();

                //TODO: further handling (loop through all chest results, get the chest, link chest to newly created monster, add to monster list)

            }
        } catch (SQLException ex) {
            return null;
        } finally {
            closeConnection();
        }
        return monsters;
    }

    public boolean saveMonster(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public boolean updateMonster(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public boolean deleteMonster(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public boolean exists(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public Monster loadMonster(int id) {
        throw new UnsupportedOperationException();
    }
}
