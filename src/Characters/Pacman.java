package Characters;
import javax.swing.*;

public class Pacman {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int pacmanIconDirection;
    private ImageIcon[] currentIcons;
    public final ImageIcon[][] pacmanIcons;
    private int lives;

    public Pacman(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.dx = 0;
        this.dy = 0;
        this.lives = 3;

        this.currentIcons = new ImageIcon[3];
        this.pacmanIcons = new ImageIcon[][]{
                {new ImageIcon("src/Pngs/pacman/pacman_right_1.png"), new ImageIcon("src/Pngs/pacman/pacman_right_2.png"), new ImageIcon("src/Pngs/pacman/pacman_right_3.png")},
                {new ImageIcon("src/Pngs/pacman/pacman_up_1.png"), new ImageIcon("src/Pngs/pacman/pacman_up_2.png"), new ImageIcon("src/Pngs/pacman/pacman_up_3.png")},
                {new ImageIcon("src/Pngs/pacman/pacman_left_1.png"), new ImageIcon("src/Pngs/pacman/pacman_left_2.png"), new ImageIcon("src/Pngs/pacman/pacman_left_3.png")},
                {new ImageIcon("src/Pngs/pacman/pacman_down_1.png"), new ImageIcon("src/Pngs/pacman/pacman_down_2.png"), new ImageIcon("src/Pngs/pacman/pacman_down_3.png")}
        };
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

        if (dx == 1)
            pacmanIconDirection = 0; // Right
        else if (dx == -1)
            pacmanIconDirection = 2; // Left
         else if (dy == -1)
            pacmanIconDirection = 1; // Up
        else if (dy == 1)
            pacmanIconDirection = 3; // Down

        this.currentIcons = pacmanIcons[pacmanIconDirection];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void resetPacmanPosition() {
        setX(0);
        setY(0);
        setDirection(0, 0);
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

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    public void addLife(int num) {
        lives += num;
    }
}
