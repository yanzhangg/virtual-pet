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

    public void addPetToList(PetAnimal animal) {
        petList.add(animal);
    }

    public ArrayList<PetAnimal> getPets() {
        return petList;
    }

    public String viewPetList() {
        String list = "";
        for (int i = 0; i < petList.size(); i++) {
            list = list + " " + petList.get(i).getName();
        }
        return list;
    }

    public void searchPets() {

    }
}
