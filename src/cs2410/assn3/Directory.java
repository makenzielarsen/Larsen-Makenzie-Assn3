package cs2410.assn3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private List<Student> students = new ArrayList<>(100);

    public Directory(FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;;
        while ((line = bufferedReader.readLine()) != null) {
            Student student = new Student(line);
            students.add(student);
        }
    }

    public void addStudent(Student student) throws IOException {
        students.add(student);

        FileWriter fw = new FileWriter("data/cs2410-directory.data", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter writer = new PrintWriter(bw);
        String line = student.getFirstName() + " " + student.getLastName() + " " + student.getAge() + " " + student.getMajorCode() + " " + student.getStudentID();
        writer.println(line);
        writer.close();
    }

    /**
     * The function calculateAverageAge is responsible for going through the Directory of students and adding all their
     * ages together. From there the function divides the sum of ages by the student count of the Directory, giving us
     * the average age of students in the Directory. This is stored in a double value and returned back to the appropriate
     * function.
     *
     * @return double that will represent the average age of students in the Directory.
     */
    public double calculateAverageAge() {
        double ageSum = 0;
        double studentCount = students.size();
        for (int i = 0; i < studentCount; i++) {
            String ageString = students.get(i).getAge();
            ageSum += Integer.parseInt(ageString);
        }

        return (ageSum / studentCount);
    }

    public int getStudentSize() {
        return students.size();
    }

    public Student getStudent(int index) {
        return students.get(index);
    }
}
