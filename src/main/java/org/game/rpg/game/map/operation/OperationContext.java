package org.game.rpg.game.map.operation;

import org.game.rpg.game.map.operation.Operation.OperationType;
import org.game.rpg.inventory.EntityManager;
import org.game.rpg.inventory.characterInventory.Character;
import org.game.rpg.persistance.FilePersistence;
import org.game.rpg.util.GameUtil;

import java.util.Objects;

/**
 * Created by amits2526 on 3/15/2018.
 */
public class OperationContext {
    private static OperationContext instance;
    private UserMovement userMovement;

    private OperationContext(UserMovement userMovement) {
        this.userMovement = userMovement;
    }

    public static OperationContext getInstance(UserMovement userMovement) {
        if (Objects.isNull(instance)) {
            instance = new OperationContext(userMovement);
        }

        return instance;
    }

    public void executeOperation(Character user, OperationType operationType) {
        switch (operationType) {
            case RIGHT:
                new MoveRight(userMovement).performOperation(user);
                break;
            case LEFT:
                new MoveLeft(userMovement).performOperation(user);
                break;
            case UP:
                new MoveUp(userMovement).performOperation(user);
                break;
            case DOWN:
                new MoveDown(userMovement).performOperation(user);
                break;
            case SAVE:
                new FilePersistence().persist(user.getUserName(), EntityManager.getInstance().getEntities());
                break;
            case EXIT:
                GameUtil.exitGame();
            default:
        }
    }
}
