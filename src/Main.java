
import domain.Chest;
import domain.Monster;
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
	Chest c = new Chest(2, 50, new ArrayList<Monster>());
	PersistenceController.getInstance().saveChest(c);
    }
}
