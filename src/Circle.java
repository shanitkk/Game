import java.awt.*;

public class Circle  extends Shape{

    public Circle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}