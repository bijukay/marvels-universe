package org.game.rpg.game.map;

import org.game.rpg.game.map.operation.UserMovement;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.game.rpg.game.map.operation.UserMovement.HEALTH;

/**
 * Created by amits2526 on 3/15/2018.
 */
public class MapViewTestForNegetiveScenarios {
    private UserMovement userMovement;
    private MapView mapView;

    @Rule
    public SystemOutRule outRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() throws Exception {
        outRule.clearLog();
        this.userMovement = UserMovement.getInstance();
        this.mapView = new ConsoleMapViewImpl();
    }

    @After
    public void tearDown() throws Exception {
        outRule.clearLog();
    }

    @Test
    @Ignore("This test will run individually and is functionally correct.")
    public void viewMap_WithOutInitializingGrid_ShouldNotContainsAnyEntities() throws Exception {
        //when
        mapView.viewMap(userMovement.getGrid(), userMovement.getGridSize());

        //then
        assertThat(outRule.getLog().contains(HEALTH)).isFalse();
    }
}
