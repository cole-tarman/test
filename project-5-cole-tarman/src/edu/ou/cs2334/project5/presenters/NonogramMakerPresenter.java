package edu.ou.cs2334.project5.presenters;

import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.models.NonogramMakerModel;
import edu.ou.cs2334.project5.views.NonogramMakerView;

public class NonogramMakerPresenter {
    private NonogramMakerModel model;
    private NonogramMakerView view;
    private int cellLength;

    /**
     * Constructs a NonogramMakerPresenter with specified parameters.
     *
     * @param numRows   The number of rows in the Nonogram grid.
     * @param numCols   The number of columns in the Nonogram grid.
     * @param cellLength The length of each cell in the grid.
     */
    public NonogramMakerPresenter(int numRows, int numCols, int cellLength) {
        this.cellLength = cellLength;
        this.model = new NonogramMakerModel(numRows, numCols);
        this.view = new NonogramMakerView(numRows, numCols, cellLength);
        init();
    }

    /**
     * Retrieves the window in which the Nonogram grid is displayed.
     *
     * @return The Window object, or null if it's not available.
     */
    public Window getWindow() {
        try {
            return view.getPane().getScene().getWindow();
        } catch (NullPointerException e) {
            return null;
        }
    }

     /**
     * Initializes the user interface components.
     */
    private void init() {
        initToggleButtons();
        bindToggleButtons();
        configureMenuItems();
    }

    /**
     * Initializes the toggle buttons in the view.
     */
    private void initToggleButtons() {
        view.initButtons(model.getNumRows(), model.getNumCols(), cellLength);
        Window window = getWindow();
        if (window != null) {
            window.sizeToScene(); 
        }
    }

    /**
     * Binds the toggle buttons in the view to the model.
     */
    private void bindToggleButtons() {
        for (int row = 0; row < model.getNumRows(); row++) {
            for (int col = 0; col < model.getNumCols(); col++) {
                final int finalRow = row; // Final copy of row index
                final int finalCol = col; // Final copy of column index

                ToggleButton button = view.getToggleButton(row, col);
                boolean isSelected = model.getCell(row, col);
                button.setSelected(isSelected);

                button.setOnAction(event -> {
                    model.setCell(finalRow, finalCol, button.isSelected());
                });
            }
        }
    }

     /**
     * Configures the menu items for the application.
     */
    private void configureMenuItems() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialDirectory(new File("."));

        MenuItem openItem = view.getMenuItem("Open");
        openItem.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(getWindow());
            if (file != null) {
                open(file);
            }
        });

        MenuItem saveItem = view.getMenuItem("Save");
        saveItem.setOnAction(event -> {
            File file = fileChooser.showSaveDialog(getWindow());
            if (file != null) {
                save(file.getPath());
            }
        });

    }

    /**
     * Returns the main pane of the application's user interface.
     *
     * @return The Pane object of the view.
     */
    public Pane getPane() {
        return view.getPane();
    }

    /**
     * Opens a file and loads its content into the model.
     *
     * @param file The file to be opened.
     */
    public void open(File file) {
        try {
			model = new NonogramMakerModel(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        init();
    }

    /**
     * Saves the current state of the model to a file.
     *
     * @param filename The name of the file to save to.
     */
    public void save(String filename) {
        try {
			model.saveToFile(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
}