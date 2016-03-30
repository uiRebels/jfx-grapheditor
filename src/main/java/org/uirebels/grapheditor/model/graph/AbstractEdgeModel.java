/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.shaded.jackson.core.JsonProcessingException;
import org.apache.tinkerpop.shaded.jackson.databind.ObjectMapper;
import org.uirebels.grapheditor.constants.StringConstant;

/**
 *
 * @author bnamestka
 */
public class AbstractEdgeModel {

    private final Edge edge;
    private final Map<String, Object> attributeMap = new HashMap<>();

    /**
     *
     * The purpose of this class is to keep the user's domain model (for now, a
     * simple Map<String, Object> object model) and the tinkerpop property model in sync.
     *
     */
    public AbstractEdgeModel(Edge _edge) {
        edge = _edge;
    }

    /**
     *
     * @param _graphAttributeMap
     * @param _defaultAttributeMap
     */
    public AbstractEdgeModel(Edge _edge, Map<String, Object> _defaultAttributeMap) {
        edge = _edge;
        setEdgeAttributes(_defaultAttributeMap);
    }

    private Map<String, Object> setEdgeAttributes(Map<String, Object> _attributeMap) {
        Map<String, Object> attrMap = new HashMap<>();
        _attributeMap.keySet().stream().forEach((key) -> {
            attrMap.put(key, _attributeMap.get(key));
            edge.property(key, attributeMap.get(key));
        });
        return attrMap;
    }

//    /**
//     *
//     * @return
//     */
//    public Map<String, Object> getAttributes() {
//        return attributeMap;
//    }
//
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
//            Logger.getLogger(AbstractEdgeModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return jsonString;
//    }
//
//
//    /**
//     *
//     * @param _edge
//     * @param _newProperties
//     * @return
//     */
//    public Edge updateEdge(Edge _edge, HashMap<String, Object> _newProperties) {
//        Set<String> edgePropertyKeys = _edge.keys();
//        for (String propName : _newProperties.keySet()) {
//            // update tinkerpop edge property
//            if (edgePropertyKeys.contains(propName)) {
//                _edge.property(propName, _newProperties.get(propName));
//            }
//            // update domain object attribute
//            if (attributeMap.containsKey(propName)) {
//                attributeMap.put(propName, _newProperties.get(propName));
//            }
//        }
//        return _edge;
//    }
//
//    public void deleteEdge(Edge _edge) {
//        _edge.remove();
//        attributeMap.clear();
//    }
//    
//    public Edge connect(Vertex _v1, Vertex _v2) {
//        Edge addedEdge = _v1.addEdge(StringConstant.NEXT, _v2);
//        return addedEdge;
//    }

}
