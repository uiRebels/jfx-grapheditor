/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uirebels.grapheditor.exceptions;

import org.uirebels.grapheditor.constants.StringConstant;

/**
 *
 * @author bnamestka
 */
public class JFXEditorException extends Exception {

//    private static final long serialVersionUID = 1L;

    private Throwable cause = null;

    public JFXEditorException() {
        super();
    }

    public JFXEditorException(String message) {
        super(message);
    }

    public JFXEditorException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public JFXEditorException(String _errorMsgString, String _correctiveHintString, Throwable _cause) {
        super(_errorMsgString + StringConstant.COLON + _correctiveHintString, _cause);
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    @Override
    public void printStackTrace() {
        if (cause != null) {
            System.err.println("Caused by:");
        }
    }

    @Override
    public void printStackTrace(java.io.PrintStream ps) {
        super.printStackTrace(ps);
        if (cause != null) {
            ps.println("Caused by:");
            cause.printStackTrace(ps);
        }
    }

    @Override
    public void printStackTrace(java.io.PrintWriter pw) {
        super.printStackTrace(pw);
        if (cause != null) {
            pw.println("Caused by:");
            cause.printStackTrace(pw);
        }
    }
}
