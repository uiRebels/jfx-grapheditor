/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller;

/**
 * Sample Skeleton for 'SimpleTaskVertexView.fxml' Controller Class
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author bnamestka
 */
public class SimpleVertexViewController extends AbstractViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="vertexGroup"
    private Group vertexGroup; // Value injected by FXMLLoader

    @FXML // fx:id="vertexVBox"
    private VBox vertexVBox; // Value injected by FXMLLoader

    @FXML // fx:id="bannerGridPane"
    private GridPane bannerGridPane; // Value injected by FXMLLoader

    @FXML // fx:id="vertexEditLabel"
    private Label vertexEditLabel; // Value injected by FXMLLoader

    @FXML // fx:id="vertexCloseLabel"
    private Label vertexCloseLabel; // Value injected by FXMLLoader

    @FXML // fx:id="taskNameTextArea"
    private TextArea taskNameTextArea; // Value injected by FXMLLoader

    @FXML
    void deleteTask(ActionEvent event) {
        viewModel.deleteVertex(event);
    }

    @FXML
    void editTask(ActionEvent event) {
        viewModel.editVertex(event);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        viewModel.setVertexView(vertexGroup);
//        assert vertexGroup != null : "fx:id=\"vertexGroup\" was not injected: check your FXML file 'SimpleTaskVertexView.fxml'.";
//        assert vertexVBox != null : "fx:id=\"vertexVBox\" was not injected: check your FXML file 'SimpleTaskVertexView.fxml'.";
//        assert bannerGridPane != null : "fx:id=\"bannerGridPane\" was not injected: check your FXML file 'SimpleTaskVertexView.fxml'.";
//        assert vertexEditLabel != null : "fx:id=\"vertexEditLabel\" was not injected: check your FXML file 'SimpleTaskVertexView.fxml'.";
//        assert vertexCloseLabel != null : "fx:id=\"vertexCloseLabel\" was not injected: check your FXML file 'SimpleTaskVertexView.fxml'.";
//        assert taskNameTextArea != null : "fx:id=\"taskNameTextArea\" was not injected: check your FXML file 'SimpleTaskVertexView.fxml'.";

    }

    @Override
    public Group getGraphic() {
        return vertexGroup;
    }

}
