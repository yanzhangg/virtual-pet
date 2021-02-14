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

    // MODIFIES: this
    // EFFECTS: processes user input and loops command menu
    // source: code from TellerApp
    public void startingMenu() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
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
        secondMenu(dogName);
    }

    // MODIFIES: this
    // EFFECTS: names pet cat, initializes pet cat, adds pet to list
    private void nameCat() {
        System.out.println("\nGive your cat a name!");
        String catName = checkName();
        System.out.println("\n" + catName + " the cat has been created!");
        animal = new Cat(catName);
        petList.addPetToList(animal);
        secondMenu(catName);
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
    private void secondMenu(String name) {
        boolean keepGoing = true;

        while (keepGoing) {
            displayPetStats(name);
            petActionsMenu(name);
        }
    }

    // MODIFIES: this
    //EFFECTS: displays pet stats to user
    private void displayPetStats(String name) {
        System.out.println("\n" + name + "'s status:");
        System.out.println("\tFullness: " + animal.getFullnessLevel());
        System.out.println("\tHappiness: " + animal.getHappinessLevel());
        System.out.println("\tSleepiness: " + animal.getEnergyLevel());
    }

    // MODIFIES: this
    // EFFECTS: display pet action options to user
    private void petActionsMenu(String name) {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\ta -> Feed " + name);
        System.out.println("\tb -> Pet " + name);
        System.out.println("\tc -> Play with " + name);
        System.out.println("\td -> Let " + name + " sleep");
        System.out.println("\te -> Return to main menu");
        determinePet(name);
    }

    // MODIFIES: this
    // EFFECTS: determines whether pet is dog or cat
    private void determinePet(String name) {
        String action = input.next();
        if (animal instanceof Dog) {
            dogAction(name, action);
        } else {
            catAction(name, action);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user action for dog
    private void dogAction(String name, String action) {
        if (action.equals("a")) {
            dogFeed(name);
        } else if (action.equals("b")) {
            animal.pet();
            System.out.println("Woof woof!");
        } else if (action.equals("c")) {
            dogPlay(name);
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
    private void catAction(String name, String action) {
        if (action.equals("a")) {
            catFeed(name);
        } else if (action.equals("b")) {
            animal.pet();
            System.out.println("Meowwwww");
        } else if (action.equals("c")) {
            catPlay(name);
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
    private void dogFeed(String name) {
        if (animal.getFullnessLevel() == 100) {
            System.out.println(name + " is already full!");
        } else {
            animal.feed();
            System.out.println(name + " has been fed!");
        }
    }

    // MODIFIES: this
    // EFFECTS: play with dog action
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

    // MODIFIES: this
    // EFFECTS: feed dog action
    private void catFeed(String name) {
        if (animal.getFullnessLevel() == 100) {
            System.out.println(name + " is already full!");
        } else {
            animal.feed();
            System.out.println(name + " has been fed!");
        }
    }

    // MODIFIES: this
    // EFFECTS: play with act action
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
