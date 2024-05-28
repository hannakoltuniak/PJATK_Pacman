package Characters;

import javax.swing.*;

public class Pacman {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int direction;
    private ImageIcon[] currentIcons;

    public Pacman(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.dx = 0;
        this.dy = 0;

        this.currentIcons = new ImageIcon[3];
    }

    public void move(int[][] maze) {
        int newX = x + dx;
        int newY = y + dy;
        if (isValidMove(newX, newY, maze)) {
            x = newX;
            y = newY;
        }
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        if (dx == 1) {
            direction = 0; // Right
        } else if (dx == -1) {
            direction = 2; // Left
        } else if (dy == -1) {
            direction = 1; // Up
        } else if (dy == 1) {
            direction = 3; // Down
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCurrentIcons(ImageIcon[] icons) {
        this.currentIcons = icons;
    }

    public ImageIcon getCurrentIcon(int index) {
        return currentIcons[index % 3];
    }

    private boolean isValidMove(int x, int y, int[][] maze) {
        return x >= 0 && y >= 0 && x < maze[0].length && y < maze.length && maze[y][x] == 0;
    }
}
