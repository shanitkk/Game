import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameScene extends JPanel {
    public static final int PLAYER_SPEED = 0;
    public static final int FALLING_ORANGES_SPEED = 10;
    public static final int SCORE_BOARD_Y = 0, SCORE_BOARD_WIDTH = 150, SCORE_BOARD_HEIGHT = 150;
    public static final int LABEL_SCORE_Y = 27, LABEL_SCORE_WIDTH = 80, LABEL_SCORE_HEIGHT = 80;
    public static final int LABEL_WIN_LOSS_X = 450,LABEL_WIN_LOSS_WIDTH = 1400, LABEL_WIN_LOSS_HEIGHT = 1000;
    public static final int LABEL_LIVE_MARGIN = 35;
    public static final String LOSS = "0";
    public static final String WIN = "10";

    Font font = new Font("Assistant", Font.BOLD, 20);
    Font fontWinLossGame = new Font("Assistant", Font.BOLD, 100);
    Random random = new Random();

    private OrangeTree orangeTree;
    private Player tapozitPlayer;
    private Orange orange;
    private boolean run;
    JLabel labelScore = new JLabel("0");
    JLabel labelLives = new JLabel("3");

    public GameScene(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.tapozitPlayer = new Player();
        this.orange = new Orange(randomRange(), 0);
        this.orangeTree = new OrangeTree(width - OrangeTree.TOP_WIDTH - OrangeTree.TREE_MARGIN,
                height - OrangeTree.TOP_HEIGHT - OrangeTree.TRUNK_HEIGHT - OrangeTree.TREE_MARGIN);
        this.run = true;
        this.mainGameScene();
        this.fallingOranges();
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

            while (this.run) {
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
                    labelScore();
                    win();
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
            while (this.run) {
                this.orange.moveDown();
                if (this.orange.getOrange().getY() == Window.WINDOW_HEIGHT) {
                    this.newOrange();
                    labelLives();
                    gameOver();
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
    }

    public void labelLives() {
        labelLives.setFont(font);
        labelLives.setBounds(Window.WINDOW_WIDTH - ScoreBoard.SCORE_TITLE_WIDTH,
                LABEL_SCORE_Y - LABEL_LIVE_MARGIN, LABEL_SCORE_WIDTH, LABEL_SCORE_HEIGHT);
        String text = labelLives.getText();
        int lives = Integer.parseInt(text);
        lives--;
        labelLives.setText(String.valueOf(lives));
        this.add(labelLives);
    }

    public void labelScore() {
        labelScore.setFont(font);
        labelScore.setBounds(Window.WINDOW_WIDTH - ScoreBoard.SCORE_TITLE_WIDTH,
                LABEL_SCORE_Y, LABEL_SCORE_WIDTH, LABEL_SCORE_HEIGHT);
        String text = labelScore.getText();
        int points = Integer.parseInt(text);
        points++;
        labelScore.setText(String.valueOf(points));
        this.add(labelScore);
    }

    public int randomRange() {
        int range;
        do {
            range = random.nextInt(Window.WINDOW_WIDTH);
        } while (range >= (Window.WINDOW_WIDTH - SCORE_BOARD_WIDTH));
        return range;
    }

    public void win(){
        if (labelScore.getText().equals(WIN)){
            this.run = false;
            JLabel win = new JLabel(" Winner!!! :) ");
            win.setFont(fontWinLossGame);
            win.setBounds(LABEL_WIN_LOSS_X, 0,
                    LABEL_WIN_LOSS_WIDTH,LABEL_WIN_LOSS_HEIGHT);
            this.add(win);
        }
    }

    public void gameOver(){
        if (labelLives.getText().equals(LOSS)){
            this.run = false;
            JLabel gameOver = new JLabel("Game over! :( ");
            gameOver.setFont(fontWinLossGame);
            gameOver.setBounds(LABEL_WIN_LOSS_X, 0,
                    LABEL_WIN_LOSS_WIDTH,LABEL_WIN_LOSS_HEIGHT);
            this.add(gameOver);
        }
    }
}
