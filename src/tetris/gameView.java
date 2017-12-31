/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author paul
 */
public abstract class gameView extends JPanel {

    private Color[][] well;
    private int score;
    public JFrame frame = new JFrame("Tetris");
    private Point pieceOrigin;
    private gameController gamecontroller = new gameController();
    private gameModel gamemodel;
    private Color gamebackground = Color.BLACK;
    private Color gamewall = Color.GRAY;
    private Block nowBlock;
    private Block nextBlock;

    public gameView() {

    }

    public void init() {
        
    }

    public Color getBackgroundColor() {
        return gamebackground;
    }

    public Color getWallColor() {
        return gamewall;
    }

    public void connectModel(gameModel m) {
        
    }

    public void update() {
        
    }

    public void connectController(gameController c) {
        
    }
    
}
