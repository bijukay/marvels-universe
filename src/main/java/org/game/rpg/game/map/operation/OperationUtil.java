package org.game.rpg.game.map.operation;

import org.game.rpg.inventory.characterInventory.Character;
import org.game.rpg.inventory.characterInventory.villain.Enemy;
import org.game.rpg.inventory.weapons.Weapon;
import org.game.rpg.util.FightUtil;
import org.game.rpg.util.GameUtil;
import org.game.rpg.view.View;
import org.game.rpg.view.console.ConsoleMenu;
import org.game.rpg.view.console.TickerView;

import java.awt.*;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.game.rpg.game.map.operation.UserMovement.HEALTH;
import static org.game.rpg.game.map.operation.UserMovement.PADDING;
import static org.game.rpg.inventory.EntityManager.getInstance;
import static org.game.rpg.inventory.characterInventory.CharacterInventory.getEnemy;
import static org.game.rpg.inventory.characterInventory.villain.EnemyType.enemyTypeStartedWith;
import static org.game.rpg.view.console.TickerView.displayTicker;

/**
 * Created by amits2526 on 3/15/2018.
 */
public class OperationUtil {

    private OperationUtil() {
    }

    public static void moveHorizontally(Character user, int distance, String[][] grid, int gridSize) {
        checkEnemy(user, distance, "y", grid);
        grid [(int) user.position().getX()] [(int) user.position().getY()] = PADDING;
        user.setPosition(new Point((int) user.position().getX(), (int) user.position().getY() + distance));
        grid [(int) user.position().getX()] [(int) user.position().getY()] = "<"+user.getAcronym()+">";
    }

    public static void moveVertically(Character user, int distance, String[][] grid, int gridSize) {
        checkEnemy(user, distance, "x", grid);
        grid[(int) user.position().getX()][(int) user.position().getY()] = PADDING;
        user.setPosition(new Point((int) user.position().getX() + distance, (int) user.position().getY()));
        grid[(int) user.position().getX()][(int) user.position().getY()] = "<"+user.getAcronym()+">";
    }

    private static void checkEnemy(Character user, int distance, String axis, String[][] grid) {
        String str;
        if (axis.equalsIgnoreCase("y")) {
            str = grid[(int) user.position().getX()][(int) user.position().getY() + distance];
        } else {
            str = grid[(int) user.position().getX() + distance][(int) user.position().getY()];
        }
        if (nonNull(str) && !str.isEmpty()) {
            if (!str.equals(PADDING)) {
                if (str.contains(HEALTH)) {
                    user.increaseHealth(50);
                    TickerView.displayTicker(format("Congratulations!! Your health Increases by 50. Your health now is %s\n", user.health()));
                } else {
                    fight(user, getEnemy(enemyTypeStartedWith(str)));
                }
            }
        }
    }

    private static void fight(Character user, Enemy enemy) {
        displayTicker(String.format("\nHero -> Name: %s, Health: %s\n", user.getName(), user.health()));
        displayTicker(String.format("Enemy -> Name: %s, Health: %s\n\n", enemy.getName(), enemy.health()));
        final View<Weapon> weaponMenu = new ConsoleMenu<>(Weapon.TITLE, Weapon.FOOTER_MESSAGE, user.getWeaponList());
        Weapon userWeapon = weaponMenu.readUserChoice();
        user.setWeapon(userWeapon);
        FightUtil.attack(user, enemy);
        if (user.health() <= 0) {
            GameUtil.gameOver();
        }

        if (enemy.health() <= 0) {
            getInstance().removeEntity(enemy);
            return;
        }
        fight(user, enemy);
    }
}
