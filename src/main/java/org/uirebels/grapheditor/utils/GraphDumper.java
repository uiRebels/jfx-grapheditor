/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.uirebels.grapheditor.model.CompositeGraph;

/**
 *
 * @author bnamestka
 */
public class GraphDumper {
    
    public static void dump(){
        try {
            Graph graph = CompositeGraph.getGraph();
            graph.io(IoCore.graphml()).writeGraph("tinkerpop-out.xml");
        } catch (IOException ex) {
            Logger.getLogger(GraphDumper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
