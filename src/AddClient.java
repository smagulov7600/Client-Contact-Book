import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddClient extends JPanel {
    public AddClient(MainFrame parent) {

        setSize(600, 600);
        setLayout(null);

        // Labels
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setSize(100, 30);
        nameLabel.setLocation(100, 100);
        add(nameLabel);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setSize(100, 30);
        surnameLabel.setLocation(100, 150);
        add(surnameLabel);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setSize(100, 30);
        phoneLabel.setLocation(100, 200);
        add(phoneLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setSize(100, 30);
        emailLabel.setLocation(100, 250);
        add(emailLabel);

        JLabel signatureLabel = new JLabel("I have read and agree to the terms and conditions:");
        signatureLabel.setSize(300, 30);
        signatureLabel.setLocation(100, 350);
        add(signatureLabel);

        // Text Fields
        JTextField nameField = new JTextField("");
        nameField.setSize(150, 30);
        nameField.setLocation(200, 100);
        add(nameField);

        JTextField surnameField = new JTextField("");
        surnameField.setSize(150, 30);
        surnameField.setLocation(200, 150);
        add(surnameField);

        JTextField phoneField = new JTextField("");
        phoneField.setSize(150, 30);
        phoneField.setLocation(200, 200);
        add(phoneField);

        JTextField emailField = new JTextField("");
        emailField.setSize(150, 30);
        emailField.setLocation(200, 250);
        add(emailField);

        JCheckBox signatureCheckBox = new JCheckBox();
        signatureCheckBox.setSize(30, 30);
        signatureCheckBox.setLocation(400, 350);
        add(signatureCheckBox);

        // Buttons
        JButton next = new JButton("Next");
        next.setSize(150, 50);
        next.setLocation(300, 425);
        add(next);

        JButton back = new JButton("Back");
        back.setSize(150, 50);
        back.setLocation(100, 425);
        add(back);

        next.addActionListener(e -> {
            String name = nameField.getText();
            String surname = surnameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            boolean agreedToTerms = signatureCheckBox.isSelected();

            if (agreedToTerms) {
                // Validate the email address using a regular expression
                if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    try {
                        // Create a connection to the database
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientlist", "root", "130620");

                        // Prepare the SQL query to insert client data
                        String insertQuery = "INSERT INTO clients (first_name, last_name, phone_number, email, date_of_business) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, surname);
                        preparedStatement.setString(3, phone);
                        preparedStatement.setString(4, email);

                        // Get today's date and format it as a string
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String currentDate = dateFormat.format(new Date());

                        preparedStatement.setString(5, currentDate); // Bind the current date

                        // Execute the query
                        preparedStatement.executeUpdate();

                        // Close the database connection
                        preparedStatement.close();
                        connection.close();

                        nameField.setText("");
                        surnameField.setText("");
                        phoneField.setText("");
                        emailField.setText("");
                        signatureCheckBox.setSelected(false);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(parent, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(parent, "User did not agree to the terms and conditions.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        back.addActionListener(e -> {
            parent.getMainMenu().setVisible(true);
            parent.getAddClient().setVisible(false);
        });
    }
}