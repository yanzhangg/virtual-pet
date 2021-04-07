package ui;

import model.PetList;

import javax.swing.*;
import java.awt.*;

// Represents the main
public class PetMain extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private PetList petList;
    private MainMenuPanel mainMenu;
    private PetMain mainFrame;

    // Constructs the main window frame
    // EFFECTS: sets up window in which Pet Zoo app will appear
    public PetMain() {
        super("Pet Zoo");

        // init frame
        mainFrame = this;
        petList = new PetList("My Pet List");
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mainMenu = new MainMenuPanel(mainFrame, petList);
        add(mainMenu);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PetMain();
    }
}
