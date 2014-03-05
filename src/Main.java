
import domain.Chest;
import domain.Monster;
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
	Chest c = PersistenceController.getInstance().loadChests().get(0);
	c.setGoldAmount(9000);
	PersistenceController.getInstance().updateChest(c);
	// TODO: @ChestMapper.updateChest --> allow new Monsters to be put in guardingMonsters (saveMonster(m))
    }
}
