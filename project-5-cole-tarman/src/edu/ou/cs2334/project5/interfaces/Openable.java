package edu.ou.cs2334.project5.interfaces;

import java.io.File;
import java.io.IOException;

public interface Openable {
    /**
     * Opens a file and reads its content.
     *
     * @param file the file to open
     * @throws IOException if an I/O error occurs
     */
    void open(File file) throws IOException;
}