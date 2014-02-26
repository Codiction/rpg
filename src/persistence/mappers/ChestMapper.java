package persistence.mappers;

import domain.Chest;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brent
 */
public class ChestMapper extends Mapper {

    private final PreparedStatement queryAddChest = prepareStatement("");
    private final PreparedStatement queryDeleteChest = prepareStatement("");
    private final PreparedStatement queryUpdateChest = prepareStatement("");
    private final PreparedStatement queryChestExists = prepareStatement("");
    private final PreparedStatement queryGetChests = prepareStatement("");

    public ChestMapper(String dbLink) {
        super(dbLink);
    }

    public void addChest(Chest c) {
        if (!exists(c)) {
            try {
                //TODO: addChest parameters invullen
                queryAddChest.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Failed to add chest (AddChest -> ChestMapper)");
                //TODO: fix
            }
        }
    }

    public void deleteChest(Chest c) {

    }

    public boolean exists(Chest c) {
        return false;
    }

    public ArrayList<Chest> loadChests() {
        return null;
    }

    public void updateChest(Chest original, Chest updated) {

    }

    public void saveChest(Chest c) {

    }
}
