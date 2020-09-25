package lin.swingy.view.create;

import lin.swingy.Main;
import lin.swingy.controller.CreateHeroController;
import lin.swingy.view.select.SelectHeroViewGUI;
import lin.swingy.view.game.GameViewGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateHeroViewGUI extends JPanel implements CreateHeroView {

    private JLabel heroNameLabel = new JLabel("Hero name:");
    private JTextField heroNameField = new JTextField(10);
    private JButton createHeroButton = new JButton("Create Hero");
    private JButton selectButton = new JButton("Select");
    private JButton redoButton = new JButton("Redo");
    private String[] heroClasses = {"Warrior", "Shaman", "Priest", "Paladin", "Mage", "Ranger"};
    private JLabel heroClass = new JLabel("Class:");
    private JComboBox<String> classesComboBox = new JComboBox<>(heroClasses);
    private JEditorPane infoPane = new JEditorPane();

    private CreateHeroController controller;

    @Override
    public void start() {
        controller = new CreateHeroController(this);

        buildUI();
    }

    private void buildUI() {
        Main.getFrame().setTitle("Create Hero");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2);

        JPanel createHeroPanel = new JPanel();
        createHeroPanel.add(heroNameLabel);
        createHeroPanel.add(heroNameField);
        createHeroPanel.setVisible(true);
        this.add(createHeroPanel, gbc);

        JPanel classPannel = new JPanel();
        classPannel.add(heroClass);
        classesComboBox.setSelectedIndex(0);
        classPannel.add(classesComboBox);
        classPannel.setVisible(true);
        this.add(classPannel, gbc);

        infoPane.setEditable(false);
        infoPane.setFont(new Font("monospaced", Font.PLAIN, 12));
        infoPane.setText("         Attack  Defense   HP\n" +
                "Warrior    30      20      100\n" +
                "Shaman     35      20      90\n" +
                "Priest     25      25      100\n" +
                "Paladin    10      30      120\n" +
                "Mage       45      15      80\n" +
                "Ranger     25      20      110\n");
        infoPane.setPreferredSize(new Dimension(250, 150));
        infoPane.setMinimumSize(new Dimension(250, 150));
        this.add(infoPane, gbc);

        this.add(createHeroButton, gbc);
        this.add(selectButton, gbc);
        this.add(redoButton, gbc);
        this.setVisible(true);

        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateButtonPressed(heroNameField.getText(), (String) classesComboBox.getSelectedItem());
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSelectButtonPressed();
            }
        });
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onRedoButtonPressed();
            }
        });
    }

    @Override
    public void getUserInput() {
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(Main.getFrame(), message);
    }

    @Override
    public void openGame() {
        this.setVisible(false);
        new GameViewGUI().start();
    }

    @Override
    public void openSelectHero() {
        this.setVisible(false);
        new SelectHeroViewGUI().start();
    }

    @Override
    public void openCreateHero() {
        this.setVisible(false);
        new CreateHeroViewGUI().start();
    }
}