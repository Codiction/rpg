package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A1
 */
public class Monster {

    private ArrayList<Chest> guardedChests;
    private String name;
    private int power, defence, speed;
    private int awareness;
    private String avatarDir;
    private int monsterId;

    public Monster() {
    }

    public Monster(int monsterId, String name, int pow, int def, int sp, int awaren, String avDir, ArrayList<Chest> guardedChests) {
	setName(name);
	setPower(pow);
	setDefence(def);
	setSpeed(sp);
	setAwareness(awaren);
	setAvatarDir(avDir);
	setMonsterId(monsterId);
	setGuardedChests(guardedChests);
    }

    public Monster(String name, int pow, int def, int sp, int awaren, String avDir, ArrayList<Chest> guardedChests) {
	setName(name);
	setPower(pow);
	setDefence(def);
	setSpeed(sp);
	setAwareness(awaren);
	setAvatarDir(avDir);
	setGuardedChests(guardedChests);
    }

    public void setGuardedChests(ArrayList<Chest> chests) {
	guardedChests = chests;
    }

    public ArrayList<Chest> getGuardedChests() {
	return guardedChests;
    }

    public int getMonsterId() {
	return monsterId;
    }

    public void setMonsterId(int monsterId) {
	this.monsterId = monsterId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getPower() {
	return power;
    }

    public void setPower(int power) {
	this.power = power;
    }

    public int getDefence() {
	return defence;
    }

    public void setDefence(int defence) {
	this.defence = defence;
    }

    public int getSpeed() {
	return speed;
    }

    public void setSpeed(int speed) {
	this.speed = speed;
    }

    public int getAwareness() {
	return awareness;
    }

    public void setAwareness(int awareness) {
	this.awareness = awareness;
    }

    public String getAvatarDir() {
	return avatarDir;
    }

    public void setAvatarDir(String avatarDir) {
	this.avatarDir = avatarDir;
    }

}
