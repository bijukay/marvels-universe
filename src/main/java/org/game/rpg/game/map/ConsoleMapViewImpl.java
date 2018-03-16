package org.game.rpg.game.map;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;
import static org.game.rpg.game.map.operation.UserMovement.PADDING;

/**
 * This class provide functionality to view map on console.
 *
 * Created by amits2526 on 3/14/2018.
 */
public class ConsoleMapViewImpl implements MapView {

    private static final String PIPE = "|";
    private static final String NEWLINE = "\n";
    private static final int MAP_SIZE_MARGIN = 6;

    @Override
    public void viewMap(String[][] grid, int gridSize) {
        printHLine(gridSize);
        for (int i = 0; i < gridSize; i++) {
            out.print(PIPE);
            for (int j = 0; j < gridSize; j++) {
                printGrid(grid[i][j]);
            }
            out.print(PIPE + NEWLINE);
        }
        printHLine(gridSize);
    }

    private void printGrid(String str) {
        if (str == null || str.isEmpty()) {
            out.print(PADDING);
        } else {
            out.print(str);
        }
    }

    private void printHLine(int gridSize) {
        out.println(
                generate(() -> "-").limit(gridSize * MAP_SIZE_MARGIN).collect(joining())
        );
    }
}
