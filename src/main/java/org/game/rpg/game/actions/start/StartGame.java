package org.game.rpg.game.actions.start;

import org.game.rpg.game.actions.GameMode;
import org.game.rpg.game.map.GameLoader;
import org.game.rpg.game.map.GameLoaderImpl;
import org.game.rpg.game.map.operation.UserMovement;
import org.game.rpg.inventory.EntityManager;
import org.game.rpg.inventory.characterInventory.Character;
import org.game.rpg.inventory.characterInventory.hero.HeroType;
import org.game.rpg.inventory.characterInventory.villain.EnemyType;
import org.game.rpg.view.View;
import org.game.rpg.view.console.ConsoleMenu;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.game.rpg.inventory.characterInventory.CharacterInventory.getEnemy;
import static org.game.rpg.inventory.characterInventory.CharacterInventory.getHero;
import static org.game.rpg.util.GameUtil.getUserName;

/**
 * This method provide functionality to start a new game.
 *
 * Created by amits2526 on 3/14/2018.
 */
public class StartGame implements GameMode {
    private EntityManager entityManager;
    private final Character userCharacter;

    public StartGame() {
        this.userCharacter = createUser();
        entityManager = EntityManager.getInstance();
        entityManager.addEntities(getEnemyList(5));
        entityManager.addEntity(userCharacter);
    }

    @Override
    public void action() {
        UserMovement.getInstance().initializeMap();
        GameLoader gameLoader = new GameLoaderImpl();
        gameLoader.loadGame();
    }

    private Character createUser() {
        final Character user = getHero(getCharacterType());
        user.setUserName(getUserName());
        return user;
    }

    private List<Character> getEnemyList(int size) {
        final List<Character> enemies = new LinkedList<>();
        for (int counter = 0; counter < size; counter++) {
            final Character randomEnemy = getRandomEnemy(enemies);
            enemies.add(randomEnemy);
        }
        return enemies;
    }

    private Character getRandomEnemy(List<Character> enemies) {
        final EnemyType enemyType = EnemyType.chooseRandom();
        Optional<Character> optionalEnemy = enemies.stream().filter(e -> e.getName().equals(enemyType.getName())).findAny();
        if (optionalEnemy.isPresent()) {
            return getRandomEnemy(enemies);
        }
        return getEnemy(enemyType);
    }

    private HeroType getCharacterType() {
        final View<HeroType> heroTypeMenu = new ConsoleMenu<>(HeroType.TITLE, HeroType.FOOTER_MESSAGE, HeroType.values());
        return heroTypeMenu.readUserChoice();
    }
}
