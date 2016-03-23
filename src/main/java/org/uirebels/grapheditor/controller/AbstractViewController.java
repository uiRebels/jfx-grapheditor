/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller;

import javafx.scene.Group;
import org.uirebels.grapheditor.interfaces.viewmodel.ViewModel;

/**
 *
 * @author bnamestka
 */
public class AbstractViewController {
    
        // instance variables and methods NOT defined by SceneBuilder
    ViewModel viewModel;

    public void setViewModel(ViewModel _viewModel) {
        viewModel = _viewModel;
    }
    
    public Group getGraphic(){
        return new Group();
    }
    
}
