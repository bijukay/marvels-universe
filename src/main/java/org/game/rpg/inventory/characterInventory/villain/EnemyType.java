package org.game.rpg.inventory.characterInventory.villain;

import java.util.Random;

import static java.lang.String.format;
import static org.game.rpg.view.console.TickerView.displayTicker;

public enum EnemyType {
    DOCTORDOOM("Doctor Doom", "DOC", 80),
    MAGNETO("Magneto", "MAG", 70),
    REDSKULL("Red Skull", "RED", 130),
    LOKI("Loki", "LOK", 100),
    THANOS("Thanos", "THA", 150);

    private static final String GET_READY_TO_FIGHT = "\nGet ready to Fight with %s\n";
    private String name;
    private String acronym;
    private int health;

    EnemyType(String name, String acronym, int health) {
        this.name = name;
        this.acronym = acronym;
        this.health = health;
    }

    public static EnemyType chooseRandom() {
        final Random random = new Random();
        final int randomValue = random.nextInt(values().length);
        final EnemyType[] values = values();
        return values[randomValue];
    }

    public String getAcronym() {
        return acronym;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public static EnemyType enemyTypeStartedWith(String str) {
        String substring = str.substring(1, 4);
        switch (substring) {
            case "DOC":
                displayTicker(format(GET_READY_TO_FIGHT, DOCTORDOOM.getName()));
                return DOCTORDOOM;
            case "MAG":
                displayTicker(format(GET_READY_TO_FIGHT, MAGNETO.getName()));
                return MAGNETO;
            case "RED":
                displayTicker(format(GET_READY_TO_FIGHT, REDSKULL.getName()));
                return REDSKULL;
            case "LOK":
                displayTicker(format(GET_READY_TO_FIGHT, LOKI.getName()));
                return LOKI;
            case "THA":
                displayTicker(format(GET_READY_TO_FIGHT, THANOS.getName()));
                return THANOS;
            default:
                throw new UnsupportedOperationException("Enemy type not found");
        }
    }

    @Override
    public String toString() {
        return name + ", health=" + health;
    }
}
