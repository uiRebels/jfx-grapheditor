/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.uirebels.grapheditor.constants.ConfigurationConstant;

/**
 *
 * @author bnamestka
 */
public abstract class AbstractVertex {

    // only used by the subclass to initialize the tinkerpopPropertyMap
    protected static final HashMap<String, Object> ATTRIBUTE_MAP = new HashMap<>();

    private Vertex vertex;
    private final Map<String, Object> tinkerpopPropertyMap;
    // following is the javafx ObservableMap which contains 
    private final ObservableMap<String, Object> propertyMap;

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
     * @param _defaultAttributeMap
     */
    public AbstractVertex() {
        tinkerpopPropertyMap = new HashMap<>(ATTRIBUTE_MAP);
        propertyMap = FXCollections.observableMap(tinkerpopPropertyMap);
    }

    private void setVertexProperties() {
        System.out.println("-----  Setting AbstractVertex properties ---------");
        tinkerpopPropertyMap.keySet().stream().forEach((String key) -> {
            System.out.print("Key: ");
            System.out.println(key);
            System.out.print("Value: ");
            System.out.println(tinkerpopPropertyMap.get(key));
            vertex.property(key, tinkerpopPropertyMap.get(key));
        });
    }

    public void initializeVertex(Vertex _vertex) {
        vertex = _vertex;
        setVertexProperties();
    }

    public void update(Map<String, Object> _attrMap) {
        Set<String> vertexPropertyKeys = vertex.keys();
        for (String propName : _attrMap.keySet()) {
            // update tinkerpop vertex property
            if (vertexPropertyKeys.contains(propName)) {
                VertexProperty vProp = vertex.property(propName, _attrMap.get(propName));
                propertyMap.put(propName, _attrMap.get(propName));
            }
        }
    }

    public void delete() {
        // remove all bindings
        // remove vertex from graph
        vertex.remove();
    }

    public String getName() {
        System.out.println(">>>>>>>>>>>>>>>>>> name is " + (String) tinkerpopPropertyMap.get(ConfigurationConstant.ELEMENT_NAME_KEY));
        return (String) tinkerpopPropertyMap.get(ConfigurationConstant.ELEMENT_NAME_KEY);
//        return (String) vertex.value(ConfigurationConstant.ELEMENT_NAME_KEY);
    }

    public Vertex getVertex() {
        return vertex;
    }

    public Map<String, Object> getPropertiesMap() {
        Map<String, Object> propMap = new HashMap<>();
        Set<String> propKeys = vertex.keys();
        propKeys.stream().forEach((key) -> {
            propMap.put(key, vertex.property(key).value());
        });
        return propMap;
    }

    public ObservableMap<String, Object> getPropertyMap() {
        return propertyMap;
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
//            Logger.getLogger(AbstractVertex.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return jsonString;
//    }
//
//
//
}
