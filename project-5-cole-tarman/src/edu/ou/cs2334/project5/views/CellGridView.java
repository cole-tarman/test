package edu.ou.cs2334.project5.views;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class CellGridView {
    private ArrayList<ToggleButton> gridButtons;
    private GridPane gridPane;
    private int numRows;
    private int numCols;
    private int cellLength;

     /**
     * Constructs a new CellGridView with specified grid dimensions and cell size.
     *
     * @param numRows   The number of rows in the grid.
     * @param numCols   The number of columns in the grid.
     * @param cellLength The length of each side of the square cells.
     */
    public CellGridView(int numRows, int numCols, int cellLength) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.cellLength = cellLength;
        this.gridButtons = new ArrayList<>(numRows * numCols);
        this.gridPane = new GridPane();
        this.gridPane.setAlignment(Pos.CENTER);

        initButtons(numRows, numCols, cellLength);
    }

    /**
     * Gets the number of rows in the grid.
     *
     * @return The number of rows.
     */
    public int getNumRows() {
        return numRows;
    }

     /**
     * Gets the number of columns in the grid.
     *
     * @return The number of columns.
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Initializes the toggle buttons in the grid.
     * This method clears any existing buttons and creates new ones according to the grid dimensions.
     *
     * @param numRows   The number of rows for the grid.
     * @param numCols   The number of columns for the grid.
     * @param cellLength The length of each side of the square cells.
     */
    public void initButtons(int numRows, int numCols, int cellLength) {
        gridButtons.clear();
        gridPane.getChildren().clear();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ToggleButton toggleButton = new ToggleButton();
                toggleButton.setMinSize(cellLength, cellLength);
                toggleButton.setMaxSize(cellLength, cellLength);
                toggleButton.setPrefSize(cellLength, cellLength);

                gridButtons.add(toggleButton);
                gridPane.add(toggleButton, col, row);
            }
        }
    }

     /**
     * Retrieves a toggle button located at a specific row and column in the grid.
     *
     * @param row The row index of the button.
     * @param col The column index of the button.
     * @return The ToggleButton at the specified row and column.
     */
    public ToggleButton getToggleButton(int row, int col) {
        return gridButtons.get(row * numCols + col);
    }

    /**
     * Gets the Pane that contains the grid.
     *
     * @return The GridPane containing the toggle buttons.
     */
    public Pane getPane() {
        return gridPane;
    }
}