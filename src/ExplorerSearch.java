import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        Set<String> visited = new HashSet<>();

        int[] start = startLocation(island);

        reachableArea(start, island, visited);

        return visited.size();
    }

    private static void reachableArea(int[] currentLoc, int[][] island, Set<String> visited){
        int row = currentLoc[0];
        int col = currentLoc[1];

        String coordinates = row + "," + col;

        if(visited.contains(coordinates)){
            return;
        }

       visited.add(coordinates);

        for(int[] move : possibleMoves(island, currentLoc)){
            reachableArea(move, island, visited);
        }
    }

    public static List<int[]> possibleMoves(int[][] island, int[] location) {
        int x = location[0];
        int y = location[1];

        List<int[]> validLocs = new ArrayList<>();


       // UP 
        int newX = x - 1; 
        if (newX >= 0 && island[newX][y] == 1) {
            validLocs.add(new int[]{newX, y});
        }

        // RIGHT
        newX = x + 1; 
        if (newX < island.length && island[newX][y] == 1) {
            validLocs.add(new int[]{newX, y});
        }

        // LEFT
        int newY = y - 1; 
        if (newY >= 0 && island[x][newY] == 1) {
            validLocs.add(new int[]{x, newY});
        }

        // RIGHT
        newY = y + 1; 
        if (newY < island[0].length && island[x][newY] == 1) {
            validLocs.add(new int[]{x, newY});
        }

        return validLocs;
    }

    public static int[] startLocation(int[][] island){
        for(int row = 0; row < island.length; row++){
            for(int col = 0; col < island[0].length; col++){
                if(island[row][col] == 0){
                    return new int[]{row, col};
                }
            }
        }

        throw new IllegalArgumentException("No explorer present");
    }
}
