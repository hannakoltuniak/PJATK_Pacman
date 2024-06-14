package Game;

import Characters.Pacman;
import Characters.Ghost;
import Menu.MenuFrame;
import GameData.PacmanUpgrades;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Menu.*;

public class GameBoard extends JPanel {
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
    private final ImageIcon upgradeIcon;
    private int pacmanAnimationIndex = 0;
    private final JPanel heartsPanel;
    private final JLabel timeLabel;
    private long startTime;
    private final JLabel pointsLabel;
    private int points;
    private boolean isPaused = false;
    private boolean isOver = false;
    private boolean collisionDetected = false;
    private boolean upgradeCreated = false;
    private final JButton returnToMenuButton;
    private PacmanUpgrades pacmanUpgrade;
    private boolean immunityActive = false;
    private boolean dumberGhostsActive = false;
    private boolean speedBoostActive = false;
    private final int upgradeDuration = 15000;
    private final JFrame gameWindow;

    public GameBoard(int rows, int cols, short[] levelData, JFrame gameWindow) {
        this.rows = rows;
        this.cols = cols;
        this.gameWindow = gameWindow;

        this.maze = generateMaze(rows, cols, levelData);
        this.dots = new boolean[rows][cols];
        this.grid = new JLabel[rows][cols];

        this.dotIcon = new ImageIcon("src/Pngs/dot.png");
        this.wallIcon = new ImageIcon("src/Pngs/wall.png");
        this.emptyIcon = new ImageIcon("src/Pngs/empty.png");
        this.heartIcon = new ImageIcon("src/Pngs/heart.png");
        this.upgradeIcon = new ImageIcon("src/Pngs/upgrade.png");

        initializeDots();

        this.pacman = new Pacman(1, 1);
        System.out.println(this.pacman.getLives());

        this.ghosts = new ArrayList<>();
        int numberOfGhosts = 4;
        spawnGhosts(numberOfGhosts);

        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JPanel gameInfoPanel = new JPanel();
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

        returnToMenuButton = new JButton("Return to Menu");
        returnToMenuButton.setVisible(false);
        returnToMenuButton.setFont(new Font("Arial", Font.PLAIN, 16));
        returnToMenuButton.setPreferredSize(new Dimension(200, 50));
        returnToMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnToMenuButton.setForeground(Color.YELLOW);
        returnToMenuButton.setBackground(new Color(32, 32, 32));
        returnToMenuButton.addActionListener(e -> returnToMenu());
        this.add(returnToMenuButton, BorderLayout.SOUTH);

        createGrid(gridPanel);

        add(gridPanel, BorderLayout.CENTER);

        setFocusable(true);

        isOver = false;

        pacman.setCurrentIcons(pacman.pacmanIcons[0]);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (!isPaused) {
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

                if (keyCode == KeyEvent.VK_SPACE) {
                    togglePause();
                }
            }
        });


        startPacmanThread();
        startGhostThreads();
        startPacmanAnimationThread();
        startGameTimer();
    }

    private int[][] generateMaze(int rows, int cols, short[] levelData) {
        int[][] maze = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;

                if (index < levelData.length) {
                    maze[i][j] = (levelData[index] == 0) ? 1 : 0;
                } else {
                    maze[i][j] = 0;
                }
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

    private void updateGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 1) {
                    grid[i][j].setIcon(wallIcon);
                } else if (upgradeCreated && pacmanUpgrade != null && pacmanUpgrade.getX() == j && pacmanUpgrade.getY() == i) {
                    grid[i][j].setIcon(upgradeIcon);
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
                    int sleepTime = speedBoostActive ? 100 : 200;
                    Thread.sleep(sleepTime);
                    if (!isPaused) {
                        pacman.move(maze);
                        removeDot(pacman.getX(), pacman.getY());
                        checkCollision();
                        updateGrid();
                    }
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
                    if (!isPaused) {
                        pacmanAnimationIndex = (pacmanAnimationIndex + 1) % pacman.pacmanIcons.length;
                        updateGrid();
                    }
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
            while (!isOver) {
                try {
                    Thread.sleep(1000);
                    if (!isPaused) {
                        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                        SwingUtilities.invokeLater(() -> timeLabel.setText("Time: " + elapsedTime + "s"));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        timerThread.start();
    }

    private void checkCollision() {
        checkGhostCollision();
        checkUpgradeCollision();
    }

    //region Ghosts
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
                        if (!isPaused) {
                            if (!dumberGhostsActive && isPacmanClose(ghost)) {
                                ghost.moveTowards(pacman.getX(), pacman.getY(), maze, this);
                            } else {
                                ghost.move(maze);
                            }
                            checkCollision();
                            updateGrid();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });

            Thread upgradeThreat = new Thread(() -> {
                Random random = new Random();
                while (true) {
                    try {
                        Thread.sleep(5000);
                        if (!isPaused) {
                            double probability = random.nextDouble();
                            if (probability < 0.25) {
                                createUpgrade(ghost.getX(), ghost.getY());
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });

            upgradeThreat.start();
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

    private void checkGhostCollision() {
        if (collisionDetected || immunityActive) {
            return;
        }

        for (Ghost ghost : ghosts) {
            if (pacman.getX() == ghost.getX() && pacman.getY() == ghost.getY()) {
                collisionDetected = true;
                pacman.loseLife();
                updateHearts();
                System.out.println(pacman.getLives());

                if (pacman.getLives() <= 0) {
                    isOver = true;
                    gameOver();
                } else {
                    pacman.resetPacmanPosition();
                    new Thread(() -> {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        collisionDetected = false;
                    }).start();
                }
                return;
            }
        }
    }
    //endregion

    //region Upgrades
    private synchronized void createUpgrade(int upgradeX, int upgradeY) {
        if (upgradeCreated) {
            return;
        }

        if (upgradeX >= 0 && upgradeX < cols && upgradeY >= 0 && upgradeY < rows) {
            dots[upgradeY][upgradeX] = false;

            pacmanUpgrade = new PacmanUpgrades(upgradeX, upgradeY);
            pacmanUpgrade.getRandomUpgrade();

            upgradeCreated = true;
            grid[upgradeY][upgradeX].setIcon(upgradeIcon);
            updateGrid();
            System.out.println("created " + pacmanUpgrade.currentUpgradeType);
        }
    }

    private synchronized void checkUpgradeCollision() {
        if (upgradeCreated && pacmanUpgrade != null && pacman.getX() == pacmanUpgrade.getX() && pacman.getY() == pacmanUpgrade.getY()) {
            System.out.println("Pacman collided with an upgrade: " + pacmanUpgrade.currentUpgradeType);

            switch (pacmanUpgrade.currentUpgradeType) {
                case LIFE1:
                    pacman.addLife(1);
                    updateHearts();
                    break;
                case IMMUNITY:
                    activateImmunity();
                    break;
                case DUMBER_GHOSTS:
                    activateDumberGhosts();
                    break;
                case SPEEDx2:
                    activateSpeedBoost();
                    break;
                case POINTS50:
                    points += 50;
                    updatePoints();
                    break;
            }

            grid[pacmanUpgrade.getY()][pacmanUpgrade.getX()].setIcon(emptyIcon);
            dots[pacmanUpgrade.getY()][pacmanUpgrade.getX()] = false;

            upgradeCreated = false;
            pacmanUpgrade = null;
            updateGrid();
        }
    }

    private void activateImmunity() {
        immunityActive = true;
        new Thread(() -> {
            try {
                Thread.sleep(upgradeDuration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            immunityActive = false;
        }).start();
    }

    private void activateDumberGhosts() {
        dumberGhostsActive = true;
        new Thread(() -> {
            try {
                Thread.sleep(upgradeDuration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            dumberGhostsActive = false;
        }).start();
    }

    private void activateSpeedBoost() {
        speedBoostActive = true;
        new Thread(() -> {
            try {
                Thread.sleep(upgradeDuration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            speedBoostActive = false;
        }).start();
    }
    //endregion

    private void returnToMenu() {
        MenuFrame menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
        gameWindow.dispose();
    }

    private void togglePause() {
        isPaused = !isPaused;
        returnToMenuButton.setVisible(isPaused);
    }

    private void gameOver() {
        returnToMenuButton.setVisible(true);

        String playerName = JOptionPane.showInputDialog(this, "Game Over! Enter your name:", "Game Over", JOptionPane.PLAIN_MESSAGE);

        if (playerName != null && !playerName.trim().isEmpty()) {
            Scores ranking = Scores.loadScore();
            ranking.addEntry(new Scores.ScoresEntry(playerName, points, rows));
            ranking.saveScore();
        }

        returnToMenu();
    }
}