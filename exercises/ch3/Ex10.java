package exercises.ch3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by y.dovganich on 02.03.2017.
 */
/*
Why can’t one call
UnaryOperator op = Color::brighter;
Image finalImage = transform(image, op.compose(Color::grayscale));
Look carefully at the return type of the compose method of UnaryOperator<T>.
Why is it not appropriate for the transform method? What does that say about
the utility of structural and nominal types when it comes to function
composition?
 */
public class Ex10 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("http://cache.images.core.optasports.com/soccer/teams/150x150/2254.png");
        UnaryOperator<Color> op = Color::brighter;
        Image transformedImage = transform(image, op.compose(Color::grayscale));

        Stage stage = new Stage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
    }
    private static Image transform(Image in, Function<Color, Color> transformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color cc = in.getPixelReader().getColor(x, y);
                out.getPixelWriter().setColor(x, y, transformer.apply(cc));
            }
        }
        return out;
    }}
