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
import javafx.scene.shape.Line;
import org.uirebels.grapheditor.exceptions.EdgeViewException;
import org.uirebels.grapheditor.viewmodel.AbstractViewModel;

public class SimpleEdgeView extends AbstractEdgeView {

    private static final String FXML_VIEW_PATH = "/fxml/SimpleEdgeView.fxml";

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Line edgeLine;

    public SimpleEdgeView() throws EdgeViewException {
        this(null);
    }

    public SimpleEdgeView(AbstractViewModel _viewModel) throws EdgeViewException {
        URL fxmlURL = this.getClass().getResource(FXML_VIEW_PATH);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlURL);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
//            exception.printStackTrace();
            throw new EdgeViewException(FXML_VIEW_PATH);
        }
        if (_viewModel != null) {
            setViewModel(_viewModel);
        }
    }

    @FXML
    void initialize() {
    }

    @Override
    public void bindEndPoints(AbstractVertexView vView1, AbstractVertexView vView2) {
        // binding
        edgeLine.startXProperty().bind(vView1.outXProperty());
        edgeLine.startYProperty().bind(vView1.outYProperty());
        edgeLine.endXProperty().bind(vView2.inXProperty());
        edgeLine.endYProperty().bind(vView2.inYProperty());
    }

}
