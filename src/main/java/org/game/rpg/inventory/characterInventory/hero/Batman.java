package org.game.rpg.inventory.characterInventory.hero;

import org.game.rpg.inventory.weapons.Weapon;

import java.awt.*;

public class Batman extends Hero {
    private HeroType heroType = HeroType.BATMAN;
    private Weapon weapon;
    private int health = heroType.getHealth();
    private Point position;
    private boolean isUser;
    private String userName;

    @Override
    public String getName() {
        return heroType.getName();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public int health() {
        return this.health;
    }

    @Override
    public void decreaseHealth(int damage) {
        this.health -= damage;
    }

    @Override
    public Point position() {
        return position;
    }

    @Override
    public void setPosition(Point newPosition) {
        this.position = newPosition;
    }

    @Override
    public Weapon[] getWeaponList() {
        return heroType.getWeapons();
    }

    @Override
    public void increaseHealth(int health) {
        this.health += health;
    }

    @Override
    public boolean isUser() {
        return isUser;
    }

    @Override
    public String getUserName() {
        return isUser ? userName : "no user";
    }

    @Override
    public void setUserName(String userName) {
        this.isUser = true;
        this.userName = userName;
    }

    @Override
    public String getAcronym() {
        return heroType.getAcronym();
    }
}
