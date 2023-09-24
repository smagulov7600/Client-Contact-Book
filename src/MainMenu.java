import javax.swing.*;

public class MainMenu extends JPanel {
    private MainFrame parent;
    private JButton addButton;
    private JButton listButton;

    public MainMenu(MainFrame parent){

        this.parent = parent;

        setSize(500,500);
        setLayout(null);

        addButton = new JButton("First Page");
        addButton.setSize(300,30);
        addButton.setLocation(100,100);
        add(addButton);

        listButton = new JButton("Second Page");
        listButton.setSize(300,30);
        listButton.setLocation(100,150);
        add(listButton);
    }
}
