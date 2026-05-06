public class Penguin extends  Animal implements Swimming, LandBased{
    private double maxDiveDepth;

    public Penguin(String name, int age, String colour, double weight, double maxDiveDepth) {
        super(name, age, colour, weight);
        this.maxDiveDepth = maxDiveDepth;
    }


    @Override
    public String makeSound() {
        return String.format("Honk! Honk! I am %s, a %d year old %s penguin weighing %.2fkg with a %.2fm dive depth.",
                name, age, colour, weight, maxDiveDepth);
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.maxDiveDepth;
    }

    @Override
    public String swim() {
        return name + " is belly-flopping into the water and swimming!";
    }

    @Override
    public void checkOxygenLevels() {
        System.out.println("Checking " + name + "'s lung capacity for a " + maxDiveDepth + "m dive.");
    }

    @Override
    public String walk() {
        return getName() + " the Penguin waddles slowly";
    }

    @Override
    public void vaccinate() {
        System.out.println("--- MEDICAL RECORD UPDATE ---");
        System.out.println("Animal: " + name + " (Penguin)");
        System.out.println("Action: Administering avian flu protection.");
        System.out.println("Status: Completed for " + colour + " penguin.");
    }

    //Requirement: Partial step for modifying animals
    public double getMaxDiveDepth() {return maxDiveDepth;}
    public void setMaxDiveDepth(double maxDiveDepth) {this.maxDiveDepth = maxDiveDepth;}
}
