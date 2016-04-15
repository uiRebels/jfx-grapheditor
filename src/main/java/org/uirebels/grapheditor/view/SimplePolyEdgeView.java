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
import java.util.List;
import javafx.scene.shape.Polyline;
import org.uirebels.grapheditor.exceptions.EdgeViewException;

public class SimplePolyEdgeView extends AbstractEdgeView {

    private final Polyline edgeLine;
    private static final double XOFFSET = 15.0;
    private final List<Double> pointList = new ArrayList<>();

    public SimplePolyEdgeView() throws EdgeViewException {
        edgeLine = new Polyline();
        getChildren().add(edgeLine);
    }


    @Override
    public void setSegmentEndPoints() {        
        edgeLine.getPoints().clear();

        double outX = sourceView.getBoundsInParent().getMaxX();
        double outY = sourceView.getBoundsInParent().getMaxY() - (sourceView.getHeight() * 0.5);
        double inX = targetView.getBoundsInParent().getMinX();
        // not sure why v2's view isn't fully manifested (ie. height, boundsInParent MaxX & Y)
        //     so using v1 to get the height
        double inY = targetView.getBoundsInParent().getMinY() + (sourceView.getHeight() * 0.5);
        
        if (targetView.getBoundsInParent().getMinX() > sourceView.getBoundsInParent().getMinX()) {
            // v2 is to the right of v1, use staight line
            pointList.add(outX);
            pointList.add(outY);
            pointList.add(inX);
            pointList.add(inY);
            edgeLine.getPoints().addAll(pointList);
        } else {
            // v2 is to the left of (and presumably down from) v1, segment endpoints accordingly
            // beginning pt (1)
            pointList.add(outX);
            pointList.add(outY);
            // first segment endpt is same Y but X is offset by XOFFSET  (2)
            pointList.add(outX + XOFFSET);
            pointList.add(outY);
            // next segment endpt is same X but Y is offset by half the distance to the next node (in Y direction) (3)
            pointList.add(outX + XOFFSET);
            double yOffset = (inY - outY) / 2.0;
            pointList.add(outY + yOffset);
            // next segment endpt is same Y but X is offset by the distance to the next vertex + an offset (in -X direction)  (4)
            pointList.add(inX - XOFFSET);
            pointList.add(outY + yOffset);
            // next segment endpt is same X but Y is the same as Y of next vertex  (5)
            pointList.add(inX - XOFFSET);
            pointList.add(inY);
            // final segment ends at the in port location of the second (connecting) vertex  (6)
            pointList.add(inX);
            pointList.add(inY);
            edgeLine.getPoints().addAll(pointList);
        }
    }

//    @Override
//    public void bindEndPoints(AbstractVertexView vView1, AbstractVertexView vView2) {
//        edgeLine.getPoints().clear();
//        if (vView2.inXProperty().get() > vView1.outXProperty().get()) {
//            // v2 is to the right of v1, use staight line
//            System.out.println("------  Straight line edge --------");
//            System.out.print("From: " + vView1.outXProperty().doubleValue());
//            System.out.println(", " + vView1.outYProperty().doubleValue());
//            System.out.print("  To: " + vView2.inXProperty().doubleValue());
//            System.out.println(", " + vView2.inYProperty().doubleValue());
//            System.out.println("-----------------------------------");
//            pointList.add(vView1.outXProperty().doubleValue());
//            pointList.add(vView1.outYProperty().doubleValue());
//            pointList.add(vView2.inXProperty().doubleValue());
//            pointList.add(vView2.inYProperty().doubleValue());
//            edgeLine.getPoints().addAll(pointList);
////            edgeLine.getPoints().addAll((Double[]) pointList.toArray());
//        } else {
//            // v2 is to the left of (and presumably down from) v1, segment endpoints accordingly
//            // beginning pt
//            pointList.add(vView1.outXProperty().doubleValue());
//            pointList.add(vView1.outYProperty().doubleValue());
//            // first segment endpt is same Y but X is offset by XOFFSET
//            pointList.add(vView1.outXProperty().doubleValue() + XOFFSET);
//            pointList.add(vView1.outYProperty().doubleValue());
//            // next segment endpt is same X but Y is offset by half the distance to the next node (in Y direction)
//            pointList.add(vView1.outXProperty().doubleValue() + XOFFSET);
//            double yOffset = (vView2.inYProperty().doubleValue() - vView1.outYProperty().doubleValue()) / 2.0;
//            pointList.add(vView1.outYProperty().doubleValue() + yOffset);
//            // next segment endpt is same Y but X is offset by the distance to the next vertex + an offset (in -X direction)
//            pointList.add(vView2.outXProperty().doubleValue() - XOFFSET);
//            pointList.add(vView1.outYProperty().doubleValue() + yOffset);
//            // next segment endpt is same X but Y is the same as Y of next vertex
//            pointList.add(vView2.outXProperty().doubleValue() - XOFFSET);            
//            pointList.add(vView2.inYProperty().doubleValue());
//            // final segment ends at the in port location of the second (connecting) vertex
//            pointList.add(vView2.inXProperty().doubleValue());
//            pointList.add(vView2.inYProperty().doubleValue());
//            edgeLine.getPoints().addAll(pointList);            
//        }
//    }

}
