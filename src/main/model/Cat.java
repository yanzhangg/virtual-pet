package model;

public class Cat extends PetAnimal {
    public Cat(String name) {
        super(name);
    }

    // MODIFIES: this
    // EFFECTS: feed cat action
    public void feed() {
        if (getFullnessLevel() <= 95) {
            this.fullness = this.fullness + 5;
        } else {
            this.fullness = 100;
        }
    }

    // MODIFIES: this
    // EFFECTS: play with cat action
    public void play() {
        if (getHappinessLevel() <= 97) {
            this.happiness = this.happiness + 3;
        } else {
            this.happiness = 100;
        }
        this.energy = this.energy - 2;
        this.fullness = this.fullness - 1;
    }

    // MODIFIES: this
    // EFFECTS: let cat sleep action
    public void sleep() {
        if (getEnergyLevel() <= 85) {
            this.energy = this.energy + 15;
        } else {
            this.energy = 100;
        }
    }

}