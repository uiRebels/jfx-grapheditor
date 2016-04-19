/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.constants;

/**
 *
 * @author bnamestka
 */
public class ConfigurationConstant {

    // Enum of Tinkerpop Graph Providers
    public enum GraphType {
        TINKERGRAPH,
        NEO4J,
        TITAN
    }

    public static final GraphType GRAPH_TYPE = GraphType.TINKERGRAPH;
    
    public static final String ELEMENT_NAME_KEY = "Name";
//    public static final String DATA_PATH = "data/";
    public static final String DATA_PATH = "/Users/bnamestka/graphs/";
    
}
