import javax.swing.*;

public class MainFrame extends JFrame {
    private MainMenu mainMenu;
    private AddClient addClient;
    private ListAllClients listAllClients;
    public MainFrame(){
        setTitle("");
        setSize(600,600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainMenu = new MainMenu(this);
        mainMenu.setVisible(true);
        add(mainMenu);

        addClient = new AddClient(this);
        addClient.setVisible(false);
        add(addClient);

        listAllClients = new ListAllClients(this);
        listAllClients.setVisible(false);
        add(listAllClients);
    }
    public MainMenu getMainMenu() {
        return mainMenu;
    }
    public AddClient getAddClient() {
        return addClient;
    }
    public ListAllClients getListAllClients() {
        return listAllClients;
    }
}