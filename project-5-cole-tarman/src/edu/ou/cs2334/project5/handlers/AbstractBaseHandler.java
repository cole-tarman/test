package edu.ou.cs2334.project5.handlers;

import javafx.scene.control.ToggleButton;
import edu.ou.cs2334.project5.models.NonogramMakerModel;
import edu.ou.cs2334.project5.views.NonogramMakerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Abstract base class for event handlers in the Nonogram puzzle maker application.
 * This class provides a foundation for all handlers that need to interact with both
 * the model and the view of the Nonogram puzzle maker.
 */
public abstract class AbstractBaseHandler implements EventHandler<ActionEvent> {
    
    /**
     * The model of the Nonogram puzzle maker, representing the state and logic of the puzzle.
     */
    protected NonogramMakerModel model;
    
    /**
     * The view of the Nonogram puzzle maker, representing the UI components.
     */
    protected NonogramMakerView view;

    /**
     * Constructs an AbstractBaseHandler with the specified model and view.
     *
     * @param model The NonogramMakerModel to be used by the handler.
     * @param view  The NonogramMakerView to be used by the handler.
     */
    public AbstractBaseHandler(NonogramMakerModel model, NonogramMakerView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Handles an ActionEvent. This method is intended to be overridden by subclasses
     * to provide specific event handling logic.
     *
     * @param event The ActionEvent to be handled.
     */
    @Override
    public abstract void handle(ActionEvent event);
}