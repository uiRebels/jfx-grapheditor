/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.experimental.bobs.model;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tinkerpop.shaded.jackson.core.JsonProcessingException;
import org.apache.tinkerpop.shaded.jackson.databind.ObjectMapper;
import org.uirebels.grapheditor.exceptions.model.DomainObjectAttributeNotFoundException;

/**
 *
 * @author bnamestka
 */
public class AbstractDomainModel {

    private final ObjectMapper mapper = new ObjectMapper();
    ;
    private final Map<String, Object> attributeMap = new HashMap<>();
    private String name;
    private String jsonString;

    public AbstractDomainModel() {
    }

    public void update(String _key, Object _value) throws DomainObjectAttributeNotFoundException {
        if (attributeMap.containsKey(_key)) {
            attributeMap.put(_key, _value);
        } else {
            throw new DomainObjectAttributeNotFoundException(name, _key);
        }
    }

    public void update(HashMap<String, Object> _updateMap) throws DomainObjectAttributeNotFoundException {
        for (String key : _updateMap.keySet()) {
            if (attributeMap.containsKey(key)) {
                attributeMap.put(key, _updateMap.get(key));
            } else {
                throw new DomainObjectAttributeNotFoundException(name, key);
            }
        }
    }

    public String getJsonString() {
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(attributeMap);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(AbstractDomainModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonString;
    }

}
