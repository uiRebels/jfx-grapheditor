/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.view;

/**
 *
 * @author bnamestka
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.uirebels.grapheditor.exceptions.VertexViewException;
import org.uirebels.grapheditor.viewmodel.AbstractViewModel;

public class SimpleFXMLVertexView extends AbstractVertexView {

    private static final String FXML_VIEW_PATH = "/fxml/SimpleVertexView.fxml";
    private static final String CSS_STYLE_PATH = "/styles/SimpleVertexViewStyle.css";

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="vertexVBox"
    private VBox vertexVBox; // Value injected by FXMLLoader

    @FXML // fx:id="bannerGridPane"
    private GridPane bannerGridPane; // Value injected by FXMLLoader

    @FXML // fx:id="vertexNameLabel"
    private Label vertexNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="vertexEditLabel"
    private Label vertexEditLabel; // Value injected by FXMLLoader

    @FXML // fx:id="vertexCloseLabel"
    private Label vertexCloseLabel; // Value injected by FXMLLoader

    @FXML // fx:id="vertexTextArea"
    private TextArea vertexTextArea; // Value injected by FXMLLoader

    public SimpleFXMLVertexView() throws VertexViewException {
        this(null);
    }

    public SimpleFXMLVertexView(AbstractViewModel _viewModel) throws VertexViewException {
        URL fxmlURL = this.getClass().getResource(FXML_VIEW_PATH);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlURL);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new VertexViewException(FXML_VIEW_PATH);
        }
        if (_viewModel != null) {
            setViewModel(_viewModel);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        vertexEditLabel.setOnMouseClicked((MouseEvent event) -> {
            event.consume();
            editVertex();
        });
        vertexCloseLabel.setOnMouseClicked((MouseEvent event) -> {
            event.consume();
            deleteVertex();
        });
    }

    // instance variables and methods NOT defined by SceneBuilder
//    private static ViewModel viewModel;
    public void setNameText(String _textString) {
        vertexNameLabel.setText(_textString);
    }

    public void setDescriptionText(String _textString) {
        vertexTextArea.setText(_textString);
    }

}
