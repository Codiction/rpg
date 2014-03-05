package persistence.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A1
 */
public class Mapper {

    private String dbLink;
    private Connection connection;
    private boolean connected;

    public Mapper(String dbLink) {
        this.dbLink = dbLink;
    }
    
    public void openConnection() {
        if (!dbLink.isEmpty()) {
            if (!connected) {
                try {
                    connection = DriverManager.getConnection(dbLink);
                    connected = true;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    connected = false;
                }
            }
        }
    }

    public void closeConnection() {
        if (isConnected()) {
            try {
                connection.close();
		connected = false;
            } catch (SQLException ex) {
                Logger.getLogger(Mapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public PreparedStatement prepareStatement(String sql) {
        if (isConnected()) {
            try {
                return connection.prepareStatement(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }
    
    public PreparedStatement prepareStatement(String sql, int mode){
	if(isConnected()){
	    try{
		return connection.prepareStatement(sql, mode);
	    }catch(SQLException se){
		return null;
	    }
	}
	return null;
    }

    public boolean isConnected() {
        if (connection == null && !connected) {
            return false;
        } else {
            return connected;
        }
    }
}
