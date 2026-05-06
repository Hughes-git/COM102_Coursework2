public class Eagle extends  Animal implements Flyable, LandBased{
    private double wingSpan;

    public Eagle(String name, int age, String colour, double weight, double wingSpan) {
        super(name, age, colour, weight);
        this.wingSpan = wingSpan;
    }


    @Override
    public String makeSound() {
        return String.format("Screech! I am %s, a %d year old %s eagle weighing %.2fkg with a %.1fcm wingspan.",
                name, age, colour, weight, wingSpan);
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.wingSpan;
    }

    @Override
    public String fly() {
        return name + " soars through the sky with a " + wingSpan + "cm wingspan.";
    }

    @Override
    public void checkWingHealth() {
        System.out.println("Checking " + name + "'s feathers for parasites.");
    }

    @Override
    public String walk() {
        return getName() + " the Eagle hops awkwardly on the ground";
    }

    @Override
    public void vaccinate() {
        System.out.println("--- MEDICAL RECORD UPDATE ---");
        System.out.println("Animal: " + name + " (Eagle)");
        System.out.println("Action: Administering avian flu vaccine.");
        System.out.println("Status: Completed for " + colour + " eagle.");
    }

    //Requirement: Partial step for modifying animals
    public double getWingSpan() {return wingSpan;}
    public void setWingSpan(double wingSpan) {this.wingSpan = wingSpan;}
}
