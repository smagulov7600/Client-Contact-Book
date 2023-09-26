import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ListAllClients extends JPanel {
    private JTable table;
    private JTextField searchField;

    public ListAllClients(MainFrame parent) {
        setSize(900, 600);
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Search Field
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton back = new JButton("Back");
        JButton refresh = new JButton("Refresh");
        buttonPanel.add(back);
        buttonPanel.add(refresh);

        // Table
        String[] columnNames = {"ID", "Name", "Surname", "Phone", "Email", "Date"}; // Column names
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only single-row selection
        table.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        table.setRowHeight(30);

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        fetchClientData();

        refresh.addActionListener(e -> fetchClientData());

        back.addActionListener(e -> {
            parent.getMainMenu().setVisible(true);
            parent.getListAllClients().setVisible(false);
            parent.setSize(600, 600);
        });

        // Search Field DocumentListener
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch(searchField.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch(searchField.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not used for plain text fields
            }
        });
    }

    private void performSearch(String searchText) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientlist", "root", "mypass");

            Statement statement = connection.createStatement();

            // MySQL Query with a WHERE clause to filter by client name
            String query = "SELECT client_index, first_name, last_name, phone_number, email, date_of_business FROM clients WHERE first_name LIKE '%" + searchText + "%' OR last_name LIKE '%" + searchText + "%'";
            ResultSet resultSet = statement.executeQuery(query);

            DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.setRowCount(0);

            while (resultSet.next()) {
                String id = resultSet.getString("client_index");
                String name = resultSet.getString("first_name");
                String surname = resultSet.getString("last_name");
                String phone = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String dateOfBusiness = resultSet.getString("date_of_business");

                model.addRow(new Object[]{id, name, surname, phone, email, dateOfBusiness});
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchClientData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientlist", "root", "mypass");

            Statement statement = connection.createStatement();

            // MySQL Query
            String query = "SELECT client_index, first_name, last_name, phone_number, email, date_of_business FROM clients";
            ResultSet resultSet = statement.executeQuery(query);

            DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.setRowCount(0);

            while (resultSet.next()) {
                String id = resultSet.getString("client_index");
                String name = resultSet.getString("first_name");
                String surname = resultSet.getString("last_name");
                String phone = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String dateOfBusiness = resultSet.getString("date_of_business");

                model.addRow(new Object[]{id, name, surname, phone, email, dateOfBusiness});
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}