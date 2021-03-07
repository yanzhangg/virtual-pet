package persistence;

import model.PetList;
import model.PetAnimal;
import model.Dog;
import model.Cat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PetList petList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPetList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPetList.json");
        try {
            PetList petList = reader.read();
            assertEquals("My pet list", petList.getName());
            assertEquals(0, petList.numPets());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderPetList() {
        JsonReader reader = new JsonReader("./data/testReaderPetList.json");
        try {
            PetList pl = reader.read();
            assertEquals("My pet list", pl.getName());
            ArrayList<PetAnimal> petList = pl.getPets();
            assertEquals(2, petList.size());
            checkPetAnimal("Billy", "dog",100, 100, 100, petList.get(0));
            checkPetAnimal("Larry", "cat", 100, 100, 100, petList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
