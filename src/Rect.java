import java.awt.*;

public class Rect extends Shape{

    public Rect(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public void moveRight()
    {
        this.setX(this.getX()+1);
    }

    public void moveLeft()
    {
        this.setX(this.getX()-1);
    }

    public void paint (Graphics graphics)
    {
        super.paint(graphics);
        graphics.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

}
