/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.model;

import org.uirebels.grapheditor.model.graph.AbstractVertexModel;
import java.util.HashMap;
import org.uirebels.grapheditor.constants.ConfigurationConstant;

/**
 *
 * @author bnamestka
 */
public class SimpleTaskModel extends AbstractVertexModel {

    private static final HashMap<String, Object> ATTRIBUTE_MAP = new HashMap<>();

    static {
        ATTRIBUTE_MAP.put(ConfigurationConstant.ELEMENT_NAME_KEY, "Task");
        ATTRIBUTE_MAP.put("Description", "Simple description");
    }

    public SimpleTaskModel() {
        super(ATTRIBUTE_MAP);
    }

}
