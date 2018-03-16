package org.game.rpg.game.map;

import org.game.rpg.exception.ExitGameException;
import org.game.rpg.game.map.operation.Operation.OperationType;
import org.game.rpg.game.map.operation.OperationContext;
import org.game.rpg.game.map.operation.UserMovement;
import org.game.rpg.inventory.EntityManager;
import org.game.rpg.inventory.characterInventory.Character;

import java.util.Optional;

import static org.game.rpg.inventory.EntityManager.getInstance;
import static org.game.rpg.util.GameUtil.*;

/**
 * This class implements {@link GameLoader} to provide functionality to user to navigate ( explore ) game.
 * This class also handles operations like save, exit.
 *
 * Created by amits2526 on 3/14/2018.
 */
public class GameLoaderImpl implements GameLoader {
    private MapView mapView;
    private Character user;
    private EntityManager entityManager;
    private UserMovement userMovement;

    public GameLoaderImpl() {
        userMovement = UserMovement.getInstance();
        entityManager = getInstance();

        final Optional<Character> optionalUser = entityManager.getUser();
        if (optionalUser.isPresent()) {
            this.user = optionalUser.get();
        } else {
            throw new ExitGameException("Unable to find User");
        }

        this.mapView = new ConsoleMapViewImpl();
    }

    @Override
    public void loadGame() {
        do {
            mapView.viewMap(userMovement.getGrid(), userMovement.getGridSize());
            final OperationType operationType = getOperationType();
            OperationContext operationContext = OperationContext.getInstance(userMovement);
            operationContext.executeOperation(user, operationType);
        } while (!enemyRemaining() && user.health() > 0);

        if(user.health() <= 0) {
            gameOver();
        } else {
            youWin();
        }
    }

    private boolean enemyRemaining() {
        return getInstance().getEntities().size() == 1;
    }
}
