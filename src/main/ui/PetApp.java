package ui;

import model.Cat;
import model.Dog;
import model.PetAnimal;
import model.PetList;

import java.util.Scanner;

public class PetApp {
    private PetAnimal animal;
    private PetList petList = new PetList();
    private Scanner input = new Scanner(System.in);

    // EFFECTS: runs the pet application
    public PetApp() {
        System.out.println("Welcome to PetZoo!");
        startingMenu();
    }

    public void startingMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("d")) {
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
        System.out.println("\td -> Quit");
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
//            searchPets();
        } else {
            System.out.println("Selection not valid, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows users to choose a cat or dog
    private void createPet() {
        System.out.println("\nChoose your pet (cat / dog)");
        String petType = input.nextLine();
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
    // EFFECTS: names your pet dog
    private void nameDog() {
        System.out.println("\nGive your dog a name!");
        String dogName = input.nextLine();
        System.out.println("\n" + dogName + " the dog has been created!");
        animal = new Dog(dogName);
        petList.addPetToList(animal);
        displayPetStats(dogName);

    }

    // MODIFIES: this
    // EFFECTS: names your pet cat
    private void nameCat() {
        System.out.println("\nGive your cat a name!");
        String catName = input.nextLine();
        System.out.println("\n" + catName + " the cat has been created!");
        animal = new Cat(catName);
        petList.addPetToList(animal);
        displayPetStats(catName);

    }

    //EFFECTS: displays cat stats to user
    private void displayPetStats(String name) {
        System.out.println("\n" + name + "'s status:");
        System.out.println("\tFullness: " + animal.getFullnessLevel());
        System.out.println("\tHappiness: " + animal.getHappinessLevel());
        System.out.println("\tSleepiness: " + animal.getEnergyLevel());
        runPetActions(name);
    }

    // MODIFIES: this
    // EFFECTS: action options for cat
    private void runPetActions(String name) {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t1. Feed " + name);
        System.out.println("\t2. Pet " + name);
        System.out.println("\t3. Play with " + name);
        System.out.println("\t4. Let " + name + " sleep");
        System.out.println("\t5. Return to main menu");
        determinePet(name);
    }

    public void determinePet(String name) {
        int action = input.nextInt();
        if (animal.getClass().getName() == "dog") {
            dogAction(name, action);
        } else {
            catAction(name, action);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user action for dog
    private void dogAction(String name, int action) {
        if (action == 1) {
            dogFeed(name);
        } else if (action == 2) {
            animal.pet();
        } else if (action == 3) {
            dogPlay(name);
        } else if (action == 4) {
            animal.sleep();
        } else if (action == 5) {
            displayMenu();
        } else {
            System.out.println("Selection not valid, please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user action for cat
    private void catAction(String name, int action) {
        if (action == 1) {
            catFeed(name);
        } else if (action == 2) {
            animal.pet();
        } else if (action == 3) {
            catPlay(name);
        } else if (action == 4) {
            animal.sleep();
        } else if (action == 5) {
            displayMenu();
        } else {
            System.out.println("Selection not valid, please try again.");
        }
    }

    private void dogFeed(String name) {
        if (animal.getFullnessLevel() == 100) {
            System.out.println(name + " is already full!");
        } else {
            animal.feed();
            System.out.println(name + " has been fed!");
        }
    }

    private void dogPlay(String name) {
        if (animal.getEnergyLevel() <= 4) {
            System.out.println(name + " is too tired to play!");
        } else if (animal.getFullnessLevel() <= 6) {
            System.out.println(name + " is too hungry to play!");
        } else {
            animal.play();
            System.out.println("Tug of war is so much fun!");
        }
    }

    private void catFeed(String name) {
        if (animal.getFullnessLevel() == 100) {
            System.out.println(name + " is already full!");
        } else {
            animal.feed();
            System.out.println(name + " has been fed!");
        }
    }

    private void catPlay(String name) {
        if (animal.getEnergyLevel() <= 1) {
            System.out.println(name + " is too tired to play!");
        } else if (animal.getFullnessLevel() == 0) {
            System.out.println(name + " is too hungry to play!");
        } else {
            animal.play();
            System.out.println("Hide and seek is so much fun!");
        }
    }
}

