package hahakdog ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class hahakdog extends JFrame {

    private String company_Name = "";
    private String model_Name = "";
    private int year = 0;
    private double mileAge = 0;

    // GUI components
    private JTextField companyField, modelField, yearField, mileageField;
    private JTextArea outputArea;
    private JButton processButton, clearButton;

    public hahakdog() {
        setTitle("Car Information System");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input fields
        add(new JLabel("Company Name:"));
        companyField = new JTextField(20);
        add(companyField);

        add(new JLabel("Model Name:"));
        modelField = new JTextField(20);
        add(modelField);

        add(new JLabel("Year:"));
        yearField = new JTextField(20);
        add(yearField);

        add(new JLabel("Mileage (km):"));
        mileageField = new JTextField(20);
        add(mileageField);

        // Buttons
        processButton = new JButton("Process and Display");
        add(processButton);

        clearButton = new JButton("Clear");
        add(clearButton);

        // Output area
        outputArea = new JTextArea(12, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        // Button actions
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processCarInfo();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    // Process input and display results
    private void processCarInfo() {
        try {
            company_Name = companyField.getText();
            model_Name = modelField.getText();
            year = Integer.parseInt(yearField.getText());
            mileAge = Double.parseDouble(mileageField.getText());

            outputArea.setText(
                    "--- USER INPUT RESULTS ---\n" +
                    getCarInfo() +
                    "Mileage Category: " + getMileageCategory() + "\n" +
                    "Car Age: " + getCarAge() + " years\n" +
                    "Estimated Resale Value: ₱" + String.format("%,.2f", estimateResaleValue())
            );
        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Please enter valid numbers for year and mileage.");
        }
    }

    // Clear all input/output fields
    private void clearFields() {
        companyField.setText("");
        modelField.setText("");
        yearField.setText("");
        mileageField.setText("");
        outputArea.setText("");
    }

    // Car information
    private String getCarInfo() {
        return "Car Information:\n" +
                "Company: " + company_Name +
                "\nModel: " + model_Name +
                "\nYear: " + year +
                "\nMileage: " + mileAge + " km\n";
    }

    // FEATURE 3: Mileage category
    private String getMileageCategory() {
        if (mileAge < 20000) return "Low Mileage (Excellent condition)";
        else if (mileAge < 70000) return "Average Usage";
        else return "High Mileage (Heavily Used)";
    }

    // FEATURE 4: Car age
    private int getCarAge() {
        int currentYear = 2025;
        return currentYear - year;
    }

    // FEATURE 1: Estimated resale value based on car age
    private double estimateResaleValue() {
        double baseValue = 500_000;
        double age = getCarAge();
        double depreciation = age * 0.05;
        if (depreciation > 0.8) depreciation = 0.8; // max 80% depreciation
        return baseValue * (1 - depreciation);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new hahakdog().setVisible(true));
    }
}
