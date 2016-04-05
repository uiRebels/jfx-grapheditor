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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import org.uirebels.grapheditor.viewmodel.AbstractViewModel;

public abstract class AbstractEdgeView extends Group {

    private static double XOFFSET = 20.0;
    private static AbstractViewModel viewModel = null;
//    private final ArrayList endPoints = new ArrayList();

    public static void setViewModel(AbstractViewModel _viewModel) {
        viewModel = _viewModel;
    }

    public void deleteEdge() {
        viewModel.deleteEdge(this);
    }

//    public List getEndPoints(){
//        return endPoints;
//    }
    
    public List getEndPoints(AbstractVertexView vView1, AbstractVertexView vView2) {
        ArrayList endPoints = new ArrayList();
        Bounds v1Bounds = vView1.getBoundsInParent();
        Bounds v2Bounds = vView2.getBoundsInParent();
        
        double ctrX1 = v1Bounds.getMinX() + (v1Bounds.getWidth() * 0.5);
        double ctrY1 = v1Bounds.getMinY() + (v1Bounds.getHeight() * 0.5);
        double ctrX2 = v2Bounds.getMinX() + (v2Bounds.getWidth() * 0.5);
        double ctrY2 = v2Bounds.getMinY() + (v2Bounds.getHeight() * 0.5);

        double slope = (ctrY2 - ctrY1) / (ctrX2 - ctrX1);
        double yint1 = ctrY1 - (slope * ctrX1);
        double yint2 = ctrY2 - (slope * ctrX2);
        
        double x1 = v1Bounds.getMinX() + v1Bounds.getWidth() + XOFFSET;
        double y1 = slope * x1 + yint1;
        double x2 = v2Bounds.getMinX() - XOFFSET;
        double y2 = slope * x2 + yint2;
        
        endPoints.addAll(Arrays.asList(x1, y1, x2, y2));
        return endPoints;
    }
    
    protected void setXOffset(double _offset){
        XOFFSET =  _offset;
    }

}
