package affiche;

import connecting.GlobalConnection;
import elements.Players;
import identity.Switch;
import listen.KeyMihaino;
import listen.MouseMihaino;
import models.GlobalRequestManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class Affiche extends JPanel{

    Connection co = GlobalConnection.connectToPostgresql();
    Switch sw = new Switch();
    Players players = new Players();
    private final int width = 120;
    private final int height = 40;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Affiche() throws SQLException {
        launch();
    }
    public void launch() throws SQLException {
        JFrame frame = new JFrame("Dashboard");
        JPanel panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(1350, 750);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel jlTitle = new JLabel("Basketball Dashboard");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btn = new JButton("Pause");
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.setBounds(610,10,this.getWidth(),this.getHeight());
        MouseMihaino mouseMihaino = new MouseMihaino(btn);
        btn.addMouseListener(mouseMihaino);
        panel.add(btn);

        JButton[] buttons = new JButton[10];
        MouseMihaino[] mouseMihainos = new MouseMihaino[10];
        KeyMihaino[] keyMihainos = new KeyMihaino[10];
        GlobalRequestManager[] globalRequestManagers = players.find(players, co);

            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JButton(((Players)globalRequestManagers[i]).getNamePlayer());
                buttons[i].setFont(new Font("Arial", Font.BOLD, 10));
                mouseMihainos[i] = new MouseMihaino(buttons[i]);
                keyMihainos[i] = new KeyMihaino(this);
                buttons[i].addKeyListener(keyMihainos[i]);
                buttons[i].addMouseListener(mouseMihainos[i]);
                panel.add(buttons[i]);
            }
        buttons[0].setBounds(70,50,this.getWidth(),this.getHeight());
        buttons[1].setBounds(70,500,this.getWidth(),this.getHeight());
        buttons[2].setBounds(250,140,this.getWidth(),this.getHeight());
        buttons[3].setBounds(250,400,this.getWidth(),this.getHeight());
        buttons[4].setBounds(400,270,this.getWidth(),this.getHeight());
        buttons[5].setBounds(1150,50,this.getWidth(),this.getHeight());
        buttons[6].setBounds(1150,500,this.getWidth(),this.getHeight());
        buttons[7].setBounds(950,140,this.getWidth(),this.getHeight());
        buttons[8].setBounds(950,400,this.getWidth(),this.getHeight());
        buttons[9].setBounds(770,270,this.getWidth(),this.getHeight());

        frame.add(jlTitle);

        String[] note = {"P --> PASS", "L --> LAUNCH", "M --> MARK", "O --> OFFENSIVE REBOUND", "D --> DEFENSIVE REBOUND"};
        JLabel[] labels = new JLabel[5];
        for (int i = 0; i < note.length ; i++) {
            labels[i] = new JLabel(note[i]);
            labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            labels[i].setFont(new Font("Arial", Font.BOLD, 15));
            frame.add(labels[i]);
        }

        frame.add(panel);
        panel.setLayout(null);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        frame.add(panel);

        frame.setVisible(true);
    }


}
