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
        gamemodel = new gameModel();
        gameview = new gameView();
        //gameview.init();
    }

    public void userHasInput() {

    }

    public void start() {

        gameview.f.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        gamemodel.turnNowBlock(-1);
                        break;
                    case KeyEvent.VK_DOWN:
                        gamemodel.turnNowBlock(+1);
                        break;
                    case KeyEvent.VK_LEFT:
                        gamemodel.moveNowBlock(-1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        gamemodel.moveNowBlock(+1);
                        break;
                    case KeyEvent.VK_SPACE:
                        gamemodel.dropNowBlock();
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        gamemodel.dropNowBlock();
                        //gameview.update();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        gameController gamecontroller = new gameController();
        gamecontroller.start();
    }

}
