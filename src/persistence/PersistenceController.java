package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brent
 */
public class PersistenceController {

    private DatabaseReader dbReader;
    private DatabaseWriter dbWriter;
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/rpg?user=rpg?password=rpg";

    public PersistenceController() {
        try {
            dbReader = new DatabaseReader();
            dbWriter = new DatabaseWriter();
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
