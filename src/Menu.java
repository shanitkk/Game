import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    Font font = new Font("Assistant", Font.BOLD, 20);

    public static final int BUTTON_WIDTH = 250, BUTTON_HEIGHT = 100;
    public static final int RULES_X = 300, RULES_Y = 100, RULES_WIDTH = 800, RULES_HEIGHT = 300;

    private ImageIcon background;
    private ImageIcon rules;
    private JButton startGame;

    public Menu(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.startGame = addButton("Start Game",
                (Window.WINDOW_WIDTH / 2) - (BUTTON_WIDTH / 2),
                (Window.WINDOW_HEIGHT / 2) - (BUTTON_HEIGHT / 2),
                BUTTON_WIDTH,
                BUTTON_HEIGHT);
        this.setDoubleBuffered(true);
        this.background = new ImageIcon("oranges.jpg");
        this.rules = new ImageIcon("rules.png");
        newGame();
        this.setVisible(true);
    }

    private JButton addButton(String buttonName, int x, int y, int width, int height) {
        JButton addButton = new JButton(buttonName);
        addButton.setFont(font);
        this.setVisible(true);
        this.add(addButton);
        addButton.setBounds(x, y, width, height);
        return addButton;
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(this.background.getImage(), 0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, null);
        graphics.drawImage(this.rules.getImage(), RULES_X, RULES_Y, RULES_WIDTH, RULES_HEIGHT, null);

    }

    private void newGame() {
        this.startGame.addActionListener((event) -> {
            GameScene gameScene = new GameScene(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
            this.add(gameScene);
            this.startGame.setVisible(false);
        });
    }

}
