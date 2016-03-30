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
import javafx.scene.layout.Pane;
import org.uirebels.grapheditor.viewmodel.AbstractViewModel;

public abstract class AbstractVertexView extends Pane {

    private static AbstractViewModel viewModel = null;
    
    public static void setViewModel(AbstractViewModel _viewModel) {
        viewModel = _viewModel;
    }

    public void deleteVertex() {
        viewModel.deleteVertex(this);
    }

    public void editVertex() {
        viewModel.editVertex(this);
    }

}
