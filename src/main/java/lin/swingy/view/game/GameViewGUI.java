package lin.swingy.view.game;

import lin.swingy.Main;
import lin.swingy.controller.GameController;
import lin.swingy.model.Game;
import lin.swingy.data.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameViewGUI extends JPanel implements GameView {

    private String[] directions = {"North", "East", "South", "West"};
    private JComboBox<String> directionsComboBox = new JComboBox<>(directions);
    private JButton moveButton = new JButton("Move");
    private JButton switchButton = new JButton("Switch to console");

    private JEditorPane infoPane = new JEditorPane();
    private JScrollPane infoScroll = new JScrollPane(infoPane);
    private JEditorPane mapPane = new JEditorPane();
    private JScrollPane mapScroll = new JScrollPane(mapPane);

    private GameController controller;

    @Override
    public void start() {
        controller = new GameController(this);

        buildUI();
        controller.onStart();
    }

    private void buildUI() {
        Main.getFrame().setTitle("Game");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        directionsComboBox.setSelectedIndex(0);
        this.add(directionsComboBox, gbc);
        this.add(moveButton, gbc);
        this.add(switchButton, gbc);

        this.setVisible(true);
        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onMove((String) directionsComboBox.getSelectedItem());
            }
        });
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSwitchButtonPressed();
            }
        });
    }

    @Override
    public void update(Game game, boolean[][] map, Point heroCoord) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("MAP %dx%d\n", map.length, map.length));
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (heroCoord.getX() == j && heroCoord.getY() == i)
                    stringBuilder.append("h ");
                else if (map[i][j])
                    stringBuilder.append("1 ");
                else
                    stringBuilder.append("0 ");
            }
            stringBuilder.append("\n");
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        mapPane.setEditable(false);
        mapPane.setText(stringBuilder.toString());
        mapScroll.setPreferredSize(new Dimension(500, 400));
        mapScroll.setMinimumSize(new Dimension(300, 300));
        this.add(mapScroll, gbc);

        infoPane.setEditable(false);
        infoPane.setText(game.getHero().toString() +
                "Position: " + "(" + game.getHeroCoord().getX() +
                "," + game.getHeroCoord().getY() + ")");
        infoScroll.setPreferredSize(new Dimension(500, 400));
        infoScroll.setMinimumSize(new Dimension(300, 300));
        this.add(infoScroll, gbc);
    }

    @Override
    public void gameFinished() {
        Main.hideFrame();
        Main.getFrame().dispose();
        Main.closeConnections();
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(Main.getFrame(), message);
    }

    @Override
    public void getVillainCollisionInput() {
        Object options[] = {"Fight", "Run"};

        int result = JOptionPane.showOptionDialog(Main.getFrame(),
                "You moved to position occupied by villain",
                "Fight or run?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (result == JOptionPane.YES_OPTION)
            controller.onFight();
        else
            controller.onRun();
    }

    @Override
    public boolean replaceArtifact(String replaceMessage) {
        Object options[] = {"Replace", "Leave"};

        int result = JOptionPane.showOptionDialog(Main.getFrame(),
                "Would you like to replace " + replaceMessage + "?",
                "Replace or leave?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return result == JOptionPane.YES_OPTION;
    }

    @Override
    public void switchView() {
        Main.hideFrame();
        new GameViewConsole().start();
    }
}