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
    
    private Image imgList[] = new Image[7];
    private Color colorList[] = {Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red};
    
    
    public B10415003() {
        this.gamecontroller = new gameController();
    }

    public void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((15 + 3) * 26 + 10, 30 * 23 + 25);
        frame.setVisible(true);
        frame.add(this);
        
        imgList[0] = Toolkit.getDefaultToolkit().getImage(".\\src\\img\\block0.png");
        imgList[1] = Toolkit.getDefaultToolkit().getImage(".\\src\\img\\block1.png");
        imgList[2] = Toolkit.getDefaultToolkit().getImage(".\\src\\img\\block2.png");
        imgList[3] = Toolkit.getDefaultToolkit().getImage(".\\src\\img\\block3.png");
        imgList[4] = Toolkit.getDefaultToolkit().getImage(".\\src\\img\\block4.png");
        imgList[5] = Toolkit.getDefaultToolkit().getImage(".\\src\\img\\block5.png");
        imgList[6] = Toolkit.getDefaultToolkit().getImage(".\\src\\img\\block6.png");
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
        
        Point[][] shape = nowBlock.getShape();
        for (Point p : shape[nowBlock.getRotate()]) {
            int flag = 0;
            for (int k = 0 ; k < 7 ; k = k + 1)
            {
                if (nowBlock.getColor() == colorList[k])
                {
                    flag = 1;
                    g.drawImage(imgList[k], (p.x + pieceOrigin.x) * 26, (p.y + pieceOrigin.y) * 26, this);
                }
            }
            if (flag == 0)
            {
                g.setColor(nextBlock.getColor());
                g.fillRect((p.x + pieceOrigin.x) * 26,
                    (p.y + pieceOrigin.y) * 26,
                    25, 25);
            }
            
        }
    }

    private void drawNext(Graphics g) {
        g.setColor(Color.WHITE);
        Point[][] shape = nextBlock.getShape();
        
        g.fillRect(13 * 26, 1 * 26, 150, 100);
        
        for (Point p : shape[nextBlock.getRotate()]) {
            int flag = 0;
            for (int k = 0 ; k < 7 ; k = k + 1)
            {
                if (nextBlock.getColor() == colorList[k])
                {
                    flag = 1;
                    g.drawImage(imgList[k], (p.x + 4) * 26 + 235, (p.y + 0) * 26 + 45, this);
                }
            }
            if (flag == 0)
            {
                g.setColor(nextBlock.getColor());
                g.fillRect((p.x + 5) * 26+235,
                    (p.y + 0) * 26+45,
                    25, 25);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // Paint the blockContainer
        g.fillRect(0, 0, 26 * 12, 26 * 23);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                
                int flag = 0;
                for (int k = 0 ; k < 7 ; k = k + 1)
                {
                    if (blockContainer[i][j] == colorList[k])
                    {
                        flag = 1;
                        g.drawImage(imgList[k], 26 * i, 26 * j, this);
                    }
                }
                if (flag == 0)
                {
                    g.setColor(blockContainer[i][j]);
                    g.fillRect(26 * i, 26 * j, 25, 25);
                }
            }
        }

        // Display the score
        g.setColor(Color.WHITE);
        g.fillRect(13 * 26, 125, 150, 75);
        
        g.setColor(Color.BLACK);
        g.drawString("Next", 28 * 12, 20);
        g.drawString("Score: " + score, 28 * 12, 150);

        // Draw the currently falling piece
         
        if (gamemodel.isGameover())
        {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier New", Font.PLAIN, 32));
            g.drawString("----------------", 6, 175);
            g.drawString("----gameover----", 6, 200);
            g.drawString("----------------", 6, 225);
            g.drawString("----gameover----", 6, 250);
            g.drawString("----------------", 6, 275);
            g.drawString("----gameover----", 6, 300);
            g.drawString("----------------", 6, 325);
            g.drawString("----gameover----", 6, 350);
            g.drawString("----------------", 6, 375);
            g.drawString("----gameover----", 6, 400);
            g.drawString("----------------", 6, 425);
        }
        else 
        {
            drawPiece(g);
            drawNext(g);
        }
    }

}