package org.game.rpg.game.map.operation;

import org.game.rpg.inventory.characterInventory.Character;
import org.game.rpg.view.console.TickerView;

import static java.lang.String.format;
import static org.game.rpg.game.map.operation.OperationUtil.moveHorizontally;
import static org.game.rpg.game.map.operation.UserMovement.BACKWARD;
import static org.game.rpg.game.map.operation.UserMovement.CANT_MOVE_MESSAGE;

/**
 * Created by amits2526 on 3/15/2018.
 */
public class MoveLeft implements Operation {
    private UserMovement userMovement;
    private int gridSize;
    private String[][] grid;

    public MoveLeft(UserMovement userMovement) {
        this.userMovement = userMovement;
        this.gridSize = userMovement.getGridSize();
        this.grid = userMovement.getGrid();
    }

    @Override
    public void performOperation(Character user) {
        if (canMoveLeft(user)) {
            moveHorizontally(user, BACKWARD, grid, gridSize);
        } else {
            TickerView.displayTicker(format(CANT_MOVE_MESSAGE, "LEFT"));
        }
    }

    private boolean canMoveLeft(Character user) {
        return ((int) user.position().getY()) > 0;
    }
}
