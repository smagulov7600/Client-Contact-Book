import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClient extends JPanel {
    private MainFrame parent;

    // Labels
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel signatureLabel;

    // Text Fields
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField signatureField;

    // Buttons
    private JButton back;
    private JButton next;

    public AddClient(MainFrame parent) {

        this.parent = parent;

        setSize(600, 600);
        setLayout(null);

        // Labels
        nameLabel = new JLabel("Name:");
        nameLabel.setSize(300, 30);
        nameLabel.setLocation(100, 100);
        add(nameLabel);

        surnameLabel = new JLabel("Surname:");
        surnameLabel.setSize(300, 30);
        surnameLabel.setLocation(200, 100);
        add(surnameLabel);



        signatureLabel = new JLabel("Name:");
        signatureLabel.setSize(300, 30);
        signatureLabel.setLocation(400, 100);
        add(signatureLabel);

        //Fields



        // Buttons
        next = new JButton("Next");
        next.setSize(300, 30);
        next.setLocation(300, 150);
        add(next);

        back = new JButton("Back");
        back.setSize(300, 30);
        back.setLocation(100, 150);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(true);
                parent.getAddClient().setVisible(false);
            }
        });
    }
}