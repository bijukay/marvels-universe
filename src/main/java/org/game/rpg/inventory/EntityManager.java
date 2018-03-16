package org.game.rpg.inventory;

import org.game.rpg.inventory.characterInventory.Character;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

/**
 * Created by amits2526 on 3/16/2018.
 */
public class EntityManager {

    private static EntityManager entityManager;
    private List<Character> entities = new LinkedList<>();

    private EntityManager() {
    }

    public static EntityManager getInstance() {
        if (isNull(entityManager)) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }

    public List<Character> getEntities() {
        return this.entities;
    }

    public void addEntity(Character entity) {
        this.entities.add(entity);
    }

    public void addEntities(List<Character> entities) {
        this.entities.addAll(entities);
    }

    public void removeEntity(Character entity) {
        this.entities.remove(entity);
    }

    public boolean setEntityPosition(Character entity, Point position) {
        final Optional<Character> optionalEntity = entities.stream().filter(e -> e == entity).findFirst();
        if (optionalEntity.isPresent()) {
            final Character charEntity = optionalEntity.get();
            charEntity.setPosition(position);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Character> getUser() {
        return this.entities.stream().filter(entity -> entity.isUser()).findFirst();
    }
}
