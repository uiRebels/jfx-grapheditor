/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.uirebels.grapheditor.constants.ConfigurationConstant;

/**
 *
 * @author bnamestka
 */
public abstract class CompositeVertex {

    // only used by the subclass to initialize the tinkerpopPropertyMap
    protected static final HashMap<String, Object> ATTRIBUTE_MAP = new HashMap<>();

    private Vertex vertex;
    private final Map<String, Object> tinkerpopPropertyMap;
    // following is the javafx ObservableMap which contains the tinkerpop properties 
    private final ObservableMap<String, Object> observablePropertyMap;

    /**
     *
     * The purpose of this class is INITIALIZE a tinkerpop vertex property model
     * from a user's domain model. ALL operations (including data reads &
     * updates will go against the Tinkerpop properties of the vertex (NOT the
     * user's domain attribute map).
     *
     */
    /**
     *
     */
    public CompositeVertex() {
        tinkerpopPropertyMap = new HashMap<>(ATTRIBUTE_MAP);
        observablePropertyMap = FXCollections.observableMap(tinkerpopPropertyMap);
    }

    private void initializeVertexProperties() {
//        System.out.println("-----  Setting CompositeVertex properties ---------");
        tinkerpopPropertyMap.keySet().stream().forEach((String key) -> {
            vertex.property(key, tinkerpopPropertyMap.get(key));
        });
    }

    public void initializeVertex(Vertex _vertex) {
        vertex = _vertex;
        initializeVertexProperties();
    }

    public void update(Map<String, Object> _attrMap) {
        // keeps both JavaFX map and tinkerpop vertex properties in sync
        Set<String> vertexPropertyKeys = vertex.keys();
        for (String propName : _attrMap.keySet()) {
            // update tinkerpop vertex property
            if (vertexPropertyKeys.contains(propName)) {
                VertexProperty vProp = vertex.property(propName, _attrMap.get(propName));
                observablePropertyMap.put(propName, _attrMap.get(propName));
            }
        }
    }

    public void delete() {
        // remove all bindings
        // remove vertex from graph
        vertex.remove();
    }

    public String getName() {
        return (String) tinkerpopPropertyMap.get(ConfigurationConstant.ELEMENT_NAME_KEY);
//        return (String) vertex.value(ConfigurationConstant.ELEMENT_NAME_KEY);
    }

    public Vertex getVertex() {
        return vertex;
    }

    public List<Edge> getInEdges() {
        return getEdges(Direction.IN);
    }

    public List<Edge> getOutEdges() {
        return getEdges(Direction.OUT);
    }
    
    private List<Edge> getEdges(Direction _dir) {
        List<Edge> edgeList = new ArrayList<>();
        Iterator<Edge> inIter = vertex.edges(_dir);
        while (inIter.hasNext()) {
            edgeList.add(inIter.next());
        }
        return edgeList;
    }

    public Map<String, Object> getPropertiesMap() {
        Map<String, Object> propMap = new HashMap<>();
        Set<String> propKeys = vertex.keys();
        propKeys.stream().forEach((key) -> {
            propMap.put(key, vertex.property(key).value());
        });
        return propMap;
    }

    public ObservableMap<String, Object> getPropertiesAsObservableMap() {
        return observablePropertyMap;
    }
//
//    /**
//     *
//     * @return
//     */
//    public String getJsonString() {
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = null;
//        try {
//            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(attributeMap);
//        } catch (JsonProcessingException ex) {
//            Logger.getLogger(CompositeVertex.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return jsonString;
//    }
//
//
//
}
