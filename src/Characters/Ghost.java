package Characters;
import Frames.GameBoardPanel;

import java.awt.*;
import java.util.Random;

public class Ghost {
    private int x, y;
    private final int size;
    private final Color color;
    private final Random random;

    public Ghost(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.random = new Random();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int[][] maze) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int direction = random.nextInt(4);

        int newX = x + dx[direction];
        int newY = y + dy[direction];

        if (newX >= 0 && newX < maze[0].length && newY >= 0 && newY < maze.length && maze[newY][newX] == 0) {
            x = newX;
            y = newY;
        }
    }

    public void moveTowards(int pacmanX, int pacmanY, int[][] maze, GameBoardPanel gameBoard) {
        int bestX = x, bestY = y;
        int bestDistance = Math.abs(pacmanX - x) + Math.abs(pacmanY - y);

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newX < maze[0].length && newY >= 0 && newY < maze.length && maze[newY][newX] == 0 &&
                    !gameBoard.isPositionOccupiedByGhost(newX, newY)) {
                int newDistance = Math.abs(pacmanX - newX) + Math.abs(pacmanY - newY);
                if (newDistance < bestDistance) {
                    bestX = newX;
                    bestY = newY;
                    bestDistance = newDistance;
                }
            }
        }

        x = bestX;
        y = bestY;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x * size, y * size, size, size);
    }
}
