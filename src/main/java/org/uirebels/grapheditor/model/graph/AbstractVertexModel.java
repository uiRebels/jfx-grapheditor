/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model.graph;

import java.util.HashMap;
import java.util.Map;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.uirebels.grapheditor.constants.ConfigurationConstant;

/**
 *
 * @author bnamestka
 */
public class AbstractVertexModel {

    private Vertex vertex;
    private Map<String, Object> attributeMap = new HashMap<>();

    /**
     *
     * The purpose of this class is initialize a tinkerpop vertex property model
     * from a user's domain model
     *
     */

    /**
     *
     * @param _vertex
     * @param _defaultAttributeMap
     */
    public AbstractVertexModel(Map<String, Object> _defaultAttributeMap) {
        attributeMap = _defaultAttributeMap;
    }

    private void setVertexAttributes() {
        attributeMap.keySet().stream().forEach((key) -> {
            vertex.property(key, attributeMap.get(key));
        });
    }

    public Vertex initializeVertex(Vertex _vertex) {
        vertex = _vertex;
        setVertexAttributes();
        return vertex;
    }

    public String getName() {
        return (String) attributeMap.get(ConfigurationConstant.ELEMENT_NAME_KEY);
    }

//    /**
//     *
//     * @return
//     */
//    public Map<String, Object> getAttributes() {
//        return attributeMap;
//    }
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
//            Logger.getLogger(AbstractVertexModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return jsonString;
//    }
//
//
//    /**
//     *
//     * @param _vertex
//     * @param _newProperties
//     * @return
//     */
//    public Vertex updateVertex(Vertex _vertex, HashMap<String, Object> _newProperties) {
//        Set<String> vertexPropertyKeys = _vertex.keys();
//        for (String propName : _newProperties.keySet()) {
//            // update tinkerpop vertex property
//            if (vertexPropertyKeys.contains(propName)) {
//                _vertex.property(propName, _newProperties.get(propName));
//            }
//            // update domain object attribute
//            if (attributeMap.containsKey(propName)) {
//                attributeMap.put(propName, _newProperties.get(propName));
//            }
//        }
//        return _vertex;
//    }
//
//    public void deleteVertex(Vertex _vertex) {
//        _vertex.remove();
//        attributeMap.clear();
//    }

}
