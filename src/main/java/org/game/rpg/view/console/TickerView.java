package org.game.rpg.view.console;

import static java.lang.System.out;

/**
 * This class used to display ticker messages on UI.
 *
 * Created by amits2526 on 3/15/2018.
 */
public class TickerView {

    private TickerView() {
    }

    public static void displayTicker(String message) {
        out.println(message);
    }
}
