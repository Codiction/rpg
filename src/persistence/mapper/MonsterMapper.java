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

                Monster m = new Monster(rs.getInt("monsterId"), rs.getString("name"), rs.getInt("power"),
                        rs.getInt("defence"), rs.getInt("speed"), rs.getInt("awareness"), rs.getString("avatarDir"), new ArrayList<Chest>());
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
        //check if monster exists
        //if exists, update method
        //if not exists do INSERT statement for monster and guardedchest

        super.openConnection();
        if (exists(monster)) {
            updateMonster(monster);
        } else {
            String qryInsert = "INSERT INTO `monster` (`name`,`power`,"
                    + "`defence`,`speed`,`awareness`,`avatarDir`) VALUES (?,?,"
                    + "?,?,?,?)";
            String queryLinkChestToMonster = "INSERT INTO guardedchest VALUES (?, ?)";

            PreparedStatement insert = prepareStatement(qryInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement link = prepareStatement(queryLinkChestToMonster);

            try {
                insert.setString(1, monster.getName());
                insert.setInt(2, monster.getPower());
                insert.setInt(3, monster.getDefence());
                insert.setInt(4, monster.getSpeed());
                insert.setInt(5, monster.getAwareness());
                insert.setString(6, monster.getAvatarDir());

                insert.executeUpdate();
                int id = 0;
                ResultSet keys = insert.getGeneratedKeys();
                if (keys.next()) {
                    id = keys.getInt(1);
                }

		monster.setMonsterId(id);
		
                link.setInt(1, id);
                for (Chest c : monster.getGuardedChests()) {
		    chestMapper.saveChest(c);
                    link.setInt(2, c.getTreasureId());
                    link.executeUpdate();
                }
                return true;
            } catch (SQLException se) {

            } finally {
                super.closeConnection();
            }
        }

        super.closeConnection();
        return false;
    }

    public boolean updateMonster(Monster monster) {
        super.openConnection();
        if (!exists(monster)) {
            saveMonster(monster);
        } else {
            String qryUpdateMonster = "UPDATE `projecten1_rpg`.`monster` SET "
                    + "`monsterId` = ?, `name` = ?, `power` = ?, `defence` = ?, "
                    + "`speed` = ?, `awareness` = ?, `avatarDir` = ? WHERE "
                    + "`monsterId` = ?";
            String qryDeleteGuardedChest = "DELETE FROM guardedchest WHERE monsterId = ?";
            String qryLinkMonsterToChest = "INSERT INTO guardedchest VALUES (?, ?)";
            PreparedStatement update = prepareStatement(qryUpdateMonster);
            PreparedStatement delete = prepareStatement(qryDeleteGuardedChest);
            PreparedStatement insert = prepareStatement(qryLinkMonsterToChest);
            try {
                update.setInt(1, monster.getMonsterId());
                update.setString(2, monster.getName());
                update.setInt(3, monster.getPower());
                update.setInt(4, monster.getDefence());
                update.setInt(5, monster.getSpeed());
                update.setInt(6, monster.getAwareness());
                update.setString(7, monster.getAvatarDir());
                update.setInt(8, monster.getMonsterId());

                update.executeUpdate();

                delete.setInt(1, monster.getMonsterId());
                delete.executeUpdate();

                for (Chest c : monster.getGuardedChests()) {
                    chestMapper.saveChest(c);
                    insert.setInt(1, monster.getMonsterId());
                    insert.setInt(2, c.getTreasureId());
                    insert.executeUpdate();
                }

                return true;
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                super.closeConnection();
            }
        }

        super.closeConnection();
        return false;
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
                m = new Monster(rs.getInt("monsterId"), rs.getString("name"), rs.getInt("power"),
                        rs.getInt("defence"), rs.getInt("speed"), rs.getInt("awareness"), rs.getString("avatarDir"), new ArrayList<Chest>());

            }
            return m;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
