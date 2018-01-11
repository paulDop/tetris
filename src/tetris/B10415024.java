/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;

/**
 *
 * @author 珮均
 */
public class B10415024 extends gameView {
     private Color[][] blockContainer;
    private int score;
    private Point pieceOrigin = new Point(5, 2);
    private gameController gamecontroller;
    private gameModel gamemodel;
    private final Color gamebackground = Color.DARK_GRAY;
    private final Color gamewall = Color.LIGHT_GRAY;
    private Block nowBlock;
    private Block nextBlock;
    
    public B10415024(){
         this.gamecontroller = new gameController();
    }
    public void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(15 * 22 + 0, 30 * 22 + 20);
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
                    (p.y + pieceOrigin.y) * 26+35,
                    25, 25);
        }
    }

    private void drawNext(Graphics g) {
        g.setColor(nextBlock.getColor());
        Point[][] shape = nextBlock.getShape();
        for (Point p : shape[nextBlock.getRotate()]) {
            g.fillRect((p.x + 5) * 26,
                    (p.y + 0) * 26+35,
                    25, 25);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // Paint the blockContainer
        g.fillRect(0, 35, 26 * 12, 26 * 23);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                g.setColor(blockContainer[i][j]);
                g.fillRect(26 * i, 26 * j+35, 25, 25);
            }
        }

        // Display the score
       g.setColor(Color.BLACK);
       g.fillRect(0 * 0, 10, 500, 20);
       
       g.setColor(Color.WHITE);
       g.drawString("Score: " + score, 18 * 12, 25);
       
       if (gamemodel.isGameover())
        {
            g.setColor(Color.RED);
            g.drawString("GAMEOVER", 7 * 12, 25);
        }
        else 
        {    
            g.setColor(Color.RED);
            g.drawString("Next", 5 * 12, 70);
            drawPiece(g);
            drawNext(g);
        }
    }
}
