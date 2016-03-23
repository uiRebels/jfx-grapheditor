package org.uirebels.grapheditor.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class JFXGraphEditorViewController  extends AbstractViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="graphEditorView"
    private BorderPane graphEditorView; // Value injected by FXMLLoader

    @FXML // fx:id="menuBar"
    private MenuBar menuBar; // Value injected by FXMLLoader

    @FXML // fx:id="fileMenu"
    private Menu fileMenu; // Value injected by FXMLLoader

    @FXML // fx:id="newGraphEditorMenuItem"
    private MenuItem newGraphEditorMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="saveGraphEditorMenuItem"
    private MenuItem saveGraphEditorMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="saveAsGraphEditor"
    private MenuItem saveAsGraphEditor; // Value injected by FXMLLoader

    @FXML // fx:id="closeGraphEditorMenuItem"
    private MenuItem closeGraphEditorMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="exitGraphEditorMenuItem"
    private MenuItem exitGraphEditorMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="editMenu"
    private Menu editMenu; // Value injected by FXMLLoader

    @FXML // fx:id="undoOperationMenuItem"
    private MenuItem undoOperationMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="redoOperationMenuItem"
    private MenuItem redoOperationMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="copyOperationMenuItem"
    private MenuItem copyOperationMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="cutOperationMenuItem"
    private MenuItem cutOperationMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="pasteOperationMenuItem"
    private MenuItem pasteOperationMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="selectAllOperationMenuItem"
    private MenuItem selectAllOperationMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="deleteOperationMenuItem"
    private MenuItem deleteOperationMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="settingsMenu"
    private Menu settingsMenu; // Value injected by FXMLLoader

    @FXML // fx:id="showGridMenuItem"
    private CheckMenuItem showGridMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="snapToGridMenuItem"
    private CheckMenuItem snapToGridMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="connectionTypeMenuItem"
    private MenuItem connectionTypeMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="nodeTypeMenuItem"
    private MenuItem nodeTypeMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="helpMenu"
    private Menu helpMenu; // Value injected by FXMLLoader

    @FXML // fx:id="graphViewScrollPane"
    private ScrollPane graphViewScrollPane; // Value injected by FXMLLoader

    @FXML // fx:id="graphViewPane"
    private Pane graphViewPane; // Value injected by FXMLLoader

    @FXML
    void addVertex(MouseEvent event) {
        viewModel.addVertex(event.getX(), event.getY());
    }

    @FXML
    void closeGraphEditor(ActionEvent event) {

    }

    @FXML
    void copyOperation(ActionEvent event) {

    }

    @FXML
    void cutOperation(ActionEvent event) {

    }

    @FXML
    void deleteOperation(ActionEvent event) {

    }

    @FXML
    void exitGraphEditor(ActionEvent event) {

    }

    @FXML
    void helpMenu(ActionEvent event) {

    }

    @FXML
    void newGraphEditor(ActionEvent event) {

    }

    @FXML
    void pasteOperation(ActionEvent event) {

    }

    @FXML
    void redoOperation(ActionEvent event) {

    }

    @FXML
    void saveAsGraphEditorMenuItem(ActionEvent event) {

    }

    @FXML
    void saveGraphEditor(ActionEvent event) {

    }

    @FXML
    void selectAllOperation(ActionEvent event) {

    }

    @FXML
    void toggleGridVisibility(ActionEvent event) {

    }

    @FXML
    void toggleSnapToGrid(ActionEvent event) {

    }

    @FXML
    void undoOperation(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        viewModel.setGraphView(graphViewPane);
//        assert graphEditorView != null : "fx:id=\"graphEditorView\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert fileMenu != null : "fx:id=\"fileMenu\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert newGraphEditorMenuItem != null : "fx:id=\"newGraphEditorMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert saveGraphEditorMenuItem != null : "fx:id=\"saveGraphEditorMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert saveAsGraphEditor != null : "fx:id=\"saveAsGraphEditor\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert closeGraphEditorMenuItem != null : "fx:id=\"closeGraphEditorMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert exitGraphEditorMenuItem != null : "fx:id=\"exitGraphEditorMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert editMenu != null : "fx:id=\"editMenu\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert undoOperationMenuItem != null : "fx:id=\"undoOperationMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert redoOperationMenuItem != null : "fx:id=\"redoOperationMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert copyOperationMenuItem != null : "fx:id=\"copyOperationMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert cutOperationMenuItem != null : "fx:id=\"cutOperationMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert pasteOperationMenuItem != null : "fx:id=\"pasteOperationMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert selectAllOperationMenuItem != null : "fx:id=\"selectAllOperationMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert deleteOperationMenuItem != null : "fx:id=\"deleteOperationMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert settingsMenu != null : "fx:id=\"settingsMenu\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert showGridMenuItem != null : "fx:id=\"showGridMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert snapToGridMenuItem != null : "fx:id=\"snapToGridMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert connectionTypeMenuItem != null : "fx:id=\"connectionTypeMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert nodeTypeMenuItem != null : "fx:id=\"nodeTypeMenuItem\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert helpMenu != null : "fx:id=\"helpMenu\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert graphViewScrollPane != null : "fx:id=\"graphViewScrollPane\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";
//        assert graphViewPane != null : "fx:id=\"graphViewPane\" was not injected: check your FXML file 'JFXGraphEditorView.fxml'.";

    }    
} 