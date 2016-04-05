package org.uirebels.grapheditor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.uirebels.grapheditor.controller.JFXGraphEditorViewController;
import org.uirebels.grapheditor.view.AbstractVertexView;
import org.uirebels.grapheditor.view.SimpleEdgeView;
import org.uirebels.grapheditor.view.SimpleVertexView;
import org.uirebels.grapheditor.viewmodel.SimpleTaskViewModel;
import org.uirebels.grapheditor.view.AbstractEdgeView;
import org.uirebels.grapheditor.viewmodel.AbstractViewModel;
import static javafx.application.Application.launch;

public class JFXGraphEditor extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        AbstractViewModel viewModel = new SimpleTaskViewModel();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JFXGraphEditorView.fxml"));
        Parent root = loader.load();

        JFXGraphEditorViewController mainController = loader.<JFXGraphEditorViewController>getController();
        mainController.setViewModel(viewModel);
        viewModel.setGraphView(mainController.graphViewPane);

        AbstractVertexView vView = new SimpleVertexView(viewModel);
        viewModel.setVertexView(vView);
        AbstractEdgeView eView = new SimpleEdgeView(viewModel);
        viewModel.setEdgeView(eView);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Tinkerpop3 Together");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    @Override
    public void stop() {
        System.out.println("Graph Editor closing.");
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
