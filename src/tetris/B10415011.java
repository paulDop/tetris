package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class B10415011 extends gameView 
{
    
    private Color[][] blockContainer;
    private int score;
    private Point pieceOrigin = new Point(5, 2);
    private gameController gamecontroller;
    private gameModel gamemodel;
    private final Color gamebackground = Color.BLACK;
    private final Color gamewall = Color.YELLOW;
    private Block nowBlock;
    private Block nextBlock;
    public JLabel jLabel = new JLabel("Score:");
    
    public B10415011() 
    {
        this.gamecontroller = new gameController();
    }

    public void init() 
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(15 * 26 + 10, 30 * 23 + 25);
        frame.setVisible(true);
        frame.add(this);
        frame.getContentPane().add(BorderLayout.SOUTH, jLabel);
        jLabel.setForeground(Color.BLACK);
     }

    public Color getBackgroundColor() 
    {
        return gamebackground;
    }

    public Color getWallColor() 
    {
        return gamewall;
    }

    public void connectModel(gameModel m) 
    {
        gamemodel = m;
        blockContainer = gamemodel.getContainer();
        pieceOrigin = gamemodel.getNowBlockPoint();
        nowBlock = gamemodel.getNowBlock();
        nextBlock = gamemodel.getNextBlock();
        score = gamemodel.getScore();
    }

    public void update() 
    {
        nowBlock = gamemodel.getNowBlock();
        nextBlock = gamemodel.getNextBlock();
        blockContainer = gamemodel.getContainer();
        pieceOrigin = gamemodel.getNowBlockPoint();
        score = gamemodel.getScore();
        repaint();
    }

    public void connectController(gameController c) 
    {
        gamecontroller = c;
    }
    // Creates a border around the blockContainer and initializes the dropping piece

    private void drawPiece(Graphics g) 
    {
        g.setColor(nowBlock.getColor());//setColor嚗身摰鼓鋆賡???
        Point[][] shape = nowBlock.getShape();
        for (Point p : shape[nowBlock.getRotate()]) 
        {
            g.fillRect((p.x + pieceOrigin.x) * 26+30,
            			(p.y + pieceOrigin.y) * 26+30,
            				25, 25);//fillRect嚗?箏‵皛輸??脩??瑟敶?
        }
    }

    private void drawNext(Graphics g) 
    {
        g.setColor(nextBlock.getColor());
        Point[][] shape = nextBlock.getShape();
        for (Point p : shape[nextBlock.getRotate()]) 
        {
            g.fillRect((p.x + 5) * 26+30,
                    (p.y + 0) * 26+30,
                    25, 25);
        }
    }
    public void paintComponent(Graphics g) 
    {
        // Paint the blockContainer

        g.fillRect(30, 30, 26 * 12, 26 * 23);
        for (int i = 0; i < 12; i++) 
        {
            for (int j = 0; j < 23; j++) 
            {
                g.setColor(blockContainer[i][j]);
                g.fillRect(26 * i+30, 26 * j+30, 25, 25);
            }
        }
        jLabel.setText("Score:\b" + score);
        if(gamemodel.isGameover())
        {
        	g.setColor(Color.RED);
            g.drawString("Game over.", 150, 20);
        }
        else
        {
        	drawPiece(g);
        	drawNext(g);
        }
    }
}