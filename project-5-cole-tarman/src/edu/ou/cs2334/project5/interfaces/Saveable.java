package edu.ou.cs2334.project5.interfaces;

import java.io.File;
import java.io.IOException;

public interface Saveable {
    /**
     * Saves the current state to the given file.
     *
     * @param filename the name of the file to save to
     * @throws IOException if an I/O error occurs
     */
    void save(String filename) throws IOException;
}