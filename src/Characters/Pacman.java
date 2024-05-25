package Characters;

import java.awt.*;

public class Pacman {
    private int x;
    private int y;
    private int cellSize;
    private int dx;
    private int dy;
    private boolean mouthOpen;
    private int mouthAngle;
    private int mouthDirection;


    public Pacman(int startX, int startY, int cellSize) {
        this.x = startX;
        this.y = startY;
        this.cellSize = cellSize;
        this.dx = 0;
        this.dy = 0;

        this.mouthOpen = true;
        this.mouthAngle = 45;
        this.mouthDirection = 1;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        if (mouthOpen) {
            int startAngle = getStartAngle();
            int arcAngle = 300;
            g.fillArc(x * cellSize, y * cellSize, cellSize, cellSize, startAngle, arcAngle);
        } else {
            g.fillOval(x * cellSize, y * cellSize, cellSize, cellSize);
        }
    }

    public void move(int[][] maze) {
        int newX = x + dx;
        int newY = y + dy;
        if (newX >= 0 && newY >= 0 && newX < maze[0].length && newY < maze.length && maze[newY][newX] == 0) {
            x = newX;
            y = newY;
        }
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void animateMouth() {
        mouthOpen = !mouthOpen;
    }

    private int getStartAngle() {
        if (dx == 1) return 45;
        if (dx == -1) return 225;
        if (dy == -1) return 135;
        if (dy == 1) return 315;
        return 45;
    }

    private int getArcAngle() {
        return 360 - mouthAngle * 2;
    }
}
