package edu.ou.cs2334.project5.handlers;

import java.io.IOException;
import edu.ou.cs2334.project5.interfaces.Saveable;
import edu.ou.cs2334.project5.models.NonogramMakerModel;

/**
 * A handler responsible for saving the current state of the NonogramMakerModel to a file.
 */
public class SaveHandler implements Saveable {

    /**
     * The model of the Nonogram puzzle that will be saved to a file.
     */
    private NonogramMakerModel model;

    /**
     * Constructs a SaveHandler with the specified NonogramMakerModel.
     *
     * @param model The NonogramMakerModel whose data is to be saved.
     */
    public SaveHandler(NonogramMakerModel model) {
        this.model = model;
    }

    /**
     * Saves the current state of the NonogramMakerModel to the specified file.
     * The specific file format and writing mechanism are implemented within the NonogramMakerModel class.
     *
     * @param filename The name of the file to which the Nonogram puzzle data is to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    @Override
    public void save(String filename) throws IOException {
        model.saveToFile(filename);
    }
}