
import domain.Chest;
import java.util.ArrayList;
import persistence.PersistenceController;

/**
 *
 * @author A1
 */
public class Main {

    public static void main(String[] args) {
        new Main();

    }

    public Main() {
        ArrayList<Chest> bla = PersistenceController.getInstance().loadChests();
       
    }
}
