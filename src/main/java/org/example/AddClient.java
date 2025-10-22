package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddClient extends JPanel {
    private String username;
    private String password;
    private ArrayList<Point> currentLine;
    private final ArrayList<ArrayList<Point>> lines;
    private boolean hasDrawn = false;
    public AddClient(MainFrame parent) {
        lines = new ArrayList<>();
        currentLine = new ArrayList<>();

        setSize(600, 600);
        setLayout(null);

        // Drawing Panel
        JPanel drawingPanel = new DrawingPanel();
        drawingPanel.setSize(300,100);
        drawingPanel.setLocation(150,315);
        add(drawingPanel);

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentLine = new ArrayList<>();
                currentLine.add(e.getPoint());
                hasDrawn = true;
                lines.add(currentLine);
                repaint();
            }
        });
        drawingPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentLine.add(e.getPoint());
                repaint();
            }
        });

        // Labels
        JLabel nameLabel = new JLabel("Name:*");
        nameLabel.setSize(100, 30);
        nameLabel.setLocation(160, 100);
        add(nameLabel);

        JLabel surnameLabel = new JLabel("Surname:*");
        surnameLabel.setSize(100, 30);
        surnameLabel.setLocation(160, 150);
        add(surnameLabel);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setSize(100, 30);
        phoneLabel.setLocation(160, 200);
        add(phoneLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setSize(100, 30);
        emailLabel.setLocation(160, 250);
        add(emailLabel);

        JLabel signatureLabel = new JLabel("Signature Field:*");
        signatureLabel.setSize(100,30);
        signatureLabel.setLocation(160,290);
        add(signatureLabel);

        // Text Fields
        JTextField nameField = new JTextField("");
        nameField.setSize(150, 30);
        nameField.setLocation(260, 100);
        add(nameField);

        JTextField surnameField = new JTextField("");
        surnameField.setSize(150, 30);
        surnameField.setLocation(260, 150);
        add(surnameField);

        JTextField phoneField = new JTextField("");
        phoneField.setSize(150, 30);
        phoneField.setLocation(260, 200);
        add(phoneField);

        JTextField emailField = new JTextField("");
        emailField.setSize(150, 30);
        emailField.setLocation(260, 250);
        add(emailField);

        // Buttons
        JButton next = new JButton("Proceed");
        next.setSize(150, 50);
        next.setLocation(320, 450);
        add(next);

        JButton back = new JButton("Back");
        back.setSize(150, 50);
        back.setLocation(100, 450);
        add(back);

        next.addActionListener(e -> {
            String name = nameField.getText();
            String surname = surnameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            int clientIndex = -1;

            username = parent.getAuthorizationMenu().getUsername();
            password = parent.getAuthorizationMenu().getPassword();

            if (hasDrawn) {
                // Validate the email address using a regular expression
                if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$") || !phone.isEmpty()) {
                    try {
                        // Create a connection to the database
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientlist", username, password);

                        // Prepare the SQL query to insert client data
                        String insertQuery = "INSERT INTO clients (first_name, last_name, phone_number, email, date_of_business) VALUES (?, ?, ?, ?, ?);";
                        String insertQuery2 = "SELECT MAX(client_index) FROM clients";
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                        PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery2);
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, surname);
                        preparedStatement.setString(3, phone);
                        preparedStatement.setString(4, email);

                        // Get today's date and format it as a string
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String currentDate = dateFormat.format(new Date());

                        preparedStatement.setString(5, currentDate); // Bind the current date

                        // Executing queries
                        preparedStatement.executeUpdate();

                        ResultSet resultSet = preparedStatement1.executeQuery();
                        if (resultSet.next()) {
                            clientIndex = resultSet.getInt(1);
                        }

                        BufferedImage image = new BufferedImage(drawingPanel.getWidth(), drawingPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = image.createGraphics();

                        g2d.setColor(Color.WHITE);
                        g2d.fillRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());

                        // Draw the lines onto the image
                        for (ArrayList<Point> line : lines) {
                            for (int i = 1; i < line.size(); i++) {
                                Point p1 = line.get(i-1);
                                Point p2 = line.get(i);
                                g2d.setColor(Color.BLACK);
                                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                            }
                        }

                        String fileName = clientIndex + "_" +  name + "_" + surname + ".png";
                        try {
                            File drawingsFolder = new File(System.getProperty("user.home") + "\\Downloads\\Signatures");
                            drawingsFolder.mkdirs();

                            File outputFile = new File(drawingsFolder, fileName);

                            ImageIO.write(image, "png", outputFile);

                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Error saving the signature", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // Close the database connection
                        preparedStatement.close();
                        preparedStatement1.close();
                        connection.close();

                        nameField.setText("");
                        surnameField.setText("");
                        phoneField.setText("");
                        emailField.setText("");
                        hasDrawn = false;
                        lines.clear();
                        repaint();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(parent, "Email or Phone number field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (name.isEmpty()){
                JOptionPane.showMessageDialog(parent,"Name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (surname.isEmpty()){
                JOptionPane.showMessageDialog(parent, "Surname field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(parent, "Signature field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        back.addActionListener(e -> {
            parent.getMainMenu().setVisible(true);
            parent.getAddClient().setVisible(false);
        });
    }
    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (ArrayList<Point> line : lines) {
                for (int i = 1; i < line.size(); i++) {
                    Point p1 = line.get(i-1);
                    Point p2 = line.get(i);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
            int panelHeight = getHeight();
            g.setColor(Color.BLACK);
            g.drawLine(0, panelHeight - 1, getWidth(), panelHeight - 1);
        }
    }
}