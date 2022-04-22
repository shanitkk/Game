import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    Font font = new Font("Assistant", Font.BOLD, 20);

    public static final int BUTTON_WIDTH = 150;
    public static final int BUTTON_HEIGHT = 100;

    private ImageIcon background;
    private JButton startGame;
    private JLabel description;


    public Menu(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.startGame = addButton("Start Game",
                (Window.WINDOW_WIDTH / 2) - (BUTTON_WIDTH / 2),
                (Window.WINDOW_HEIGHT / 2) - (BUTTON_HEIGHT / 2),
                BUTTON_WIDTH,
                BUTTON_HEIGHT);
        this.description = addLabel("10 ניצחון", this.startGame.getX(),
                this.startGame.getY() + this.startGame.getHeight(), 250, 250);
        this.setDoubleBuffered(true);
        this.background = new ImageIcon("oranges.jpg");
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

    private JLabel addLabel(String text, int x, int y, int width, int height) {
        JLabel addLabel = new JLabel(text);
        addLabel.setFont(font);
        addLabel.setBounds(x, y, width, height);
        this.add(addLabel);
        return addLabel;
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(this.background.getImage(), 0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, null);
    }

    private void newGame() {
        this.startGame.addActionListener((event) -> {
            GameScene gameScene = new GameScene(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
            this.add(gameScene);
            this.startGame.setVisible(false);
            this.description.setVisible(false);

        });
    }

}
