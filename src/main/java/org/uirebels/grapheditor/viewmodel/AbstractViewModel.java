/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.viewmodel;

import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;
import org.uirebels.grapheditor.model.graph.AbstractGraphModel;
import org.uirebels.grapheditor.model.edge.AbstractEdge;
import org.uirebels.grapheditor.model.vertex.AbstractVertex;
import org.uirebels.grapheditor.view.AbstractVertexView;
import org.uirebels.grapheditor.view.AbstractEdgeView;

/**
 *
 * @author bnamestka
 */
public abstract class AbstractViewModel {

//    private static JFXGraphEditorViewController mainController;
//    private static AbstractViewController vertexController;
//    private static AbstractViewController edgeController;
//    private final ObjectProperty<ContextModel> graphModelProperty;
//    private final ObjectProperty<Vertex> vertexModelProperty;
//    private final ObjectProperty<Edge> edgeModelProperty;
    protected  final ObjectProperty<Pane> graphViewProperty;
    protected  final ObjectProperty<AbstractVertexView> vertexViewProperty;
    protected  final ObjectProperty<AbstractVertexView> lastVertexViewProperty;
    protected  final ObjectProperty<AbstractEdgeView> edgeViewProperty;

    protected final AbstractGraphModel contextModel;

    public AbstractViewModel() {
        contextModel = new AbstractGraphModel();
//        graphModelProperty = new SimpleObjectProperty<>();
//        vertexModelProperty = new SimpleObjectProperty<>();
//        edgeModelProperty = new SimpleObjectProperty<>();
        graphViewProperty = new SimpleObjectProperty<>();
        vertexViewProperty = new SimpleObjectProperty<>();
        lastVertexViewProperty = new SimpleObjectProperty<>();
        edgeViewProperty = new SimpleObjectProperty<>();
//        domainModelProperty = new SimpleObjectProperty<>();
    }

    public AbstractGraphModel getContextModel() {
        return contextModel;
    }

    // ------------------------------------------------------------------------
    // following methods deal with the Tinkerpop Graph structure model
    // methods for getting/setting and responding to tinkerpop GRAPH changes
//
//    public ObjectProperty<ContextModel> getGraphModelProperty() {
//        return graphModelProperty;
//    }
//
//
//    public AbstractGraphModel getGraphModel() {
//        return graphModelProperty.get();
//    }
//
//
//    public void setModel(AbstractGraphModel _model) {
//        graphModelProperty.setValue(_model);
//    }
//
    // ------------------------------------------------------------------------
    // following methods deal with the JavaFX based graph views
    // methods for getting/setting and responding to graph view changes
    public ObjectProperty<Pane> getGraphViewProperty() {
        return graphViewProperty;
    }

    public Pane getGraphView() {
        return graphViewProperty.get();
    }

    public void setGraphView(Pane _graphView) {
        graphViewProperty.set(_graphView);
    }

    // methods for getting/setting and responding to vertex view changes
    public ObjectProperty<AbstractVertexView> getVertexViewProperty() {
        return vertexViewProperty;
    }

    public AbstractVertexView getVertexView() {
        return vertexViewProperty.get();
    }

    public void setVertexView(AbstractVertexView _vertexView) {
        AbstractVertexView.setViewModel(this);
        vertexViewProperty.set(_vertexView);
    }

    public ObjectProperty<AbstractVertexView> getLastVertexViewProperty() {
        return lastVertexViewProperty;
    }

    public AbstractVertexView getLastVertexView() {
        return lastVertexViewProperty.get();
    }

    public void setLastVertexView(AbstractVertexView _vertexView) {
        lastVertexViewProperty.set(_vertexView);
    }

    // methods for getting/setting and responding to edge view changes
    public ObjectProperty<AbstractEdgeView> getEdgeViewProperty() {
        return edgeViewProperty;
    }

    public AbstractEdgeView getEdgeView() {
        return edgeViewProperty.get();
    }

    public void setEdgeView(AbstractEdgeView _edgeView) {
        edgeViewProperty.set(_edgeView);
    }

    // ------------------------------------------------------------------------
    // following methods deal with the GraphEditor operations
    public abstract void addVertex(double x, double y);
    public abstract void editVertex(AbstractVertexView _vertexView);
    public abstract void connect(AbstractVertexView _vView1, AbstractVertexView _vView2);

    public void deleteVertex(AbstractVertexView _vertexView) {
        AbstractVertex vertex = (AbstractVertex) _vertexView.getUserData();
        contextModel.deleteVertex(vertex);
    }

    public void updateVertex(AbstractVertex _vertex, Map<String, Object> _editedAttributes) {
        contextModel.updateVertex(_vertex, _editedAttributes);
    }

    public void deleteEdge(AbstractEdgeView _edgeView) {
        AbstractEdge edge = (AbstractEdge) _edgeView.getUserData();
        contextModel.deleteEdge(edge);
    }

}
