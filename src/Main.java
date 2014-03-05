
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
	Chest c = new Chest(55, PersistenceController.getInstance().loadMonsters());
	PersistenceController.getInstance().saveChest(c);
	// TODO: Allow Chest and Monster constructor without the id-parameter (do not forget to automatically asign a primary key at saveMonster and saveChest!)
    }
}
