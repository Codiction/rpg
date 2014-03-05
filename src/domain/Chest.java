package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A1
 */
public class Chest {

    private ArrayList<Monster> monsters;
    private int treasureId;
    private int goldAmount;

    public Chest() {
	setMonsters(new ArrayList<Monster>());
    }

    public Chest(int treasureId, int goldAmount, ArrayList<Monster> monsters) {
	setTreasureId(treasureId);
	setGoldAmount(goldAmount);
	setMonsters(monsters);
    }
    
    public Chest(int goldAmount){
	this(goldAmount, new ArrayList<Monster>());
    }

    public Chest(int goldAmount, ArrayList<Monster> monsters) {
	this(0, goldAmount, monsters);
    }

    private void setMonsters(ArrayList<Monster> monsters) {
	this.monsters = monsters;
    }

    public ArrayList<Monster> getMonsters() {
	return monsters;
    }

    public int getTreasureId() {
	return treasureId;
    }

    public void setTreasureId(int treasureId) {
	this.treasureId = treasureId;
    }

    public int getGoldAmount() {
	return goldAmount;
    }

    public void setGoldAmount(int goldAmount) {
	this.goldAmount = goldAmount;
    }

    public List<Monster> getGuardingMonsters() {
	return monsters;
    }
}
