package edu.ou.cs2334.project5.views;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import java.util.HashMap;

public class NonogramMakerView {
    private BorderPane borderPane;
    private CellGridView cellGridView; 
    private MenuBar menuBar;
    private HashMap<String, MenuItem> menuItemsMap = new HashMap<>();

    /**
     * Constructs the NonogramMakerView with specified dimensions and cell size for the grid.
     *
     * @param numRows   The number of rows in the Nonogram grid.
     * @param numCols   The number of columns in the Nonogram grid.
     * @param cellLength The length of each side of the square cells.
     */
    public NonogramMakerView(int numRows, int numCols, int cellLength) {
        cellGridView = new CellGridView(numRows, numCols, cellLength); 
        borderPane = new BorderPane();
        borderPane.setCenter(cellGridView.getPane()); 

        initMenuBar();
        borderPane.setTop(menuBar);
    }

      /**
     * Initializes the menu bar for the application.
     */
    private void initMenuBar() {
        menuBar = new MenuBar();
        
        Menu fileMenu = new Menu("_File");
        
        MenuItem openItem = new MenuItem("_Open");
        MenuItem saveItem = new MenuItem("_Save");
        MenuItem exitItem = new MenuItem("_Exit");
        
        fileMenu.getItems().addAll(openItem, saveItem, exitItem);
        menuItemsMap.put("Open", openItem);
        menuItemsMap.put("Save", saveItem);
        menuItemsMap.put("Exit", exitItem);
        
        exitItem.setOnAction(event -> Platform.exit());

        menuBar.getMenus().add(fileMenu);
    }

     /**
     * Retrieves a menu item by its key.
     *
     * @param key The key associated with the menu item.
     * @return The MenuItem associated with the given key.
     */
    public MenuItem getMenuItem(String key) {
        return menuItemsMap.get(key);
    }

     /**
     * Gets the main BorderPane of the application's user interface.
     *
     * @return The BorderPane object of the view.
     */
    public BorderPane getPane() {
        return borderPane;
    }

     /**
     * Initializes the toggle buttons in the grid view.
     */
    public void initButtons() {
        cellGridView.initButtons(0, 0, 0);
    }

    /**
     * Retrieves a toggle button located at a specific row and column in the grid.
     *
     * @param rowIdx The row index of the button.
     * @param colIdx The column index of the button.
     * @return The ToggleButton at the specified row and column.
     */
    public ToggleButton getToggleButton(int rowIdx, int colIdx) {
        return cellGridView.getToggleButton(rowIdx, colIdx);
    }

    /**
     * Gets the number of rows in the grid.
     *
     * @return The number of rows.
     */
    public int getNumRows() {
        return cellGridView.getNumRows();
    }

    /**
     * Gets the number of columns in the grid.
     *
     * @return The number of columns.
     */
    public int getNumCols() {
        return cellGridView.getNumCols();
    }

	public void initButtons(int numRows, int numCols, int cellLength) {
		// TODO Auto-generated method stub
		return;
		
	}
}