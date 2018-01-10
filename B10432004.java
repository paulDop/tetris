/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Trif
 */
public class B10432004 extends gameView implements ActionListener{
    
    private Color[][] blockContainer;
    private int score;
    private Point pieceOrigin = new Point(5, 2);
    private gameController gamecontroller;
    private gameModel gamemodel;
    private final Color gamebackground = Color.BLACK;
    private final Color gamewall = Color.GRAY;
    private Block nowBlock;
    private Block nextBlock;
    private JLabel nextL,scoreL1,scoreL2;//show next, score
    private JButton htp;//how to play buttons
    private JTextArea htpL;//how to play texts
    int times=0;//count the button clicked times
    
    public B10432004() {
        this.gamecontroller = new gameController();
    }

    public void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,638);
        frame.setVisible(true);
        frame.add(this);
        this.setLayout(null);
        nextL = new JLabel("Next:");
        nextL.setBounds(315,2,100,50);
        nextL.setFont(new Font("Calibri", Font.BOLD, 26));
        this.add(nextL);
        
        scoreL1 = new JLabel("Score: ");
        scoreL1.setBounds(315,170,100,50);
        scoreL1.setFont(new Font("Calibri", Font.BOLD, 26));
        this.add(scoreL1);
        
        scoreL2 = new JLabel(score+"");
        scoreL2.setBounds(430,170,100,50);
        scoreL2.setFont(new Font("Calibri", Font.BOLD, 26));
        this.add(scoreL2);
        
        htp = new JButton("how to play");
        htp.setBounds(350,250,100,60);
        this.add(htp);
        htp.addActionListener(this);
        
        htpL = new JTextArea();
        htpL.setBackground(this.getBackground());
        htpL.setBounds(350,350,150,200);
        htpL.setFont(new Font("Calibri", Font.BOLD, 18));
        htpL.setText("Use ↑↓←→ \nto Play\n\n↑:Turn\n↓:move down\n←:move left\n→:move right");
        this.add(htpL);
        htpL.setVisible(false);

    }

    public Color getBackgroundColor() {
        return gamebackground;
    }

    public Color getWallColor() {
        return gamewall;
    }

    public void connectModel(gameModel m) {
        gamemodel = m;
        blockContainer = gamemodel.getContainer();
        pieceOrigin = gamemodel.getNowBlockPoint();
        nowBlock = gamemodel.getNowBlock();
        nextBlock = gamemodel.getNextBlock();
        score = gamemodel.getScore();
    }

    public void update() {
        nowBlock = gamemodel.getNowBlock();
        nextBlock = gamemodel.getNextBlock();
        blockContainer = gamemodel.getContainer();
        pieceOrigin = gamemodel.getNowBlockPoint();
        score = gamemodel.getScore();
        repaint();
    }

    public void connectController(gameController c) {
        gamecontroller = c;
    }
    // Creates a border around the blockContainer and initializes the dropping piece

    private void drawPiece(Graphics g) {
        g.setColor(nowBlock.getColor());
        Point[][] shape = nowBlock.getShape();
        for (Point p : shape[nowBlock.getRotate()]) {
            g.fillRect((p.x + pieceOrigin.x) * 26,
                    (p.y + pieceOrigin.y) * 26,
                    25, 25);
        }
    }

    private void drawNext(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(350,35,150,90);
        g.setColor(nextBlock.getColor());
        Point[][] shape = nextBlock.getShape();
        for (Point p : shape[nextBlock.getRotate()]) {
            g.fillRect((p.x + 5) * 26+235,
                    (p.y + 0) * 26+45,
                    25, 25);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // Paint the blockContainer
        g.fillRect(0, 0, 26 * 12, 26 * 23);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                g.setColor(blockContainer[i][j]);
                g.fillRect(26 * i, 26 * j, 25, 25);
            }
        }

        // Display the score
        
        g.setColor(this.getBackground());
        g.fillRect(350,170,150,90);
        scoreL2.setText(score+"");

        g.setColor(Color.BLACK);
        // Draw the currently falling piece
        drawPiece(g);
        drawNext(g);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==htp)//button Clicked
        {
            htpL.setVisible(true);
            times++;
            if(times/10==1){
                
            }
        }
    }
}