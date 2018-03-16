package org.game.rpg.game.map.operation;

import org.game.rpg.inventory.EntityManager;
import org.game.rpg.inventory.characterInventory.Character;

import java.awt.*;
import java.util.List;

import static java.util.Objects.isNull;
import static org.game.rpg.util.RandomUtil.getRandomPoint;

/**
 * This class is used to populate game arena.
 *
 * Created by amits2526 on 3/14/2018.
 */
public class UserMovement {
    private static final UserMovement INSTANCE = new UserMovement();
    public static final String HEALTH = "< >";
    public static final int BACKWARD = -1;
    public static final int FORWARD = 1;
    public static final String PADDING = "     ";
    public static final String CANT_MOVE_MESSAGE = "You can not go to %s!";

    private int gridSize = 5;
    private String[][] grid = new String[gridSize][gridSize];

    public int getGridSize() {
        return gridSize;
    }

    public String[][] getGrid() {
        return grid;
    }

    void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public void resetGrid() {
        this.grid = new String[gridSize][gridSize];
    }

    private UserMovement() {
        //do nothing
    }

    public static UserMovement getInstance() {
        return INSTANCE;
    }

    public void initializeMap() {
        final EntityManager entityManager = EntityManager.getInstance();
        List<Character> entities = entityManager.getEntities();

        entities
                .stream()
                .filter(e -> isNull(e.position()))
                .forEach(entity -> entityManager.setEntityPosition(entity, getRandomPoint())
                );
        placeHealthOptionInMap(2);

        entities.forEach(this::placeEntityInMap);
    }

    private void placeEntityInMap(Character entity) {
        grid [(int) entity.position().getX()] [(int) entity.position().getY()] = "<" + entity.getAcronym() + ">";
    }

    private void placeHealthOptionInMap(int times) {
        for (int counter = 0; counter < times; counter++) {
            final Point randomPoint = getRandomPoint();
            grid[(int) randomPoint.getX()][(int) randomPoint.getY()] = "<" + HEALTH + ">";
        }
    }
}
