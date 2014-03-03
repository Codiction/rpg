
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A1
 */
public class Monster {
    
    private ArrayList<Chest> guardedChests;

    public Monster() {
    }
    
    public List<Chest> getGuardedChests(){
        return guardedChests;
    }
    
    
}
