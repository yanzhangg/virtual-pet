package model;

import model.PetAnimal;
import model.Dog;
import model.Cat;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetTests {
    Dog dog;
    Cat cat;
    PetList petList;

    @BeforeEach
    void runBefore() {
        petList = new PetList("Your pets");
        dog = new Dog("Billy");
        cat = new Cat("Larry");
    }

    @Test
    void testInitialLevels() {
        assertEquals(100, dog.getFullnessLevel());
        assertEquals(100, dog.getHappinessLevel());
        assertEquals(100, dog.getEnergyLevel());

        assertEquals(100, cat.getFullnessLevel());
        assertEquals(100, cat.getHappinessLevel());
        assertEquals(100, cat.getEnergyLevel());

    }

    @Test
    void testGetName() {
        assertEquals("Billy", dog.getName());
        assertEquals("Larry", cat.getName());
    }

    @Test
    void testFeedWhenFull() {
        dog.feed();
        assertEquals(100, dog.getFullnessLevel());

        cat.feed();
        assertEquals(100, cat.getFullnessLevel());
    }

    @Test
    void testFeedWhenNotFull() {
        // dog fullness = 93
        dog.play();
        dog.feed();
        assertEquals(97, dog.getFullnessLevel());

        // cat fullness = 95
        cat.play();
        cat.play();
        cat.play();
        cat.play();
        cat.play();
        cat.feed();
        assertEquals(100, cat.getFullnessLevel());
    }

    @Test
    void testSleepWhenEnergyFull() {
        dog.sleep();
        assertEquals(100, dog.getEnergyLevel());

        cat.sleep();
        assertEquals(100, cat.getEnergyLevel());
    }

    @Test
    void testSleepWhenEnergyNotFull () {
        dog.play();
        dog.play();
        dog.sleep();
        // dog energy = 90
        assertEquals(100, dog.getEnergyLevel());

        cat.play();
        cat.play();
        cat.play();
        cat.play();
        cat.play();
        cat.play();
        cat.play();
        cat.play();
        cat.sleep();
        //cat energy = 84
        assertEquals(99, cat.getEnergyLevel());
    }

    @Test
    void testHappinessWhenFull() {
        // right now happiness never decreases unless set

        // pet
        dog.pet();
        assertEquals(100, dog.getHappinessLevel());
        cat.pet();
        assertEquals(100, cat.getHappinessLevel());

        // play
        dog.play();
        assertEquals(100, dog.getHappinessLevel());
        cat.play();
        assertEquals(100, cat.getHappinessLevel());
    }
    @Test
    void testSetHappinessLevel() {
        dog.setHappinessLevel(50);
        assertEquals(50, dog.getHappinessLevel());

        cat.setHappinessLevel(40);
        assertEquals(40, cat.getHappinessLevel());
    }

    @Test
    void testHappinessWhenNotFull() {
        dog.setHappinessLevel(50);
        dog.play();
        assertEquals(55, dog.getHappinessLevel());

        cat.setHappinessLevel(30);
        cat.play();
        assertEquals(33, cat.getHappinessLevel());
    }

    @Test
    void testDecreaseEnergy() {
        dog.play();
        dog.play();
        assertEquals(90, dog.getEnergyLevel());

        cat.play();
        cat.play();
        assertEquals(96, cat.getEnergyLevel());
    }

    @Test
    void testPettingWhenHappinessNotFull () {
        dog.setHappinessLevel(20);
        dog.pet();
        assertEquals(25, dog.getHappinessLevel());

        cat.setHappinessLevel(94);
        cat.pet();
        assertEquals(99, cat.getHappinessLevel());
    }

    @Test
    void testPettingWhenHappinessFull () {
        dog.pet();
        assertEquals(100, dog.getHappinessLevel());

        cat.pet();
        assertEquals(100, cat.getHappinessLevel());
    }

    @Test
    void testAddPetToList () {
        // empty petList
        assertEquals(0, petList.getPets().size());

        petList.addPetToList(dog);
        assertEquals(1, petList.getPets().size());

        petList.addPetToList(cat);
        assertEquals(2, petList.getPets().size());
    }

    @Test
    void testGetNameOfPets () {
        // empty petList
        assertEquals("", petList.viewPetList());

        petList.addPetToList(dog);
        assertEquals("Billy\n", petList.viewPetList());

        petList.addPetToList(cat);
        assertEquals("Billy\nLarry\n", petList.viewPetList());
    }

}