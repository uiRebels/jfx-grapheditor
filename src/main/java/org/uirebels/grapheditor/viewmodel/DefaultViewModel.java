/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.viewmodel;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.uirebels.grapheditor.controller.AbstractViewController;
import org.uirebels.grapheditor.controller.JFXGraphEditorViewController;
import org.uirebels.grapheditor.exceptions.EdgeViewException;
import org.uirebels.grapheditor.exceptions.VertexViewException;
import org.uirebels.grapheditor.interfaces.viewmodel.ViewModel;
import org.uirebels.grapheditor.view.SimpleVertexView;


/**
 *
 * @author bnamestka
 */
public class DefaultViewModel implements ViewModel {

    private static JFXGraphEditorViewController mainController;
    private static AbstractViewController vertexController;
    private static AbstractViewController edgeController;

    private final ObjectProperty<Graph> graphModelProperty;
    private final ObjectProperty<Vertex> vertexModelProperty;
    private final ObjectProperty<Edge> edgeModelProperty;

    private final ObjectProperty<Pane> graphViewProperty;
    private final ObjectProperty<Group> vertexViewProperty;
    private final ObjectProperty<Group> edgeViewProperty;

    public DefaultViewModel() {
        graphModelProperty = new SimpleObjectProperty<>();
        vertexModelProperty = new SimpleObjectProperty<>();
        edgeModelProperty = new SimpleObjectProperty<>();
        graphViewProperty = new SimpleObjectProperty<>();
        vertexViewProperty = new SimpleObjectProperty<>();
        edgeViewProperty = new SimpleObjectProperty<>();
    }

    @Override
    public JFXGraphEditorViewController getMainController() {
        return mainController;
    }

    @Override
    public void setMainController(JFXGraphEditorViewController _jfxGEVController) {
        mainController = _jfxGEVController;
    }

    @Override
    public AbstractViewController getVertexController() {
        return vertexController;
    }

    @Override
    public void setVertexController(AbstractViewController _vertexController) {
        vertexController = _vertexController;
    }

    @Override
    public AbstractViewController getEdgeController() {
        return edgeController;
    }

    @Override
    public void setEdgeController(AbstractViewController _edgeController) {
        edgeController = _edgeController;
    }

    // ------------------------------------------------------------------------
    // following methods deal with the Tinkerpop Graph structure model
    // methods for getting/setting and responding to tinkerpop GRAPH changes
    @Override
    public ObjectProperty<Graph> getGraphModelProperty() {
        return graphModelProperty;
    }

    @Override
    public Graph getGraph() {
        return graphModelProperty.get();
    }

    @Override
    public void setGraph(Graph _graph) {
        graphModelProperty.set(_graph);
    }

    // methods for getting/setting and responding to tinkerpop VERTEX changes
    @Override
    public ObjectProperty<Vertex> getVertexModelProperty() {
        return vertexModelProperty;
    }

    @Override
    public Vertex getVertex() {
        return vertexModelProperty.get();
    }

    @Override
    public void setVertex(Vertex _vertex) {
        vertexModelProperty.set(_vertex);
    }

    // methods for getting/setting and responding to tinkerpop EDGE changes
    @Override
    public ObjectProperty<Edge> getEdgeModelProperty() {
        return edgeModelProperty;
    }

    @Override
    public Edge getEdge() {
        return edgeModelProperty.get();
    }

    @Override
    public void setEdge(Edge _edge) {
        edgeModelProperty.set(_edge);
    }

    // ------------------------------------------------------------------------
    // following methods deal with the JavaFX based graph views
    // methods for getting/setting and responding to graph view changes
    @Override
    public ObjectProperty<Pane> getGraphViewProperty() {
        return graphViewProperty;
    }

    @Override
    public Pane getGraphView() {
        return graphViewProperty.get();
    }

    @Override
    public void setGraphView(Pane _graphView) {
        graphViewProperty.set(_graphView);
    }

    // methods for getting/setting and responding to vertex view changes
    @Override
    public ObjectProperty<Group> getVertexViewProperty() {
        return vertexViewProperty;
    }

    @Override
    public Group getVertexView() {
        return vertexViewProperty.get();
    }

    @Override
    public void setVertexView(Group _vertexView) {
        vertexViewProperty.set(_vertexView);
    }

    @Override
    public void setVertexView(String _vertexViewFXML) throws VertexViewException {
        String fxmlViewPath = "/fxml/" + _vertexViewFXML + ".fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlViewPath));
        Group vertexView = null;
        try {
            vertexView = loader.load();
            vertexViewProperty.set(vertexView);
        } catch (IOException ex) {
            Logger.getLogger(DefaultViewModel.class.getName()).log(Level.SEVERE, null, ex);
            throw new VertexViewException(fxmlViewPath);
        }
    }

    // methods for getting/setting and responding to edge view changes
    @Override
    public ObjectProperty<Group> getEdgeViewProperty() {
        return vertexViewProperty;
    }

    @Override
    public Group getEdgeView() {
        return edgeViewProperty.get();
    }

    @Override
    public void setEdgeView(Group _vertexView) {
        edgeViewProperty.set(_vertexView);
    }

    @Override
    public void setEdgeView(String _edgeViewFXML) throws EdgeViewException {
        String fxmlViewPath = "/fxml/" + _edgeViewFXML + ".fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlViewPath));
        Group edgeView = null;
        try {
            edgeView = loader.load();
            setEdgeView(edgeView);
        } catch (IOException ex) {
            Logger.getLogger(DefaultViewModel.class.getName()).log(Level.SEVERE, null, ex);
            throw new EdgeViewException(fxmlViewPath);
        }
    }

    // ------------------------------------------------------------------------
    // following methods deal with the GraphEditor operations

    @Override
    public void newGraph() {
        Graph graph = TinkerGraph.open();
        setGraph(graph);
    }

    @Override
    public void addVertex(double x, double y) {
        Group vertex = new SimpleVertexView();
        vertex.setLayoutX(x);
        vertex.setLayoutY(y);
        Graph graph = getGraph();
        final Vertex marko = graph.addVertex("name", "marko", "age", 29);
    }

    @Override
    public void deleteVertex(ActionEvent _event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editVertex(ActionEvent _event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
