package org.game.rpg.game.actions;

/**
 * Interface provide functionality to perform actions based on game mode.
 *
 * Created by amits2526 on 3/14/2018.
 */
@FunctionalInterface
public interface GameMode {
    void action();

    public enum Mode {
        START("Start New Game"),
        RESUME("Resume Saved Game"),
        HELP("Help Tips"),
        STORY("Story Mode");

        private String name;

        Mode(String name) {
            this.name = name;
        }

        public static final String TITLE = "Welcome to Marvel's Universe\n\nChoose Mode";
        public static final String FOOTER_MESSAGE = "Please put operation number";

        @Override
        public String toString() {
            return  name;
        }
    }
}
