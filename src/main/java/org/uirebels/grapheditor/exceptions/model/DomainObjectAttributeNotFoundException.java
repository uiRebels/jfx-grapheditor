/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.exceptions.model;

import org.uirebels.grapheditor.exceptions.*;

/**
 *
 * @author bnamestka
 */
public class DomainObjectAttributeNotFoundException extends JFXEditorException {

    public DomainObjectAttributeNotFoundException(String _domainModel, String _attrName) {
        super(_attrName + " attribute not defined for : "+ _domainModel);
    }

}
