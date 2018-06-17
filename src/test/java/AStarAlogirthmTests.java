import com.lukhol.politechnika.aiclient.game.AStarAlgorithm;
import com.lukhol.politechnika.aiclient.model.GameMap;
import com.lukhol.politechnika.aiclient.model.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AStarAlogirthmTests {

    @Test
    public void initTest() {
        int[][] fields = {
                {0,0,0,0,0,0},
                {0,0,0,50,0,0},
                {0,0,50,0,0,0},
                {0,50,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };

        System.out.println(fields[3][1]);

        Point startPoint = new Point(1, 2);
        Point finishPoint = new Point(4,5);
        GameMap gameMap = new GameMap(6,7, fields);

        AStarAlgorithm aStarAlgorithm = new AStarAlgorithm(startPoint, finishPoint, gameMap);
        Point actualPoint = aStarAlgorithm.getActualPoint();
        assertEquals(finishPoint, actualPoint);
    }
}