/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model;

import org.uirebels.grapheditor.model.graph.AbstractVertexModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.ElementHelper;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.shaded.jackson.core.JsonProcessingException;
import org.apache.tinkerpop.shaded.jackson.databind.ObjectMapper;
import org.uirebels.grapheditor.constants.ConfigurationConstant;
import org.uirebels.grapheditor.constants.StringConstant;

/**
 *
 * @author bnamestka
 */
public class ContextModel {

    private static Graph GRAPH;
    private static Map<String, Object> graphAttributeMap = new HashMap<>();
    private static Vertex lastVertex;

    /**
     *
     * The purpose of this class is to keep the user's domain model (for now, a
     * simple Map<String, Object> object model) and the tinkerpop property model
     * in sync.
     *
     */
    public ContextModel() {
        GRAPH = createGraph();
    }

    /**
     *
     * @param _graphAttributeMap
     * @param _defaultAttributeMap
     */
    public ContextModel(Map<String, Object> _graphAttributeMap) {
        GRAPH = createGraph();
        setGraphAttributes(_graphAttributeMap);
    }

    public Map<String, Object> setGraphAttributes(Map<String, Object> _graphAttributeMap) {
        graphAttributeMap.clear();
        _graphAttributeMap.keySet().stream().forEach((key) -> {
            graphAttributeMap.put(key, _graphAttributeMap.get(key));
            GRAPH.variables().set(key, _graphAttributeMap.get(key));
        });
        return graphAttributeMap;
    }

    public final Graph createGraph() {
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

//    public final Graph openGraph(String _name){     }    
//    public final void saveGraph(){      }
//    public final void saveGraphAs(String _name){      }
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

    /**
     *
     * @return
     */
    public String getJsonString() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(graphAttributeMap);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ContextModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonString;
    }

    public Map<String, Object> getProperties(Element _element) {
        return ElementHelper.propertyValueMap(_element);
    }

    public void setProperties(Element _element, Map<String, Object> _propertyMap) {
        _propertyMap.keySet().stream().forEach((key) -> {
            _element.property(key, _propertyMap.get(key));
        });
    }

    public Vertex getLastVertex() {
        return lastVertex;
    }

    /**
     *
     * @param _vertexModel
     * @return
     */
    public Vertex addVertex(AbstractVertexModel _vertexModel) {
        Vertex v = GRAPH.addVertex(T.label, _vertexModel.getName());
        v = _vertexModel.initializeVertex(v);
        lastVertex = v;
        return v;
    }

    /**
     *
     * @param _vertex
     * @param _newProperties
     * @return
     */
    public Vertex updateVertex(Vertex _vertex, Map<String, Object> _newProperties) {
        Set<String> vertexPropertyKeys = _vertex.keys();
        for (String propName : _newProperties.keySet()) {
            // update tinkerpop vertex property
            if (vertexPropertyKeys.contains(propName)) {
                _vertex.property(propName, _newProperties.get(propName));
            }
        }
        return _vertex;
    }

    public void deleteVertex(Vertex _vertex) {
        _vertex.remove();
//        attributeMap.clear();
    }

    public Edge connect(Vertex _v1, Vertex _v2) {
        Edge addedEdge = _v1.addEdge(StringConstant.NEXT, _v2);
        return addedEdge;
    }

    public void deleteEdge(Edge _edge) {
        _edge.remove();
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
}
