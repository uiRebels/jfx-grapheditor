/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.structure.util.ElementHelper;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.uirebels.grapheditor.constants.ConfigurationConstant;
import org.uirebels.grapheditor.constants.StringConstant;
import org.uirebels.grapheditor.controller.AbstractGraphController;

/**
 *
 * @author bnamestka
 */
public class CompositeGraph {

    private static Graph GRAPH;
    private static CompositeVertex lastVertex;
    // following is the javafx ObservableMap which contains the tinkerpop properties 
    private final ObservableMap<String, Object> observablePropertyMap;

    /**
     *
     * The purpose of this class is to keep the user's domain model (for now, a
     * simple Map<String, Object> object model) and the tinkerpop property model
     * in sync.
     *
     */
    public CompositeGraph() {
        this(null);
    }

    /**
     *
     * @param _graphAttributeMap
     */
    public CompositeGraph(Map<String, Object> _graphAttributeMap) {
        GRAPH = createGraph();
        observablePropertyMap = FXCollections.observableHashMap();
        if (_graphAttributeMap != null) {
            setGraphAttributes(_graphAttributeMap);
        }
    }

    public void setGraphAttribute(String _key, Object _attribute) {
        GRAPH.variables().set(_key, _attribute);
        observablePropertyMap.put(_key, _attribute);
    }

    public final void setGraphAttributes(Map<String, Object> _graphAttributeMap) {
        observablePropertyMap.clear();
        clearTinkerpopProperties();
        _graphAttributeMap.keySet().stream().forEach((key) -> {
            observablePropertyMap.put(key, _graphAttributeMap.get(key));
            GRAPH.variables().set(key, _graphAttributeMap.get(key));
        });
    }

    public static final Graph createGraph() {
        Graph g = null;
        switch (ConfigurationConstant.GRAPH_TYPE) {
            case TINKERGRAPH:
                g = TinkerGraph.open();
                break;
//            case TITAN:
//               NEED proper configuration params for TitanFactory   
//                g = TitanFactory.open();
//                break;
        }
        return g;
    }

    public static CompositeGraph openGraph(String _graphFilePath) {
        CompositeGraph newGraph = new CompositeGraph();
        try {
            GRAPH.io(IoCore.graphson()).readGraph(_graphFilePath);
            int firstCharPos = _graphFilePath.lastIndexOf(StringConstant.SLASH) + 1;
            int lastCharPos = _graphFilePath.indexOf(StringConstant.DOT);
            setGraphName(_graphFilePath.substring(firstCharPos, lastCharPos));
        } catch (IOException ex) {
            Logger.getLogger(AbstractGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newGraph;
    }

    public static void saveGraph(String _dirPath) {
        try {
            GRAPH.io(IoCore.graphson()).writeGraph(_dirPath + StringConstant.SLASH + getGraphName() + StringConstant.DOT_JSON);
        } catch (IOException ex) {
            Logger.getLogger(AbstractGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveGraphAs(String _dirPath, String _graphName) {
        try {
            GRAPH.io(IoCore.graphson()).writeGraph(_dirPath + StringConstant.SLASH + _graphName + StringConstant.DOT_JSON);
        } catch (IOException ex) {
            Logger.getLogger(AbstractGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public final void closeGraph(){   }
//    public final void deleteGraph(){   }
    public static Graph getGraph() {
        return GRAPH;
    }

    public static void setGraph(Graph _graph) {
        GRAPH = _graph;
    }

    public static String getGraphName() {
        return (String) GRAPH.variables().asMap().get(ConfigurationConstant.ELEMENT_NAME_KEY);
    }

    public static void setGraphName(String _name) {
        GRAPH.variables().set(ConfigurationConstant.ELEMENT_NAME_KEY, _name);
    }

    public static List<Vertex> getGraphVertices() {
        List<Vertex> vertexList = new ArrayList<>();
        Iterator<Vertex> vIter = GRAPH.vertices();
        while (vIter.hasNext()) {
            vertexList.add(vIter.next());
        }
        return vertexList;
    }

    /**
     *
     * @return
     */
//    public String getJsonString() {
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = null;
//        try {
//            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(graphAttributeMap);
//        } catch (JsonProcessingException ex) {
//            Logger.getLogger(CompositeGraph.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return jsonString;
//    }

    public Map<String, Object> getProperties(Element _element) {
        return ElementHelper.propertyValueMap(_element);
    }

    public void setProperties(Element _element, Map<String, Object> _propertyMap) {
        _propertyMap.keySet().stream().forEach((key) -> {
            _element.property(key, _propertyMap.get(key));
        });
    }

    public CompositeVertex getLastVertex() {
        return lastVertex;
    }

    /**
     *
     * @param _vertexModel
     * @return
     */
    public CompositeVertex addVertex(CompositeVertex _vertex) {
        Vertex tinkerpopVertex = GRAPH.addVertex(T.label, _vertex.getClass().getSimpleName());
        _vertex.initializeVertex(tinkerpopVertex);
        lastVertex = _vertex;
        return _vertex;
    }

    /**
     *
     * @param _vertex
     * @param _newProperties
     */
    public void updateVertex(CompositeVertex _vertex, Map<String, Object> _newProperties) {
        _vertex.update(_newProperties);
    }

    public void deleteVertex(CompositeVertex _vertex) {
        _vertex.delete();
    }

    public CompositeEdge connect(CompositeVertex _absV1, CompositeVertex _absV2) {
        Vertex v1 = _absV1.getVertex();
        Vertex v2 = _absV2.getVertex();
        Edge tinkerpopEdge = v1.addEdge(StringConstant.NEXT, v2);
//        CompositeEdge addedEdge = new CompositeEdge(tinkerpopEdge);
        CompositeEdge addedEdge = new CompositeEdge();
        addedEdge.initializeEdge(tinkerpopEdge);
        return addedEdge;
    }

    public void deleteEdge(CompositeEdge _edge) {
        _edge.delete();
    }

    public Edge updateEdge(Edge _edge, HashMap<String, Object> _newProperties) {
        Set<String> edgePropertyKeys = _edge.keys();
        for (String propName : _newProperties.keySet()) {
            // update tinkerpop edge properties
            if (edgePropertyKeys.contains(propName)) {
                _edge.property(propName, _newProperties.get(propName));
            }
        }
        return _edge;
    }

    private void clearTinkerpopProperties() {
        Set<String> keys = GRAPH.variables().keys();
        for (String key : keys) {
            GRAPH.variables().remove(key);
        }
    }
}
