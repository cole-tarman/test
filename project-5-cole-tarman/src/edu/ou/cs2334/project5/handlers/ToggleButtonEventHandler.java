package edu.ou.cs2334.project5.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import edu.ou.cs2334.project5.models.NonogramMakerModel;

/**
 * Event handler for toggle buttons in a NonogramMakerModel.
 * This handler updates the model based on the state of the toggle buttons.
 */
public class ToggleButtonEventHandler implements EventHandler<ActionEvent> {
    private final NonogramMakerModel model;

    /**
     * Constructs a new event handler for toggle buttons.
     *
     * @param model The NonogramMakerModel to be updated when toggle buttons change state.
     */
    public ToggleButtonEventHandler(NonogramMakerModel model) {
        this.model = model;
    }

    /**
     * Handles the action event triggered by a toggle button.
     * It updates the corresponding cell in the NonogramMakerModel based on the toggle state.
     *
     * @param event The action event that was fired.
     */
    @Override
    public void handle(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof ToggleButton) {
            ToggleButton button = (ToggleButton) source;
            CellPosition position = (CellPosition) button.getUserData();
            if (position != null) {
                model.setCell(position.getRow(), position.getCol(), button.isSelected());
            }
        }
    }
    
    /**
     * Helper class to store row and column data for a ToggleButton.
     */
    public static class CellPosition {
        private final int row;
        private final int col;

        /**
         * Constructs a new CellPosition object with the given row and column indices.
         *
         * @param row The row index of the cell.
         * @param col The column index of the cell.
         */
        public CellPosition(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * Gets the row index of the cell.
         *
         * @return The row index.
         */
        public int getRow() {
            return row;
        }

        /**
         * Gets the column index of the cell.
         *
         * @return The column index.
         */
        public int getCol() {
            return col;
        }
    }
}