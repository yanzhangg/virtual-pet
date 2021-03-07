package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of pets
public class PetList implements Writable {
    private String name; // the name of pet list
    private ArrayList<PetAnimal> petList; // list of pets

    // EFFECTS: constructs an empty pet list
    public PetList(String name) {
        this.name = name;
        this.petList = new ArrayList<>();
    }

    // getter
    public String getName() {
        return name;
    }

    // EFFECTS: returns number of pets in this pet list
    public int numPets() {
        return petList.size();
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
    // EFFECTS: search for pet by name, return pet object if found, null if not
    public PetAnimal searchPets(String name) {
        for (PetAnimal p : petList) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("petList", petListToJson());
        return json;
    }

    // EFFECTS: returns pet animals in this pet list as a JSON array
    private JSONArray petListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PetAnimal p : petList) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
