
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
        ArrayList<Chest> bla = PersistenceController.getInstance().loadChests();
       for(Chest c : bla){
	   System.out.println("Id: " + c.getTreasureId() + ", goldval: " + c.getGoldAmount());
	   for(Monster m : c.getGuardingMonsters()){
	       System.out.println(" Monster id: " + m.getMonsterId());
	   }
       }
    }
}
