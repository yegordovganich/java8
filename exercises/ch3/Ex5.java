package exercises.ch3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.scene.paint.Color;

/**
 * Created by y.dovganich on 01.03.2017.
 */
/*
5. Here is a concrete example of a ColorTransformer. We want to put a frame around
an image, like this:
First, implement a variant of the transform method of Section 3.3, “Choosing
a Functional Interface,” on page 50, with a ColorTransformer instead of an
UnaryOperator<Color>. Then call it with an appropriate lambda expression to put
a 10 pixel gray frame replacing the pixels on the border of an image.
 */
public class Ex5 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("http://cache.images.core.optasports.com/soccer/teams/150x150/2254.png");
        ColorTransformer transformer = (x, y, c) ->
            x < 10 || y < 10 || x > image.getWidth() - 10 || y > image.getHeight() - 10 ? Color.GRAY : c;
        Image transformedImage = transform(image, transformer);

        Stage stage = new Stage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
    }
    private static Image transform(Image in, ColorTransformer transformer) {
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

    @FunctionalInterface
    public interface ColorTransformer {
        Color apply(int x, int y, Color colorAtXY);
    }
}
