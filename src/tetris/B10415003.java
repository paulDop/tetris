/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author paul
 */
public class B10415003 extends gameView {
    
    private Color[][] blockContainer;
    private int score;
    private Point pieceOrigin = new Point(5, 2);
    private gameController gamecontroller;
    private gameModel gamemodel;
    private final Color gamebackground = Color.BLACK;
    private final Color gamewall = Color.GRAY;
    private Block nowBlock;
    private Block nextBlock;
    
    public B10415003() {
        this.gamecontroller = new gameController();
    }

    public void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((15 + 3) * 26 + 10, 30 * 23 + 25);
        frame.setVisible(true);
        frame.add(this);
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
        g.setColor(Color.WHITE);
        Point[][] shape = nextBlock.getShape();
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                g.fillRect((i + 5 + 8) * 26,
                    (j + 0 + 1) * 26,
                    25, 25);
            }
        }
        
        g.setColor(nextBlock.getColor());
        
        for (Point p : shape[nextBlock.getRotate()]) {
            g.fillRect((p.x + 5 + 8) * 26,
                    (p.y + 0 + 1) * 26,
                    25, 25);
            
            Image img1 = Toolkit.getDefaultToolkit().getImage("test1.png");
            g.drawImage(img1, (p.x + 5) * 26, (p.y + 0) * 26, this);
            
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
        g.setColor(Color.WHITE);
        g.fillRect(28 * 12, 125, 100, 50);
        
        g.setColor(Color.BLACK);
        g.drawString("Next", 28 * 12, 20);
        g.drawString("Score: " + score, 28 * 12, 150);

        // Draw the currently falling piece
         
        if (gamemodel.isGameover())
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesNewRoman", Font.PLAIN, 32));
            g.drawString("gameover!!", 7 * 12, 200);
        }
        else 
        {
            drawPiece(g);
            drawNext(g);
        }
    }

}