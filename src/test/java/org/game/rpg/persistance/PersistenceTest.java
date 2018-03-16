package org.game.rpg.persistance;

import org.game.rpg.inventory.characterInventory.Character;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.game.rpg.utils.TestUtil.prepareListOfEntities;

/**
 * Created by amits2526 on 3/15/2018.
 */
public class PersistenceTest {

    private static final String RESOURCE_PATH = "src/main/resources/%s.txt";
    private static final String TEST_FILE_NAME = "test";
    private Persistence persistence;

    @Rule
    public SystemOutRule outRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() throws Exception {
        outRule.clearLog();
        persistence = new FilePersistence();
    }

    @After
    public void tearDown() throws Exception {
        outRule.clearLog();
    }


    @Test
    public void persist_ValidEntities_WillSaveGameSuccessfully() throws Exception {

        try {
            persistence.persist(TEST_FILE_NAME, prepareListOfEntities());
            assertThat(outRule.getLog()).contains("Game Saved Successfully");
        } finally {
            Path testFilePath = Paths.get(format(RESOURCE_PATH, TEST_FILE_NAME));
            if (Files.exists(testFilePath)) {
                Files.delete(testFilePath);
            }
        }
    }

    @Test
    public void load() throws Exception {
        try {
            List<Character> inputEntities = prepareListOfEntities();
            persistence.persist(TEST_FILE_NAME, inputEntities);
            List<Character> outputEntities = persistence.load(TEST_FILE_NAME);
            assertThat(outputEntities).isEqualTo(inputEntities);
        } finally {
            Path testFilePath = Paths.get(format(RESOURCE_PATH, TEST_FILE_NAME));
            if (Files.exists(testFilePath)) {
                Files.delete(testFilePath);
            }
        }
    }
}