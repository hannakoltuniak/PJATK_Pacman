package Frames;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    private final JPanel mainPanel;

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

    private void showMainMenu() {
        mainPanel.removeAll();

        JLabel titleLabel = new JLabel("PACMAN", SwingConstants.CENTER);
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(200, 50);

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

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setForeground(Color.YELLOW);
        exitButton.setBackground(new Color(32, 32, 32));

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
        cols = switch (size) {
            case "Small" -> {
                rows = 10;
                yield 10;
            }
            case "Medium" -> {
                rows = 15;
                yield 15;
            }
            case "Large" -> {
                rows = 20;
                yield 20;
            }
            case "Very Large" -> {
                rows = 25;
                yield 25;
            }
            case "Huge" -> {
                rows = 30;
                yield 30;
            }
            default -> {
                rows = 5;
                yield 5;
            }
        };

        mainPanel.removeAll();

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setOpaque(false);
        GameBoardPanel gameBoard = new GameBoardPanel(rows, cols);
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
