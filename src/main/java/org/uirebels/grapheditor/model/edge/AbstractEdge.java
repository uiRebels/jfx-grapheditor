/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model.edge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;

/**
 *
 * @author bnamestka
 */
public class AbstractEdge {

    private Edge edge;
    private Map<String, Object> tinkerpopPropertyMap = new HashMap<>();
    private MapProperty<String, Property> mapProperty = new SimpleMapProperty<>(FXCollections.observableHashMap());

    /**
     *
     * The purpose of this class is to keep the user's domain model (for now, a
     * simple Map<String, Object> object model) and the tinkerpop property model
     * in sync.
     *
     * @param _edge
     */
    public AbstractEdge(Edge _edge) {
        edge = _edge;
    }

    /**
     *
     * @param _edge
     * @param _defaultAttributeMap
     */
    public AbstractEdge(Edge _edge, Map<String, Object> _defaultAttributeMap) {
        edge = _edge;
        tinkerpopPropertyMap = _defaultAttributeMap;
        setEdgeProperties();
    }

    private void setEdgeProperties() {
        tinkerpopPropertyMap.keySet().stream().forEach((key) -> {
            Property vProp = edge.property(key, tinkerpopPropertyMap.get(key));
            mapProperty.put(key, vProp);

        });
    }

    public void initializeEdge(Edge _edge) {
        edge = _edge;
        setEdgeProperties();
    }

    public Edge getEdge() {
        return edge;
    }

    public void update(Map<String, Object> _attributeMap) {
        Set<String> propertyKeys = edge.keys();
        for (String propName : _attributeMap.keySet()) {
            // update tinkerpop vertex property
            if (propertyKeys.contains(propName)) {
                Property prop = edge.property(propName, _attributeMap.get(propName));
                mapProperty.put(propName, prop);
            }
        }
    }

    public void delete() {
        // remove all bindings
        // remove vertex from graph
        edge.remove();
        // set refs to null
        tinkerpopPropertyMap = null;
        mapProperty = null;
    }

    public Map<String, Object> getPropertiesMap() {
        Map<String, Object> propMap = new HashMap<>();
        Set<String> propKeys = edge.keys();
        propKeys.stream().forEach((key) -> {
            propMap.put(key, edge.property(key));
        });
        return propMap;
    }

}
