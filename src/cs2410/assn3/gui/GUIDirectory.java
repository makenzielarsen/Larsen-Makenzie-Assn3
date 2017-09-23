package cs2410.assn3.gui;

import cs2410.assn3.Directory;
import cs2410.assn3.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class GUIDirectory extends JDialog {

    private Directory directory;

    private JTextField textField;

    public static void main(String[] args) throws IOException {
        GUIDirectory directory = new GUIDirectory();
        directory.setVisible(true);
    }

    GUIDirectory() throws IOException {
        super();
        FileReader fileReader = new FileReader("data/cs2410-Directory.data");
        directory = new Directory(fileReader);

        setSize(400, 300);
        JPanel topPanel = createTopPanel();
        JPanel bottomPanel = createBottomPanel();
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }


    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> pressedCancel());
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> pressedOK());
        bottomPanel.add(cancelButton);
        bottomPanel.add(okButton);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        return bottomPanel;
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        JTextArea menu = new JTextArea("Menu: \n 1. List Directory contents \n 2. Add new student to the Directory \n 3. Display average age of students \n 4. Quit program. \n");
        textField = new JTextField();
        textField.setColumns(5);
        topPanel.add(menu);
        topPanel.add(textField);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        return topPanel;
    }

    private void promptUserToAddStudent() {
        Student freshMeat = new Student();
        freshMeat.setFirstName(JOptionPane.showInputDialog(this, "First Name:"));
        if (freshMeat.getFirstName() == null) { return; }
        freshMeat.setLastName(JOptionPane.showInputDialog(this, "Last Name:"));
        if (freshMeat.getLastName() == null) { return; }
        freshMeat.setAge(JOptionPane.showInputDialog(this, "Age:"));
        if (freshMeat.getAge() == null) { return; }
        freshMeat.setMajorCode(JOptionPane.showInputDialog(this, "Major Code:"));
        if (freshMeat.getMajorCode() == null) { return; }
        freshMeat.setStudentID(JOptionPane.showInputDialog(this, "Student ID:"));
        if (freshMeat.getStudentID() == null) { return; }

        try {
            directory.addStudent(freshMeat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "The following student has been added to the Directory: \n " + freshMeat.toString());
    }

    public void quit() {
        System.exit(0);
    }

    private void pressedOK() {
        // parse the value of the the text field
        // do the thing for that value
        switch (Integer.parseInt(textField.getText())) {
            case 1:
                showDirectory();
                break;
            case 2:
                promptUserToAddStudent();
                break;
            case 3:
                double average = directory.calculateAverageAge();
                String num = String.format("%.1f", average);
                JOptionPane.showMessageDialog(this, "The average age of all the students is " + num + " years.", "Average Age", JOptionPane.PLAIN_MESSAGE);
                break;
            case 4:
                quit();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void showDirectory() {
        JDialog dialog = new JDialog();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        dialog.setSize(400,400);
        topPanel.setSize(350,350);
        topPanel.setLayout(new GridLayout(0,5));

        topPanel.add(new JLabel("First name", SwingConstants.LEFT));
        topPanel.add(new JLabel("Last name", SwingConstants.LEFT));
        topPanel.add(new JLabel("Age", SwingConstants.LEFT));
        topPanel.add(new JLabel("Major Code", SwingConstants.LEFT));
        topPanel.add(new JLabel("Student ID", SwingConstants.LEFT));

        for(int i = 0; i < directory.getStudentSize(); i++) {
            Student student = directory.getStudent(i);
            topPanel.add(new JLabel(student.getFirstName(), SwingConstants.LEFT));
            topPanel.add(new JLabel(student.getLastName(), SwingConstants.LEFT));
            topPanel.add(new JLabel(student.getAge(), SwingConstants.LEFT));
            topPanel.add(new JLabel(student.getMajorCode(), SwingConstants.LEFT));
            topPanel.add(new JLabel(student.getStudentID(), SwingConstants.LEFT));
        }

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dialog.setVisible(false));
        bottomPanel.add(okButton);

        dialog.add(topPanel, BorderLayout.CENTER);
        dialog.add(bottomPanel, BorderLayout.PAGE_END);

        dialog.setVisible(true);
    }

    private void pressedCancel() {
        quit();
    }
}
