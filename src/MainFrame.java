import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainMenu mainMenu;
    private AddClient addClient;
    private ListAllClients listAllClients;
    private Authorization authorizationMenu;
    public MainFrame(){
        setTitle("Client Panel");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth())/3,(screenSize.height - getHeight())/4);
        setSize(400,300); // Initial size of the mainframe is 600 by 600
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        authorizationMenu = new Authorization(this);
        authorizationMenu.setVisible(true);
        add(authorizationMenu);

        mainMenu = new MainMenu(this);
        mainMenu.setVisible(false);
        add(mainMenu);

        addClient = new AddClient(this);
        addClient.setVisible(false);
        add(addClient);

        listAllClients = new ListAllClients(this);
        listAllClients.setVisible(false);
        add(listAllClients);
    }
    public Authorization getAuthorizationMenu(){return authorizationMenu;}
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