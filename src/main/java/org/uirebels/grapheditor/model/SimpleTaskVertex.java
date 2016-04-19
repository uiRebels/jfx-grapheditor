/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.uirebels.grapheditor.constants.ConfigurationConstant;

/**
 *
 * @author bnamestka
 */
public class SimpleTaskVertex extends CompositeVertex {

    // user domain object attibutes
    static {
        ATTRIBUTE_MAP.clear();
        ATTRIBUTE_MAP.put(ConfigurationConstant.ELEMENT_NAME_KEY, "Task Name");
        ATTRIBUTE_MAP.put("Description", "Simple description");
    }

    public SimpleTaskVertex() {
        super();
    }

    public SimpleTaskVertex(Vertex _vertex) {
        super(_vertex);
    }

}
