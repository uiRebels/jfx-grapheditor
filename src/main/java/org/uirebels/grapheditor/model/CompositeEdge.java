/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.uirebels.grapheditor.constants.ConfigurationConstant;

/**
 *
 * @author bnamestka
 */
public class CompositeEdge {

    // only used by the subclass to initialize the tinkerpopPropertyMap
    protected static final HashMap<String, Object> ATTRIBUTE_MAP = new HashMap<>();

    private Edge edge;
    private Map<String, Object> tinkerpopPropertyMap = new HashMap<>();
    // following is the javafx ObservableMap which contains the tinkerpop properties 
    private final ObservableMap<String, Object> observablePropertyMap;

    /**
     *
     * The purpose of this class is to keep the user's domain model (for now, a
     * simple Map<String, Object> object model) and the tinkerpop property model
     * in sync.
     *
     */
    public CompositeEdge() {
        tinkerpopPropertyMap = new HashMap<>(ATTRIBUTE_MAP);
        observablePropertyMap = FXCollections.observableMap(tinkerpopPropertyMap);
    }

    /**
     *
     * @param _edge
     */
    // used to reconstitute from a saved tinkerpop edge
    public CompositeEdge(Edge _edge) {
        edge = _edge;
        tinkerpopPropertyMap = new HashMap<>();
        for(String key : _edge.keys()){
            tinkerpopPropertyMap.put(key, edge.value(key));
        }
        observablePropertyMap = FXCollections.observableMap(tinkerpopPropertyMap);
    }

    private void initializeEdgeProperties() {
        tinkerpopPropertyMap.keySet().stream().forEach((key) -> {
            Property vProp = edge.property(key, tinkerpopPropertyMap.get(key));
        });
    }

    public void initializeEdge(Edge _edge) {
        edge = _edge;
        initializeEdgeProperties();
    }

    public Edge getEdge() {
        return edge;
    }

    public void update(Map<String, Object> _attrMap) {
        // keeps both JavaFX map and tinkerpop vertex properties in sync
        Set<String> propertyKeys = edge.keys();
        for (String propName : _attrMap.keySet()) {
            // update tinkerpop edge property
            if (propertyKeys.contains(propName)) {
                Property prop = edge.property(propName, _attrMap.get(propName));
                observablePropertyMap.put(propName, _attrMap.get(propName));
            }
        }
    }

    public void delete() {
        // remove all bindings
        // remove vertex from graph
        edge.remove();
        // set refs to null
    }

    public String getName() {
        return (String) tinkerpopPropertyMap.get(ConfigurationConstant.ELEMENT_NAME_KEY);
    }

    public Map<String, Object> getPropertiesMap() {
        Map<String, Object> propMap = new HashMap<>();
        Set<String> propKeys = edge.keys();
        propKeys.stream().forEach((key) -> {
            propMap.put(key, edge.property(key));
        });
        return propMap;
    }

    public ObservableMap<String, Object> getPropertiesAsObservableMap() {
        return observablePropertyMap;
    }

}
