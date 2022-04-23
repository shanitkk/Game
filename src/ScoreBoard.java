import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ScoreBoard extends JPanel {
    Font font = new Font("Assistant", Font.BOLD, 20);
    public static final int SCORE_TITLE_WIDTH = 80, SCORE_TITLE_HEIGHT = 80;
    public static final int DUPLICATE_MARGIN = 8;
    private Live lives;
    private Score score;

    public ScoreBoard(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(204, 255, 204));
        this.setLayout(null);
        this.setDoubleBuffered(true);
        scoreBoard();
        duplicate();
        this.score = new Score();
        this.lives = new Live();
        this.setVisible(true);
    }

    public Live getLives() {
        return lives;
    }

    public void setLives (Live live) {
        this.lives = lives;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void scoreBoard() {
        JLabel score = new JLabel("Score: ");
        score.setFont(font);
        score.setBounds(0, this.getBounds().y / 2 + SCORE_TITLE_HEIGHT / 3, SCORE_TITLE_WIDTH, SCORE_TITLE_HEIGHT);
        this.add(score);
    }

    public void duplicate() {
        JLabel duplicate = new JLabel(" X ");
        Font font = new Font("Assistant", Font.BOLD, 20);
        duplicate.setFont(font);
        duplicate.setBounds(Live.LIVE_WIDTH + DUPLICATE_MARGIN, GameScene.LABEL_SCORE_Y - Live.LIVE_MARGIN, Live.LIVE_WIDTH, Live.LIVE_HEIGHT);
        this.add(duplicate);
    }

    public void paintComponent(Graphics graphics){
        this.lives.paintComponent(graphics);
    }
}