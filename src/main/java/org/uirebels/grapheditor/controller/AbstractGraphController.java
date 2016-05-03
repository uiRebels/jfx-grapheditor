/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller;

import java.util.Map;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    protected CompositeGraph graphModel;
    protected String graphSaveDirectory;

    protected Pane graphView;
    protected AbstractVertexView lastVertexView;

    public AbstractGraphController() {    }

    public CompositeGraph getGraphModel() {
        return graphModel;
    }

    public void setGraphAttribute(String _key, Object _attribute){
        graphModel.setGraphAttribute(_key, _attribute);
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

    // find out where to place a new vertex
    protected abstract Point2D getNextLocation();

    // ------------------------------------------------------------------------
    // following methods deal with the GraphEditor operations
    //
    // graph related ops
    public void newGraph(){
        graphModel = new CompositeGraph();
    }

    public void openGraph(String _graphName) {
        graphModel = CompositeGraph.openGraph(_graphName);
        reconstituteGraph();
    }

    public void saveGraph() {
        saveGraphTask(null);
    }

    public void saveGraphAs(String _graphName) {
        saveGraphTask(_graphName);
    }

    public abstract void addVertex();

    public abstract void addVertex(double x, double y);

    protected abstract void addVertexView(CompositeVertex _vertex);

    protected abstract void addVertexView(CompositeVertex _vertex, double x, double y);

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

    public abstract void reconstituteGraph();

    private void saveGraphTask(final String _graphName) {
        final double wndwWidth = 300.0d;
        Label updateLabel = null;
        if (_graphName == null) {
            updateLabel = new Label("Saving graph...");
        } else {
            updateLabel = new Label("Saving " + _graphName + " ...");
        }
        updateLabel.setPrefWidth(wndwWidth);
        ProgressBar progress = new ProgressBar();
        progress.setPrefWidth(wndwWidth);

        VBox updatePane = new VBox();
        updatePane.setPadding(new Insets(10));
        updatePane.setSpacing(5.0d);
        updatePane.getChildren().addAll(updateLabel, progress);

        Stage taskUpdateStage = new Stage(StageStyle.UTILITY);
        taskUpdateStage.setScene(new Scene(updatePane));
        taskUpdateStage.show();

        Task saveTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(1000);
                if (_graphName == null) {
                    CompositeGraph.saveGraph(graphSaveDirectory);
                } else {
                    CompositeGraph.saveGraphAs(graphSaveDirectory, _graphName);
                }
                // Return null at the end of a Task of type Void
                return null;
            }
        };

        saveTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                taskUpdateStage.hide();
            }
        });

        taskUpdateStage.show();
        new Thread(saveTask).start();
    }

    void setSavePath(String _filePath) {
        graphSaveDirectory = _filePath;
    }

}
