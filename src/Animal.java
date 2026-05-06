public abstract class Animal {
    // Protected access modifier allows subclasses to access variables directly
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

    // Abstract method requires all subclasses to implement their own noise
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

    public void setName(String name) {this.name = name;}
    public void setAge(int age) {this.age = age;}
    public void setColour(String colour) {this.colour = colour;}
    public void setWeight(double weight) {this.weight = weight;}

    // Overriding toString() provides a default CVS for all animals
    @Override
    public String toString() {
        return name+","+age+","+colour+","+weight;
    }
}
