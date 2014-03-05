
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
	for(Monster m : monsters){
	    if(m.getName().equals("Baas")){
		PersistenceController.getInstance().deleteMonster(m);
	    }
	}
	// TODO: Allow Chest and Monster constructor without the id-parameter (do not forget to automatically asign a primary key at saveMonster and saveChest!)
    }
}
