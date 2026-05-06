import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class Zoo {
    private String zooName;
    private ArrayList<Animal> animalList;

    public Zoo(String zooName) {
        this.zooName = zooName;
        this.animalList = new ArrayList<>();
    }

    public String getZooName() {
        return zooName;
    }
    public void setZooName(String newZooName) {
        this.zooName = newZooName;
    }

    //Requirement: Allows animals to be added to the zoo
    public void addAnimal(Animal animal) {
        if (animal != null) {
            this.animalList.add(animal);
            System.out.println(animal.getName() + " has been added to " + zooName);
        }

    }

    // Requirement: Allows animals to be removed from the zoo
    public void removeAnimal(String name) {
        if (animalList.isEmpty()) {
            System.out.println("The zoo is empty. Nothing to remove.");
            return;
        }

        for (int i = 0; i < this.animalList.size(); i++) {
            if (this.animalList.get(i).getName().equalsIgnoreCase(name)) {
                this.animalList.remove(i);
                System.out.println(name + " has been removed from " + zooName);
                return;
            }
        }
        System.out.println("Animal not found");
    }

    //Requirement: Partial step for modifying animals within zoo
    public Animal getAnimal(String name) {
        for (Animal a : animalList) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }


    // Requirement: Display all animals
    public void displayAnimals() {
        System.out.println("\n--- " + zooName + " Inventory ---");
        if (this.animalList.isEmpty()) {
            System.out.println("No animals have been added to " + zooName);
        }  else {
            for (Animal animal : this.animalList) {
                System.out.println(animal.toString() + " | Sound: " + animal.makeSound());
            }
        }
    }


    // Requirement: Search by name or colour
    public void searchAnimals(String query) {
        boolean found = false;
        System.out.println("\n--- Searching for: '" + query + "' ---");

        for (Animal a : animalList) {
            if (a.getName().equalsIgnoreCase(query) || a.getColour().equalsIgnoreCase(query)) {

                // 1. Display sought animal's details
                System.out.println("Match Found: " + a.toString());

                // 2. Call its makeSound() method as required
                System.out.println("The animal responds: " + a.makeSound());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No animals found with the name or color: " + query);
        }
    }

    // Requirement: Specialized daily care based on implemented interfaces
    public void performDailyCare() {
        System.out.println("\n=== " + zooName.toUpperCase() + " DAILY CARE ROUTINE ===");

        if (this.animalList.isEmpty()) {
            System.out.println("The zoo is empty. No care tasks to perform.");
            return;
        }

        for (Animal a : this.animalList) {
            System.out.println("\n[Care Record: " + a.getName() + "]");

            // 1. General Care (For all animals)
            System.out.println("- Basic Needs: Food and water levels checked.");

            // 2. Specialized Care (Interface-based)
            if (a instanceof LandBased) {
                System.out.println("- Land Activity: " + ((LandBased) a).walk());
                ((LandBased) a).vaccinate();
            }

            if (a instanceof Flyable) {
                System.out.println("- Aerial Activity: " + ((Flyable) a).fly());
                ((Flyable) a).checkWingHealth();
            }

            if (a instanceof Swimming) {
                System.out.println("- Aquatic Activity: " + ((Swimming) a).swim());
                ((Swimming) a).checkOxygenLevels();
            }
        }
        System.out.println("\n========================================");
    }

    //Requirement: Saving current session contents to disk
    public void saveToDisk() {
        try {
            // 1. Save Zoo Details
            PrintWriter zooWriter = new PrintWriter(new FileOutputStream("ZooDetails.txt"));
            zooWriter.print(this.zooName);
            zooWriter.close();

            // 2. Save Animal Records
            PrintWriter animalWriter = new PrintWriter(new FileOutputStream("AnimalDetails.txt"));
            for (Animal a : animalList) {

                // UPDATED LOGIC: Check for null AND blank (length of zero) strings
                boolean hasValidName = a.getName() != null && !a.getName().trim().isEmpty();
                boolean hasValidColour = a.getColour() != null && !a.getColour().trim().isEmpty();

                if (hasValidName && hasValidColour) {
                    String type = a.getClass().getSimpleName();

                    // CORRECT ORDER: Name, Age, Colour, Weight
                    String line = type + "," + a.getName() + "," + a.getAge() + "," + a.getColour() + "," + a.getWeight();

                    // Append the specific extra attribute
                    if (a instanceof Dog) {
                        line += "," + ((Dog) a).getBreed();
                    } else if (a instanceof Eagle) {
                        line += "," + ((Eagle) a).getWingSpan();
                    } else if (a instanceof Penguin) {
                        line += "," + ((Penguin) a).getMaxDiveDepth();
                    }

                    animalWriter.println(line);
                } else {
                    // Requirement 9 Hint: Informing that invalid records were skipped
                    System.out.println("Skipping save for " + (a.getName() == null || a.getName().isEmpty() ? "Unknown Animal" : a.getName()) + " due to blank fields.");
                }
            }
            animalWriter.close();
            System.out.println("Data successfully saved to disk.");
        } catch (IOException e) {
            System.out.println("Error saving to disk: " + e.getMessage());
        }
    }

    private String findDominantInList(ArrayList<Animal> subList) {
        if (subList.isEmpty()) return "N/A";

        String dominant = "None";
        int maxCount = 0;

        for (Animal a : subList) {
            int currentCount = 0;
            for (Animal temp : subList) {
                if (temp.getColour().equalsIgnoreCase(a.getColour())) {
                    currentCount++;
                }
            }
            if (currentCount > maxCount) {
                maxCount = currentCount;
                dominant = a.getColour();
            }
        }
        return dominant + " (" + maxCount + ")";
    }

    public void generateZooReport() {
        ArrayList<Animal> dogsOnly = new ArrayList<>();
        ArrayList<Animal> eaglesOnly = new ArrayList<>();
        ArrayList<Animal> penguinsOnly = new ArrayList<>();

        // Sort animals into their specific type lists
        for (Animal a : animalList) {
            if (a instanceof Dog) dogsOnly.add(a);
            else if (a instanceof Eagle) eaglesOnly.add(a);
            else if (a instanceof Penguin) penguinsOnly.add(a);
        }

        System.out.println("\n--- " + zooName.toUpperCase() + " TYPE-SPECIFIC REPORT ---");

        System.out.println("DOGS:");
        System.out.println("- Total: " + dogsOnly.size());
        System.out.println("- Dominant Colour: " + findDominantInList(dogsOnly));

        System.out.println("\nEAGLES:");
        System.out.println("- Total: " + eaglesOnly.size());
        System.out.println("- Dominant Colour: " + findDominantInList(eaglesOnly));

        System.out.println("\nPENGUINS:");
        System.out.println("- Total: " + penguinsOnly.size());
        System.out.println("- Dominant Colour: " + findDominantInList(penguinsOnly));

        System.out.println("------------------------------------------");
        System.out.println("GRAND TOTAL ANIMALS: " + animalList.size());
        System.out.println("==========================================\n");
    }
}