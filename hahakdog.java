package hahakdog ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class hahakdog extends JFrame {

    private String company_Name = "";
    private String model_Name = "";
    private int year = 0;
    private double mileAge = 0;

    // GUI components
    private JTextField companyField, modelField, yearField, mileageField;
    private JTextArea outputArea;
    private JButton processButton, clearButton;
    
    // FEATURE 1: Add export button
    private JButton exportButton;
    
    // FEATURE 2: Add status label
    private JLabel statusLabel;

    public hahakdog() {
        setTitle("Car Information System");
        setSize(450, 500);  // Increased height for new components
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
        yearField = new JTextField(10);
        add(yearField);

        add(new JLabel("Mileage:"));
        mileageField = new JTextField(10);
        add(mileageField);

        // Output area
        outputArea = new JTextArea(10, 35);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane);

        // FEATURE 2: Add status label
        statusLabel = new JLabel("Ready");
        statusLabel.setForeground(Color.BLUE);
        add(statusLabel);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        processButton = new JButton("Process Data");
        clearButton = new JButton("Clear");
        
        // FEATURE 1: Add export button
        exportButton = new JButton("Export to File");

        buttonPanel.add(processButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exportButton);  // Add export button
        
        add(buttonPanel);

        // Action Listeners
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processData();
                statusLabel.setText("Data processed successfully");
                statusLabel.setForeground(Color.GREEN);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                statusLabel.setText("Fields cleared");
                statusLabel.setForeground(Color.BLUE);
            }
        });

        // FEATURE 1: Export button action listener
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToFile();
            }
        });
    }

    private void processData() {
        try {
            company_Name = companyField.getText();
            model_Name = modelField.getText();
            year = Integer.parseInt(yearField.getText());
            mileAge = Double.parseDouble(mileageField.getText());

            outputArea.setText("");
            outputArea.append("Company: " + company_Name + "\n");
            outputArea.append("Model: " + model_Name + "\n");
            outputArea.append("Year: " + year + "\n");
            outputArea.append("Mileage: " + mileAge + " km\n");
            
            // Add car age calculation
            int currentYear = LocalDateTime.now().getYear();
            int carAge = currentYear - year;
            outputArea.append("Car Age: " + carAge + " years\n");
            
            // Add condition assessment based on mileage
            String condition;
            if (mileAge < 10000) {
                condition = "Excellent";
            } else if (mileAge < 50000) {
                condition = "Good";
            } else if (mileAge < 100000) {
                condition = "Fair";
            } else {
                condition = "High Mileage";
            }
            outputArea.append("Condition: " + condition + "\n");

        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Please enter valid numbers for year and mileage.");
            statusLabel.setText("Error in input");
            statusLabel.setForeground(Color.RED);
        }
    }

    private void clearFields() {
        companyField.setText("");
        modelField.setText("");
        yearField.setText("");
        mileageField.setText("");
        outputArea.setText("");
    }

    // FEATURE 1: Export functionality
    private void exportToFile() {
        if (outputArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No data to export. Please process data first.", 
                "Export Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = "car_export_" + timestamp + ".txt";
            
            FileWriter writer = new FileWriter(filename);
            writer.write("=== Car Information Export ===\n");
            writer.write("Export Time: " + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("=============================\n\n");
            writer.write(outputArea.getText());
            writer.close();
            
            JOptionPane.showMessageDialog(this, 
                "Data exported successfully to:\n" + filename, 
                "Export Successful", 
                JOptionPane.INFORMATION_MESSAGE);
            statusLabel.setText("Exported to " + filename);
            statusLabel.setForeground(new Color(0, 100, 0)); // Dark green
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error exporting file: " + ex.getMessage(), 
                "Export Error", 
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Export failed");
            statusLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                hahakdog app = new hahakdog();
                app.setVisible(true);
            }
        });
    }
}