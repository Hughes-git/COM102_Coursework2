public abstract class Animal {
    protected String name;
    protected int age;
    protected String colour;
    protected double weight;


    public Animal(String name, int age, String colour, double weight) {
        this.name = name;
        this.age = age;
        this.colour = colour;
        this.weight = weight;
    }

    public abstract String makeSound();

    public String getName() {
        return name;
    }
    public int  getAge() {
        return age;
    }
    public String getColour() {
        return colour;
    }
    public double getWeight() {
        return weight;
    }

    public String toString() {
        return name+","+age+","+colour+","+weight;
    }
}
