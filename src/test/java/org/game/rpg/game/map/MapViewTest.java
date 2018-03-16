package org.game.rpg.game.map;

import org.game.rpg.game.map.operation.UserMovement;
import org.game.rpg.inventory.characterInventory.Character;
import org.game.rpg.utils.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.game.rpg.game.map.operation.UserMovement.HEALTH;
import static org.game.rpg.inventory.characterInventory.hero.HeroType.BATMAN;
import static org.game.rpg.inventory.characterInventory.villain.EnemyType.DOCTORDOOM;
import static org.game.rpg.inventory.characterInventory.villain.EnemyType.LOKI;

/**
 * Created by amits2526 on 3/15/2018.
 */
@RunWith(Parameterized.class)
public class MapViewTest {

    private UserMovement userMovement;
    private MapView mapView;

    @Rule
    public SystemOutRule outRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() throws Exception {
        outRule.clearLog();
        this.userMovement = UserMovement.getInstance();
        this.userMovement.resetGrid();
        this.mapView = new ConsoleMapViewImpl();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[] [] {
                {DOCTORDOOM.getAcronym()},
                {LOKI.getAcronym()},
                {HEALTH},
                {BATMAN.getAcronym()}
        });
    }

    @Parameterized.Parameter
    public String exectedEntityInMap;

    @After
    public void tearDown() throws Exception {
        outRule.clearLog();
        this.userMovement.resetGrid();
    }

    @Test
    public void viewMap() throws Exception {
        //given
        List<Character> entities = TestUtil.prepareListOfEntities();
        userMovement.initializeMap(entities, true);

        //when
        mapView.viewMap(userMovement.getGrid(), userMovement.getGridSize());

        //then
        assertThat(outRule.getLog()).contains(exectedEntityInMap);
    }
}