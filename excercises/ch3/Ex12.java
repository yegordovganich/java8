package excercises.ch3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Created by y.dovganich on 02.03.2017.
 */
/*
12. Enhance the LatentImage class in Section 3.6, “Laziness,” on page 56, so that it
supports both UnaryOperator<Color> and ColorTransformer. Hint: Adapt the former
to the latter.
 */
public class Ex12 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("http://cache.images.core.optasports.com/soccer/teams/150x150/2254.png");
        Image transformedImage = LatentImage.from(image)
                .transform(Color::brighter)
                .transform(Color::grayscale)
                .transform((x, y, c) -> x < 10 || y < 10 || x > image.getWidth() - 10 ||
                    y > image.getHeight() - 10 ? Color.GRAY : c)
                .toImage();

        Stage stage = new Stage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
    }
}

class LatentImage {
    private Image in;
    private List<Ex5.ColorTransformer> pendingOperations;

    LatentImage transform(Ex5.ColorTransformer fn) {
        pendingOperations.add(fn);
        return this;
    }
    LatentImage transform(UnaryOperator<Color> fn) {
        pendingOperations.add(convert(fn));
        return this;
    }
    private static Ex5.ColorTransformer convert(UnaryOperator<Color> fn) {
        return (x, y, c) -> fn.apply(c);
    }
    public static LatentImage from(Image in) {
        LatentImage result = new LatentImage();
        result.in = in;
        result.pendingOperations = new ArrayList<>();
        return result;
    }
    public Image toImage() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                Color c = in.getPixelReader().getColor(x, y);
                for (Ex5.ColorTransformer f : pendingOperations) c = f.apply(x, y, c);
                out.getPixelWriter().setColor(x, y, c);
            }
        return out;
    }
}
