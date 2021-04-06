package ui;

import model.PetAnimal;
import model.PetList;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;

// Represents a main menu panel class
public class MainMenuPanel extends JPanel implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private final JFrame mainFrame;
    private PetList petList;
    private PetAnimal animal;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/petlist.json";

    // EFFECTS: constructs a main menu panel
    public MainMenuPanel(JFrame mainFrame, PetList petList) {
        this.petList = petList;
        this.animal = animal;
        this.mainFrame = mainFrame;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        add(welcomePanel(), BorderLayout.NORTH);
        add(footerPanel(), BorderLayout.SOUTH);
        add(centrePanel(), BorderLayout.CENTER);
    }

    // EFFECTS: creates a welcome panel
    private JPanel welcomePanel() {
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(1000, 300));
        header.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));
        header.setBackground(Color.orange);
        BufferedImage welcome = null;
        try {
            welcome = ImageIO.read(new File("./data/welcome.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image welcomeResized = welcome.getScaledInstance(1000, 305, welcome.SCALE_SMOOTH);
        JLabel welcomeLabel = new JLabel(new ImageIcon(welcomeResized));
        header.add(welcomeLabel);
        add(header);
        return header;
    }

    // EFFECTS: creates a centre panel
    private JPanel centrePanel() {
        JPanel centre = new JPanel();
        centre.setPreferredSize(new Dimension(1000, 345));
        centre.setBackground(Color.white);
        centre.setLayout(new GridLayout(0, 1));
        centre.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        centre.add(createPetButton());
        centre.add(viewPetsButton());
        centre.add(loadPetsButton());
        centre.add(saveGameButton());
        add(centre);
        return centre;
    }

    // EFFECTS: creates a footer panel
    private JPanel footerPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setPreferredSize(new Dimension(1000, 55));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        footer.setBackground(Color.orange);
        JButton quit = new JButton("Quit");
        quit.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmQuit();
            }
        });
        footer.add(quit, BorderLayout.WEST);
        add(footer);
        return footer;
    }

    // EFFECTS: creates a create pet button
    private JButton createPetButton() {
        JButton createPet = new JButton("Create a new pet");
        createPet.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        createPet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                mainFrame.add(new CreatePetPanel(mainFrame, petList));
                mainFrame.revalidate();
            }
        });
        return createPet;
    }

    // EFFECTS: creates a view pet button
    private JButton viewPetsButton() {
        JButton viewPets = new JButton("View all pets");
        viewPets.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        viewPets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                mainFrame.add(new ViewPetListPanel(mainFrame, petList, animal));
                mainFrame.revalidate();
            }
        });
        return viewPets;
    }

    // EFFECTS: creates a load pets button
    private JButton loadPetsButton() {
        JButton loadPets = new JButton("Load pets");
        loadPets.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        loadPets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    petList = jsonReader.read();
                    System.out.println("Loaded " + petList.getName() + " from " + JSON_STORE);
                    playSound("./data/success.wav");
                } catch (IOException exception) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        });
        return loadPets;
    }

    // EFFECTS: creates a save game button
    private JButton saveGameButton() {
        JButton saveGame = new JButton("Save game");
        saveGame.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(petList);
                    jsonWriter.close();
                    System.out.println("Saved " + petList.getName() + " to " + JSON_STORE);
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });
        return saveGame;
    }

    // EFFECTS: creates a pop up that confirms quit
    private void confirmQuit() {
        int response = JOptionPane.showConfirmDialog(
                null, "Would you like to save game?", "Save Game", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            try {
                jsonWriter.open();
                jsonWriter.write(petList);
                jsonWriter.close();
                System.out.println("Saved " + petList.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
            System.exit(0);
        } else if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    // EFFECTS: plays a sound clip
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

