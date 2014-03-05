
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

        ArrayList<Monster> monsters = PersistenceController.getInstance().loadMonsters();
        

        for (Monster m : monsters) {
            if (m.getName().equals("Lesser Demon")) {
                PersistenceController.getInstance().link(m, PersistenceController.getInstance().loadChests().get(0));
                
                
            }
        }

//	Chest c = PersistenceController.getInstance().loadChests().get(0);
//	c.setGoldAmount(9000);
//	PersistenceController.getInstance().updateChest(c);
        // TODO: @ChestMapper.updateChest --> allow new Monsters to be put in guardingMonsters (saveMonster(m))
    }
}
