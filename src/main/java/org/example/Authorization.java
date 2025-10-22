package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Authorization extends JPanel {
    private String username;
    private String password;
    public Authorization(MainFrame parent){
        setSize(400,300);
        setLayout(null);

        JLabel titleLabel = new JLabel("Connect to a Database");
        titleLabel.setSize(150,75);
        titleLabel.setLocation(125,-10);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setSize(100,50);
        nameLabel.setLocation(100,40);
        add(nameLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setSize(100,80);
        passwordLabel.setLocation(100,71);
        add(passwordLabel);

        JTextField nameField = new JTextField("");
        nameField.setSize(100,30);
        nameField.setLocation(175,50);
        add(nameField);

        JPasswordField passwordField = new JPasswordField("");
        passwordField.setSize(100,30);
        passwordField.setLocation(175,95);
        add(passwordField);

        JButton nextButton = new JButton("Next");
        nextButton.setSize(175,35);
        nextButton.setLocation(100,150);
        add(nextButton);

        nextButton.addActionListener(e->{
            try{
                if (!passwordField.getText().isEmpty()&&!nameField.getText().isEmpty()){
                    username = nameField.getText();
                    password = passwordField.getText();
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientlist", username, password);
                    if (con != null){
                        parent.getMainMenu().setVisible(true);
                        parent.setSize(600,600);
                        parent.getAuthorizationMenu().setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(parent, "Database couldn't be found.", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(parent,"Please enter your username and password.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch (SQLException exception){
                if (exception.getMessage().contains("Access denied")) {
                    // Handle access denied error
                    JOptionPane.showMessageDialog(parent,"Username/Password is wrong","Access Denied",JOptionPane.ERROR_MESSAGE);
                } else {
                    // Handle other SQL exceptions
                    exception.printStackTrace();
                }
            }
        });
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}