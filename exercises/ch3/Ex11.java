package exercises.ch3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;

/**
 * Created by y.dovganich on 02.03.2017.
 */
/*
11. Implement static methods that can compose two ColorTransformer objects, and
a static method that turns a UnaryOperator<Color> into a ColorTransformer that ignores
the x- and y-coordinates. Then use these methods to add a gray frame
to a brightened image. (See Exercise 5 for the gray frame.)
 */
public class Ex11 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("http://cache.images.core.optasports.com/soccer/teams/150x150/2254.png");

        Ex5.ColorTransformer frame = (x, y, c) ->
                x < 10 || y < 10 || x > image.getWidth() - 10 || y > image.getHeight() - 10 ? Color.GRAY : c;
        Image transformedImage = transform(image, compose(convert(Color::brighter), frame));

        Stage stage = new Stage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
    }
    private static Image transform(Image in, Ex5.ColorTransformer transformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color cc = in.getPixelReader().getColor(x, y);
                out.getPixelWriter().setColor(x, y, transformer.apply(x, y, cc));
            }
        }
        return out;
    }

    private static Ex5.ColorTransformer compose(Ex5.ColorTransformer first, Ex5.ColorTransformer second) {
        return (x, y, c) -> second.apply(x, y, first.apply(x, y, c));
    }
    private static Ex5.ColorTransformer convert(UnaryOperator<Color> fn) {
        return (x, y, c) -> fn.apply(c);
    }
}
