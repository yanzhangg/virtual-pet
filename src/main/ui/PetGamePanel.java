package ui;

import model.Dog;
import model.PetAnimal;
import model.PetList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PetGamePanel extends JPanel {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private JFrame mainFrame;
    private PetList petList;
    private PetAnimal animal;

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
        System.out.println(petList.viewPetList());
    }

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

    private JPanel petStatsPanel(PetAnimal animal) {
        JPanel stats = new JPanel();
        stats.setPreferredSize(new Dimension(200, 575));
        stats.setLayout(new GridLayout(0, 1));
        stats.setBorder(BorderFactory.createEmptyBorder(60, 20, 40, 0));
        stats.setBackground(Color.white);
        JLabel statsLabel = new JLabel(animal.getName() + "'s status:");
        statsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        stats.add(statsLabel);
        JLabel fullnessLabel = new JLabel("Fullness: " + animal.getFullnessLevel());
        fullnessLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        stats.add(fullnessLabel);
        JLabel happinessLabel = new JLabel("Happiness: " + animal.getHappinessLevel());
        happinessLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        stats.add(happinessLabel);
        JLabel energyLabel = new JLabel("Energy: " + animal.getEnergyLevel());
        energyLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        stats.add(energyLabel);
        add(stats);
        return stats;
    }

    private JPanel centrePanel() {
        JPanel centre = new JPanel();
        centre.setBackground(Color.white);
        centre.setPreferredSize(new Dimension(800, 600));
        centre.add(petActionsPanel());
        centre.add(gamePanel());
        add(centre);
        return centre;
    }


    private JPanel petActionsPanel() {
        JPanel actions = new JPanel();
        actions.setLayout(new GridLayout(1,0));
        actions.setPreferredSize(new Dimension(800, 75));
        actions.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 30));
        actions.setBackground(Color.white);
        JLabel actionsLabel = new JLabel("Actions:");
        actionsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        actions.add(actionsLabel);

        JButton sleep = new JButton("Sleep");
        sleep.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));

        actions.add(feedAction());
        actions.add(petAction());
        actions.add(playAction());
        actions.add(sleep);

        add(actions);
        return actions;
    }

    private JPanel gamePanel() {
        JPanel game = new JPanel();
        game.setPreferredSize(new Dimension(800, 500));
        game.setBackground(Color.lightGray);
        add(game);
        return game;
    }

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
                mainFrame.add(new MainMenuPanel(mainFrame, petList));
                mainFrame.revalidate();
            }
        });
        footer.add(back, BorderLayout.WEST);

        add(footer);
        return footer;
    }



    private JButton feedAction() {
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

    private JButton petAction() {
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

    private JButton playAction() {
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

//    private void determinePet(PetAnimal animal) {
//        if (animal instanceof Dog) {
//            dogActions(animal);
//        } else {
//            catActions(animal);
//        }
//    }

}
