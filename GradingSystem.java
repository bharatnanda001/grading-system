import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradingSystem {
    // Declare components
    private JFrame frame;
    private JTextField nameField, marksField;
    private JButton calculateButton, clearButton;
    private JLabel gradeLabel;
    private JPanel panel;

    public GradingSystem() {
        // Create the frame
        frame = new JFrame("Grading System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create panel with layout manager for better structured layout
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add space between components
        
        // Set the title label
        JLabel titleLabel = new JLabel("Student Grading System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLUE);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);
        
        // Name input
        JLabel nameLabel = new JLabel("Enter Name:");
        nameField = new JTextField(20);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
        
        // Marks input
        JLabel marksLabel = new JLabel("Enter Marks:");
        marksField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(marksLabel, gbc);
        gbc.gridx = 1;
        panel.add(marksField, gbc);
        
        // Calculate Button
        calculateButton = new JButton("Calculate Grade");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(calculateButton, gbc);
        
        // Clear Button
        clearButton = new JButton("Clear");
        gbc.gridy = 4;
        panel.add(clearButton, gbc);
        
        // Grade output
        gradeLabel = new JLabel("Grade: ", JLabel.CENTER);
        gradeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gradeLabel.setForeground(Color.GREEN);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(gradeLabel, gbc);
        
        // Add panel to frame
        frame.add(panel);

        // Button click listener for calculating the grade
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String marksText = marksField.getText();

                // Validate input
                if (name.isEmpty() || marksText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter both name and marks.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int marks = Integer.parseInt(marksText);

                    // Validate that marks are between 0 and 100
                    if (marks < 0 || marks > 100) {
                        JOptionPane.showMessageDialog(frame, "Please enter marks between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String grade = calculateGrade(marks);
                    gradeLabel.setText("Grade for " + name + ": " + grade);
                    gradeLabel.setForeground(getGradeColor(grade)); // Change color based on grade
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numerical marks.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Button click listener for clearing the fields
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the fields and reset the label
                nameField.setText("");
                marksField.setText("");
                gradeLabel.setText("Grade: ");
                gradeLabel.setForeground(Color.GREEN); // Reset color
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    // Method to calculate grade
    public String calculateGrade(int marks) {
        if (marks >= 90) {
            return "A";
        } else if (marks >= 75) {
            return "B";
        } else if (marks >= 50) {
            return "C";
        } else if (marks >= 40) {
            return "D";
        } else {
            return "F";
        }
    }

    // Method to get color based on grade
    public Color getGradeColor(String grade) {
        switch (grade) {
            case "A":
                return Color.GREEN;
            case "B":
                return Color.BLUE;
            case "C":
                return Color.ORANGE;
            case "D":
                return Color.YELLOW;
            default:
                return Color.RED;
        }
    }

    public static void main(String[] args) {
        // Initialize the grading system
        new GradingSystem();
    }
}
