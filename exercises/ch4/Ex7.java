package exercises.ch4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by y.dovganich on 16.03.2017.
 */
/*
7. Find out how to set the border of a control without using CSS.
 */
public class Ex7 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();

        Label label = new Label("Test");
        //label.setStyle("-fx-border-color: red;");

        label.setBorder(new Border(new BorderStroke(
                Color.RED,
                BorderStrokeStyle.SOLID,
                null,
                BorderWidths.DEFAULT
        )));

        pane.setCenter(label);
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
