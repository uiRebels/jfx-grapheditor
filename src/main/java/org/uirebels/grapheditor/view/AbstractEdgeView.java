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
import javafx.scene.Group;
import org.uirebels.grapheditor.controller.AbstractGraphController;

public abstract class AbstractEdgeView extends Group {

    private static AbstractGraphController graphController = null;
    
    protected AbstractVertexView sourceView;
    protected AbstractVertexView targetView;

    public static void setGraphController(AbstractGraphController _graphController) {
        graphController = _graphController;
    }

    public void setSourceView(AbstractVertexView _vView){
        sourceView = _vView;
    }

    public void setTargetView(AbstractVertexView _vView){
        targetView = _vView;
    }

    public void deleteEdge() {
        graphController.deleteEdge(this);
    }

    public void bindEndPoints(AbstractVertexView _vView1, AbstractVertexView _vView2) {
        sourceView = _vView1;
        targetView = _vView2;
        setSegmentEndPoints();
    }
//    abstract void bindEndPoints(AbstractVertexView vView1, AbstractVertexView vView2);
    abstract void setSegmentEndPoints();

}