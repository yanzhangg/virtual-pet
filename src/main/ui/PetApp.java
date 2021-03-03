package ui;

import model.Cat;
import model.Dog;
import model.PetAnimal;
import model.PetList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Source: code from TellerApp

public class PetApp {
    private static final String JSON_STORE = "./data/petlist.json";
    private PetAnimal animal;
    private PetList petList;
    private Scanner input = new Scanner(System.in);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the pet application
    public PetApp() throws FileNotFoundException {
        petList = new PetList("your pets");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        System.out.println("Welcome to PetZoo!");
        startingMenu();
    }

    // MODIFIES: this
    // EFFECTS: processes user input and loops command menu
    public void startingMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("f")) {
                System.out.println("Do you want to save your pets? Y/N");
                String toSave = input.next();
                while ((!toSave.equals("Y")) && (!toSave.equals("N"))) {
                    System.out.println("Selection not valid, please try again.");
                    toSave = input.next();
                }
                if (toSave.equals("Y")) {
                    savePets();
                }
                keepGoing = false;
            } else {
                menuCommands(command);
            }
        }

        System.out.println("Bye bye!");

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Create a new pet");
        System.out.println("\tb -> View all pets");
        System.out.println("\tc -> Search for a pet");
        System.out.println("\td -> Save pets to file");
        System.out.println("\te -> Load pets from file");
        System.out.println("\tf -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void menuCommands(String command) {
        if (command.equals("a")) {
            createPet();
        } else if (command.equals("b")) {
            if (petList.getPets().isEmpty()) {
                System.out.println("Your pet list is empty! Try creating a pet.");
            } else {
                System.out.println("Here are your pets:");
                System.out.println(petList.viewPetList());
            }
        } else if (command.equals("c")) {
            petSearch();
        } else if (command.equals("d")) {
            savePets();
        } else if (command.equals("e")) {
            loadPets();
        } else {
            System.out.println("Selection not valid, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows users to choose a cat or dog
    private void createPet() {
        System.out.println("\nChoose your pet (cat / dog)");
        String petType = input.next();
        if ((petType.equals("dog")) || (petType.equals("cat"))) {
            choosePet(petType);
        } else {
            System.out.println("\nSorry, a pet " + petType + " is not available :( Please try again.");
            createPet();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes pet choice
    private void choosePet(String pet) {
        if (pet.equals("dog")) {
            nameDog();
        } else {
            nameCat();
        }
    }

    // MODIFIES: this
    // EFFECTS: names pet dog, initializes pet dog, adds pet to list
    private void nameDog() {
        System.out.println("\nGive your dog a name!");
        String dogName = checkName();
        System.out.println("\n" + dogName + " the dog has been created!");
        animal = new Dog(dogName);
        petList.addPetToList(animal);
        secondMenu(animal);
    }

    // MODIFIES: this
    // EFFECTS: names pet cat, initializes pet cat, adds pet to list
    private void nameCat() {
        System.out.println("\nGive your cat a name!");
        String catName = checkName();
        System.out.println("\n" + catName + " the cat has been created!");
        animal = new Cat(catName);
        petList.addPetToList(animal);
        secondMenu(animal);
    }

    // MODIFIES: this
    // EFFECTS: checks whether pet name already exists or not
    private String checkName() {
        boolean bool = true;
        String petName = "";
        do {
            petName = input.next();
            for (PetAnimal pet : petList.getPets()) {
                if (petName.equals(pet.getName())) {
                    bool = false;
                    System.out.println("That name is already taken. Try again!");
                    break;
                } else {
                    bool = true;
                }
            }
        } while (!bool);

        return petName;
    }

    // MODIFIES: this
    // EFFECTS: displays second menu of options to user, includes pet stats and pet actions
    private void secondMenu(PetAnimal animal) {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayPetStats(animal);
            petActionsMenu(animal);
            //determinePet(animal);
            command = input.next();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                determinePet(animal);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays pet stats to user
    private void displayPetStats(PetAnimal animal) {
        System.out.println("\n" + animal.getName() + "'s status:");
        System.out.println("\tFullness: " + animal.getFullnessLevel());
        System.out.println("\tHappiness: " + animal.getHappinessLevel());
        System.out.println("\tSleepiness: " + animal.getEnergyLevel());
    }

    // MODIFIES: this
    // EFFECTS: display pet action options to user
    private void petActionsMenu(PetAnimal animal) {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\ta -> Feed " + animal.getName());
        System.out.println("\tb -> Pet " + animal.getName());
        System.out.println("\tc -> Play with " + animal.getName());
        System.out.println("\td -> Let " + animal.getName() + " sleep");
        System.out.println("\te -> Return to main menu");
    }

    // MODIFIES: this
    // EFFECTS: determines whether pet is dog or cat
    private void determinePet(PetAnimal animal) {
        displayPetStats(animal);
        petActionsMenu(animal);
        String action = input.next();
        if (animal instanceof Dog) {
            dogAction(animal, action);
        } else {
            catAction(animal, action);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user action for dog
    private void dogAction(PetAnimal animal, String action) {
        if (action.equals("a")) {
            dogFeed(animal);
        } else if (action.equals("b")) {
            animal.pet();
            System.out.println("Woof woof!");
        } else if (action.equals("c")) {
            dogPlay(animal);
        } else if (action.equals("d")) {
            animal.sleep();
            System.out.println("ZZZZZZZZ");
        } else if (action.equals("e")) {
            startingMenu();
        } else {
            System.out.println("Selection not valid, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user action for cat
    private void catAction(PetAnimal animal, String action) {
        if (action.equals("a")) {
            catFeed(animal);
        } else if (action.equals("b")) {
            animal.pet();
            System.out.println("Meowwwww");
        } else if (action.equals("c")) {
            catPlay(animal);
        } else if (action.equals("d")) {
            animal.sleep();
            System.out.println("zzzzzzzz");
        } else if (action.equals("e")) {
            startingMenu();
        } else {
            System.out.println("Selection not valid, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: feeds dog action
    private void dogFeed(PetAnimal animal) {
        if (animal.getFullnessLevel() == 100) {
            System.out.println(animal.getName() + " is already full!");
        } else {
            animal.feed();
            System.out.println(animal.getName() + " has been fed!");
        }
    }

    // MODIFIES: this
    // EFFECTS: play with dog action
    private void dogPlay(PetAnimal animal) {
        if (animal.getEnergyLevel() <= 4) {
            System.out.println(animal.getName() + " is too tired to play!");
        } else if (animal.getFullnessLevel() <= 6) {
            System.out.println(animal.getName() + " is too hungry to play!");
        } else {
            animal.play();
            System.out.println("Tug of war is so much fun!");
        }
    }

    // MODIFIES: this
    // EFFECTS: feed dog action
    private void catFeed(PetAnimal animal) {
        if (animal.getFullnessLevel() == 100) {
            System.out.println(animal.getName() + " is already full!");
        } else {
            animal.feed();
            System.out.println(animal.getName() + " has been fed!");
        }
    }

    // MODIFIES: this
    // EFFECTS: play with act action
    private void catPlay(PetAnimal animal) {
        if (animal.getEnergyLevel() <= 1) {
            System.out.println(animal.getName() + " is too tired to play!");
        } else if (animal.getFullnessLevel() == 0) {
            System.out.println(animal.getName() + " is too hungry to play!");
        } else {
            animal.play();
            System.out.println("Hide and seek is so much fun!");
        }
    }

    // EFFECTS: saves the pet list to file
    private void savePets() {
        try {
            jsonWriter.open();
            jsonWriter.write(petList);
            jsonWriter.close();
            System.out.println("Saved " + petList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads pet list from file
    private void loadPets() {
        try {
            petList = jsonReader.read();
            System.out.println("Loaded " + petList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //
    private void petSearch() {
        System.out.println("Name of pet:");
        String name = input.next();
        PetAnimal pet = petList.searchPets(name);
        if (pet == null) {
            System.out.println("That pet does not exist, try again!");
        } else {
            secondMenu(pet);
        }
    }
}

