package cs2410.assn3;

import java.util.List;

public class Student {

    private String firstName;
    private String lastName;
    private String age;
    private String majorCode;
    private String studentID;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Student() {
        firstName = "";
        lastName = "";
        age = "";
        majorCode = "";
        studentID = "";
    }

    public Student(String line) {
        String student[] = line.split(" ");
        firstName = student[0];
        lastName  = student[1];
        age = student[2];
        majorCode = student[3];
        studentID = student[4];
    }

    public Student(List<String> student){
        firstName = student.get(0);
        lastName  = student.get(1);
        age = student.get(2);
        majorCode = student.get(3);
        studentID = student.get(4);
    }

    public String toString() {
        return firstName + " " + lastName + ", age: " + age + ", major: " + majorCode + ", id: " + studentID;
    }

    public void printStudent() {
        System.out.println(toString());
    }
}
