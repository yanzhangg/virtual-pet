package persistence;

import model.PetAnimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPetAnimal(String name, String type, int happiness, int fullness, int energy, PetAnimal animal) {
        assertEquals(name, animal.getName());
        assertEquals(type, animal.getType());
        assertEquals(happiness, animal.getHappinessLevel());
        assertEquals(fullness, animal.getFullnessLevel());
        assertEquals(energy, animal.getEnergyLevel());
    }
}