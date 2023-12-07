package edu.ou.cs2334.project5.models;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NonogramMakerModel {
    private static final char EMPTY_CELL_CHAR = '0';
    private static final char FILLED_CELL_CHAR = '1';
    private int numRows;
    private int numCols;
    private boolean[][] grid;

     /**
     * Constructs a NonogramMakerModel with a specified number of rows and columns.
     *
     * @param numRows the number of rows in the Nonogram grid
     * @param numCols the number of columns in the Nonogram grid
     * @throws IllegalArgumentException if the number of rows or columns is not positive
     */
    public NonogramMakerModel(int numRows, int numCols) {
        if (numRows <= 0 || numCols <= 0) {
            throw new IllegalArgumentException("Number of rows and columns must be positive.");
        }
        this.numRows = numRows;
        this.numCols = numCols;
        this.grid = new boolean[numRows][numCols];
    }

      /**
     * Constructs a NonogramMakerModel by reading puzzle data from a file.
     *
     * @param file the file from which to load the Nonogram puzzle
     * @throws IOException if there is an issue reading the file
     */
    public NonogramMakerModel(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        String[] dimensions = lines.get(0).split(" ");
        this.numRows = Integer.parseInt(dimensions[0]);
        this.numCols = Integer.parseInt(dimensions[1]);
        this.grid = new boolean[numRows][numCols];

        for (int i = 1; i <= numRows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                this.grid[i - 1][j] = line.charAt(j) == FILLED_CELL_CHAR;
            }
        }
    }
    
     /**
     * Constructs a NonogramMakerModel by reading puzzle data from a file path.
     *
     * @param filePath the path of the file from which to load the Nonogram puzzle
     * @throws IOException if there is an issue reading the file
     */
    public NonogramMakerModel(String filePath) throws IOException {
        this(new File(filePath));
    }
    
    /**
     * Sets the state of a cell at a specified row and column in the Nonogram grid.
     *
     * @param row   the row index of the cell to set
     * @param col   the column index of the cell to set
     * @param state the new state of the cell (true for filled, false for empty)
     * @throws IndexOutOfBoundsException if the row or column index is out of bounds
     */
    public void setCell(int row, int col, boolean state) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IndexOutOfBoundsException("Row or column index out of bounds.");
        }
        grid[row][col] = state;
    }

    /**
     * Retrieves the state of a cell at a specified row and column in the Nonogram grid.
     *
     * @param row the row index of the cell to retrieve
     * @param col the column index of the cell to retrieve
     * @return the state of the cell (true for filled, false for empty)
     * @throws IndexOutOfBoundsException if the row or column index is out of bounds
     */
    public boolean getCell(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IndexOutOfBoundsException("Row or column index out of bounds.");
        }
        return grid[row][col];
    }

     /**
     * Gets a deep copy of the current grid state.
     *
     * @return A copy of the grid.
     */
    public boolean[][] getGrid() {
        boolean[][] copy = new boolean[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(this.grid[i], 0, copy[i], 0, numCols);
        }
        return copy;
    }

    /**
     * Projects the counts of consecutive filled cells in a given array.
     * This is typically used for generating row and column hints for the Nonogram puzzle.
     *
     * @param cells The array of cell states to project.
     * @return A list of integers representing the counts of consecutive filled cells.
     */
    public static List<Integer> project(boolean[] cells) {
        List<Integer> counts = new ArrayList<>();
        int count = 0;

        for (boolean cell : cells) {
            if (cell) {
                count++;
            } else {
                if (count > 0) {
                    counts.add(count);
                    count = 0;
                }
            }
        }
        if (count > 0) {
            counts.add(count);
        }

        if (counts.isEmpty()) {
            counts.add(0);
        }

        return counts;
    }

    /**
     * Projects the row at the given index to get the counts of consecutive filled cells.
     *
     * @param rowIdx The index of the row to project.
     * @return A list of integers representing the row projection.
     * @throws IndexOutOfBoundsException If the row index is out of bounds.
     */
    public List<Integer> projectRow(int rowIdx) {
        if (rowIdx < 0 || rowIdx >= numRows) {
            throw new IndexOutOfBoundsException("Row index out of bounds.");
        }
        return project(grid[rowIdx]);
    }

    /**
     * Projects the column at the given index to get the counts of consecutive filled cells.
     *
     * @param colIdx The index of the column to project.
     * @return A list of integers representing the column projection.
     * @throws IndexOutOfBoundsException If the column index is out of bounds.
     */
    public List<Integer> projectCol(int colIdx) {
        if (colIdx < 0 || colIdx >= numCols) {
            throw new IndexOutOfBoundsException("Column index out of bounds.");
        }
        boolean[] col = new boolean[numRows];
        for (int i = 0; i < numRows; i++) {
            col[i] = grid[i][colIdx];
        }
        return project(col);
    }

    /**
     * Returns a string representation of the Nonogram puzzle.
     * The string includes the number of rows and columns, the projections of each row and column,
     * and the grid itself, where each cell's state is represented by '1' (filled) or '0' (empty).
     *
     * @return A string representation of the Nonogram puzzle.
     */
    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(numRows).append(" ").append(numCols).append(System.lineSeparator());
    
    for (int r = 0; r < numRows; r++) {
        appendProjection(sb, projectRow(r));
    }
    
    for (int c = 0; c < numCols; c++) {
        appendProjection(sb, projectCol(c));
    }
    
    for (int r = 0; r < numRows; r++) {
        for (int c = 0; c < numCols; c++) {
            sb.append(grid[r][c] ? '1' : '0');
        }
        sb.append(System.lineSeparator());
    }
    
    return sb.toString().trim();
}

    /**
     * Appends the projection of a row or column to a StringBuilder.
     * This method formats a list of integers representing the counts of consecutive filled cells
     * into a string and appends it to the provided StringBuilder.
     *
     * @param sb The StringBuilder to which the projection string will be appended.
     * @param projection The list of integers representing the counts of consecutive filled cells.
     */
private void appendProjection(StringBuilder sb, List<Integer> projection) {
    if (projection.isEmpty() || projection.get(0) == 0) {
        sb.append("0");
    } else {
        for (int count : projection) {
            sb.append(count).append(" ");
        }
        sb.setLength(sb.length() - 1);
    }
    sb.append(System.lineSeparator());
}

     /**
     * Gets the number of rows in the puzzle grid.
     *
     * @return The number of rows.
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Gets the number of columns in the puzzle grid.
     *
     * @return The number of columns.
     */
    public int getNumCols() {
        return numCols;
    }

     /**
     * Saves the current state of the Nonogram puzzle to a specified file.
     *
     * @param filename the name of the file to save the puzzle state
     * @throws IOException if there is an issue writing to the file
     */
    public void saveToFile(String filename) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        writer.write(this.toString());
    }
    
}
}