/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.Point2D;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.uirebels.grapheditor.constants.ConfigurationConstant;
import org.uirebels.grapheditor.exceptions.EdgeViewException;
import org.uirebels.grapheditor.exceptions.VertexViewException;
import org.uirebels.grapheditor.model.SimpleTaskVertex;
import org.uirebels.grapheditor.model.CompositeEdge;
import org.uirebels.grapheditor.model.CompositeVertex;
import org.uirebels.grapheditor.view.AbstractVertexView;
import org.uirebels.grapheditor.view.SimplePolyEdgeView;
import org.uirebels.grapheditor.view.SimpleTaskVertexView;
import org.uirebels.grapheditor.controller.dialogs.SimpleTaskDialog;
import org.uirebels.grapheditor.model.CompositeGraph;

/**
 *
 * @author bnamestka
 */
public class SimpleTaskController extends AbstractGraphController {

    final double FIRST_VERTEX_POSITION_X = 75.0;
    final double FIRST_VERTEX_POSITION_Y = 75.0;
    final double X_LOCATION_OFFSET = 25.0;
    final double Y_LOCATION_OFFSET = 125.0;

    public SimpleTaskController() {
        super();

    }

    // ------------------------------------------------------------------------
    // following methods deal with the GraphEditor operations
    @Override
    public SimpleTaskVertexView addVertex() {
        if (lastVertexView == null) {
            return addVertex(FIRST_VERTEX_POSITION_X, FIRST_VERTEX_POSITION_Y);
        } else {
            Point2D loc = getNextLocation();
            return addVertex(loc.getX(), loc.getY());
        }
    }

    @Override
    public SimpleTaskVertexView addVertex(double x, double y) {
//        SimpleTaskVertexView vertexView = null;
        // create vertex model
        final CompositeVertex vertex = graphModel.addVertex(new SimpleTaskVertex());
        return addVertexView(vertex, x, y);
//        try {
//            vertexView = new SimpleTaskVertexView();
//        } catch (VertexViewException ex) {
//            Logger.getLogger(SimpleTaskController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (vertexView != null) {
//            vertexView.setLayoutX(x);
//            vertexView.setLayoutY(y);
//
//            vertexView.setUserData(vertex);
//            graphView.getChildren().add(vertexView);
//
//            if (lastVertexView != null) {
//                connect(lastVertexView, vertexView);
//            }
//            lastVertexView = vertexView;
//        }
    }

    @Override
    public SimpleTaskVertexView addVertexView(CompositeVertex _vertex) {
        if (lastVertexView == null) {
            return addVertexView(_vertex, FIRST_VERTEX_POSITION_X, FIRST_VERTEX_POSITION_Y);
        } else {
            Point2D loc = getNextLocation();
            return addVertexView(_vertex, loc.getX(), loc.getY());
        }
    }

    @Override
    protected SimpleTaskVertexView addVertexView(CompositeVertex _vertex, double x, double y) {
        SimpleTaskVertexView vertexView = null;
        try {
            vertexView = new SimpleTaskVertexView();
        } catch (VertexViewException ex) {
            Logger.getLogger(SimpleTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (vertexView != null) {
            vertexView.setLayoutX(x);
            vertexView.setLayoutY(y);

            vertexView.setUserData(_vertex);
            graphView.getChildren().add(vertexView);
            graphView.layout();
            if (lastVertexView != null) {
                connectVertexViews(lastVertexView, vertexView);
            }
            lastVertexView = vertexView;
        }
        return vertexView;
    }

    @Override
    public void editVertexProperties(AbstractVertexView _vertexView) {
        CompositeVertex vertex = (CompositeVertex) _vertexView.getUserData();
        SimpleTaskVertexView svView = (SimpleTaskVertexView) _vertexView;
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
            graphView.getChildren().add(edgeView);

        }

    }

    private void connectVertexViews(AbstractVertexView _vView1, AbstractVertexView _vView2) {
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
            graphView.getChildren().add(edgeView);
            graphView.layout();
        }
    }

//    @Override
//    public void newGraph() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
    @Override
    protected Point2D getNextLocation() {
        System.out.println("GraphView width is : " + graphView.getWidth());
        double xLoc = lastVertexView.getBoundsInParent().getMaxX() + X_LOCATION_OFFSET;
        double yLoc = lastVertexView.getBoundsInParent().getMinY();
        // check to see if we can fit another vertex along the x axis
        if (xLoc > (graphView.getWidth() - lastVertexView.getWidth() - X_LOCATION_OFFSET)) {
            xLoc = FIRST_VERTEX_POSITION_X;
            yLoc = lastVertexView.getBoundsInParent().getMaxY() + Y_LOCATION_OFFSET;
        }
        System.out.println(" new loc is " + xLoc + ", " + yLoc);
        return new Point2D(xLoc, yLoc);
    }

    @Override
    public void reconstituteGraphView() {
        List<Vertex> vList = CompositeGraph.getGraphVertices();
        SimpleTaskVertexView stvView;
        for (Vertex v : vList) {
            stvView = addVertexView(new SimpleTaskVertex(v));
            stvView.setVisualAttributes();
        }
    }
    
}
