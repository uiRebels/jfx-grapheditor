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
import javafx.stage.Stage;

public class JFXGraphEditorViewController {

    private AbstractGraphController graphController;

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
    public Pane graphViewPane; // Value injected by FXMLLoader

    
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
        Stage stage = (Stage) graphViewPane.getScene().getWindow();
        stage.close();
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
       
        graphViewPane.addEventFilter(MouseEvent.MOUSE_CLICKED, (final MouseEvent mouseEvent) -> {
            if (mouseEvent.getTarget() == graphViewPane) {
                graphController.addVertex(mouseEvent.getX(), mouseEvent.getY());
                mouseEvent.consume();
            }
        });

    }
    
    public void setGraphController(AbstractGraphController _graphController) {
        graphController = _graphController;
        graphController.setGraphView(graphViewPane);
    }

}
