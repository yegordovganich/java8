package exercises.ch4;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by y.dovganich on 16.03.2017.
 */
/*
9. Animate a circle, representing a planet, so it travels along an elliptical orbit.
Use a PathTransition.
 */
public class Ex9 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Circle circle = new Circle(0, 0, 15, Color.BLUE);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(150);
        arcTo.setY(30);
        arcTo.setRadiusX(50);
        arcTo.setRadiusY(100);
        arcTo.setXAxisRotation(45);
        arcTo.setSweepFlag(false);
        arcTo.setLargeArcFlag(true);

        Path path = new Path();
        path.getElements().add(new MoveTo(150, 300));
        path.getElements().add(arcTo);
        path.getElements().add(arcTo);
        // path.getElements().add(new ClosePath());

        path.setStroke(Color.GREY);
        path.getStrokeDashArray().setAll(5d, 5d);
        path.setOpacity(0.5);



        PathTransition pt = new PathTransition(Duration.seconds(5), path);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setNode(circle);
        pt.play();

        stage.setScene(new Scene(new Group(circle, path), 500, 500));
        stage.show();
    }
}
