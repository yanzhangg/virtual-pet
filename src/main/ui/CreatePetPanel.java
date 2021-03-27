package ui;

import model.Cat;
import model.Dog;
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

public class CreatePetPanel extends JPanel implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private JFrame mainFrame;
    JTextField namePet;
    private PetAnimal animal;
    private PetList petList;
    private String selectedPetType;

    public CreatePetPanel(JFrame mainFrame, PetList petList) {
        this.petList = petList;
        this.animal = animal;
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setSize(new Dimension(WIDTH, HEIGHT));
        add(headerPanel(), BorderLayout.NORTH);
        add(footerPanel(), BorderLayout.SOUTH);
        add(centrePanel(), BorderLayout.CENTER);
    }

    private JPanel headerPanel() {
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(1000, 70));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        header.setBackground(Color.orange);
        JLabel headerLabel = new JLabel("CREATE A PET");
        headerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
        header.add(headerLabel);
        add(header);
        return header;
    }

    private JPanel centrePanel() {
        JPanel centre = new JPanel();
        centre.setBackground(Color.white);
        centre.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));
        centre.add(choosePetPanel());
        centre.add(petImagePanel());
        centre.add(buttonsPanel());
        centre.add(namePetPanel());
        centre.add(typePetPanel());
        add(centre);
        return centre;
    }

    private JPanel choosePetPanel() {
        JPanel choosePet = new JPanel();
        choosePet.setLayout(new BoxLayout(choosePet, BoxLayout.X_AXIS));
        choosePet.setBackground(Color.white);
        choosePet.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        choosePet.setPreferredSize(new Dimension(1000, 40));
        JLabel choosePetLabel = new JLabel("Choose a pet:");
        choosePetLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        choosePet.add(choosePetLabel);
        add(choosePet);
        return choosePet;
    }

    private JPanel petImagePanel() {
        JPanel petImage = new JPanel();
        petImage.setLayout(new BoxLayout(petImage, BoxLayout.X_AXIS));
        petImage.setBackground(Color.white);
        petImage.setBorder(BorderFactory.createEmptyBorder(10, 120, 0, 150));
        petImage.setPreferredSize(new Dimension(1000, 280));
        BufferedImage dog = null;
        BufferedImage cat = null;
        try {
            dog = ImageIO.read(new File("./data/dog.PNG"));
            cat = ImageIO.read(new File("./data/cat.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image resizedDog = dog.getScaledInstance(400, 400, dog.SCALE_SMOOTH);
        JLabel dogLabel = new JLabel(new ImageIcon(resizedDog));
        petImage.add(dogLabel);
        Image resizedCat = cat.getScaledInstance(400, 400, cat.SCALE_SMOOTH);
        JLabel catLabel = new JLabel(new ImageIcon(resizedCat));
        petImage.add(catLabel);
        add(petImage);
        return petImage;
    }

    private JPanel buttonsPanel() {
        JPanel radioButton = new JPanel();
        radioButton.setBackground(Color.white);
        radioButton.setLayout(new BorderLayout());
        radioButton.setPreferredSize(new Dimension(1000, 50));
        radioButton.setBorder(BorderFactory.createEmptyBorder(0, 275, 0, 250));
        JRadioButton dogButton = new JRadioButton();
        dogButton.setText("Dog");
        dogButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        JRadioButton catButton = new JRadioButton();
        catButton.setText("Cat");
        catButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        radioButton.add(dogButton, BorderLayout.WEST);
        radioButton.add(catButton, BorderLayout.EAST);
        ButtonGroup group = new ButtonGroup();
        group.add(dogButton);
        group.add(catButton);
        dogButton.addActionListener(dogActionListener(dogButton));
        catButton.addActionListener(catActionListener(catButton));
        add(radioButton);
        return radioButton;
    }

    private JPanel namePetPanel() {
        JPanel namePet = new JPanel();
        namePet.setLayout(new BoxLayout(namePet, BoxLayout.X_AXIS));
        namePet.setBackground(Color.white);
        namePet.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        namePet.setPreferredSize(new Dimension(1000, 55));
        JLabel namePetLabel = new JLabel("Name your pet:");
        namePetLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        namePet.add(namePetLabel);
        add(namePet);
        return namePet;
    }

    private JPanel typePetPanel() {
        JPanel name = new JPanel();
        name.setLayout(new BoxLayout(name, BoxLayout.Y_AXIS));
        name.setBackground(Color.white);
        name.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        namePet = new JTextField(20);
        namePet.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        name.add(namePet);
        add(name);
        return name;
    }

    private JPanel footerPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setPreferredSize(new Dimension(1000, 55));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        footer.setBackground(Color.orange);
        footer.add(submitButton(), BorderLayout.EAST);
        footer.add(backButton(), BorderLayout.WEST);
        add(footer);
        return footer;
    }

    private ActionListener dogActionListener(JRadioButton dogButton) {
        dogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPetType = "dog";
            }
        });
        return null;
    }

    private ActionListener catActionListener(JRadioButton catButton) {
        catButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPetType = "cat";
            }
        });
        return null;
    }

    private JButton submitButton() {
        JButton submit = new JButton("Create pet!");
        submit.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namePet.getText();
                if (selectedPetType.equals("dog")) {
                    animal = new Dog(name);
                    petList.addPetToList(animal);
                } else if (selectedPetType.equals("cat")) {
                    animal = new Cat(name);
                    petList.addPetToList(animal);
                }
                mainFrame.getContentPane().removeAll();
                mainFrame.add(new PetGamePanel(mainFrame, petList, animal));
                mainFrame.revalidate();
            }
        });
        return submit;
    }

    private JButton backButton() {
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
        return back;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
