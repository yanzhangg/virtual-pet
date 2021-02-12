package model;

public class Cat extends PetAnimal {
    public Cat(String name) {
        super(name);
    }

    public void feed() {
        if (getFullnessLevel() <= 95) {
            this.fullness = this.fullness + 5;
        } else {
            this.fullness = 100;
        }
    }

    public void play() {
        if (getHappinessLevel() <= 97) {
            this.happiness = this.happiness + 3;
        } else {
            this.happiness = 100;
        }
        this.energy = this.energy - 2;
        this.fullness = this.fullness - 1;
    }

    public void sleep() {
        if (getEnergyLevel() <= 85) {
            this.energy = this.energy + 15;
        } else {
            this.energy = 100;
        }
        System.out.println("zzzzzzzz");
    }

    public void pet() {
        if (getHappinessLevel() <= 95) {
            this.happiness = this.happiness + 5;
        } else {
            this.happiness = 100;
        }
        System.out.println("Meowwww");

    }

}
