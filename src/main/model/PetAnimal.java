package model;

import java.util.Timer;

public abstract class PetAnimal {
    protected String name; // the name of the pet
    protected int happiness; // the happiness level of the pet, 100 = happy 0 = sad
    protected int fullness; // the fullness of the pet, 100 = full 0 = hungry
    protected int energy; // the sleepiness of the pet, 100 = full energy, 0 = sleepy

    // constructor
    public PetAnimal(String name) {
        this.name = name;
        this.fullness = 100;
        this.happiness = 100;
        this.energy = 100;
    }

    public String getName() {
        return name;
    }

    public int getFullnessLevel() {
        return fullness;
    }

    public int getHappinessLevel() {
        return happiness;
    }

    public int getEnergyLevel() {
        return energy;
    }

    public abstract void feed();

    public abstract void play();

    public abstract void sleep();

    public abstract void pet();

//    public void passTime() {
//        fullness--;
//        happiness--;
//        energy--;
//    }


}