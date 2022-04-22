import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ScoreBoard extends JPanel {
    Font font = new Font("Assistant", Font.BOLD, 20);
    public static final int SCORE_TITLE_WIDTH = 80, SCORE_TITLE_HEIGHT = 80;
    private ArrayList<Live> lives;
    private Score score;

    public ScoreBoard(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(204, 255, 204));
        this.setLayout(null);
        this.setDoubleBuffered(true);
        scoreBoard();
        this.score = new Score();
        this.setVisible(true);
    }

    public ArrayList<Live> getLives() {
        return lives;
    }

    public void setLives(ArrayList<Live> lives) {
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

}