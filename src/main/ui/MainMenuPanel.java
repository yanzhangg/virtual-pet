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

    JFrame frame = new JFrame();

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public MainMenuPanel() {
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        add(welcomePanel(), BorderLayout.NORTH);
        add(footerPanel(), BorderLayout.SOUTH);
        add(centrePanel(), BorderLayout.CENTER);
    }

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

    private JPanel centrePanel() {
        JPanel centre = new JPanel();
        centre.setPreferredSize(new Dimension(1000, 345));
        centre.setBackground(Color.white);
        centre.setLayout(new GridLayout(0,1));
        centre.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        JButton createPet = new JButton("Create a new pet");
        createPet.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        createPet.addActionListener(this);
        centre.add(createPet);
        JButton viewPets = new JButton("View all pets");
        viewPets.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        centre.add(viewPets);
        JButton loadPets = new JButton("Load pets");
        loadPets.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        centre.add(loadPets);
        JButton saveGame = new JButton("Save game");
        saveGame.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        centre.add(saveGame);

        add(centre);
        return centre;
    }


    private JPanel footerPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setPreferredSize(new Dimension(1000, 55));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        footer.setBackground(Color.orange);

        JButton quit = new JButton("Quit");
        quit.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        footer.add(quit, BorderLayout.WEST);
        add(footer);
        return footer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
