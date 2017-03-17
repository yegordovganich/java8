package exercises.ch4;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.function.Function;

/**
 * Created by y.dovganich on 09.03.2017.
 */
/*
5. Write methods
public static <T, R> ObservableValue<R> observe(
 Function<T, R> f, ObservableValue<T> t)
public static <T, U, R> ObservableValue<R> observe(
 BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u)
that return observable values whose getValue method returns the value of the
lambda expression, and whose invalidation and change listeners are fired
when any of the inputs become invalid or change. For example,
larger.disableProperty().bind(observe(
 t -> t >= 100, gauge.widthProperty()));
 */
public class Ex5 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Button smaller = new Button("Smaller");
        Button larger = new Button("Larger");
        Rectangle gauge = new Rectangle(10, 15, 50, 15);
        Rectangle outline = new Rectangle(10, 15, 100, 15);
        outline.setFill(null);
        outline.setStroke(Color.BLACK);
        Pane pane = new Pane();
        pane.getChildren().addAll(gauge, outline);

        smaller.setOnAction(__ -> gauge.setWidth(gauge.getWidth() - 10));
        larger.setOnAction(__ -> gauge.setWidth(gauge.getWidth() + 10));

        larger.disableProperty().bind(observe(v -> v.intValue() == 100, gauge.widthProperty()));
        smaller.disableProperty().bind(observe(v -> v.intValue() == 0, gauge.widthProperty()));

        HBox hBox = new HBox(smaller, pane, larger);
        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        stage.show();
    }

    public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t) {
        return new SimpleObjectProperty<R>() {
            @Override
            public R getValue() {
                return f.apply(t.getValue());
            }

            @Override
            public void addListener(ChangeListener<? super  R> listener) {
                t.addListener((observable, oldValue, newValue) ->
                        listener.changed(this, f.apply(oldValue), f.apply(newValue)));
            }

            @Override
            public void addListener(InvalidationListener listener) {
                t.addListener(listener);
            }
        };
    }

//    public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u) {
//
//    }

}
