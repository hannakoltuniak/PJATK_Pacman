package Frames;
import Characters.Pacman;
import Characters.Ghost;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GameBoardPanel extends JPanel {
    //region Leveldata
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
    //endregion
    private final int rows;
    private final int cols;
    private final int[][] maze;
    private final boolean[][] dots;
    private final Pacman pacman;
    private final List<Ghost> ghosts;
    private final Random random = new Random();
    private final JLabel[][] grid;
    private final ImageIcon[][] pacmanIcons;
    private final ImageIcon dotIcon;
    private final ImageIcon wallIcon;
    private final ImageIcon emptyIcon;
    private int pacmanAnimationIndex = 0;

    public GameBoardPanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = generateMaze(rows, cols);
        this.dots = new boolean[rows][cols];
        this.grid = new JLabel[rows][cols];

        this.pacmanIcons = new ImageIcon[][]{
                {new ImageIcon("src/Pngs/pacman/pacman_right_1.png"), new ImageIcon("src/Pngs/pacman/pacman_right_2.png"), new ImageIcon("src/Pngs/pacman/pacman_right_3.png")},
                {new ImageIcon("src/Pngs/pacman/pacman_up_1.png"), new ImageIcon("src/Pngs/pacman/pacman_up_2.png"), new ImageIcon("src/Pngs/pacman/pacman_up_3.png")},
                {new ImageIcon("src/Pngs/pacman/pacman_left_1.png"), new ImageIcon("src/Pngs/pacman/pacman_left_2.png"), new ImageIcon("src/Pngs/pacman/pacman_left_3.png")},
                {new ImageIcon("src/Pngs/pacman/pacman_down_1.png"), new ImageIcon("src/Pngs/pacman/pacman_down_2.png"), new ImageIcon("src/Pngs/pacman/pacman_down_3.png")}
        };
        this.dotIcon = new ImageIcon("src/Pngs/dot.png");
        this.wallIcon = new ImageIcon("src/Pngs/wall.png");
        this.emptyIcon = new ImageIcon("src/Pngs/empty.png");

        initializeDots();
        this.pacman = new Pacman(1, 1);
        this.ghosts = new ArrayList<>();
        spawnGhosts(4);

        this.setLayout(new GridLayout(rows, cols));
        this.setBackground(Color.BLACK);

        Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
        this.setBorder(border);

        setFocusable(true);

        createGrid();

        pacman.setCurrentIcons(pacmanIcons[0]);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        pacman.setDirection(0, -1);
                        pacman.setCurrentIcons(pacmanIcons[1]);
                        break;
                    case KeyEvent.VK_DOWN:
                        pacman.setDirection(0, 1);
                        pacman.setCurrentIcons(pacmanIcons[3]);
                        break;
                    case KeyEvent.VK_LEFT:
                        pacman.setDirection(-1, 0);
                        pacman.setCurrentIcons(pacmanIcons[2]);
                        break;
                    case KeyEvent.VK_RIGHT:
                        pacman.setDirection(1, 0);
                        pacman.setCurrentIcons(pacmanIcons[0]);
                        break;
                }

                eatDot(pacman.getX(), pacman.getY());
                checkCollision();
                updateGrid();
            }
        });

        startPacmanThread();
        startGhostThreads();
        startPacmanAnimationThread();
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

    private void createGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JLabel cell = new JLabel();
                cell.setOpaque(true);
                cell.setHorizontalAlignment(SwingConstants.CENTER);

                if (maze[i][j] == 1) {
                    cell.setIcon(wallIcon);
                } else if (dots[i][j]) {
                    cell.setIcon(dotIcon);
                }

                grid[i][j] = cell;
                this.add(cell);
            }
        }

        updateGrid();
    }

    private void updateGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 1) {
                    grid[i][j].setIcon(wallIcon);
                } else if (dots[i][j]) {
                    grid[i][j].setIcon(dotIcon);
                } else {
                    grid[i][j].setIcon(emptyIcon);
                }
            }
        }

        grid[pacman.getY()][pacman.getX()].setIcon(pacman.getCurrentIcon(pacmanAnimationIndex));
        for (Ghost ghost : ghosts) {
            grid[ghost.getY()][ghost.getX()].setIcon(ghost.getIcon());
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
                    updateGrid();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        pacmanThread.start();
    }

    private void startPacmanAnimationThread() {
        Thread mouthAnimationThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    pacmanAnimationIndex = (pacmanAnimationIndex + 1) % pacmanIcons.length;
                    updateGrid();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        mouthAnimationThread.start();
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
        if (x >= 0 && x < cols && y >= 0 && y < rows && dots[y][x]) {
            dots[y][x] = false;
            grid[y][x].setIcon(emptyIcon);
        }
    }

    private void spawnGhosts(int numGhosts) {
        ImageIcon[] ghostIcons = {
            new ImageIcon("src/Pngs/ghosts/ghost_blue.png"), new ImageIcon("src/Pngs/ghosts/ghost_orange.png"), new ImageIcon("src/Pngs/ghosts/ghost_pink.png"), new ImageIcon("src/Pngs/ghosts/ghost_red.png")
        };
        int imageIndex = 0;

        for (int i = 0; i < numGhosts; i++) {
            int x, y;
            do {
                x = random.nextInt(cols);
                y = random.nextInt(rows);
            } while (maze[y][x] != 0);

            ghosts.add(new Ghost(x, y, ghostIcons[imageIndex]));
            imageIndex++;
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
                        updateGrid();
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

    private void gameOver() {
        System.out.println("game over");
        System.exit(0);
    }
}