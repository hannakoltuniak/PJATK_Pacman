package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private JPanel mainPanel;

    public MenuFrame() {
        this.setTitle("HK_Pacman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon("src/pngs/pacman_logo.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(32, 32, 32));
        this.setLayout(new BorderLayout());

        // Initialize main panel
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
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBoardSizeMenu();
            }
        });

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
        switch (size) {
            case "Small":
                rows = 10;
                cols = 10;
                break;
            case "Medium":
                rows = 15;
                cols = 15;
                break;
            case "Large":
                rows = 20;
                cols = 20;
                break;
            case "Very Large":
                rows = 25;
                cols = 25;
                break;
            case "Huge":
                rows = 30;
                cols = 30;
                break;
            default:
                rows = 15;
                cols = 15;
                break;
        }

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
