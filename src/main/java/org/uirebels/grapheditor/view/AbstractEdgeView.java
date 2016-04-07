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
import org.uirebels.grapheditor.viewmodel.AbstractViewModel;

public abstract class AbstractEdgeView extends Group {

    private static double XOFFSET = 20.0;
    private static AbstractViewModel viewModel = null;

    public static void setViewModel(AbstractViewModel _viewModel) {
        viewModel = _viewModel;
    }

    public void deleteEdge() {
        viewModel.deleteEdge(this);
    }

    protected void setXOffset(double _offset) {
        XOFFSET = _offset;
    }

    abstract void bindEndPoints(AbstractVertexView vView1, AbstractVertexView vView2);

}