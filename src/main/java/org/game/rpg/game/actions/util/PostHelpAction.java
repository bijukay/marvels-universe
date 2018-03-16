package org.game.rpg.game.actions.util;

/**
 * Created by amits2526 on 3/16/2018.
 */
public enum PostHelpAction {
    RETURN("Return to main menu"),
    EXIT("Exit game");

    private String name;

    PostHelpAction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
