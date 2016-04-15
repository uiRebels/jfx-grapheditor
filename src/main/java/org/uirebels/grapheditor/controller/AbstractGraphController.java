/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.uirebels.grapheditor.constants.StringConstant;
import org.uirebels.grapheditor.model.CompositeGraph;
import org.uirebels.grapheditor.model.CompositeEdge;
import org.uirebels.grapheditor.model.CompositeVertex;
import org.uirebels.grapheditor.view.AbstractVertexView;
import org.uirebels.grapheditor.view.AbstractEdgeView;

/**
 *
 * @author bnamestka
 */
public abstract class AbstractGraphController {

    protected final CompositeGraph graphModel;

    protected Pane graphView;
    protected AbstractVertexView lastVertexView;

    public AbstractGraphController() {
        graphModel = new CompositeGraph();
    }

    public Pane getGraphView() {
        return graphView;
    }

    public void setGraphView(Pane _graphView) {
        graphView = _graphView;
    }

    public AbstractVertexView getLastVertexView() {
        return lastVertexView;
    }

    public void setLastVertexView(AbstractVertexView _vertexView) {
        lastVertexView = _vertexView;
    }

    // ------------------------------------------------------------------------
    // following methods deal with the GraphEditor operations
    //
    // graph related ops
    public abstract void newGraph();

    public void saveGraph() {
        Graph graph = CompositeGraph.getGraph();
        try {
            graph.io(IoCore.graphson()).writeGraph("my-tinkerpop-graph.json");
        } catch (IOException ex) {
            Logger.getLogger(AbstractGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveGraphAs(String _graphName){
        Graph graph = CompositeGraph.getGraph();
        try {
            graph.io(IoCore.graphson()).writeGraph(_graphName + StringConstant.DOT_JSON);
        } catch (IOException ex) {
            Logger.getLogger(AbstractGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract void addVertex(double x, double y);

    public abstract void editVertexProperties(AbstractVertexView _vertexView);

    public abstract void connect(AbstractVertexView _vView1, AbstractVertexView _vView2);

    public void deleteVertex(AbstractVertexView _vertexView) {
        //
        //  TODO:  get attached incoming & outgoing edges and delete those as well
        //
        CompositeVertex vertex = (CompositeVertex) _vertexView.getUserData();
        // remove from JavaFX scenegraph
        graphView.getChildren().remove(_vertexView);
        // remove from tinkerpop graph model
        vertex.delete();
    }

    public void deleteEdge(AbstractEdgeView _edgeView) {
        CompositeEdge edge = (CompositeEdge) _edgeView.getUserData();
        graphModel.deleteEdge(edge);
    }

    //
    // edge related ops
    public void updateVertex(CompositeVertex _vertex, Map<String, Object> _editedAttributes) {
        _vertex.update(_editedAttributes);
    }

}
