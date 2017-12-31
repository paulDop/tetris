/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/**
 *
 * @author Jason
 */

// This is the glue code for running mvc model together
public class Tetris {

    public Tetris() {

        //create Model and View
        gameModel myModel = new gameModel();
        gameView myView = new gameView();

        //tell Model about View. 
        myModel.connectView(myView);	

        //create Controller. tell it about Model and View, initialise model
        gameController myController = new gameController();
        myController.connectModel(myModel);
        myController.connectView(myView);
        
        //tell View about Controller 
        myView.connectController(myController);
        myView.connectModel(myModel);
        myView.init();
        myController.start();
    }

    public static void main(String[] args) {
        Tetris Run = new Tetris();
    }
}
