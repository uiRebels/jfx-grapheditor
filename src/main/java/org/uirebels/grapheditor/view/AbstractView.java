/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import org.uirebels.grapheditor.interfaces.viewmodel.ViewModel;

/**
 *
 * @author bnamestka
 */
public class AbstractView extends Group {

    FXMLLoader fxmlLoader;

    public AbstractView() {
    }

    public AbstractView(String _fxmlFileName) {
        String fxmlViewPath = "/fxml/" + _fxmlFileName + ".fxml";
        fxmlLoader = new FXMLLoader(getClass().getResource(fxmlViewPath));
        setUpLoader();
    }


    // instance variables and methods NOT defined by SceneBuilder
    ViewModel viewModel;

    public void setViewModel(ViewModel _viewModel) {
        viewModel = _viewModel;
    }

    public Group getGraphic() {
        return new Group();
    }

    private void setUpLoader() {
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }

}
