public class Dog extends Animal implements LandBased {
    private String breed;

    public Dog(String name, int age, String colour, double weight, String breed) {
        super(name, age, colour, weight); //Passes name, age, colour and weight to the Animal class
        this.breed = breed;
    }


    //Requirement: makeSound() method that returns animal noise and a description
    @Override
    public String makeSound() {
        return String.format("Woof! Woof! I am %s, a %d year old %s %s weighing %.2fkg.",
                name, age, colour, breed, weight);
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.breed;
    }

    @Override
    public String walk() {
        return name + " the " + breed + " is trotting and wagging its tail!";
    }

    @Override
    public void vaccinate() {
        System.out.println("--- MEDICAL RECORD UPDATE ---");
        System.out.println("Animal: " + name + " (Dog)");
        System.out.println("Action: Administering annual rabies and parvo vaccine.");
        System.out.println("Status: Completed for " + colour + " " + breed + ".");
    }

    //Requirement: Partial step for modifying animals
    public String getBreed() {return breed;}
    public void setBreed(String breed) {this.breed = breed;}
}
