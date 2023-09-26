import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListAllClients extends JPanel {
    private JTable table;
    public ListAllClients(MainFrame parent){

        setSize(600,600);
        setLayout(null);

        // Buttons
        JButton back = new JButton("Back");
        back.setSize(150,50);
        back.setLocation(100,450);
        add(back);

        JButton refresh = new JButton("Refresh");
        refresh.setSize(70,20);
        refresh.setLocation(300,450);
        add(refresh);

        // Table model with column names
        String[] columnNames = {"Name", "Surname", "Phone", "Email", "Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);
        table.setBounds(30,40,300,200);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        fetchClientData();

        refresh.addActionListener(e -> fetchClientData());

        back.addActionListener(e -> {
            parent.getMainMenu().setVisible(true);
            parent.getListAllClients().setVisible(false);
            parent.setSize(600,600);
        });
    }
    private void fetchClientData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientlist", "root", "130620");

            Statement statement = connection.createStatement();

            // Execute a query to fetch client data (replace with your query)
            String query = "SELECT first_name, last_name, phone_number, email, date_of_business FROM clients";
            ResultSet resultSet = statement.executeQuery(query);

            // Populate the table model with data from the result set
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String dateOfBusiness = resultSet.getString("date_of_business");

                model.addRow(new Object[]{name, surname, phone, email, dateOfBusiness});
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