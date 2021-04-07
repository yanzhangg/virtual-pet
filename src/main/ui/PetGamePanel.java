package ui;

import model.PetAnimal;
import model.PetList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Represents a pet game panel class
public class PetGamePanel extends JPanel {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private JFrame mainFrame;
    private PetList petList;
    private PetAnimal animal;

    // EFFECTS: Constructs the pet game panel
    public PetGamePanel(JFrame mainFrame, PetList petList, PetAnimal animal) {
        this.mainFrame = mainFrame;
        this.petList = petList;
        this.animal = animal;
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        add(headerPanel(), BorderLayout.NORTH);
        add(footerPanel(), BorderLayout.SOUTH);
        add(petStatsPanel(animal), BorderLayout.WEST);
        add(centrePanel(), BorderLayout.CENTER);
    }

    // EFFECTS: creates a header panel
    private JPanel headerPanel() {
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(1000, 70));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        header.setBackground(Color.orange);
        JLabel headerLabel = new JLabel("PET ZOO");
        headerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
        header.add(headerLabel);
        add(header);
        return header;
    }

    // EFFECTS: creates a pet stats panel
    private JPanel petStatsPanel(PetAnimal animal) {
        JPanel stats = new JPanel();
        stats.setPreferredSize(new Dimension(200, 575));
        stats.setLayout(new GridLayout(0, 1));
        stats.setBorder(BorderFactory.createEmptyBorder(60, 20, 40, 0));
        stats.setBackground(Color.white);
        JLabel statsLabel = new JLabel(animal.getName() + "'s status:");
        statsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        stats.add(statsLabel);
        JLabel fullnessLabel = new JLabel("Fullness: " + animal.getFullnessLevel());
        fullnessLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        stats.add(fullnessLabel);
        JLabel happinessLabel = new JLabel("Happiness: " + animal.getHappinessLevel());
        happinessLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        stats.add(happinessLabel);
        JLabel energyLabel = new JLabel("Energy: " + animal.getEnergyLevel());
        energyLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        stats.add(energyLabel);
        add(stats);
        return stats;
    }

    // EFFECTS: creates a centre panel
    private JPanel centrePanel() {
        JPanel centre = new JPanel();
        centre.setBackground(Color.white);
        centre.setPreferredSize(new Dimension(800, 600));
        centre.add(petActionsPanel(animal));
        centre.add(gamePanel(animal));
        add(centre);
        return centre;
    }

    // EFFECTS: creates a pet actions panel
    private JPanel petActionsPanel(PetAnimal animal) {
        JPanel actions = new JPanel();
        actions.setLayout(new GridLayout(1, 0));
        actions.setPreferredSize(new Dimension(800, 75));
        actions.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 30));
        actions.setBackground(Color.white);
        JLabel actionsLabel = new JLabel("Actions:");
        actionsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        actions.add(actionsLabel);
        JButton sleep = new JButton("Sleep");
        sleep.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        actions.add(feedAction(animal));
        actions.add(petAction(animal));
        actions.add(playAction(animal));
        actions.add(sleep);
        add(actions);
        return actions;
    }

    // EFFECTS: creates the main game panel where the pet lives
    private JPanel gamePanel(PetAnimal animal) {
        JPanel game = new JPanel();
        game.setPreferredSize(new Dimension(800, 500));
        game.setLayout(new BorderLayout());
        game.setBackground(Color.lightGray);
        if (animal.getType().equals("dog")) {
            game.add(dogCharacter(animal), BorderLayout.SOUTH);
        } else if (animal.getType().equals("cat")) {
            game.add(catCharacter(animal), BorderLayout.SOUTH);
        }
        add(game);
        return game;
    }

    // EFFECTS: creates a footer panel
    private JPanel footerPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setPreferredSize(new Dimension(1000, 55));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        footer.setBackground(Color.orange);

        JButton back = new JButton("Back");
        back.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                mainFrame.add(new ViewPetListPanel(mainFrame, petList, animal));
                mainFrame.revalidate();
            }
        });
        footer.add(back, BorderLayout.WEST);

        add(footer);
        return footer;
    }

    // EFFECTS: creates a feed button
    private JButton feedAction(PetAnimal animal) {
        JButton feed = new JButton("Feed");
        feed.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        feed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animal.feed();
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        return feed;
    }

    // EFFECTS: creates a pet button
    private JButton petAction(PetAnimal animal) {
        JButton pet = new JButton("Pet");
        pet.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        pet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animal.pet();
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        return pet;
    }

    // EFFECTS: creates a play button
    private JButton playAction(PetAnimal animal) {
        JButton play = new JButton("Play");
        play.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animal.play();
                mainFrame.getContentPane().revalidate();
                mainFrame.getContentPane().repaint();
            }
        });
        return play;
    }

    // EFFECTS: creates a dog character label
    private JLabel dogCharacter(PetAnimal animal) {
        BufferedImage dog = null;
        try {
            dog = ImageIO.read(new File("./data/dog.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dogResized = dog.getScaledInstance(400, 400, dog.SCALE_SMOOTH);
        JLabel dogLabel = new JLabel(new ImageIcon(dogResized));
        return dogLabel;
    }

    // EFFECTS: creates a cat character label
    private JLabel catCharacter(PetAnimal animal) {
        BufferedImage cat = null;
        try {
            cat = ImageIO.read(new File("./data/cat.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image catResized = cat.getScaledInstance(400, 400, cat.SCALE_SMOOTH);
        JLabel catLabel = new JLabel(new ImageIcon(catResized));

        return catLabel;
    }

}
