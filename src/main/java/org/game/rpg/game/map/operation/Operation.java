package org.game.rpg.game.map.operation;

import org.game.rpg.inventory.characterInventory.Character;

public interface Operation {

    void performOperation(Character user);

    public enum OperationType {
        RIGHT("Right"),
        LEFT("Left"),
        UP("Up"),
        DOWN("Down"),
        SAVE("Save"),
        EXIT("Exit");

        private String name;

        OperationType(String name) {
            this.name = name;
        }

        public static final String TITLE = "Explore";
        public static final String FOOTER_MESSAGE = "Please put operation number";

        @Override
        public String toString() {
            return name;
        }
    }
}
