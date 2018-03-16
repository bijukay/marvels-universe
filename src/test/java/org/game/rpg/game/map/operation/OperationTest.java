package org.game.rpg.game.map.operation;

import org.game.rpg.game.map.operation.Operation.OperationType;
import org.game.rpg.inventory.characterInventory.Character;
import org.game.rpg.inventory.characterInventory.CharacterInventory;
import org.game.rpg.inventory.characterInventory.hero.HeroType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.game.rpg.game.map.operation.Operation.OperationType.*;
import static org.game.rpg.inventory.characterInventory.hero.HeroType.IROMMAN;

/**
 * Created by amits2526 on 3/16/2018.
 */
@RunWith(Parameterized.class)
public class OperationTest {

    private UserMovement movement;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[] [] {
                {RIGHT, 2, 3},
                {LEFT, 2, 1},
                {UP, 1, 2},
                {DOWN, 3, 2}
        });
    }

    @Parameterized.Parameter
    public OperationType operationType;

    @Parameterized.Parameter(1)
    public int x;

    @Parameterized.Parameter(2)
    public int y;

    @Before
    public void setUp() throws Exception {
        movement = UserMovement.getInstance();
        movement.resetGrid();
    }

    @After
    public void tearDown() throws Exception {
        movement.resetGrid();
    }

    @Test
    public void performOperationTest() throws Exception {
        //given
        final Character hero = getHero(IROMMAN);

        movement.initializeMap(new LinkedList<>(), false);

        //when
        OperationContext.getInstance(movement, new LinkedList<>()).executeOperation(hero, operationType);

        //then
        assertThat(hero.position().getX()).isEqualTo(x);
        assertThat(hero.position().getY()).isEqualTo(y);
    }

    static Character getHero(HeroType heroType) {
        final Character user = CharacterInventory.getHero(heroType);
        user.setPosition(new Point(2,2));
        return user;
    }

}