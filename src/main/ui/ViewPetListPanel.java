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

public class ViewPetListPanel extends JPanel implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private JFrame mainFrame;
    private PetList petList;
    private PetAnimal animal;

    public ViewPetListPanel(JFrame mainFrame, PetList petList) {
        this.petList = petList;
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        add(headerPanel(), BorderLayout.NORTH);
        add(footerPanel(), BorderLayout.SOUTH);
        add(centrePanel(), BorderLayout.CENTER);
        System.out.println(petList.viewPetList());
    }

    private JPanel headerPanel() {
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(1000, 70));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        header.setBackground(Color.orange);
        JLabel headerLabel = new JLabel("VIEW ALL PETS");
        headerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
        header.add(headerLabel);
        add(header);
        return header;
    }

    private JPanel centrePanel() {
        JPanel centre = new JPanel();
        centre.setBackground(Color.white);
        centre.setPreferredSize(new Dimension(1000, 600));
        centre.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));
        centre.add(searchPetsPanel());
        centre.add(pets());
        add(centre);
        return centre;
    }

    private JPanel searchPetsPanel() {
        JPanel search = new JPanel();
        search.setLayout(new BoxLayout(search, BoxLayout.X_AXIS));
        search.setPreferredSize(new Dimension(1000, 60));
        search.setBorder(BorderFactory.createEmptyBorder(0, 180, 5,  200));
        search.setBackground(Color.white);
        JLabel searchLabel = new JLabel("Search for a pet:  ");
        searchLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        search.add(searchLabel);
        JTextField searchPet = new JTextField(5);
        searchPet.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        search.add(searchPet);
        JButton go = new JButton("  Go  ");
        go.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        search.add(go);
        add(search);
        return search;
    }

    private JPanel pets() {
        JPanel pets = new JPanel();
        pets.setPreferredSize(new Dimension(1000, 500));
        pets.setBackground(Color.white);
        pets.add(cat());
        pets.add(dog());
        add(pets);
        return pets;
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

    private JButton cat() {
        BufferedImage cat = null;
        try {
            cat = ImageIO.read(new File("./data/cat.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image catResized = cat.getScaledInstance(200, 200, cat.SCALE_SMOOTH);
        Icon catIcon = new ImageIcon(catResized);
        JButton catButton = new JButton(catIcon);
        return catButton;
    }

    private JButton dog() {
        BufferedImage dog = null;
        try {
            dog = ImageIO.read(new File("./data/dog.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dogResized = dog.getScaledInstance(200, 200, dog.SCALE_SMOOTH);
        Icon dogIcon = new ImageIcon(dogResized);
        JButton dogButton = new JButton(dogIcon);
        return dogButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
