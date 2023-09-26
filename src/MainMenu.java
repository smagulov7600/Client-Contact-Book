import javax.swing.*;
public class MainMenu extends JPanel {
    public MainMenu(MainFrame parent){

        setSize(600,600);
        setLayout(null);

        JButton addButton = new JButton("Add Client");
        addButton.setSize(300,50);
        addButton.setLocation(150,175);
        add(addButton);
        addButton.addActionListener(e -> {
            parent.getMainMenu().setVisible(false);
            parent.getAddClient().setVisible(true);
        });

        JButton listButton = new JButton("List All Clients");
        listButton.setSize(300,50);
        listButton.setLocation(150,325);
        add(listButton);
        listButton.addActionListener(e -> {
            parent.getMainMenu().setVisible(false);
            parent.getListAllClients().setVisible(true);
            parent.setSize(900,640);
        });
    }
}
