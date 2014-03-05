
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
	ArrayList<Chest> chests = PersistenceController.getInstance().loadChests();
	for(Chest c : chests){
	    if(c.getGoldAmount() == 1995){
		PersistenceController.getInstance().deleteChest(c);
	    }
	}
	// TODO: Allow Chest and Monster constructor without the id-parameter (do not forget to automatically asign a primary key at saveMonster and saveChest!)
    }
}
