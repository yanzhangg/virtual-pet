package model;

public class Dog extends PetAnimal {
    public Dog(String name) {
        super(name);
    }

    // MODIFIES: this
    // EFFECTS: feed dog action
    public void feed() {
        if (getFullnessLevel() <= 96) {
            this.fullness = this.fullness + 4;
        } else {
            this.fullness = 100;
        }
    }

    // MODIFIES: this
    // EFFECTS: play with dog action
    public void play() {
        if (getHappinessLevel() <= 95) {
            this.happiness = this.happiness + 5;
        } else {
            this.happiness = 100;
        }
        this.energy = this.energy - 5;
        this.fullness = this.fullness - 7;
    }

    // MODIFIES: this
    // EFFECTS: let dog sleep action
    public void sleep() {
        if (getEnergyLevel() <= 90) {
            this.energy = this.energy + 10;
        } else {
            this.energy = 100;
        }
    }


}
