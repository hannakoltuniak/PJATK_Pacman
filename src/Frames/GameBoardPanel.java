package Frames;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoardPanel extends JPanel {
    private final int rows;
    private final int cols;
    private final int[][] maze;
    private final boolean[][] dots;
    private final Frames.Pacman pacman;
    private final short[] levelData = {
            19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22, 19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            21,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 21,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            21,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 21,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            21,  0,  0,  0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20, 21,  0,  0,  0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
            17, 18, 18, 18, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20, 17, 18, 18, 18, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 24, 20, 17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 24, 20,
            25, 16, 16, 16, 24, 24, 28,  0, 25, 24, 24, 16, 20,  0, 21, 25, 16, 16, 16, 24, 24, 28,  0, 25, 24, 24, 16, 20,  0, 21,
            1, 17, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 20,  0, 21,  1, 17, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 20,  0, 21,
            1, 17, 16, 16, 18, 18, 22,  0, 19, 18, 18, 16, 20,  0, 21,  1, 17, 16, 16, 18, 18, 22,  0, 19, 18, 18, 16, 20,  0, 21,
            1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,  1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,
            1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,  1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,
            1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20,  0, 21,  1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20,  0, 21,
            1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,
            1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,  1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
            9,  8,  8,  8,  8,  8,  8,  8,  8,  8, 25, 24, 24, 24, 28,  9,  8,  8,  8,  8,  8,  8,  8,  8,  8, 25, 24, 24, 24, 28,
            19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22, 19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            21,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 21,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            21,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 21,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            21,  0,  0,  0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20, 21,  0,  0,  0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
            17, 18, 18, 18, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20, 17, 18, 18, 18, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 24, 20, 17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 24, 20,
            25, 16, 16, 16, 24, 24, 28,  0, 25, 24, 24, 16, 20,  0, 21, 25, 16, 16, 16, 24, 24, 28,  0, 25, 24, 24, 16, 20,  0, 21,
            1, 17, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 20,  0, 21,  1, 17, 16, 20,  0,  0,  0,  0,  0,  0,  0, 17, 20,  0, 21,
            1, 17, 16, 16, 18, 18, 22,  0, 19, 18, 18, 16, 20,  0, 21,  1, 17, 16, 16, 18, 18, 22,  0, 19, 18, 18, 16, 20,  0, 21,
            1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,  1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,
            1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,  1, 17, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 20,  0, 21,
            1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20,  0, 21,  1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20,  0, 21,
            1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,  1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,  0, 21,
            1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,  1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
            9,  8,  8,  8,  8,  8,  8,  8,  8,  8, 25, 24, 24, 24, 28,  9,  8,  8,  8,  8,  8,  8,  8,  8,  8, 25, 24, 24, 24, 28
    };

    public GameBoardPanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = generateMaze(rows, cols);
        this.dots = new boolean[rows][cols];
        initializeDots();
        this.pacman = new Frames.Pacman(1, 1, 20);
        this.setPreferredSize(new Dimension(cols * 20, rows * 20));
        this.setBackground(Color.BLACK);
        this.setOpaque(false);

        Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
        this.setBorder(border);

        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        pacman.move(0, -1, maze);
                        break;
                    case KeyEvent.VK_DOWN:
                        pacman.move(0, 1, maze);
                        break;
                    case KeyEvent.VK_LEFT:
                        pacman.move(-1, 0, maze);
                        break;
                    case KeyEvent.VK_RIGHT:
                        pacman.move(1, 0, maze);
                        break;
                }

                eatDot(pacman.getX(), pacman.getY());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPacmanMaze(g);
        drawDots(g);
        pacman.draw(g);
    }

    private void drawPacmanMaze(Graphics g) {
        g.setColor(Color.BLUE);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 1)
                    g.fillRect(j * 20, i * 20, 20, 20);
            }
        }
    }

    private void drawDots(Graphics g) {
        g.setColor(Color.WHITE);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dots[i][j])
                    g.fillOval(j * 20 + 8, i * 20 + 8, 4, 4);
            }
        }
    }

    private void initializeDots() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 0)
                    dots[i][j] = true;
            }
        }

        dots[1][1] = false;
    }

    private void eatDot(int x, int y) {
        if (x >= 0 && x < cols && y >= 0 && y < rows && dots[y][x])
            dots[y][x] = false;
    }

    private int[][] generateMaze(int rows, int cols) {
        int[][] maze = new int[rows][cols];
        int levelWidth = 30;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * levelWidth + j;

                if (index < levelData.length)
                    maze[i][j] = (levelData[index] == 0) ? 1 : 0;
                else
                    maze[i][j] = 0;
            }
        }

        maze[1][1] = 0;

        return maze;
    }

}
