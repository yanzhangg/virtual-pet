package persistence;

import model.PetList;
import model.PetAnimal;
import model.Dog;
import model.Cat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PetList petList = new PetList("My pet list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPetList() {
        try {
            PetList petList = new PetList("My pet list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPetList.json");
            writer.open();
            writer.write(petList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPetList.json");
            petList = reader.read();
            assertEquals("My pet list", petList.getName());
            assertEquals(0, petList.numPets());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterPetList() {
        try {
            PetList pl = new PetList("My pet list");
            pl.addPetToList(new Dog("Billy"));
            pl.addPetToList(new Cat("Larry"));
            JsonWriter writer = new JsonWriter("./data/testWriterPetList.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterPetList.json");
            pl = reader.read();
            assertEquals("My pet list", pl.getName());
            ArrayList<PetAnimal> petList = pl.getPets();
            assertEquals(2, petList.size());
            checkPetAnimal("Billy", "dog", 100, 100, 100, petList.get(0));
            checkPetAnimal("Larry", "cat", 100, 100, 100, petList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}