/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.mapper;

import domain.Monster;
import java.util.ArrayList;

/**
 *
 * @author Jens
 */
public class MonsterMapper extends Mapper {

    public MonsterMapper(String dbLink) {
        super(dbLink);
    }

    public ArrayList<Monster> loadMonster() {
        throw new UnsupportedOperationException();
    }

    public boolean saveMonster(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public boolean updateMonster(Monster original, Monster updated) {
        throw new UnsupportedOperationException();
    }

    public boolean deleteMonster(Monster monster) {
        throw new UnsupportedOperationException();
    }

    public boolean exists(Monster monster) {
        throw new UnsupportedOperationException();
    }
}
