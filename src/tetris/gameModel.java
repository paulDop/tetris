/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author paul
 */
public class gameModel {
    private ArrayList<Integer> nextPieces = new ArrayList<Integer>();
    private int score = 0;
    private Block nowBlock;
    private Block nextBlock;
    private Point NowPiecePoint = new Point(5, 2);
    private Color[][] blockContainer = new Color[12][24];
    private Color background;
    private Color wall;
    private boolean first = true;
    private gameView gameview;
    private boolean gameover;

    public void connectView(gameView v) {
        gameview = v;
        background = gameview.getBackgroundColor();
        wall = gameview.getWallColor();
    }

    public Block getNowBlock() {
        return nowBlock;
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public Point getNowBlockPoint() {
        return NowPiecePoint;
    }

    public int getScore() {
        return score;
    }
    
    public boolean isGameover()
    {
        return gameover;
    }
    
    private void createNextBlock(int num) {
        nextBlock = new Block(num);
    }

    private void createNowBlock(int num) {
        nowBlock = new Block(num);
    }

    public void moveNowBlock(int dir) {
        if (!collidesAt(NowPiecePoint.x + dir, NowPiecePoint.y, nowBlock.getRotate())) {
            NowPiecePoint.x += dir;
        }
        gameview.update();
    }

    private boolean collidesAt(int x, int y, int rotate) {
        Point[][] shape = nowBlock.getShape();
        for (Point p : shape[rotate]) {
            if (blockContainer[p.x + x][p.y + y] != background) {
                return true;
            }
        }
        return false;
    }

    public void fixToWell() {
        Point[][] shape = nowBlock.getShape();
        for (Point p : shape[nowBlock.getRotate()]) {
            blockContainer[NowPiecePoint.x + p.x][NowPiecePoint.y + p.y] = nowBlock.getColor();
        }
        clearRows();
        newPieces();
        gameview.update();
    }

    public void clearRows() {
        boolean gap;
        int numClears = 0;

        for (int j = 21; j > 0; j--) {
            gap = false;
            for (int i = 1; i < 11; i++) {
                if (blockContainer[i][j] == background) {
                    gap = true;
                    break;
                }
            }
            if (!gap) {
                deleteRow(j);
                j += 1;
                numClears += 1;
            }
        }

        switch (numClears) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
        }
    }

    public void deleteRow(int row) {
        for (int j = row - 1; j > 0; j--) {
            for (int i = 1; i < 11; i++) {
                blockContainer[i][j + 1] = blockContainer[i][j];
            }
        }
    }

    public void dropNowBlock() {
        if (!collidesAt(NowPiecePoint.x, NowPiecePoint.y + 1,nowBlock.getRotate())) {
            NowPiecePoint.y += 1;
        } else {
            fixToWell();
        }
        gameview.update();
    }
    
    public void dropNowBlockHard() {
        while(!collidesAt(NowPiecePoint.x, NowPiecePoint.y + 1,nowBlock.getRotate())) {
            NowPiecePoint.y += 1;
        }
        fixToWell();
    }

    public void turnNowBlock(int dir) {
        int newdir = (nowBlock.getRotate() + dir) % 4;
        if (newdir < 0) {
            newdir = 3;
        }
        if (!collidesAt(NowPiecePoint.x,NowPiecePoint.y, newdir)) {
            nowBlock.setRotate(newdir);
        }
        gameview.update();
    }

    public void newPieces() {
        NowPiecePoint = new Point(5, 2);
        if (nextPieces.isEmpty()) {
            Collections.addAll(nextPieces, 0, 1, 2, 3, 4, 5, 6);
            Collections.shuffle(nextPieces);
        }
        if(first) {
            createNextBlock(nextPieces.get(0));
            nextPieces.remove(0);
            first = false;
        } 
        nowBlock = nextBlock;
        createNextBlock(nextPieces.get(0));
        nextPieces.remove(0);
        
        Point[][] shape = nowBlock.getShape();
        for (Point p : shape[nextBlock.getRotate()]) {
            if (blockContainer[p.x + NowPiecePoint.x][p.y + NowPiecePoint.y] != background) {
                gameover = true;
            }
        }
        
    }

    public void init() {
        blockContainer = new Color[12][24];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                if (i == 0 || i == 11 || j == 22) {
                    blockContainer[i][j] = wall;
                } else {
                    blockContainer[i][j] = background;
                }
            }
        }
        gameover = false;
    }

    public Color[][] getContainer() {
        return blockContainer;
    }

    public gameModel() {
        // initial pieces
        newPieces();
    }

}
