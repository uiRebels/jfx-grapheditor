/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.controller;

import org.uirebels.grapheditor.viewmodel.AbstractViewModel;

/**
 *
 * @author bnamestka
 */
public class AbstractViewController {
    
    AbstractViewModel viewModel;
    
    public void setViewModel(AbstractViewModel _viewModel) {
        viewModel = _viewModel;
    }
      
}
