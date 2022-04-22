import javax.swing.*;

public class Window extends JFrame {

    public static final int WINDOW_HEIGHT = 1000;
    public static final int WINDOW_WIDTH = 1400;

    public Window() {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Ta..... by adar & shanit");
        Menu menu = new Menu(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
        this.add(menu);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        Window main = new Window();
    }




}
