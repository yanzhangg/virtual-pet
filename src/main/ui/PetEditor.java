package ui;

import ui.MainMenuPanel;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class PetEditor extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private MainMenuPanel mainMenu;
    private CreatePetPanel createPet;
    private PetActionsPanel petActions;

    // constructs main window
    // effects: sets up window in which Pet Zoo app will appear
    public PetEditor() {
        super("Pet Zoo");
        initializeGraphics();
        mainMenu = new MainMenuPanel();
//        createPet = new CreatePetPanel();
//        petActions = new PetActionsPanel();
//        add(createPet);
        add(mainMenu);
//        add(petActions);
    }

    public void initializeGraphics() {
        setLayout(new BorderLayout());
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new PetEditor();
    }
}
