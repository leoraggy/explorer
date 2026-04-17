import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void testReachableArea_ZeroMoves() {
        int[][] island = {
            {3, 3, 3},
            {3, 0, 2},
            {3, 2, 2}
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(1, actual);
    }

    @Test
    public void testReachableArea_Five() {
        int[][] island = {
            {0, 1, 3},
            {3, 1, 3},
            {3, 1, 1}
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(5, actual);
    }

    @Test
    public void testReachableArea_noExplorerThrowsException() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        
        // Verifies that the custom exception you wrote in startLocation() works
        assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.reachableArea(island);
        });
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

    @Test
    public void testPossibleMoves_allDirectionsOpen() {
       int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1},
        };
       
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,0")); // left
        assertTrue(moveSet.contains("1,2")); // right
    }

    @Test
    public void testPossibleMoves_leftOpenOnlyWaterOnly() {
       int[][] island = {
            {1, 2, 1},
            {1, 0, 2},
            {1, 2, 1},
        };
       
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("1,0")); // left
    }

    @Test
    public void testPossibleMoves_RightOpenOnlyMountainOnly() {
       int[][] island = {
            {1, 3, 1},
            {3, 0, 1},
            {1, 3, 1},
        };
       
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("1,2")); // right
    }

    @Test
    public void testPossibleMoves_upOpenOnlyMixed() {
       int[][] island = {
            {1, 1, 1},
            {2, 0, 3},
            {1, 2, 1},
        };
       
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,1")); // left
    }

    @Test
    public void testPossibleMoves_upAndDownOpen() {
       int[][] island = {
            {1, 1, 1},
            {2, 0, 3},
            {1, 1, 1},
        };
       
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("0,1"));
         assertTrue(moveSet.contains("2,1")); 
    }



     private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }

}
