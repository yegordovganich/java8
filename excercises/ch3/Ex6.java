package excercises.ch3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.function.BiFunction;

/**
 * Created by y.dovganich on 01.03.2017.
 */
/*
6. Complete the method
public static <T> Image transform(Image in, BiFunction<Color, T> f, T arg)
from Section 3.4, “Returning Functions,” on page 53.
 */
public class Ex6 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("http://cache.images.core.optasports.com/soccer/teams/150x150/2254.png");
        Image transformedImage = transform(image, (c, factor) -> c.deriveColor(0, 1, factor, 1), 5);

        Stage stage = new Stage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
    }

    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color cc = in.getPixelReader().getColor(x, y);
                out.getPixelWriter().setColor(x, y, f.apply(cc, arg));
            }
        }
        return out;
    }
}
