package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Timer;

// Represents a pet animal having a name, and a happiness, fullness, and energy level
public abstract class PetAnimal implements Writable {
    protected String name; // the name of the pet
    protected int happiness; // the happiness level of the pet, 100 = happy 0 = sad
    protected int fullness; // the fullness of the pet, 100 = full 0 = hungry
    protected int energy; // the sleepiness of the pet, 100 = full energy, 0 = sleepy
    protected String type;

    // EFFECTS: constructs a pet animal
    public PetAnimal(String name) {
        this.name = name;
        this.happiness = 100;
        this.fullness = 100;
        this.energy = 100;
    }

    public PetAnimal(String name, int happiness, int fullness, int energy) {
        this.name = name;
        this.happiness = happiness;
        this.fullness = fullness;
        this.energy = energy;
    }

    // getter
    public String getName() {
        return name;
    }

    // getter
    public int getFullnessLevel() {
        return fullness;
    }

    // getter
    public int getHappinessLevel() {
        return happiness;
    }

    // getter
    public int getEnergyLevel() {
        return energy;
    }

    // setter
    public int setHappinessLevel(Integer num) {
        return this.happiness = num;
    }

    public abstract void feed();

    public abstract void play();

    public abstract void sleep();

    // MODIFIES: this
    // EFFECTS: petting the pet action
    public void pet() {
        if (getHappinessLevel() <= 95) {
            this.happiness = this.happiness + 5;
        } else {
            this.happiness = 100;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("happiness", happiness);
        json.put("fullness", fullness);
        json.put("energy", energy);
        return json;
    }

}
    // MODIFIES: this
    // EFFECTS: decrease levels as time passes
    // to do next phase
//    public void passTime() {
//        fullness--;
//        happiness--;
//        energy--;
//    }

