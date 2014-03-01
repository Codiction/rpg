
package domain;

import java.util.ArrayList;

/**
 *
 * @author A1
 */
public class Monster {
    
    private ArrayList<Chest> chest;
    private int monsterId;

    public int getMonsterId() {
	return this.monsterId;
    }

    public void setMonsterId(int id) {
	this.monsterId = id;
    }

    public Monster() {
    }
    
    public ArrayList<Chest> getGuardedChests(){
        
        throw new UnsupportedOperationException();
    }
    
    
}
