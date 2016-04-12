/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.view;

/**
 *
 * @author bnamestka
 */
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.uirebels.grapheditor.viewmodel.AbstractViewModel;

public abstract class AbstractVertexView extends Pane {

    private static AbstractViewModel viewModel = null;
    protected final Delta dragDelta = new Delta();

    protected final DoubleProperty inX = new SimpleDoubleProperty();
    protected final DoubleProperty inY = new SimpleDoubleProperty();
    protected final DoubleProperty outX = new SimpleDoubleProperty();
    protected final DoubleProperty outY = new SimpleDoubleProperty();
    public DoubleProperty inXProperty() { return inX; }
    public DoubleProperty inYProperty() {  return inY;  }
    public DoubleProperty outXProperty() {   return outX; }
    public DoubleProperty outYProperty() {  return outY; }

    public static void setViewModel(AbstractViewModel _viewModel) {
        viewModel = _viewModel;
    }

    public void deleteVertex() {
        viewModel.deleteVertex(this);
    }

    public void editVertex() {
        viewModel.editVertex(this);
    }

// -----------------------------------------------------------------------------
    protected void bindPortLocations() {
        inX.bind(layoutXProperty());
        inY.bind(layoutYProperty().add(Bindings.multiply(heightProperty(), 0.5)));
        outX.bind(layoutXProperty().add(widthProperty()));
        outY.bind(layoutYProperty().add(Bindings.multiply(heightProperty(), 0.5)));
    }

    private void unBindPortLocations(){
        inX.unbind();
        inY.unbind();
        outX.unbind();
        outY.unbind();
    }

    protected void setMouseHandlers() {
        
        setOnMousePressed((MouseEvent event) -> {
            unBindPortLocations();
            dragDelta.x = getTranslateX() - event.getSceneX();
            dragDelta.y = getTranslateY() - event.getSceneY();
            event.consume();
        });

        setOnMouseDragged((MouseEvent event) -> {
            double newX = dragDelta.x + event.getSceneX();
            double newY = dragDelta.y + event.getSceneY();
            setTranslateX(newX);
            setTranslateY(newY);
            event.consume();
            resetPortLocations();
        });

        setOnMouseReleased((MouseEvent event) -> {
            event.consume();
        });
    }
        
    public void resetPortLocations(){
        Bounds bounds = getBoundsInParent();
        inX.set(bounds.getMinX());
        inY.set(bounds.getMinY() + (getHeight() * 0.5));
        outX.set(bounds.getMaxX());
        outY.set(bounds.getMaxY() - (getHeight() * 0.5));
    }
    
    // records relative x and y co-ordinates.
    protected class Delta {
        double x, y;
    }

}
