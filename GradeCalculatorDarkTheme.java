import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorDarkTheme extends JFrame {
    private JTextField subjectField;
    private JTextArea marksArea;
    private JLabel resultLabel;

    public GradeCalculatorDarkTheme() {
        // Frame settings
        setTitle("Student Grade Calculator - Dark Theme");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Dark theme colors
        Color backgroundColor = new Color(18, 18, 18); // Dark gray
        Color textColor = new Color(240, 240, 240); // Light gray
        Color accentColor = new Color(70, 130, 180); // Samsung blue

        // Header
        JLabel header = new JLabel("Student Grade Calculator", SwingConstants.CENTER);
        header.setForeground(accentColor);
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.setOpaque(true);
        header.setBackground(backgroundColor);
        header.setBorder(new EmptyBorder(10, 0, 10, 0)); // Padding for the header
        add(header, BorderLayout.NORTH);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around edges
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Spacing between components

        // Input: Number of subjects
        JLabel subjectLabel = createStyledLabel("Enter number of subjects:", textColor);
        subjectField = createStyledTextField(textColor, backgroundColor);

        // Input: Marks for subjects
        JLabel marksLabel = createStyledLabel("Enter marks (out of 100) for each subject (comma-separated):", textColor);
        marksArea = new JTextArea();
        marksArea.setBackground(new Color(40, 40, 40)); // Input background
        marksArea.setForeground(textColor); // Input text color
        marksArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        marksArea.setBorder(new EmptyBorder(10, 10, 10, 10)); // Inner padding for text area

        mainPanel.add(subjectLabel);
        mainPanel.add(subjectField);
        mainPanel.add(marksLabel);
        mainPanel.add(new JScrollPane(marksArea));

        add(mainPanel, BorderLayout.CENTER);

        // Submit button and results
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(accentColor);
        calculateButton.setForeground(textColor);
        calculateButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        calculateButton.setFocusPainted(false);

        resultLabel = createStyledLabel("", textColor);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setBorder(new EmptyBorder(10, 20, 20, 20)); // Padding for bottom section
        bottomPanel.add(calculateButton, BorderLayout.NORTH);
        bottomPanel.add(resultLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button action listener
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });
    }

    private JLabel createStyledLabel(String text, Color textColor) {
        JLabel label = new JLabel(text, SwingConstants.LEFT);
        label.setForeground(textColor);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return label;
    }

    private JTextField createStyledTextField(Color textColor, Color backgroundColor) {
        JTextField textField = new JTextField();
        textField.setForeground(textColor);
        textField.setBackground(backgroundColor);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textField.setBorder(new EmptyBorder(5, 5, 5, 5)); // Padding inside the text field
        return textField;
    }

    private void calculateGrade() {
        try {
            int numSubjects = Integer.parseInt(subjectField.getText().trim());
            String[] marksStrings = marksArea.getText().trim().split(",");
            if (marksStrings.length != numSubjects) {
                resultLabel.setText("Error: Number of marks entered doesn't match number of subjects!");
                return;
            }

            int totalMarks = 0;
            for (String mark : marksStrings) {
                totalMarks += Integer.parseInt(mark.trim());
            }

            double averagePercentage = totalMarks / (double) numSubjects;
            String grade;

            if (averagePercentage >= 90) {
                grade = "A+";
            } else if (averagePercentage >= 80) {
                grade = "A";
            } else if (averagePercentage >= 70) {
                grade = "B";
            } else if (averagePercentage >= 60) {
                grade = "C";
            } else if (averagePercentage >= 50) {
                grade = "D";
            } else {
                grade = "F";
            }

            resultLabel.setText(String.format(
                    "Total Marks: %d | Average: %.2f%% | Grade: %s",
                    totalMarks, averagePercentage, grade
            ));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid input. Please enter numeric values only.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GradeCalculatorDarkTheme calculator = new GradeCalculatorDarkTheme();
            calculator.setVisible(true);
        });
    }
}
