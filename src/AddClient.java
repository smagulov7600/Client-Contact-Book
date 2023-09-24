import javax.swing.*;

public class AddClient extends JPanel {
    private MainFrame parent;

    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel signatureLabel;
    private JButton back;
    private JButton add;

    public AddClient(MainFrame parent) {

        this.parent = parent;

        setSize(500, 500);
        setLayout(null);


        // Labels
        nameLabel = new JLabel("Name:");
        nameLabel.setSize(300, 30);
        nameLabel.setLocation(100, 100);
        add(nameLabel);

        surnameLabel = new JLabel("Surname:");
        surnameLabel.setSize(300, 30);
        surnameLabel.setLocation(100, 100);
        add(surnameLabel);

        signatureLabel = new JLabel("Name:");
        signatureLabel.setSize(300, 30);
        signatureLabel.setLocation(100, 100);
        add(signatureLabel);

        back = new JButton("Back");
        back.setSize(300, 30);
        back.setLocation(100, 150);
        add(back);
    }
}