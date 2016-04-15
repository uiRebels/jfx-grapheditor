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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.uirebels.grapheditor.controller.AbstractGraphController;

public abstract class AbstractVertexView extends Pane {

    protected static AbstractGraphController graphController = null;
    protected final Delta dragDelta = new Delta();

    public static void setGraphController(AbstractGraphController _graphController) {
        graphController = _graphController;
    }

    protected void setMouseHandlers() {

        setOnMousePressed((MouseEvent event) -> {
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
        });

        setOnMouseReleased((MouseEvent event) -> {
            event.consume();
        });
    }

//    public double getOutX(){
//        return getBoundsInParent().getMaxX();
//    }
//    
//    public double getOutY(){
//        return getBoundsInParent().getMaxY() - (getHeight() * 0.5);
//    }
//    
//    public double getInX(){
//        return getBoundsInParent().getMinX();        
//    }
//    
//    public double getInY(){
//        return getBoundsInParent().getMinY() + (getHeight() * 0.5);
//    }
    // records relative x and y co-ordinates.
    protected class Delta {
        double x, y;
    }

}
