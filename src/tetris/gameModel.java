/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;

/**
 *
 * @author paul
 */
public class gameModel {
    
    private int score = 0;
    private Block nowBlock;
    private Block nextBlock;
    private Color[][] well = new Color[12][24];
    private int blockContainer[][] = new int[10][20];
    public gameView gameview; 
    
    
    
    public Block getNowBlock(){
        return nowBlock;
    }
    
    public Block getNextBlock(){
        return nextBlock;
    }
    
    public int getScore() {
        return score;
    }
    
    public int[][] getConatainer() {
        return blockContainer;
    }
    
    private void createNextBlock() { 
        
    }
    
    private void createNowBlock() { 
        
    }
    
    public void moveNowBlock(int dir) {
        
    }
   
    public void dropNowBlock() {
        System.out.println("drop");
        gameview.update();
    }
    
    public void turnNowBlock(int dir) {
        System.out.println("turn");
    }
    
    public gameModel() {
        createNowBlock();
        createNextBlock();
    }
    
}
