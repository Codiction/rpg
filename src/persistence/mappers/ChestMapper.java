package persistence.mappers;

import domain.Chest;
import java.sql.Connection;
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
public class ChestMapper {
    
    private PreparedStatement queryGetChests;
    private PreparedStatement queryWriteChest;
    private PreparedStatement queryChestExists;

    public ChestMapper(Connection c) {
        try {
            //TODO: finish
            queryGetChests = c.prepareStatement("SELECT * FROM schat");
            queryWriteChest = c.prepareStatement("INSERT INTO schat ");
            queryChestExists = c.prepareStatement("SELECT * FROM schat WHERE bla = ?");
            
        } catch (SQLException bla) {
            
        }
    }

    public ArrayList<Chest> loadChests(Connection c) {
        ArrayList<Chest> list = new ArrayList<>();
        try {
            ResultSet rs = queryGetChests.executeQuery();
            while(rs.next()) {
                //TODO: finish
                list.add(new Chest());
            }
        } catch (SQLException ex) {
            
        }
        
        return list;
    }
}
