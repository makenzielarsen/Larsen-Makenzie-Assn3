package cs2410.assn3.command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cs2410.assn3.Directory;
import cs2410.assn3.Student;

public class CommandDirectory {

    Directory directory;

    public CommandDirectory(FileReader fileReader) throws IOException {
        directory = new Directory(fileReader);
    }


    /**
     *
     * @return
     * @throws IOException
     */
    public Student addStudent() throws IOException{
        Scanner scanner = new Scanner(System.in);

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

        Student student = new Student(elements);

        directory.addStudent(student);

        return student;
    }

    /**
     *  The printDictionary function is responsible printing a header of what to expect. Then looping through the students
     *  in the Directory and printing out all their information in aligned columns for easier readability.
     */
    public void printDirectory() {
        String formatString = "%-15s %-15s %3s %-10s %-20s";
        String header = String.format(formatString , "First Name", "Last Name", "Age", "Major Code", "Student ID Number");
        System.out.println(header);
        for(int i = 0; i < directory.getStudentSize(); i++) {
            Student temp = directory.getStudent(i);
            String output = String.format(formatString , temp.getFirstName(), temp.getLastName(), temp.getAge(), temp.getMajorCode(), temp.getStudentID() );
            System.out.println(output);
        }
        System.out.println('\n');
    }

}
