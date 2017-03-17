package exercises.ch4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Created by y.dovganich on 09.03.2017.
 */
/*
1. Write a program with a text field and a label. As with the Hello, JavaFX program,
the label should have the string Hello, FX in a 100 point font. Initialize
the text field with the same string. Update the label as the user edits the text
field.
 */
public class Ex1 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label();
        label.setFont(new Font(100));
        TextField input = new TextField("Hello, FX");
        label.textProperty().bind(input.textProperty());
        VBox vBox = new VBox(label, input);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
