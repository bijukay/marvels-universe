package org.game.rpg.game.map.operation;

import org.game.rpg.inventory.characterInventory.Character;
import org.game.rpg.view.console.TickerView;

import static java.lang.String.format;
import static org.game.rpg.game.map.operation.OperationUtil.moveVertically;
import static org.game.rpg.game.map.operation.UserMovement.CANT_MOVE_MESSAGE;
import static org.game.rpg.game.map.operation.UserMovement.FORWARD;

/**
 * Created by amits2526 on 3/15/2018.
 */
public class MoveDown implements Operation {

    private UserMovement userMovement;
    private int gridSize;
    private String[][] grid;

    public MoveDown(UserMovement userMovement) {
        this.userMovement = userMovement;
        this.gridSize = userMovement.getGridSize();
        this.grid = userMovement.getGrid();
    }

    @Override
    public void performOperation(Character user) {

        if (canMoveDown(user)) {
            moveVertically(user, FORWARD, grid, gridSize);
        } else {
            TickerView.displayTicker(format(CANT_MOVE_MESSAGE, "DOWN"));
        }
    }

    private boolean canMoveDown(Character user) {
        return ((int) user.position().getX()) < gridSize-1;
    }
}
