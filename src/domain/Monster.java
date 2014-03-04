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
    private float power, defence, speed;
    private int awareness;
    private String avatarDir;
    private int monsterId;

    public Monster() {
    }

    public Monster(int monsterId, String name, float pow, float def, float sp, int awaren, String avDir) {
        setName(name);
        setPower(pow);
        setDefence(def);
        setSpeed(sp);
        setAwareness(awaren);
        setAvatarDir(avDir);
        setMonsterId(monsterId);
    }

    public void setGuardedChests(ArrayList<Chest> chests) {
        guardedChests = chests;
    }

    public List<Chest> getGuardedChests() {
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

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getDefence() {
        return defence;
    }

    public void setDefence(float defence) {
        this.defence = defence;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
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
