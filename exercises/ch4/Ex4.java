package exercises.ch4;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Created by y.dovganich on 09.03.2017.
 */
/*
4. Enhance the program in Section 4.5, “Bindings,” on page 75 so that the circle
stays centered and always touches at least two of the sides of the scene.
 */
public class Ex4 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Circle circle = new Circle(100, 100, 100);
        circle.setFill(Color.RED);
        Pane pane = new Pane();
        pane.getChildren().add(circle);
        Scene scene = new Scene(pane);
        circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
        circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
        circle.radiusProperty().bind(Bindings.divide(
                Bindings.min(scene.widthProperty(), scene.heightProperty()),
                2));
        stage.setScene(scene);
        stage.show();
    }
}
