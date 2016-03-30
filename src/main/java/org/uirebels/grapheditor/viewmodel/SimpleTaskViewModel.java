/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.viewmodel;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tinkerpop.gremlin.structure.Edge;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.uirebels.grapheditor.exceptions.EdgeViewException;
import org.uirebels.grapheditor.exceptions.VertexViewException;
import org.uirebels.grapheditor.model.SimpleTaskModel;
import org.uirebels.grapheditor.view.AbstractVertexView;
import org.uirebels.grapheditor.view.SimpleFXMLEdgeView;
import org.uirebels.grapheditor.view.SimpleFXMLVertexView;
import org.uirebels.grapheditor.viewmodel.dialogs.SimpleTaskDialog;

/**
 *
 * @author bnamestka
 */
public class SimpleTaskViewModel extends AbstractViewModel {

////    private final ObjectProperty<ContextModel> graphModelProperty;
//    private final ObjectProperty<Pane> graphViewProperty;
//    private final ObjectProperty<AbstractVertexView> vertexViewProperty;
//    private final ObjectProperty<AbstractVertexView> lastVertexViewProperty;
//    private final ObjectProperty<AbstractEdgeView> edgeViewProperty;
//
//    private final ContextModel contextModel;
    public SimpleTaskViewModel() {
        super();
    }

    // ------------------------------------------------------------------------
    // following methods deal with the GraphEditor operations
    @Override
    public void addVertex(double x, double y) {
        SimpleFXMLVertexView vertexView = null;
        try {
            vertexView = new SimpleFXMLVertexView();
        } catch (VertexViewException ex) {
            Logger.getLogger(SimpleTaskViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (vertexView != null) {
            vertexView.setLayoutX(x);
            vertexView.setLayoutY(y);

            final Vertex vertex = contextModel.addVertex(new SimpleTaskModel());
            vertexView.setUserData(vertex);
            getGraphView().getChildren().add(vertexView);

            if (getLastVertexView() != null) {
                connect(getLastVertexView(), vertexView);
            }

            setLastVertexView(vertexView);
        }
    }

    @Override
    public void editVertex(AbstractVertexView _vertexView) {
        Vertex vertex = (Vertex) _vertexView.getUserData();
        SimpleTaskDialog.pop(this, vertex);
    }

    @Override
    public void connect(AbstractVertexView _vView1, AbstractVertexView _vView2) {
        Vertex v1 = (Vertex) _vView1.getUserData();
        Vertex v2 = (Vertex) _vView2.getUserData();

        SimpleFXMLEdgeView edgeView = null;
        try {
            edgeView = new SimpleFXMLEdgeView();
        } catch (EdgeViewException ex) {
            Logger.getLogger(SimpleTaskViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (edgeView != null) {
            edgeView.setLineEndPoints(edgeView.getEndPoints(_vView1, _vView2));
            final Edge edge = contextModel.connect(v1, v2);
            edgeView.setUserData(edge);
            getGraphView().getChildren().add(edgeView);
        }

    }

}
