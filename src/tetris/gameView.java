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
import javax.swing.JPanel;

/**
 *
 * @author paul
 */
public class gameView extends JPanel {

    private Color[][] well;
    private int score;
    public JFrame f = new JFrame("Tetris");
    private Point pieceOrigin = new Point(5, 2);
    private gameController gamecontroller = new gameController();
    private gameModel gamemodel;
    private Color gamebackground = Color.BLACK;
    private Color gamewall = Color.GRAY;
    private Block nowBlock;
    private Block nextBlock;

    public gameView() {

    }

    public void init() {
        System.out.println("run view");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(15 * 26 + 10, 30 * 23 + 25);
        f.setVisible(true);
        f.add(this);
    }

    public Color getBackgroundColor() {
        return gamebackground;
    }

    public Color getWallColor() {
        return gamewall;
    }

    public void connectModel(gameModel m) {
        gamemodel = m;
        well = gamemodel.getWell();
        pieceOrigin = gamemodel.getNowBlockPoint();
        nowBlock = gamemodel.getNowBlock();
        nextBlock = gamemodel.getNextBlock();
        score = gamemodel.getScore();
    }

    public void update() {
        nowBlock = gamemodel.getNowBlock();
        nextBlock = gamemodel.getNextBlock();
        well = gamemodel.getWell();
        pieceOrigin = gamemodel.getNowBlockPoint();
        score = gamemodel.getScore();
        repaint();
    }

    public void connectController(gameController c) {
        c = gamecontroller;
    }
    // Creates a border around the well and initializes the dropping piece

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
        g.setColor(nextBlock.getColor());
        Point[][] shape = nextBlock.getShape();
        for (Point p : shape[nextBlock.getRotate()]) {
            g.fillRect((p.x + 5) * 26,
                    (p.y + 0) * 26,
                    25, 25);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // Paint the well
        g.fillRect(0, 0, 26 * 12, 26 * 23);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                g.setColor(well[i][j]);
                g.fillRect(26 * i, 26 * j, 25, 25);
            }
        }

        // Display the score
        g.setColor(Color.WHITE);
        g.drawString("Next", 5 * 12, 25);
        g.drawString("" + score, 19 * 12, 25);

        // Draw the currently falling piece
        drawPiece(g);
        drawNext(g);
    }

}
