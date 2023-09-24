import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private MainFrame parent;
    private JButton addButton;
    private JButton listButton;

    public MainMenu(MainFrame parent){

        this.parent = parent;

        setSize(600,600);
        setLayout(null);

        addButton = new JButton("Add Client");
        addButton.setSize(300,50);
        addButton.setLocation(150,175);
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                parent.getMainMenu().setVisible(false);
                parent.getAddClient().setVisible(true);
            }
        });

        listButton = new JButton("List All Clients");
        listButton.setSize(300,50);
        listButton.setLocation(150,325);
        add(listButton);
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getListAllClients().setVisible(true);
            }
        });
    }
}
