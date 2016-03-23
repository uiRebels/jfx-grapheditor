/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.exceptions;

/**
 *
 * @author bnamestka
 */
public class EdgeViewException extends JFXEditorException {

    public EdgeViewException(String _fxmlName) {
        super("Can't instantiate Edge view from FXML file: "+ _fxmlName);
    }

}
