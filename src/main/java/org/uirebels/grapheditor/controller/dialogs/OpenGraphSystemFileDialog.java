/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller.dialogs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import org.uirebels.grapheditor.controller.AbstractGraphController;

/**
 *
 * @author bnamestka
 */
public class OpenGraphSystemFileDialog {
    
    public static void pop(AbstractGraphController _graphController) {
//        String graphFileName = _graphController.getGraphModel().getGraphName() + StringConstant.DOT_JSON;
        File initialDir = new File(_graphController.getSavePath());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("jfx-grapheditor Open graph");
        fileChooser.setInitialDirectory(initialDir);
//        if (graphFileName != null) {
//            fileChooser.setInitialFileName(graphFileName);
//        } 
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Graph files", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);        
        File selectedFile = fileChooser.showOpenDialog(_graphController.getGraphView().getScene().getWindow());
        try {
            System.out.println("Selected Open file is: " + selectedFile.getCanonicalPath());
            _graphController.openGraph(selectedFile.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(OpenGraphSystemFileDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
