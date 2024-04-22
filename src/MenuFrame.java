import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    public MenuFrame() {
        this.setTitle("HK_Pacman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //app
        this.setSize(500, 500);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon("src/pngs/pacman_logo.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(32, 32, 32));
        this.setLayout(new BorderLayout());

        //title
        JLabel titleLabel = new JLabel("PACMAN", SwingConstants.CENTER);
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(titleLabel, BorderLayout.NORTH);

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(200, 50);

        //new game btn
        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(buttonFont);
        newGameButton.setPreferredSize(buttonSize);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.setForeground(Color.YELLOW);
        newGameButton.setBackground(new Color(32, 32, 32));

        //scores btn
        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.setFont(buttonFont);
        highScoresButton.setPreferredSize(buttonSize);
        highScoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScoresButton.setForeground(Color.YELLOW);
        highScoresButton.setBackground(new Color(32, 32, 32));

        //exit btn
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setForeground(Color.YELLOW);
        exitButton.setBackground(new Color(32, 32, 32));

        //btn panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        this.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(highScoresButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalGlue());

        this.setVisible(true);
    }
}
