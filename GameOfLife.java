

/**
 * Model for Conway's Game of Life.
 *
 * This class stores the society in a 2D boolean array.
 * true  = live cell
 * false = empty location
 *
 * IMPORTANT FOR THIS PROJECT:
 * The board does NOT wrap around. Any location outside the array is simply
 * ignored when counting neighbors.
 */
public class GameOfLife {

    private boolean[][] society;

    /**
     * Creates an empty society with the requested number of rows and columns.
     */
    public GameOfLife(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }
        society = new boolean[rows][cols];
        
    }

    /** Returns the number of rows in the society. */
    public int numberOfRows() {
        return society.length;
    }

    /** Returns the number of columns in the society. */
    public int numberOfColumns() {
        return society[0].length;
    }

    /** Makes the location at row, col alive. */
    public void growCellAt(int row, int col) {
        if (row >= 0 && row < numberOfRows() && col >= 0 && col < numberOfColumns()) {
            society[row][col] = true;
        }
    }

    /** Makes the location at row, col dead. */
    public void killCellAt(int row, int col) {
        if (row >= 0 && row < numberOfRows() && col >= 0 && col < numberOfColumns()) {
            society[row][col] = false;
        }
    }

    /** Returns true if the location contains a live cell. */
    public boolean cellAt(int row, int col) {
        return society[row][col];
    }

    /** Makes every location in the society dead. */
    public void clear() {
        for (int row = 0; row < numberOfRows(); row++) {
            for (int col = 0; col < numberOfColumns(); col++) {
                society[row][col] = false;
            }
        }
    }

    /**
     * Counts the live neighbors surrounding one location.
     *
     * A location can have at most eight neighbors. Locations outside the
     * board DO NOT wrap around to the other side.
     *
     * TODO: Complete this method.
     */
    public int neighborCount(int row, int col) {
        if (row < 0 || row >= society.length || col < 0 || col >= society[0].length) {
            throw new IllegalArgumentException("Row and column are out of bounds.");
        }

        int count = 0;

        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r == row && c == col) {
                    continue;
                }
                if (r >= 0 && r < society.length && c >= 0 && c < society[0].length && society[r][c]) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Advances the entire society by one generation.
     *
     * Rules:
     * 1. A dead cell with exactly 3 live neighbors becomes alive.
     * 2. A live cell with 2 or 3 live neighbors survives.
     * 3. A live cell with fewer than 2 neighbors dies from isolation.
     * 4. A live cell with more than 3 neighbors dies from overpopulation.
     *
     * TODO: Complete this method.
     */
    public void update() {
        // TODO: Create a SECOND 2D boolean array for the next generation.
        //
        // IMPORTANT:
        // Do not change society while you are still using it to calculate
        // neighbors. Every cell in the new generation must be based on the
        // same old generation.
        boolean[][] nextGeneration = new boolean[society.length][society[0].length];

        for (int row = 0; row < society.length; row++) {
            for (int col = 0; col < society[0].length; col++) {
                int liveNeighbors = neighborCount(row, col);

                if (society[row][col]) {
                    nextGeneration[row][col] = liveNeighbors == 2 || liveNeighbors == 3;
                }
                else {
                    nextGeneration[row][col] = liveNeighbors == 3;
                }
            }
        }

        society = nextGeneration;
    }

    /**
     * Returns a text version of the board.
     * O = live cell
     * . = dead cell
     *
     * TODO: Complete this method.
     */
    @Override
    public String toString() {
        // TODO: Use nested loops to build one String containing the board.
        //       Add a newline after every row.
        String board = "";
        for(int row =0; row<society.length; row++)
        {
            for(int col=0; col<society[0].length; col++)
            {
                if(society[row][col])
                {
                    board+="O";
                }
                else
                {
                    board+=".";
                }
            }
            board+="\n";
        }

        return board;
    }
}
