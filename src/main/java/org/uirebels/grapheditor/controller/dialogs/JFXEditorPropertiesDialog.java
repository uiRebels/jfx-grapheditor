/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller.dialogs;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.uirebels.grapheditor.constants.ConfigurationConstant;
import org.uirebels.grapheditor.model.CompositeVertex;

/**
 *
 * @author bnamestka
 */
public class JFXEditorPropertiesDialog {

    public static void pop(CompositeVertex _vertex) {

        Dialog<String> dialog = new Dialog<>();
        final TextField txUserName = new TextField();
        final PasswordField txPassword = new PasswordField();
        final GridPane content = new GridPane();
        content.setHgap(10);
        content.setVgap(10);
        Map<String, Object> vertexProperties = _vertex.getPropertiesMap();
        // "Name" is an integral part of a Tinkerpop Element (as the Element.label)
        //  this is also included in the property map, so for this use case, temporarily
        // remove from the property map.
        if(vertexProperties.containsKey(ConfigurationConstant.ELEMENT_NAME_KEY)){
            vertexProperties.remove(ConfigurationConstant.ELEMENT_NAME_KEY);
        }
        String name = _vertex.getName();
        content.add(new Label(ConfigurationConstant.ELEMENT_NAME_KEY), 0, 0);
        TextField tField = new TextField();
        tField.setUserData(ConfigurationConstant.ELEMENT_NAME_KEY);
        tField.setText(name);
        content.add(tField, 1, 0);
        int row = 1;
        for (String key : vertexProperties.keySet()) {
            content.add(new Label(key), 0, row);
            tField = new TextField();
            tField.setUserData(key);
            tField.setText((String) vertexProperties.get(key));
            content.add(tField, 1, row);
            GridPane.setHgrow(tField, Priority.ALWAYS);
            row++;
        }

        dialog.setResizable(false);
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(null);
        dialog.setResult(txUserName + ":" + txPassword);

        Optional<String> response = dialog.showAndWait();
        if (response.toString().contains("OK")) {
//            System.out.println(" ***>>>> Fields are:");
            Map<String, Object> editedAttrs = new HashMap<>();
            for (Node child : content.getChildrenUnmodifiable()) {
                if (child instanceof TextField) {
//                    System.out.println(child.getUserData() + ":" + ((TextField) child).getText());
                    editedAttrs.put((String)child.getUserData(), ((TextField) child).getText());
                }
            }
            _vertex.update(editedAttrs);
        }

    }

}
