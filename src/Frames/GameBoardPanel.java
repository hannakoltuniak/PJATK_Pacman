package Frames;
import Characters.Pacman;
import Characters.Ghost;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private final Pacman pacman;
    private final List<Ghost> ghosts;
    private final int numGhosts = 4;
    private final Random random = new Random();
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
        this.pacman = new Pacman(1, 1, 20);
        this.ghosts = new ArrayList<>();
        spawnGhosts(4);

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
                        pacman.setDirection(0, -1);
                        break;
                    case KeyEvent.VK_DOWN:
                        pacman.setDirection(0, 1);
                        break;
                    case KeyEvent.VK_LEFT:
                        pacman.setDirection(-1, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        pacman.setDirection(1, 0);
                        break;
                }

                eatDot(pacman.getX(), pacman.getY());
                checkCollision();
                repaint();
            }
        });

        startPacmanThread();
        startGhostThreads();
        startPacmanAnimation();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPacmanMaze(g);
        drawDots(g);
        pacman.draw(g);
        for (Ghost ghost : ghosts) {
            ghost.draw(g);
        }
    }

    private void startPacmanThread() {
        Thread pacmanThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                    pacman.move(maze);
                    eatDot(pacman.getX(), pacman.getY());
                    checkCollision();
                    repaint();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        pacmanThread.start();
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

    private void spawnGhosts(int numGhosts) {
        Color[] colors = {Color.RED, Color.GREEN, Color.PINK, Color.ORANGE};

        for (int i = 0; i < numGhosts; i++) {
            int x, y;
            do {
                x = random.nextInt(cols);
                y = random.nextInt(rows);
            } while (maze[y][x] != 0);

            ghosts.add(new Ghost(x, y, 20, colors[i % colors.length]));
        }
    }

    private void startGhostThreads() {
        for (Ghost ghost : ghosts) {
            Thread ghostThread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(250);
                        if (isPacmanNearby(ghost)) {
                            ghost.moveTowards(pacman.getX(), pacman.getY(), maze, this);
                        } else {
                            ghost.move(maze);
                        }
                        checkCollision();
                        repaint();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            ghostThread.start();
        }
    }

    private boolean isPacmanNearby(Ghost ghost) {
        int distance = Math.abs(pacman.getX() - ghost.getX()) + Math.abs(pacman.getY() - ghost.getY());
        int range = 5;
        return distance < range;
    }

    public boolean isPositionOccupiedByGhost(int x, int y) {
        for (Ghost ghost : ghosts) {
            if (ghost.getX() == x && ghost.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private void checkCollision() {
        for (Ghost ghost : ghosts) {
            if (ghost.getX() == pacman.getX() && ghost.getY() == pacman.getY()) {
                gameOver();
            }
        }
    }

    private void startPacmanAnimation() {
        Thread mouthAnimationThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    pacman.animateMouth();
                    repaint();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        mouthAnimationThread.start();
    }

    private void gameOver() {
        System.out.println("game over");
        System.exit(0);
    }

}
