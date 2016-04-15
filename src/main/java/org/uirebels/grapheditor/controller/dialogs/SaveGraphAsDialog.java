/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller.dialogs;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.uirebels.grapheditor.constants.ConfigurationConstant;
import org.uirebels.grapheditor.model.CompositeGraph;

/**
 *
 * @author bnamestka
 */
public class SaveGraphAsDialog {

    public static void pop(String _graphName) {

        Dialog<String> dialog = new Dialog<>();
//        final TextField txUserName = new TextField();
//        final PasswordField txPassword = new PasswordField();
        final GridPane content = new GridPane();
        content.setHgap(10);
        content.setVgap(10);

        content.add(new Label(ConfigurationConstant.ELEMENT_NAME_KEY), 0, 0);
        TextField nameField = new TextField();
        nameField.setText(_graphName);
        content.add(nameField, 1, 0);

        dialog.setResizable(false);
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(null);
//        dialog.setResult(txUserName + ":" + txPassword);

        Optional<String> response = dialog.showAndWait();
        if (response.toString().contains("OK")) {
//            System.out.println(" ***>>>> Fields are:");
            String enteredName = nameField.getText();
            if (!enteredName.isEmpty()) {
               CompositeGraph.setGraphName(enteredName);
            }
        }

    }

}
