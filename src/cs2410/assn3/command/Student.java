package cs2410.assn3.command;

import java.util.List;

public class Student {

    String firstName;
    String lastName;
    String age;
    String majorCode;
    String studentID;

    Student(String line) {
        String student[] = line.split(" ");
        firstName = student[0];
        lastName  = student[1];
        age = student[2];
        majorCode = student[3];
        studentID = student[4];
    }

    Student(List<String> student){
        firstName = student.get(0);
        lastName  = student.get(1);
        age = student.get(2);
        majorCode = student.get(3);
        studentID = student.get(4);
    }

    public void printStudent() {
        System.out.println(firstName + " " + lastName + ", age: " + age + ", major: " + majorCode + ", id: " + studentID);
    }
}
