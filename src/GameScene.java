import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameScene extends JPanel {
    public static final int PLAYER_SPEED = 0;
    public static final int FALLING_ORANGES_SPEED = 2;
    public static final int SCORE_BOARD_Y = 0, SCORE_BOARD_WIDTH = 150, SCORE_BOARD_HEIGHT = 150;
    public static final int LABEL_SCORE_Y = 27, LABEL_SCORE_WIDTH = 80, LABEL_SCORE_HEIGHT = 80;
    Font font = new Font("Assistant", Font.BOLD, 20);
    Random random = new Random();

    private OrangeTree orangeTree;
    private Player tapozitPlayer;
    private Orange orange;

    public GameScene(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.tapozitPlayer = new Player();
        this.orange = new Orange(randomRange(), 0);
        this.orangeTree = new OrangeTree(width - OrangeTree.TOP_WIDTH - OrangeTree.TREE_MARGIN,
                height - OrangeTree.TOP_HEIGHT - OrangeTree.TRUNK_HEIGHT - OrangeTree.TREE_MARGIN);
        this.mainGameScene();
        this.fallingOranges();
        //this.live = new Live(); //*
        ScoreBoard scoreBoard = new ScoreBoard(Window.WINDOW_WIDTH - SCORE_BOARD_WIDTH, SCORE_BOARD_Y, SCORE_BOARD_WIDTH, SCORE_BOARD_HEIGHT);
        this.add(scoreBoard);
    }

    public void mainGameScene() {
        Thread move = new Thread(() -> {
            MovementKey movementKey = new MovementKey(this.tapozitPlayer);
            this.addKeyListener(movementKey);
            this.requestFocus();
            this.setFocusable(true);
            this.setLayout(null);
            this.setDoubleBuffered(true);
            this.setBackground(new Color(204, 255, 204));
            JLabel labelScore = new JLabel("0");
            labelScore(labelScore);
            while (true) {
                switch (this.tapozitPlayer.getDirection()) {
                    case Player.RIGHT: {
                        this.tapozitPlayer.moveRight();
                        break;
                    }
                    case Player.LEFT: {
                        this.tapozitPlayer.moveLeft();
                        break;
                    }
                }
                if (this.tapozitPlayer.isCollected(orange)) {
                    newOrange();
                    String text = labelScore.getText();
                    int points = Integer.parseInt(text);
                    points++;
                    labelScore.setText(String.valueOf(points));
                    System.out.println(labelScore.getText());
                }
                limit();
                repaint();
                try {
                    Thread.sleep(PLAYER_SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        move.start();
    }

    private void fallingOranges() {
        Thread fallingOranges = new Thread(() -> {
            while (true) {
                this.orange.moveDown();
                if (this.orange.getOrange().getY() == Window.WINDOW_HEIGHT) {
                    this.newOrange();
                    //  this.live.loseLive();
                }
                repaint();
                try {
                    Thread.sleep(FALLING_ORANGES_SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fallingOranges.start();
    }

    public void limit() {
        while (this.tapozitPlayer.getRightHand().getX() + (2 * this.tapozitPlayer.getRightHand().getWidth()) == Window.WINDOW_WIDTH)
            this.tapozitPlayer.moveLeft();
        while (this.tapozitPlayer.getLeftHand().getX() - (Player.WEIGHT_BASKET / 2) == 0)
            this.tapozitPlayer.moveRight();
    }

    public void newOrange() {
        this.orange.setLocation(randomRange(), 0);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.orangeTree.paintComponent(graphics);
        this.tapozitPlayer.paintComponent(graphics);
        this.orange.paint(graphics);
        //  this.live.paintComponent(graphics);
    }

    public void labelScore(JLabel labelScore) {
        labelScore.setFont(font);
        labelScore.setBounds(Window.WINDOW_WIDTH - ScoreBoard.SCORE_TITLE_WIDTH, LABEL_SCORE_Y, LABEL_SCORE_WIDTH, LABEL_SCORE_HEIGHT);
        labelScore.setBackground(Color.black);
        this.add(labelScore);
    }

    public int randomRange() {
        int range;
        do {
            range = random.nextInt(Window.WINDOW_WIDTH);
        } while (range >= (Window.WINDOW_WIDTH - SCORE_BOARD_WIDTH));
        return range;
    }

//    public void exitButton() {
//        JButton exitButton = new JButton("Exit");
//        this.add(exitButton);
//        exitButton.setBounds(EXIT_BUTTON_X, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
//        exitButton.addActionListener((event) -> {
//            Window main = new Window();
//        });
//    }

}
