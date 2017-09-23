package cs2410.assn3.gui;

import cs2410.assn3.Directory;
import cs2410.assn3.Student;
import cs2410.assn3.command.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

// GUIDirectory - MenuDialog
// DirectoryListingDialog
// StudentInputDialog - allows you to set the label
// use system error and alert dialogs for other stuff

public class GUIDirectory extends JDialog implements ActionListener {

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
        cancelButton.addActionListener(this);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
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

    private void createStudentInputPanel(String prompt) throws IOException {

        JDialog dialog = new JDialog(this, "Create Student");
        dialog.setVisible(true);

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JTextArea menu = new JTextArea(prompt);
        textField = new JTextField();
        textField.setColumns(5);

        topPanel.add(menu);
        topPanel.add(textField);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));

        JButton enter = new JButton("Enter");
        enter.addActionListener(this);

        bottomPanel.add(enter);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));

        setSize(400, 300);
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "OK") {
            try {
                pressedOK();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            pressedCancel();
        }
    }

    public void quit() {
        System.exit(0);
    }

    private void pressedOK() throws IOException {
        // parse the value of the the text field
        // do the thing for that value
        switch (Integer.parseInt(textField.getText())) {
            case 1:
                String formatString = "%-15s %-15s %3s %-10s %-20s";
                StringJoiner stringJoiner = new StringJoiner("\n");
                for(int i = 0; i < directory.getStudentSize(); i++) {
                    Student temp = directory.getStudent(i);
                    String student = String.format(formatString , temp.getFirstName(), temp.getLastName(), temp.getAge(), temp.getMajorCode(), temp.getStudentID() );
                    stringJoiner.add(student);
                }
                String header = String.format(formatString , "First Name", "Last Name", "Age", "Major Code", "Student ID Number");
                String output = stringJoiner.toString();
                JOptionPane.showMessageDialog(this, header + "\n" + output, "Directory", JOptionPane.PLAIN_MESSAGE);
                break;
            case 2:
                List inputPrompts = new List();
                inputPrompts.add("First Name: ");
                inputPrompts.add("Last Name: ");
                inputPrompts.add("Age: ");
                inputPrompts.add("Major Code");
                inputPrompts.add("Student ID Number");
                List studentInput = new List();
                for (int i = 0; i < directory.getStudentSize(); i++) {
                    createStudentInputPanel(inputPrompts.getItem(i));
                    studentInput.add(textField.getText());
                }

                Student freshMeat = new Student(studentInput.toString());

                JOptionPane.showMessageDialog(this, "The following student has been added to the Directory: \n " + freshMeat.toString());
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

    private void pressedCancel() {
        quit();
    }
}
