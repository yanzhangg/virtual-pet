package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CreatePetPanel extends JPanel {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public CreatePetPanel() {
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));

        JPanel header = new JPanel();
        add(header, BorderLayout.NORTH);
        header.setPreferredSize(new Dimension(100, 50));
        header.setBackground(Color.orange);
        JLabel headerLabel = new JLabel("Create A Pet");
        headerLabel.setFont(new Font("Roboto", Font.PLAIN, 25));
        header.add(headerLabel);
        add(ChoosePet(), BorderLayout.CENTER);
    }

    private JPanel ChoosePet() {
        JPanel choosePet = new JPanel();
        choosePet.setPreferredSize(new Dimension(100, 500));
        choosePet.setBackground(Color.white);
        JLabel choosePetLabel = new JLabel("Choose a pet:");
        JRadioButton dog = new JRadioButton();
        dog.setText("Dog");
        JRadioButton cat = new JRadioButton();
        cat.setText("Cat");
        JButton submit = new JButton("Create pet!");
        choosePet.add(choosePetLabel);
        choosePet.add(dog);
        choosePet.add(cat);
        JLabel giveName = new JLabel("Name your pet:");
        choosePet.add(giveName);
        JTextField namePet = new JTextField("Give your pet a name", 20);
        choosePet.add(namePet);
        choosePet.add(submit);
        return choosePet;
    }



//    private JPanel createNamePet() {
//
//    }
}
