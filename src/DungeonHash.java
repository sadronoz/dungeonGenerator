import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DungeonHash {
    int length = 10;
    boolean[][] map = new boolean[length][length];
    int amountOfRooms = 25;
    int usedRooms = 0;

    ArrayList<Point> availablePoints = new ArrayList<Point>();

    public DungeonHash() {
        initMap();
        startingRoom();

        while(usedRooms < amountOfRooms) {
            getAvailablePoints();
            generateRoom();
        }
    }

    void initMap() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = false;
            }
        }
    }

    void startingRoom() {
        map[length/2][length/2] = true;
        usedRooms++;
    }

    void getAvailablePoints() {
        availablePoints.clear();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (map[i][j]) {
                    if (i != length-1 && !availablePoints.contains(new Point(i+1, j)) && !map[i+1][j]) {
                        availablePoints.add(new Point(i+1, j));
                    }
                    if (i != 0 && !availablePoints.contains(new Point(i-1, j)) && !map[i-1][j]) {
                        availablePoints.add(new Point(i-1, j));
                    }
                    if (j != length-1 && !availablePoints.contains(new Point(i, j+1))  && !map[i][j+1]) {
                        availablePoints.add(new Point(i, j+1));
                    }
                    if (j != 0 && !availablePoints.contains(new Point(i, j-1)) && !map[i][j-1]) {
                        availablePoints.add(new Point(i, j-1));
                    }
                }
            }
        }
    }

    void generateRoom() {
        int amount = availablePoints.size();
        Random rand = new Random();
        int chosen =  rand.nextInt(amount);

        Point point = availablePoints.get(chosen);
        map[point.x][point.y] = true;
        usedRooms++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                sb.append(map[j][i] ? "X" : "0");
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
