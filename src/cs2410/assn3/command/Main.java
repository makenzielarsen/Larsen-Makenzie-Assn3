package cs2410.assn3.command;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * The is the main class, Main. This class runs a interactive user-face through the command line. It accesses a previously
 * existing directory of students and gives the user the ability to add new students to their directory. These new students
 * are added to the directory data file so that next time the program is run the newer student's data can still be accessed.
 *
 * @author Makenzie Larsen
 * @version 1
 */
public class Main {

    /**
     * The printMenu function prints out a list of options the user is allowed to choose to manipulate their directory.
     * Listed in menu format.
     */
    public static void printMenu() {
        System.out.println("Menu: ");
        System.out.println("1. List directory contents");
        System.out.println("2. Add student to directory");
        System.out.println("3. Display average age of students");
        System.out.println("4. Quit program");
    }

    /**
     * The main function is responsible for creating our directory and calling the appropriate functions to have all the
     * pre-recorded information. It is then responsible for taking in the UserInput on which menu item they wish to pursue
     * and calling the functions needed to manipulate the directory correctly.
     *
     * @param args or the initial command line arguments (if there are any) are stored here.
     * @throws IOException catches any exceptions thrown by files/user input not being found or opened correctly.
     */
    public static void main(String[] args) throws IOException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("data/cs2410-directory.data");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        CommandDirectory commandDirectory = new CommandDirectory(fileReader);

        Scanner scanner = new Scanner(System.in);

        printMenu();

        int choice = scanner.nextInt();
        System.out.println('\n');
        while (choice != 4) {
            switch (choice) {
                case 1:
                    commandDirectory.printDirectory();
                    break;
                case 2:
                    Student freshMeat = commandDirectory.addStudent();
                    if (freshMeat != null) {
                        System.out.println('\n');
                        System.out.println("The following student has been added to the directory: ");
                        freshMeat.printStudent();
                        System.out.println('\n');
                    }
                    break;
                case 3:
                    double average = commandDirectory.calculateAverageAge();
                    String num = String.format("%.1f", average);
                    System.out.println("The average age of all the students is " + num + " years.");
                    System.out.println('\n');
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            printMenu();
            choice = scanner.nextInt();
            System.out.println('\n');
        }
    }
}
