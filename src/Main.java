
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

	ArrayList<Chest> chests = PersistenceController.getInstance().loadChests();

	for (Chest c : chests) {
	    if(c.getGoldAmount() == 9000){
		PersistenceController.getInstance().link(new Monster("Jens", 0, 2, -5, 5, "images/noobs/Jens.noob"), c);
	    }
	}

//	Chest c = PersistenceController.getInstance().loadChests().get(0);
//	c.setGoldAmount(9000);
//	PersistenceController.getInstance().updateChest(c);
	// TODO: @ChestMapper.updateChest --> allow new Monsters to be put in guardingMonsters (saveMonster(m))
    }
}
