import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;


public class Main {

    //Requirement: Loading save data from a text file, allowing for the program to be repopulated
    public static Zoo loadFromDisk() {
        Zoo loadedZoo = null;
        try {
            File zooFile = new File("ZooDetails.txt");
            if (zooFile.exists()) {
                BufferedReader zooReader = new BufferedReader(new FileReader(zooFile));
                String name = zooReader.readLine();
                loadedZoo = new Zoo(name);
                zooReader.close();

                File animalFile = new File("AnimalDetails.txt");
                if (animalFile.exists()) {
                    BufferedReader animalReader = new BufferedReader(new FileReader(animalFile));
                    String line;
                    while ((line = animalReader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length < 6) continue; // Skip lines that are missing data

                        String type = parts[0];
                        String aName = parts[1];
                        int age = Integer.parseInt(parts[2].trim());
                        String colour = parts[3].trim();
                        double weight = Double.parseDouble(parts[4]);
                        String extra = parts[5].trim(); // This is the breed, wingspan, or speed

                        // Re-instantiate the correct animal type with its unique attribute
                        if (type.equals("Dog")) {
                            loadedZoo.addAnimal(new Dog(aName, age, colour, weight, extra));
                        } else if (type.equals("Eagle")) {
                            double wingSpan = Double.parseDouble(extra);
                            loadedZoo.addAnimal(new Eagle(aName, age, colour, weight, wingSpan));
                        } else if (type.equals("Penguin")) {
                            double maxDiveDepth = Double.parseDouble(extra);
                            loadedZoo.addAnimal(new Penguin(aName, age, colour, weight, maxDiveDepth));
                        }
                    }
                    animalReader.close();
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("No previous valid data found or file error, starting fresh.");
        }

        return (loadedZoo != null) ? loadedZoo : new Zoo("Default Zoo");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Zoo myZoo = loadFromDisk();
        boolean running = true;

        if (myZoo.getZooName().equalsIgnoreCase("Default Zoo")) {
            System.out.println("No existing zoo found. Let's set one up!");
            System.out.print("Enter the name for your new Zoo: ");
            String customName = input.nextLine();
            myZoo.setZooName(customName);
        }

            String adminPIN = "1234";
            String PIN = "";

            System.out.println("Welcome to the Zoo Management System!");

            while (running) {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Add Animal");
                System.out.println("2. Remove Animal");
                System.out.println("3. Modify Animal Details");
                System.out.println("4. Display All Animals");
                System.out.println("5. Search Database (by Name or Colour)");
                System.out.println("6. Daily Care Tasks");
                System.out.println("7. Generate Report");
                System.out.println("8. Exit");
                System.out.println("Enter your choice: ");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1: // Add Animal
                        System.out.println("\n--- Adding New Animal ---");
                        // Requirement 2: Informing the user about blank fields
                        System.out.println("NOTE: If name or colour is left blank, this animal will NOT be saved to disk.");
                        System.out.print("Select Animal Type (Dog/Eagle/Penguin): ");
                        String type = input.nextLine();
                        System.out.print("Enter Animal Name: ");
                        String name = input.nextLine();
                        System.out.print("Enter Animal Age: ");
                        int age = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter Animal Colour: ");
                        String colour = input.nextLine();
                        System.out.print("Enter Animal Weight: ");
                        double weight = input.nextDouble();
                        input.nextLine();

                        if (type.equalsIgnoreCase("Dog")) {
                            System.out.println("Enter Animal Breed (Husky, Labrador, Poodle, etc.): ");
                            String breed = input.nextLine();
                            myZoo.addAnimal(new Dog(name, age, colour, weight, breed));
                        } else if (type.equalsIgnoreCase("Eagle")) {
                            System.out.println("Enter Wing Span (centimeters): ");
                            double wingSpan = input.nextDouble();
                            input.nextLine();
                            myZoo.addAnimal(new Eagle(name, age, colour, weight, wingSpan));
                        } else if (type.equalsIgnoreCase("Penguin")) {
                            System.out.println("Enter Maximum Dive Depth (meters): ");
                            double maxDiveDepth = input.nextDouble();
                            input.nextLine();
                            myZoo.addAnimal(new Penguin(name, age, colour, weight, maxDiveDepth));
                        } else {
                            System.out.println("Invalid Animal Type");
                        }
                        break;

                    case 2: //Remove Animal
                        System.out.print("Enter name of animal to remove: ");
                        String removeName = input.nextLine();

                        //Authorization to ensure that the user doesn't accidentally remove wrong animal
                        System.out.print("\n--- Security Check ---");
                        System.out.print("Enter Admin PIN to proceed: ");
                        PIN = input.nextLine();

                        if (PIN.equals(adminPIN)) {
                            System.out.println("PIN Accepted");
                            myZoo.removeAnimal(removeName);
                        } else {
                            System.out.println("Access denied: Incorrect PIN");
                        }
                        break;

                    case 3: //Modify Animal
                        System.out.print("Enter name of animal to modify: ");
                        String modifyName = input.nextLine();
                        Animal found = myZoo.getAnimal(modifyName);

                        if (found != null) {
                            System.out.print("Enter Admin PIN to proceed: ");
                            PIN = input.nextLine();

                            if (PIN.equals(adminPIN)) {
                                boolean modifying = true;
                                while (modifying) {
                                    System.out.println("\nUpdating : " + found.getName());
                                    System.out.println("1. Change Name");
                                    System.out.println("2. Change Age");
                                    System.out.println("3. Change Colour");
                                    System.out.println("4. Change Weight");
                                    if (found instanceof Dog) System.out.println("5. Change Breed");
                                    else if (found instanceof Eagle) System.out.println("5. Change Wing Span");
                                    else if (found instanceof Penguin)
                                        System.out.println("5. Change Maximum Dive Depth");
                                    System.out.println("6. Finish");
                                    System.out.print("Enter your choice: ");

                                    int modChoice = input.nextInt();
                                    input.nextLine();

                                    switch (modChoice) {
                                        case 1:
                                            System.out.println("Enter New Animal Name: ");
                                            found.setName(input.nextLine());
                                            System.out.println("Name Updated!");
                                            break;
                                        case 2:
                                            System.out.println("Enter New Animal Age: ");
                                            found.setAge(input.nextInt());
                                            input.nextLine();
                                            System.out.println("Age Updated!");
                                            break;
                                        case 3:
                                            System.out.println("Enter New Animal Colour: ");
                                            found.setColour(input.nextLine());
                                            System.out.println("Colour Updated!");
                                            break;
                                        case 4:
                                            System.out.println("Enter New Animal Weight: ");
                                            found.setWeight(input.nextDouble());
                                            input.nextLine();
                                            System.out.println("Weight Updated!");
                                            break;
                                        case 5:
                                            if (found instanceof Dog) {
                                                System.out.println("Enter New Breed: ");
                                                String newBreed = input.nextLine();
                                                ((Dog) found).setBreed(newBreed);
                                                System.out.println("Breed Updated!");
                                            } else if (found instanceof Eagle) {
                                                System.out.println("Enter New Wing Span: ");
                                                double newWingSpan = input.nextDouble();
                                                input.nextLine();
                                                ((Eagle) found).setWingSpan(newWingSpan);
                                                System.out.println("Wing Span Updated!");
                                            } else if (found instanceof Penguin) {
                                                System.out.println("Enter New Maximum Dive Depth: ");
                                                double newMaxDiveDepth = input.nextDouble();
                                                input.nextLine();
                                                ((Penguin) found).setMaxDiveDepth(newMaxDiveDepth);
                                                System.out.println("Maximum Dive Depth Updated!");
                                            }
                                            break;
                                        case 6:
                                            modifying = false;
                                            System.out.println("Exiting Modification Menu");
                                            break;
                                    }
                                }
                            }
                        }
                        break;

                    case 4: //Display All Animals
                        myZoo.displayAnimals();
                        break;
                    case 5: //Search Database
                        System.out.println("Please enter the name or colour of the animal that you would like to search for: ");
                        String query = input.nextLine();
                        myZoo.searchAnimals(query);
                        break;
                    case 6: //Daily Care Tasks
                        myZoo.performDailyCare();
                        break;

                    case 7: //Generate Report
                        myZoo.generateZooReport();
                        break;

                    case 8:
                        System.out.println("Saving data and exiting...");
                        myZoo.saveToDisk(); // Requirement 9
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid Input! Please enter a number between 1 and 8");
                        break;
                }
            }

    }
}