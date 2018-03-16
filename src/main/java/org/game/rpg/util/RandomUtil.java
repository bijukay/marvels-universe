package org.game.rpg.util;

import org.game.rpg.game.map.operation.UserMovement;

import java.awt.*;
import java.util.Random;

/**
 * This class provides utility to get random position for User/Enemy to view on map.
 *
 * Created by amits2526 on 3/14/2018.
 */
public class RandomUtil {
    private static final Random random = new Random();
    private static final UserMovement USER_MOVEMENT = UserMovement.getInstance();

    public static Point getRandomPoint() {
        final Point point = new Point(random.nextInt(USER_MOVEMENT.getGridSize()), random.nextInt(USER_MOVEMENT.getGridSize()));
        if (positionTaken(point, USER_MOVEMENT.getGrid())) {
            return getRandomPoint();
        }
        return point;
    }

    private static boolean positionTaken(Point point, String[] [] grid) {
        String pointStr = grid[(int) point.getX()][(int) point.getY()];
        if (pointStr == null) {
            return false;
        } else {
            return !pointStr.isEmpty();
        }
    }
}
