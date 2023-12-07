package edu.ou.cs2334.project5.handlers;

import java.io.File;
import java.io.IOException;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.NonogramMakerModel;

/**
 * A handler responsible for opening files and initializing the NonogramMakerModel with data from the file.
 */
public class OpenHandler implements Openable {
    
    /**
     * The model of the Nonogram puzzle that will be loaded from a file.
     */
    private NonogramMakerModel model;

    /**
     * Constructs an OpenHandler with the specified NonogramMakerModel.
     *
     * @param model The NonogramMakerModel to load data into.
     */
    public OpenHandler(NonogramMakerModel model) {
        this.model = model;
    }

    /**
     * Opens a file and initializes the NonogramMakerModel with the data read from it.
     * The specific file format and reading mechanism are implemented within the NonogramMakerModel class.
     *
     * @param file The file from which the Nonogram puzzle data is to be loaded.
     * @throws IOException If there is an error reading from the file.
     */
    
    @Override
	public void open(File file) throws IOException {
		// TODO Auto-generated method stub
		return;
	}
   
}