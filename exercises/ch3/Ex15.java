package exercises.ch3;

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
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by y.dovganich on 02.03.2017.
 */
/*
15. Combine the lazy evaluation of Section 3.6, “Laziness,” on page 56, with the
parallel evaluation of Section 3.7, “Parallelizing Operations,” on page 57.
 */
public class Ex15 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("http://cache.images.core.optasports.com/soccer/teams/150x150/2254.png");
        Image transformedImage = ParallelLatentImage.from(image)
                .transform(Color::brighter)
                .transform(Color::grayscale)
                .toImage();

        Stage stage = new Stage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(transformedImage))));
        stage.show();
    }
}

class ParallelLatentImage {
    private Image in;
    private List<UnaryOperator<Color>> pendingOperations;
    private List<Pixel> pixels = new ArrayList<>();

    private static class Pixel {
        int x;
        int y;
        Color color;
        Pixel(int x, int y, Color color) {
            this.x = x; this.y = y; this.color = color;
        }
    }

    ParallelLatentImage transform(UnaryOperator<Color> fn) {
        pendingOperations.add(fn);
        return this;
    }

    public static ParallelLatentImage from(Image in) {
        ParallelLatentImage result = new ParallelLatentImage();
        result.in = in;
        result.pendingOperations = new ArrayList<>();
        for (int x = 0; x < (int) in.getWidth(); x++)
            for (int y = 0; y < (int) in.getHeight(); y++)
                result.pixels.add(new Pixel(x, y, in.getPixelReader().getColor(x, y)));
        return result;
    }

    public Image toImage() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);

        final Function<Color, Color> action = getFinalTransform();

        pixels.parallelStream().forEach(p -> {
//            Color newColor = p.color;
//            for (UnaryOperator<Color> fn : pendingOperations) newColor = fn.apply(newColor);

            Color newColor = action.apply(p.color);

            out.getPixelWriter().setColor(p.x, p.y, newColor);
        });
        return out;
    }

    private Function<Color, Color> getFinalTransform() {
//        Function<Color, Color> action = Function.identity();
//        for (Function<? super Color, ? extends Color> fn : pendingOperations) action = action.andThen(fn);
        return pendingOperations
                .parallelStream()
                .reduce(
                        Function.identity(),
                        (fn1, fn2) -> fn1.andThen(fn2),
                        (fn1, fn2) -> fn1.andThen(fn2)
                );
    }
}
