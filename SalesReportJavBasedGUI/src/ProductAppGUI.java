

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductAppGUI extends JFrame {
    private JTextField txtCustomerName;
    private JComboBox<String> cmbProducts;
    private JTextField txtPrice;
    private JButton btnProcess;

    

    public ProductAppGUI() {
        setTitle("Product App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Menu Bar setup
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuTools = new JMenu("Tools");

        JMenuItem itemExit = new JMenuItem("Exit");
        JMenuItem itemProcess = new JMenuItem("Process");
        JMenuItem itemClear = new JMenuItem("Clear");

        menuFile.add(itemExit);
        menuTools.add(itemProcess);
        menuTools.add(itemClear);

        menuBar.add(menuFile);
        menuBar.add(menuTools);
        setJMenuBar(menuBar);

        // Components
        JLabel lblCustomer = new JLabel("Customer Name:");
        lblCustomer.setBounds(20, 30, 120, 25);
        add(lblCustomer);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(150, 30, 200, 25);
        add(txtCustomerName);

        JLabel lblProduct = new JLabel("Select Product:");
        lblProduct.setBounds(20, 80, 120, 25);
        add(lblProduct);

        String[] products = {
                "Cozy Nights Monarch",
                "Restonic Barbados",
                "Sealy Phuket"
        };
        cmbProducts = new JComboBox<>(products);
        cmbProducts.setBounds(150, 80, 200, 25);
        add(cmbProducts);

        JLabel lblPrice = new JLabel("Enter Price:");
        lblPrice.setBounds(20, 130, 120, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(150, 130, 200, 25);
        add(txtPrice);

        btnProcess = new JButton("PROCESS");
        btnProcess.setBounds(150, 180, 200, 30);
        add(btnProcess);

        // Action Listeners
        btnProcess.addActionListener(e -> processSalesData());
        itemProcess.addActionListener(e -> processSalesData());
        itemClear.addActionListener(e -> clearForm());
        itemExit.addActionListener(e -> System.exit(0));
    }

    private void processSalesData() {
        try {
            String customerName = txtCustomerName.getText().trim();
            String selectedProduct = (String) cmbProducts.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText().trim());

            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            // Display message dialog matching sample screenshot
            String message = "Customer Name: " + customerName + "\n" +
                    "Product: " + selectedProduct + "\n" +
                    "VAT: R " + vat + "\n" +
                    "COMMISSION: R " + commission + "\n" +
                    "TOTAL: R " + total;

            JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

            // Save details to data.txt
            saveToFile(customerName, selectedProduct, vat, commission, total);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile(String customer, String product, double vat, double commission, double total) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", false))) {
            writer.println("DATA LOG");
            writer.println("**************************************************");
            writer.println("Customer Name: " + customer);
            writer.println("Product: " + product);
            writer.println("VAT: R " + vat);
            writer.println("COMMISSION: R " + commission);
            writer.println("TOTAL: R " + total);
            writer.println("**************************************************");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProducts.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductAppGUI().setVisible(true));
    }
}