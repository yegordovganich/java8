package exercises.ch3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by y.dovganich on 02.03.2017.
 */
/*
8. Generalize Exercise 5 by writing a static method that yields a ColorTransformer
that adds a frame of arbitrary thickness and color to an image.
 */
public class Ex8 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("http://cache.images.core.optasports.com/soccer/teams/150x150/2254.png");
        Image transformedImage = transform(image, colorTransformer(image, 5, Color.RED));

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

    private static Ex5.ColorTransformer colorTransformer(Image image, int thickness, Color color) {
        return (x, y, c) -> {
            return x < thickness || y < thickness || x > image.getWidth() - thickness ||
                    y > image.getHeight() - thickness ? color : c;
        };
    }

}
