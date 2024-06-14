package Menu;

import GameData.LevelData;
import Game.*;
import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    private final JPanel mainPanel;
    private final LevelData levelData = new LevelData();

    public MenuFrame() {
        this.setTitle("HK_Pacman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon("src/pngs/pacman_logo.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(32, 32, 32));
        this.setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        this.add(mainPanel, BorderLayout.CENTER);

        showMainMenu();

        this.setVisible(true);
    }

    public void showMainMenu() {
        mainPanel.removeAll();

        JLabel titleLabel = new JLabel("PACMAN", SwingConstants.CENTER);
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(200, 50);

        //region btns
        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(buttonFont);
        newGameButton.setPreferredSize(buttonSize);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.setForeground(Color.YELLOW);
        newGameButton.setBackground(new Color(32, 32, 32));
        newGameButton.addActionListener(e -> showBoardSizeMenu());

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setFont(buttonFont);
        highScoresButton.setPreferredSize(buttonSize);
        highScoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScoresButton.setForeground(Color.YELLOW);
        highScoresButton.setBackground(new Color(32, 32, 32));
        highScoresButton.addActionListener(e -> showHighScores());

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setForeground(Color.YELLOW);
        exitButton.setBackground(new Color(32, 32, 32));
        exitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(highScoresButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalGlue());
        //endregion

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showHighScores() {
        mainPanel.removeAll();

        JLabel titleLabel = new JLabel("High Scores", SwingConstants.CENTER);
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea highScoresArea = new JTextArea();
        highScoresArea.setFont(new Font("Arial", Font.PLAIN, 16));
        highScoresArea.setForeground(Color.YELLOW);
        highScoresArea.setBackground(new Color(32, 32, 32));
        highScoresArea.setEditable(false);

        Scores scores = Scores.loadScore();
        StringBuilder highScoresText = new StringBuilder();
        for (Scores.ScoresEntry entry : scores.getScoreEntries()) {
            highScoresText.append(entry).append("\n");
        }
        highScoresArea.setText(highScoresText.toString());

        JScrollPane scrollPane = new JScrollPane(highScoresArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setForeground(Color.YELLOW);
        backButton.setBackground(new Color(32, 32, 32));
        backButton.addActionListener(e -> showMainMenu());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createVerticalGlue());

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showBoardSizeMenu() {
        mainPanel.removeAll();

        JLabel titleLabel = new JLabel("Select Board Size", SwingConstants.CENTER);
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(200, 50);

        String[] sizes = {"Small", "Medium", "Large", "Very Large", "Huge"};
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        for (String size : sizes) {
            JButton sizeButton = new JButton(size);
            sizeButton.setFont(buttonFont);
            sizeButton.setPreferredSize(buttonSize);
            sizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            sizeButton.setForeground(Color.YELLOW);
            sizeButton.setBackground(new Color(32, 32, 32));
            sizeButton.addActionListener(e -> startGameWithSize(size));
            buttonPanel.add(sizeButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void startGameWithSize(String size) {
        int rows, cols;
        short[] selectedLevelData;

        cols = switch (size) {
            case "Small" -> {
                rows = 10;
                selectedLevelData = levelData.levelDataSmall;
                yield 10;
            }
            case "Medium" -> {
                rows = 12;
                selectedLevelData = levelData.levelDataMedium;
                yield 12;
            }
            case "Large" -> {
                rows = 15;
                selectedLevelData = levelData.levelDataLarge;
                yield 15;
            }
            case "Very Large" -> {
                rows = 17;
                selectedLevelData = levelData.levelDataVeryLarge;
                yield 17;
            }
            case "Huge" -> {
                rows = 20;
                selectedLevelData = levelData.levelDataHuge;
                yield 20;
            }
            default -> {
                throw new IllegalStateException("Wrong value");
            }
        };

        mainPanel.removeAll();

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setOpaque(false);
        GameBoard gameBoard = new GameBoard(rows, cols, selectedLevelData,this);
        wrapperPanel.add(gameBoard);

        mainPanel.add(wrapperPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();

        gameBoard.requestFocusInWindow();
    }

    public static void main(String[] args) {
        new MenuFrame();
    }
}
