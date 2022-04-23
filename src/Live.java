import javax.swing.*;
import java.awt.*;

public class Live{
    public static final int LIVE_WIDTH = 30;
    public static final int LIVE_HEIGHT= 30;
    public static final int LIVE_MARGIN= 10;

    private ImageIcon live;

    public Live() {
        this.live = new ImageIcon("heart.png");
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(this.live.getImage(), 0,
                GameScene.LABEL_SCORE_Y - LIVE_MARGIN,
                LIVE_WIDTH, LIVE_HEIGHT, null);
    }
}
