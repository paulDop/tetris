/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author paul
 */
public class gameController extends JPanel {

    public gameModel gamemodel;
    public gameView gameview;

    public gameController() {
        
    }
    
    public void connectView(gameView v) { 
        gameview = v;
    }
    
    public void connectModel(gameModel m) {
        gamemodel = m;
    }
    
    public void userHasInput() {

    }

    public void start() {
        // Listener code
        // using Fframe Listener
        gameview.f.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        gamemodel.turnNowBlock(-1);
                        break;
                    // softdrop can be used for speed up
                    case KeyEvent.VK_DOWN:
                        gamemodel.dropNowBlock();
                        break;
                    case KeyEvent.VK_LEFT:
                        gamemodel.moveNowBlock(-1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        gamemodel.moveNowBlock(+1);
                        break;
                    // hard drop can be used immediately drop
                    case KeyEvent.VK_SPACE:
                        gamemodel.dropNowBlockHard();
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });  // Listener end

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        gamemodel.dropNowBlock();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }

}
