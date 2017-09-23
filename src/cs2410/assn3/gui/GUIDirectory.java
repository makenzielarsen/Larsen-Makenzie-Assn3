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

    private void pressedOK() throws IOException {
        // parse the value of the the text field
        // do the thing for that value
        switch (Integer.parseInt(textField.getText())) {
            case 1:

//                directory.printDirectory();
                break;
            case 2:
//                Student freshMeat = directory.addStudent();
//                if (freshMeat != null) {
//                    System.out.println('\n');
//                    System.out.println("The following student has been added to the Directory: ");
//                    freshMeat.printStudent();
//                    System.out.println('\n');
//                }
                break;
            case 3:
                double average = directory.calculateAverageAge();
                String num = String.format("%.1f", average);
                System.out.println("The average age of all the students is " + num + " years.");
                System.out.println('\n');
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void pressedCancel() {

    }
}
