/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author PriyankaPrabhu
 */
public class TestFrame extends JFrame {

    final private JButton button;
    private ImageIcon hare;
    private ImageIcon tortoise;
    private ImageIcon winnerT;
    private ImageIcon winnerH;
    private int xT = -70;
    private int xH = -70;
    private int yT = 150;
    private int yH = 250;
    private String stringT = " ", stringH = " ", annouceT = " ", annouceH = " ";
    Running running;
    Display d;
    Race t;
    Race h;
    private boolean isRaceStartedInFrame = false;
    final private Container contentPane;
    final private Panel p;

    public TestFrame() {

        setTitle("Race between hare and tortoise");
        setSize(1100, 500);
        running = new Running(this);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        contentPane = getContentPane();
        getContentPane().setLayout(new BorderLayout());
        d = new Display();
        contentPane.add(BorderLayout.CENTER, d);
        p = new Panel();
        button = new JButton("Start");
        p.add(button);
        contentPane.add(BorderLayout.SOUTH, p);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                isRaceStartedInFrame = true;
                running.setRaceStart(true);
                t = new Race('T', running);
                t.start();
                h = new Race('H', running);
                h.start();
                button.setEnabled(false);
            }
        });

    }

    class Display extends JPanel {

        @Override
        public void paintComponent(Graphics g) {

            if (isRaceStartedInFrame) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, 1100, 500);
                ImageIcon finish = new ImageIcon(this.getClass().getResource("finish.jpg"));
                hare = new ImageIcon(this.getClass().getResource("hare.gif"));
                tortoise = new ImageIcon(this.getClass().getResource("tortoise.gif"));
                winnerT = new ImageIcon(this.getClass().getResource("tortoiseW.jpg"));
                winnerH = new ImageIcon(this.getClass().getResource("hareW.jpg"));
                finish.paintIcon(this, g, 1000, 60);
                g.setColor(Color.BLACK);
                g.drawString(stringT, 460, 40);
                g.drawString(stringH, 460, 60);
                g.setFont(new Font("SansSerif", Font.BOLD, 15));
                g.drawString(annouceT, 480, 220);
                g.drawString(annouceH, 480, 340);
                hare.paintIcon(this, g, xH, yH);
                tortoise.paintIcon(this, g, xT, yT);
            }
        }
    }

    public void move(int x, int y, char participant, String a) {

        if (participant == 'T') {

            xT = x;
            yT = y;
            stringT = "Tortoise at position " + xT + "," + yT;
            annouceT = "Tortoise :" + a;

        } else {
            xH = x;
            yH = y;
            stringH = "Hare at position " + xH + "," + yH;
            annouceH = "Hare :" + a;
        }
        d.repaint();

    }

    public void finalDisplay(char p) {

        if (p == 'T') {

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Exception");
            }
            JOptionPane.showMessageDialog(contentPane, "Tortoise has won", "Winner", JOptionPane.INFORMATION_MESSAGE, winnerT);
            resetValues();
        } else if (p == 'H') {

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Exception");
            }
            JOptionPane.showMessageDialog(contentPane, "Hare has won", "Winner", JOptionPane.INFORMATION_MESSAGE, winnerH);
            resetValues();
        }
    }

    public void resetValues() {

        xT = -70;
        xH = -80;
        yT = 150;
        yH = 250;
        stringT = " ";
        stringH = " ";
        annouceT = " ";
        annouceH = " ";
        button.setEnabled(true);
        running.setRaceStart(false);
    }

    public static void main(String[] args) {

        TestFrame testFrame = new TestFrame();
        testFrame.setVisible(true);
    }

}
