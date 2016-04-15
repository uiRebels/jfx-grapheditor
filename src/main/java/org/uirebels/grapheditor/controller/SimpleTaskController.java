/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import org.uirebels.grapheditor.constants.ConfigurationConstant;
import org.uirebels.grapheditor.exceptions.EdgeViewException;
import org.uirebels.grapheditor.exceptions.VertexViewException;
import org.uirebels.grapheditor.model.SimpleTaskVertex;
import org.uirebels.grapheditor.model.CompositeEdge;
import org.uirebels.grapheditor.model.CompositeVertex;
import org.uirebels.grapheditor.view.AbstractVertexView;
import org.uirebels.grapheditor.view.SimplePolyEdgeView;
import org.uirebels.grapheditor.view.SimpleVertexView;
import org.uirebels.grapheditor.controller.dialogs.SimpleTaskDialog;

/**
 *
 * @author bnamestka
 */
public class SimpleTaskController extends AbstractGraphController {

    public SimpleTaskController() {
        super();
    }

    // ------------------------------------------------------------------------
    // following methods deal with the GraphEditor operations
    @Override
    public void addVertex(double x, double y) {
        SimpleVertexView vertexView = null;
        try {
            vertexView = new SimpleVertexView();
        } catch (VertexViewException ex) {
            Logger.getLogger(SimpleTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (vertexView != null) {
            vertexView.setLayoutX(x);
            vertexView.setLayoutY(y);

            final CompositeVertex vertex = graphModel.addVertex(new SimpleTaskVertex());
            vertexView.setUserData(vertex);
            getGraphView().getChildren().add(vertexView);

            if (lastVertexView != null) {
                connect(lastVertexView, vertexView);
            }
            lastVertexView = vertexView;
        }
    }


    @Override
    public void editVertexProperties(AbstractVertexView _vertexView) {
        CompositeVertex vertex = (CompositeVertex) _vertexView.getUserData();
        SimpleVertexView svView = (SimpleVertexView) _vertexView;
        // setup temporary bindings so that the dialog can get the changes 
        // to the CompositeVertex  and those values are bound to the JavaFX vertex view
        ObjectBinding<Object> nameBinding = Bindings.valueAt(vertex.getPropertiesAsObservableMap(), ConfigurationConstant.ELEMENT_NAME_KEY);
        ObjectBinding<Object> descriptionBinding = Bindings.valueAt(vertex.getPropertiesAsObservableMap(), "Description");
        svView.getNameLabel().textProperty().bind(nameBinding.asString());
        svView.getDescriptionTextArea().textProperty().bind(descriptionBinding.asString());
        SimpleTaskDialog.pop(vertex);
        svView.getNameLabel().textProperty().unbind();
        svView.getDescriptionTextArea().textProperty().unbind();
    }

    @Override
    public void connect(AbstractVertexView _vView1, AbstractVertexView _vView2) {
        CompositeVertex v1 = (CompositeVertex) _vView1.getUserData();
        CompositeVertex v2 = (CompositeVertex) _vView2.getUserData();

        SimplePolyEdgeView edgeView = null;
        try {
            edgeView = new SimplePolyEdgeView();
        } catch (EdgeViewException ex) {
            Logger.getLogger(SimpleTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (edgeView != null) {
            edgeView.bindEndPoints(_vView1, _vView2);
            final CompositeEdge edge = graphModel.connect(v1, v2);
            edgeView.setUserData(edge);
            getGraphView().getChildren().add(edgeView);
        }

    }

    @Override
    public void newGraph() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
