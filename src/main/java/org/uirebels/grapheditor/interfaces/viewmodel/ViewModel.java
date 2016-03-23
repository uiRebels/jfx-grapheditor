/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.interfaces.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.uirebels.grapheditor.controller.AbstractViewController;
import org.uirebels.grapheditor.controller.JFXGraphEditorViewController;
import org.uirebels.grapheditor.exceptions.EdgeViewException;
import org.uirebels.grapheditor.exceptions.VertexViewException;


/**
 *
 * @author bnamestka
 */
public interface ViewModel {
        
    public JFXGraphEditorViewController getMainController();    
    public void setMainController(JFXGraphEditorViewController _jfxGEVController);

    public AbstractViewController getVertexController();
    public void setVertexController(AbstractViewController _vertexController);
    
    public AbstractViewController getEdgeController();
    public void setEdgeController(AbstractViewController _edgeController);
    
    
    // ------------------------------------------------------------------------
    // following methods deal with the Tinkerpop Graph structure model
    
    // methods for getting/setting and responding to tinkerpop GRAPH changes
    public ObjectProperty<Graph> getGraphModelProperty();   
    public Graph getGraph();
    public void setGraph(Graph _graph);
    
    // methods for getting/setting and responding to tinkerpop VERTEX changes
    public ObjectProperty<Vertex> getVertexModelProperty();    
    public Vertex getVertex();    
    public void setVertex(Vertex _vertex);
    
    // methods for getting/setting and responding to tinkerpop EDGE changes
    public ObjectProperty<Edge> getEdgeModelProperty();   
    public Edge getEdge();   
    public void setEdge(Edge _edge);
    
    // ------------------------------------------------------------------------
    // following methods deal with the JavaFX based graph views
    
    // methods for getting/setting and responding to graph view changes
    public ObjectProperty<Pane> getGraphViewProperty();    
    public Pane getGraphView();    
    public void setGraphView(Pane _graphView);

    // methods for getting/setting and responding to vertex view changes
    public ObjectProperty<Group> getVertexViewProperty();    
    public Group getVertexView();    
    public void setVertexView(Group _vertexView);
    public void setVertexView(String simpleVertexView) throws VertexViewException ;
    
    // methods for getting/setting and responding to edge view changes
    public ObjectProperty<Group> getEdgeViewProperty();    
    public Group getEdgeView();    
    public void setEdgeView(Group _vertexView);
    public void setEdgeView(String simpleEdgeView) throws EdgeViewException;

    // ------------------------------------------------------------------------
    // following methods deal with the GraphEditor operations
    public void newGraph();
    public void addVertex(double x, double y);
    public void deleteVertex(ActionEvent event);
    public void editVertex(ActionEvent event);

}
