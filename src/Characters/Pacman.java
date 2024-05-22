package Frames;

import java.awt.*;

public class Pacman {
    private int x;
    private int y;
    private int cellSize;

    public Pacman(int startX, int startY, int cellSize) {
        this.x = startX;
        this.y = startY;
        this.cellSize = cellSize;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    public void move(int dx, int dy, int[][] maze) {
        int newX = x + dx;
        int newY = y + dy;
        if (newX >= 0 && newY >= 0 && newX < maze[0].length && newY < maze.length && maze[newY][newX] == 0) {
            x = newX;
            y = newY;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
