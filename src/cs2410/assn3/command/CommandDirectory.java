package cs2410.assn3.command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandDirectory {

    List<Student> students = new ArrayList<>(100);

    public CommandDirectory(FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Student student = new Student(line);
            students.add(student);
        }
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public Student addStudent() throws IOException{
        String studentString = "";
        Scanner scanner = new Scanner(System.in);
        Student student = null;

        List<String> elements = new ArrayList<>(5);

        System.out.println("First Name:");
        elements.add(scanner.next());
        System.out.println("Last Name:");
        elements.add(scanner.next());
        System.out.println("Age:");
        elements.add(scanner.next());
        System.out.println("Major Code:");
        elements.add(scanner.next());
        System.out.println("Student ID Number:");
        elements.add(scanner.next());

        student = new Student(elements);

        students.add(student);

        try (FileWriter fw = new FileWriter("data/cs2410-directory.data", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            String line = student.firstName + " " + student.lastName + " " + student.age + " " + student.majorCode + " " + student.studentID;
            out.println(line);
        }
        return student;
    }

    /**
     *  The printDictionary function is responsible printing a header of what to expect. Then looping through the students
     *  in the directory and printing out all their information in aligned columns for easier readability.
     */
    public void printDirectory() {
        String header = String.format("%-15s %-15s %3s %-10s %-20s" , "First Name", "Last Name", "Age", "Major Code", "Student ID Number");
        System.out.println(header);
        for(int i = 0; i < students.size(); i++) {
            Student temp = students.get(i);
            String output = String.format("%-15s %-15s %3s %-10s %-20s" , temp.firstName, temp.lastName, temp.age, temp.majorCode, temp.studentID );
            System.out.println(output);
        }
        System.out.println('\n');
    }

    /**
     * The function calculateAverageAge is responsible for going through the directory of students and adding all their
     * ages together. From there the function divides the sum of ages by the student count of the directory, giving us
     * the average age of students in the directory. This is stored in a double value and returned back to the appropriate
     * funciton.
     *
     * @return double that will represent the average age of students in the directory.
     */
    public double calculateAverageAge() {
        double ageSum = 0;
        double studentCount = students.size();
        for(int i = 0; i < studentCount; i++) {
            String ageString = students.get(i).age;
            ageSum += Integer.parseInt(ageString);
        }

        return (ageSum / studentCount);
    }

}
