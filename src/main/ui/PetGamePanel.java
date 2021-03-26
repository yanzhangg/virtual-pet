package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PetGamePanel extends JPanel {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public PetGamePanel() {
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        add(headerPanel(), BorderLayout.NORTH);
        add(footerPanel(), BorderLayout.SOUTH);
        add(petStatsPanel(), BorderLayout.WEST);
        add(centrePanel(), BorderLayout.CENTER);
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

    private JPanel petStatsPanel() {
        JPanel stats = new JPanel();
        stats.setPreferredSize(new Dimension(200, 575));
        stats.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        stats.setBackground(Color.white);
        JLabel statsLabel = new JLabel("Pet Stats");
        statsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        stats.add(statsLabel);
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
        actions.setLayout(new BoxLayout(actions, BoxLayout.X_AXIS));
        actions.setPreferredSize(new Dimension(800, 75));
        actions.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, 0));
        actions.setBackground(Color.white);
        JLabel actionsLabel = new JLabel("Actions: ");
        actionsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        actions.add(actionsLabel);
        JButton feed = new JButton("Feed");
        feed.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        JButton pet = new JButton("Pet");
        pet.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        JButton play = new JButton("Play");
        play.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        JButton sleep = new JButton("Sleep");
        sleep.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

        actions.add(feed);
        actions.add(pet);
        actions.add(play);
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
        footer.add(back, BorderLayout.WEST);

        add(footer);
        return footer;
    }
}
