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
import org.uirebels.grapheditor.constants.ConfigurationConstant;
import org.uirebels.grapheditor.constants.StringConstant;
import org.uirebels.grapheditor.controller.AbstractGraphController;

/**
 *
 * @author bnamestka
 */
public class SaveGraphSystemFileDialog {
    
    public static void pop(AbstractGraphController _graphController) {
        String graphFileName = _graphController.getGraphModel().getGraphName() + StringConstant.DOT_JSON;
        File initialDir = new File(ConfigurationConstant.DATA_PATH);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("jfx-grapheditor Save graph");
        fileChooser.setInitialDirectory(initialDir);
        if (graphFileName != null) {
            fileChooser.setInitialFileName(graphFileName);
        } 
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Graph files", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);        
        File selectedFile = fileChooser.showSaveDialog(_graphController.getGraphView().getScene().getWindow());
        try {
            System.out.println("Selected Save file is: " + selectedFile.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(SaveGraphSystemFileDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
