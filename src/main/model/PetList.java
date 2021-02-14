package model;

import java.util.ArrayList;

public class PetList {
    private ArrayList<PetAnimal> petList;
    private Dog dog;
    private Cat cat;

    // EFFECTS: constructs an empty pet list
    public PetList() {
        this.petList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds pet to list
    public void addPetToList(PetAnimal animal) {
        petList.add(animal);
    }

    // getter
    public ArrayList<PetAnimal> getPets() {
        return petList;
    }

    // MODIFIES: this
    // EFFECTS: view names of all pets
    public String viewPetList() {
        String list = "";
        for (int i = 0; i < petList.size(); i++) {
            list = list + petList.get(i).getName() + "\n";
        }
        return list;
    }

    // MODIFIES: this
    // EFFECTS: search for pet by name
    // to do next phase
//    public void searchPets() {
//
//    }

}
