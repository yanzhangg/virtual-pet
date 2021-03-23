package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PetActionsPanel extends JPanel {

    public PetActionsPanel() {
        setLayout(new GridLayout(1, 0));
        setBorder(BorderFactory.createEmptyBorder(80, 80, 100, 80));
        feedAction();
        petAction();
        playAction();
        sleepAction();
    }

    public void feedAction() {
        JButton feed = new JButton("Feed");
        add(feed);
    }

    public void petAction() {
        JButton pet = new JButton("Pet");
        add(pet);
    }

    public void playAction() {
        JButton play = new JButton("Play");
        add(play);
    }

    public void sleepAction() {
        JButton sleep = new JButton("Sleep");
        add(sleep);
    }
}
