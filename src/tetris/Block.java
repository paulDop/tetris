/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Jason
 */
public class Block {

    private int type;
    private int rotate;
    private Point[][] shape;
    private Color color;
    private final Color[] blockColors = {
        Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red
    };
    private final Point[][][] Tetraminos = {
        // I-Piece
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)}
        },
        // J-Piece
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0)}
        },
        // L-Piece
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0)}
        },
        // O-Piece
        {
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)}
        },
        // S-Piece
        {
            {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)}
        },
        // T-Piece
        {
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)}
        },
        // Z-Piece
        {
            {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)},
            {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)}
        }
    };

    public Block(int type) {
        this.type = type;
        rotate = 0;
        color = blockColors[type];
        shape = this.Tetraminos[this.type];
    }

    public int getType() {
        return type;
    }

    public int getRotate() {
        return rotate;
    }

    public Point[][] getShape() {
        return shape;
    }
    
    public Color getColor() {
        return color;
    }
    public void setRotate(int dir) {
        rotate = dir;
    }
}
