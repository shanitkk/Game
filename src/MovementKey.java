import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementKey implements KeyListener {
    private Player player;

    public MovementKey(Player player) {
        this.player = player;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.player.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.player.moveLeft();
        }
    }
    public void keyReleased(KeyEvent e) {
    }
}
