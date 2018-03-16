package org.game.rpg.persistance;

import org.game.rpg.inventory.characterInventory.Character;

import java.util.List;

/**
 * Interface provides functionality to persist and load game entities.
 *
 * Created by amits2526 on 3/14/2018.
 */
public interface Persistence {

    /**
     * This method will persist data to store (based on implementation).
     * @param userName Name of the user.
     * @param entities {@link List} of entities to save.
     * @return {@code true} if entities saved successfully else {@code false}
     */
    boolean persist(String userName, List<Character> entities);

    /**
     * This will load the entities from store based on user name
     * @param userName
     * @return {@link List} of entities load from store.
     */
    List<Character> load(String userName);
}
