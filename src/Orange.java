import java.awt.*;

public class Orange {
    public static final int ORANGE_WEIGHT = 45;
    public static final int ORANGE_HEIGHT = 45;
    public static final int LEAF_MARGIN = 3;
    public static final int LEAF_WEIGHT = 6;
    public static final int LEAF_HEIGHT = 12;

    private Circle orange;
    private Circle leaf;

    public Orange(int x,int y) {
        this.orange = new Circle(x, y, ORANGE_WEIGHT, ORANGE_HEIGHT, new Color(255, 154, 0));
        this.leaf = new Circle(this.orange.getX() + (this.orange.getWidth() / 2),
                this.orange.getY() + LEAF_MARGIN, LEAF_WEIGHT, LEAF_HEIGHT, new Color(0, 204, 0));
    }

    public Circle getOrange() {
        return orange;
    }

    public void setOrange(Circle orange) {
        this.orange = orange;
    }

    public Circle getLeaf() {
        return leaf;
    }

    public void setLeaf(Circle leaf) {
        this.leaf = leaf;
    }

    public void moveDown()
    {
        this.orange.setY(this.orange.getY()+1);
        this.leaf.setY(this.leaf.getY()+1);
    }

    public void setLocation (int x, int y)
    {
        this.orange.setX(x);
        this.orange.setY(y);
        this.leaf.setX(x + (this.orange.getWidth() / 2));
        this.leaf.setY(y + LEAF_MARGIN);
    }

    public void paint(Graphics graphics) {
        this.orange.paint(graphics);
        this.leaf.paint(graphics);
    }

}
