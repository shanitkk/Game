
public class Score {

    public static final int WIN = 10;
    public static final int MIN_POINTS = 0;
    private int points;

    public Score() {
        this.points = MIN_POINTS;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isWin() {
        boolean isWin = false;
        if (this.points == WIN)
            isWin = true;
        return isWin;
    }

    public void addPoint() {
        this.points++;
    }

}
