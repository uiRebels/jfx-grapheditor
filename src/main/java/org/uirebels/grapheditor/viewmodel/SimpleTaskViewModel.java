/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.viewmodel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import org.uirebels.grapheditor.constants.ConfigurationConstant;
import org.uirebels.grapheditor.exceptions.EdgeViewException;
import org.uirebels.grapheditor.exceptions.VertexViewException;
import org.uirebels.grapheditor.model.SimpleTask;
import org.uirebels.grapheditor.model.edge.AbstractEdge;
import org.uirebels.grapheditor.model.vertex.AbstractVertex;
import org.uirebels.grapheditor.view.AbstractVertexView;
import org.uirebels.grapheditor.view.SimpleEdgeView;
import org.uirebels.grapheditor.view.SimpleVertexView;
import org.uirebels.grapheditor.viewmodel.dialogs.SimpleTaskDialog;

/**
 *
 * @author bnamestka
 */
public class SimpleTaskViewModel extends AbstractViewModel {

    public SimpleTaskViewModel() {
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
            Logger.getLogger(SimpleTaskViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (vertexView != null) {
            vertexView.setLayoutX(x);
            vertexView.setLayoutY(y);

            final AbstractVertex vertex = contextModel.addVertex(new SimpleTask());
            vertexView.setUserData(vertex);
            System.out.println("****>> Vertex name is " + vertex.getName());
            getGraphView().getChildren().add(vertexView);

            if (getLastVertexView() != null) {
                connect(getLastVertexView(), vertexView);
            }
            setLastVertexView(vertexView);
        }
    }

    @Override
    public void deleteVertex(AbstractVertexView _vertexView) {
        AbstractVertex vertex = (AbstractVertex) _vertexView.getUserData();
        getGraphView().getChildren().remove(_vertexView);
        vertex.delete();
    }

    @Override
    public void editVertex(AbstractVertexView _vertexView) {
        AbstractVertex vertex = (AbstractVertex) _vertexView.getUserData();
        SimpleVertexView svView = (SimpleVertexView) _vertexView;
        // setup temporary bindings so that the dialog can get the changes 
        // to the AbstractViewModel which changes the AbstractVertex 
        // and those values are bound to the UI
        ObjectBinding<Object> nameBinding = Bindings.valueAt(vertex.getObservablePropertyMap(), ConfigurationConstant.ELEMENT_NAME_KEY);
        ObjectBinding<Object> descriptionBinding = Bindings.valueAt(vertex.getObservablePropertyMap(), "Description");
        svView.getNameLabel().textProperty().bind(nameBinding.asString());
        svView.getDescriptionTextArea().textProperty().bind(descriptionBinding.asString());
        SimpleTaskDialog.pop(this, vertex);
        svView.getNameLabel().textProperty().unbind();
        svView.getDescriptionTextArea().textProperty().unbind();
    }

    @Override
    public void connect(AbstractVertexView _vView1, AbstractVertexView _vView2) {
        AbstractVertex v1 = (AbstractVertex) _vView1.getUserData();
        AbstractVertex v2 = (AbstractVertex) _vView2.getUserData();

        SimpleEdgeView edgeView = null;
        try {
            edgeView = new SimpleEdgeView();
        } catch (EdgeViewException ex) {
            Logger.getLogger(SimpleTaskViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (edgeView != null) {
            edgeView.bindEndPoints(_vView1, _vView2);
            final AbstractEdge edge = contextModel.connect(v1, v2);
            edgeView.setUserData(edge);
            getGraphView().getChildren().add(edgeView);
        }

    }

    @Override
    public void newGraph() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveGraph() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveGraphAs(String _graphName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
