package org.game.rpg.game.map.operation;

import org.game.rpg.inventory.characterInventory.Character;
import org.game.rpg.view.console.TickerView;

import static org.game.rpg.game.map.operation.OperationUtil.moveHorizontally;
import static org.game.rpg.game.map.operation.UserMovement.FORWARD;

/**
 * Created by amits2526 on 3/15/2018.
 */
public class MoveRight implements Operation {

    private UserMovement userMovement;
    private int gridSize;
    private String[][] grid;

    public MoveRight(UserMovement userMovement) {
        this.userMovement = userMovement;
        this.gridSize = userMovement.getGridSize();
        this.grid = userMovement.getGrid();
    }

    @Override
    public void performOperation(Character user) {

        if (canMoveRight(user)) {
            moveHorizontally(user, FORWARD, grid, gridSize);
        } else {
            TickerView.displayTicker(String.format(UserMovement.CANT_MOVE_MESSAGE, "RIGHT"));
        }
    }

    private boolean canMoveRight(Character user) {
        return ((int) user.position().getY()) < gridSize-1;
    }
}
