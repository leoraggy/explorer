import static org.junit.Assert.*;
import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }


    @Test
    public void testExplorerLocation_centerOfGrid() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1},
        };
        int[] actual = ExplorerSearch.startLocation(island);
        int[] expected = {1, 1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExplorerLocation_topLeftCorner() {
        int[][] island = {
            {0, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
        };
        int[] actual = ExplorerSearch.startLocation(island);
        int[] expected = {0, 0};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testExplorerLocation_notFound_throwsException() {
        int[][] island = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
        };
       
       assertThrows(IllegalArgumentException.class, () -> {
        ExplorerSearch.startLocation(island);
    });
    }
    
}
