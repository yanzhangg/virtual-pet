package ui;

import model.PetAnimal;
import model.PetList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.ws.Action;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// Represents a view pet list panel class
public class ViewPetListPanel extends JPanel implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private JFrame mainFrame;
    private PetList petList;
    private PetAnimal animal;
    private JPanel petsPanel;

    // Constructs a view pet list panel
    public ViewPetListPanel(JFrame mainFrame, PetList petList, PetAnimal animal) {
        this.petList = petList;
        this.mainFrame = mainFrame;
        this.animal = animal;
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        add(headerPanel(), BorderLayout.NORTH);
        add(footerPanel(), BorderLayout.SOUTH);
        add(centrePanel(), BorderLayout.CENTER);
        System.out.println(petList.viewPetList());
    }

    // EFFECTS: creates a header panel
    private JPanel headerPanel() {
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(1000, 70));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        header.setBackground(Color.orange);
        JLabel headerLabel = new JLabel("VIEW ALL PETS");
        headerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
        header.add(headerLabel);
        return header;
    }

    // EFFECTS: creates a centre panel
    private JPanel centrePanel() {
        JPanel centre = new JPanel();
        petsPanel = new JPanel();
        petsPanel.setPreferredSize(new Dimension(1000, 500));
        petsPanel.setBackground(Color.white);
        pets(petList.getPets());
        centre.setBackground(Color.white);
        centre.setPreferredSize(new Dimension(1000, 600));
        centre.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));
        centre.add(searchPetsPanel());
        centre.add(petsPanel);
        return centre;
    }

    // EFFECTS: creates a search pets panel
    private JPanel searchPetsPanel() {
        ArrayList<PetAnimal> petSearchList = new ArrayList<>();
        JPanel search = new JPanel();
        search.setLayout(new BoxLayout(search, BoxLayout.X_AXIS));
        search.setPreferredSize(new Dimension(1000, 60));
        search.setBorder(BorderFactory.createEmptyBorder(0, 180, 5, 200));
        search.setBackground(Color.white);
        JLabel searchLabel = new JLabel("Search for a pet:  ");
        searchLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        search.add(searchLabel);
        JTextField searchPet = new JTextField(5);
        searchPet.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        search.add(searchPet);
        JButton go = new JButton("  Go  ");
        go.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (PetAnimal animal : petList.getPets()) {
                    if (animal.getName().equals(searchPet.getText())) {
                        petSearchList.add(animal);
                        System.out.println(animal.getName());
                    }
                }
                petsPanel.removeAll();
                petsPanel.revalidate();
                pets(petSearchList);
                petsPanel.repaint();
            }
        });
        search.add(go);
        return search;
    }

    // EFFECTS: searches the array and adds corresponding pet animal to pet panel
    private void pets(ArrayList<PetAnimal> petArrayList) {
        for (PetAnimal animal : petArrayList) {
            if (animal.getType().equals("dog")) {
                petsPanel.add(dog(animal));
            } else if (animal.getType().equals("cat")) {
                petsPanel.add(cat(animal));
            }
        }
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
                mainFrame.add(new MainMenuPanel(mainFrame, petList));
                mainFrame.revalidate();
            }
        });
        footer.add(back, BorderLayout.WEST);
        add(footer);
        return footer;
    }

    // EFFECTS: creates a cat button
    private JButton cat(PetAnimal animal) {
        BufferedImage cat = null;
        try {
            cat = ImageIO.read(new File("./data/cat.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image catResized = cat.getScaledInstance(150, 150, cat.SCALE_SMOOTH);
        Icon catIcon = new ImageIcon(catResized);
        JButton catButton = new JButton(catIcon);
        catButton.setPreferredSize(new Dimension(200, 220));
        catButton.setText(animal.getName());
        catButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        catButton.setVerticalTextPosition(JButton.TOP);
        catButton.setHorizontalTextPosition(JButton.CENTER);
        catButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                mainFrame.add(new PetGamePanel(mainFrame, petList, animal));
                mainFrame.revalidate();
            }
        });
        return catButton;
    }

    // EFFECTS: creates a dog button
    private JButton dog(PetAnimal animal) {
        BufferedImage dog = null;
        try {
            dog = ImageIO.read(new File("./data/dog.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dogResized = dog.getScaledInstance(150, 150, dog.SCALE_SMOOTH);
        Icon dogIcon = new ImageIcon(dogResized);
        JButton dogButton = new JButton(dogIcon);
        dogButton.setPreferredSize(new Dimension(200, 220));
        dogButton.setText(animal.getName());
        dogButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        dogButton.setVerticalTextPosition(JButton.TOP);
        dogButton.setHorizontalTextPosition(JButton.CENTER);
        dogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                mainFrame.add(new PetGamePanel(mainFrame, petList, animal));
                mainFrame.revalidate();
            }
        });
        return dogButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
