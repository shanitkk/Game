import javax.swing.*;
import java.awt.*;

public class Player {

    public static final int MARGIN_BOTTOM = 100;
    public static final int MARGIN_LEG = 15;
    public static final int WEIGHT_LEG = 15;
    public static final int HEIGHT_LEG = 40;
    public static final int HEIGHT_BODY = 80;
    public static final int HEIGHT_BELT = 15;
    public static final int NECK_DISTANCE = 10;
    public static final int NECK_HEIGHT = 14;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int WEIGHT_HAND = 15;
    public static final int HEIGHT_HAND = 60;
    public static final int HEIGHT_FACE = 60;
    public static final int WEIGHT_BASKET = 80;
    public static final int HEIGHT_BASKET = 90;


    private ImageIcon face;
    private Rect neck;
    private Rect body;
    private Rect belt;
    private Rect rightHand;
    private Rect leftHand;
    private Rect rightLeg;
    private Rect leftLeg;
    private int direction; // מצביע על הכיוון של השחקן
    private ImageIcon basket;

    public Player() {
        this.leftLeg = new Rect(Window.WINDOW_WIDTH / 2,
                Window.WINDOW_HEIGHT - MARGIN_BOTTOM,
                WEIGHT_LEG, HEIGHT_LEG,
                new Color(255, 204, 153, 211));

        this.rightLeg = new Rect(Window.WINDOW_WIDTH / 2 + this.leftLeg.getWidth() + MARGIN_LEG,
                Window.WINDOW_HEIGHT - MARGIN_BOTTOM,
                WEIGHT_LEG, HEIGHT_LEG,
                new Color(255, 204, 153, 211));

        this.body = new Rect(this.leftLeg.getX(),
                this.rightLeg.getY() - HEIGHT_BODY,
                (2 * WEIGHT_LEG) + MARGIN_LEG,
                HEIGHT_BODY,
                new Color(255, 0, 127));

        this.belt = new Rect(this.leftLeg.getX(),
                this.body.getY() + (this.body.getHeight() / 2) - HEIGHT_BELT / 2,
                this.body.getWidth(), HEIGHT_BELT, Color.PINK);

        this.face = new ImageIcon("face2.jpg");

        this.neck = new Rect(this.body.getX() + (this.body.getWidth() / 2) - NECK_DISTANCE,
                this.body.getY() - NECK_HEIGHT,
                NECK_DISTANCE * 2,
                NECK_HEIGHT, new Color(255, 204, 153, 211));

        this.rightHand = new Rect(this.body.getX() + this.body.getWidth(),
                this.rightLeg.getY() - HEIGHT_BODY,
                WEIGHT_HAND,
                HEIGHT_HAND,
                new Color(255, 204, 153, 211));

        this.leftHand = new Rect(this.body.getX() - HEIGHT_HAND,
                this.rightLeg.getY() - HEIGHT_BODY,
                HEIGHT_HAND,
                WEIGHT_HAND,
                new Color(255, 204, 153, 211));
        this.basket = new ImageIcon("basket.jpeg");
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Rect getNeck() {
        return neck;
    }

    public void setNeck(Rect neck) {
        this.neck = neck;
    }

    public Rect getBody() {
        return body;
    }

    public void setBody(Rect body) {
        this.body = body;
    }

    public Rect getBelt() {
        return belt;
    }

    public void setBelt(Rect belt) {
        this.belt = belt;
    }

    public Rect getRightHand() {
        return rightHand;
    }

    public void setRightHand(Rect rightHand) {
        this.rightHand = rightHand;
    }

    public Rect getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Rect leftHand) {
        this.leftHand = leftHand;
    }

    public Rect getRightLeg() {
        return rightLeg;
    }

    public void setRightLeg(Rect rightLeg) {
        this.rightLeg = rightLeg;
    }

    public Rect getLeftLeg() {
        return leftLeg;
    }

    public void setLeftLeg(Rect leftLeg) {
        this.leftLeg = leftLeg;
    }

    public ImageIcon getFace() {
        return face;
    }

    public void setFace(ImageIcon face) {
        this.face = face;
    }

    public ImageIcon getBasket() {
        return basket;
    }

    public void setBasket(ImageIcon basket) {
        this.basket = basket;
    }


    public void moveRight() {
        this.leftLeg.moveRight();
        this.rightLeg.moveRight();
        this.body.moveRight();
        this.belt.moveRight();
        this.neck.moveRight();
        this.rightHand.moveRight();
        this.leftHand.moveRight();
    }

    public void moveLeft() {
        this.leftLeg.moveLeft();
        this.rightLeg.moveLeft();
        this.body.moveLeft();
        this.belt.moveLeft();
        this.neck.moveLeft();
        this.rightHand.moveLeft();
        this.leftHand.moveLeft();
    }

    public boolean isCollected(Orange orange) {
        boolean collect = false;
        Rectangle orangeToCollect = new Rectangle(orange.getOrange().getX(), orange.getOrange().getY(),
                orange.getOrange().getWidth(), orange.getOrange().getHeight());

        Rectangle playerBody = new Rectangle(
                this.leftHand.getX(), this.leftHand.getY(),
                this.leftHand.getWidth() + this.body.getWidth(), this.leftHand.getHeight());

        if (orangeToCollect.intersects(playerBody))
            collect = true;

        return collect;
    }

    public void paintComponent(Graphics graphics) {
        this.leftLeg.paint(graphics);
        this.rightLeg.paint(graphics);
        this.body.paint(graphics);
        this.belt.paint(graphics);
        this.neck.paint(graphics);
        graphics.drawImage(face.getImage(),
                this.body.getX() - this.rightHand.getWidth(),
                this.neck.getY() - (this.neck.getHeight() * 4),
                this.body.getWidth() + (2 * this.rightHand.getWidth()),
                HEIGHT_FACE, null);
        this.rightHand.paint(graphics);
        this.leftHand.paint(graphics);
        graphics.drawImage(basket.getImage(), this.leftHand.getX() - (WEIGHT_BASKET / 2),
                this.leftHand.getY() + this.leftHand.getHeight(),
                WEIGHT_BASKET,
                HEIGHT_BASKET,
                null);
    }
}