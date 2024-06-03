package Views;
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
    //TODO: make better lvl data
    //region Leveldata
    private final short[] levelData = {
            1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1,
            1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,
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
    private final ImageIcon dotIcon;
    private final ImageIcon wallIcon;
    private final ImageIcon emptyIcon;
    private final ImageIcon heartIcon;
    private int pacmanAnimationIndex = 0;
    private JPanel gameInfoPanel;
    private JPanel heartsPanel;
    private JLabel timeLabel;
    private long startTime;
    private JLabel pointsLabel;
    private int points;

    public GameBoardPanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maze = generateMaze(rows, cols);
        this.dots = new boolean[rows][cols];
        this.grid = new JLabel[rows][cols];

        this.dotIcon = new ImageIcon("src/Pngs/dot.png");
        this.wallIcon = new ImageIcon("src/Pngs/wall.png");
        this.emptyIcon = new ImageIcon("src/Pngs/empty.png");
        this.heartIcon = new ImageIcon("src/Pngs/heart.png");

        initializeDots();
        this.pacman = new Pacman(1, 1);
        System.out.println(this.pacman.getLives());
        this.ghosts = new ArrayList<>();
        spawnGhosts(4);

        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        gameInfoPanel = new JPanel();
        gameInfoPanel.setBackground(Color.BLACK);
        gameInfoPanel.setLayout(new BorderLayout());

        heartsPanel = new JPanel();
        heartsPanel.setBackground(Color.BLACK);
        gameInfoPanel.add(heartsPanel, BorderLayout.WEST);

        pointsLabel = new JLabel("Points: 0");
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameInfoPanel.add(pointsLabel, BorderLayout.CENTER);

        timeLabel = new JLabel("Time: 0s");
        timeLabel.setForeground(Color.WHITE);
        gameInfoPanel.add(timeLabel, BorderLayout.EAST);

        add(gameInfoPanel, BorderLayout.NORTH);
        updateHearts();

        JPanel gridPanel = new JPanel(new GridLayout(rows, cols));
        gridPanel.setBackground(Color.BLACK);
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 3);
        gridPanel.setBorder(border);

        createGrid(gridPanel);

        add(gridPanel, BorderLayout.CENTER);

        setFocusable(true);

        pacman.setCurrentIcons(pacman.pacmanIcons[0]);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        pacman.setDirection(0, -1);
                        pacman.setCurrentIcons(pacman.pacmanIcons[1]);
                        break;
                    case KeyEvent.VK_DOWN:
                        pacman.setDirection(0, 1);
                        pacman.setCurrentIcons(pacman.pacmanIcons[3]);
                        break;
                    case KeyEvent.VK_LEFT:
                        pacman.setDirection(-1, 0);
                        pacman.setCurrentIcons(pacman.pacmanIcons[2]);
                        break;
                    case KeyEvent.VK_RIGHT:
                        pacman.setDirection(1, 0);
                        pacman.setCurrentIcons(pacman.pacmanIcons[0]);
                        break;
                }

                removeDot(pacman.getX(), pacman.getY());
                checkCollision();
                updateGrid();
            }
        });

        startPacmanThread();
        startGhostThreads();
        startPacmanAnimationThread();
        startGameTimer();
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

    private void createGrid(JPanel gridPanel) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JLabel cell = new JLabel();
                cell.setOpaque(true);
                cell.setHorizontalAlignment(SwingConstants.CENTER);

                if (maze[i][j] == 1)
                    cell.setIcon(wallIcon);
                else if (dots[i][j])
                    cell.setIcon(dotIcon);

                grid[i][j] = cell;
                gridPanel.add(cell);
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

    private void updateHearts() {
        heartsPanel.removeAll();
        for (int i = 0; i < pacman.getLives(); i++) {
            heartsPanel.add(new JLabel(heartIcon));
        }
        heartsPanel.revalidate();
        heartsPanel.repaint();
    }

    private void updatePoints() {
        pointsLabel.setText("Points: " + points);
    }

    private void startPacmanThread() {
        Thread pacmanThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                    pacman.move(maze);
                    removeDot(pacman.getX(), pacman.getY());
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
                    pacmanAnimationIndex = (pacmanAnimationIndex + 1) % pacman.pacmanIcons.length;
                    updateGrid();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        mouthAnimationThread.start();
    }

    private void startGameTimer() {
        startTime = System.currentTimeMillis();

        Thread timerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                    SwingUtilities.invokeLater(() -> timeLabel.setText("Time: " + elapsedTime + "s"));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        timerThread.start();
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

    private void removeDot(int x, int y) {
        if (x >= 0 && x < cols && y >= 0 && y < rows && dots[y][x]) {
            dots[y][x] = false;
            grid[y][x].setIcon(emptyIcon);
            points++;
            updatePoints();
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
                        if (isPacmanClose(ghost)) {
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

    private boolean isPacmanClose(Ghost ghost) {
        int distance = Math.abs(pacman.getX() - ghost.getX()) + Math.abs(pacman.getY() - ghost.getY());
        int range = 5;
        return distance < range;
    }

    public boolean isPositionAvailable(int x, int y) {
        for (Ghost ghost : ghosts) {
            if (ghost.getX() == x && ghost.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private void checkCollision() {
        for (Ghost ghost : ghosts) {
            if (pacman.getX() == ghost.getX() && pacman.getY() == ghost.getY()) {
                pacman.loseLife();
                updateHearts();
                System.out.println(pacman.getLives());

                if (pacman.getLives() <= 0)
                    gameOver();
                else
                    pacman.resetPacmanPosition();

            }
        }
    }

    private void gameOver() {
        System.out.println("game over");
        System.exit(0);
    }
}