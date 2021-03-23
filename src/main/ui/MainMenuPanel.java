package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenuPanel extends JPanel implements ActionListener {

    public MainMenuPanel() {
        setLayout(new GridLayout(0, 1));
        setBorder(BorderFactory.createEmptyBorder(80, 80, 100, 80));
        displayImage();
        createButtons();
    }

    public void createButtons() {
        JButton createPet = new JButton("Create a new pet");
        add(createPet);
        JButton viewPets = new JButton("View all pets");
        add(viewPets);
        JButton searchPets = new JButton("Search pets");
        add(searchPets);
        JButton loadPets = new JButton("Load pets");
        add(loadPets);
        JButton quit = new JButton("Quit Pet Zoo");
        add(quit);

    }

    public void displayImage() {
        BufferedImage welcome = null;
        try {
            welcome = ImageIO.read(new File("./data/welcome.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(welcome));
        add(picLabel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Create a new pet")) {
            CreatePetPanel createPet = new CreatePetPanel();
            add(createPet);
        }

    }
}
