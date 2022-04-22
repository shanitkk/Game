import java.awt.*;

public class OrangeTree {
    public static final int TOP_WIDTH = 150;
    public static final int TOP_HEIGHT = 150;
    public static final int TRUNK_WIDTH = 30;
    public static final int TRUNK_HEIGHT = 130;
    public static final int ORENGE1_X = 20;
    public static final int ORENGE1_Y = 25;
    public static final int ORENGE2_X = 80;
    public static final int ORENGE2_Y = 30;
    public static final int ORENGE3_X = 50;
    public static final int ORENGE3_Y = 75;
    public static final int TREE_MARGIN = 50;


    private Rect trunk;
    private Circle top;
    private Orange orange1;
    private Orange orange2;
    private Orange orange3;

    public OrangeTree(int x, int y) {
        this.top = new Circle(x,y,TOP_WIDTH,TOP_HEIGHT,new Color(0, 153, 0));
        this.trunk = new Rect(this.top.getX() + (this.top.getWidth() / 2) - (TRUNK_WIDTH / 2),
                this.top.getY() + this.top.getHeight(),
                TRUNK_WIDTH, TRUNK_HEIGHT,
                new Color(102, 51, 0));
        this.orange1 = new Orange(this.top.getX() + ORENGE1_X,this.top.getY() + ORENGE1_Y);
        this.orange2 = new Orange(this.top.getX() + ORENGE2_X,this.top.getY() + ORENGE2_Y);
        this.orange3 = new Orange(this.top.getX() + ORENGE3_X,this.top.getY() + ORENGE3_Y);
    }

    public void paintComponent(Graphics graphics) {
        this.trunk.paint(graphics);
        this.top.paint(graphics);
        this.orange1.paint(graphics);
        this.orange2.paint(graphics);
        this.orange3.paint(graphics);
    }
}