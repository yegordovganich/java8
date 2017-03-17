package exercises.ch4;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by y.dovganich on 16.03.2017.
 */
/*
8. Since there is no JavaFX-specific knowledge in parsing FXML files, come up
with an example where you load an object that has nothing to do with JavaFX,
with some nested objects, and set the properties in FXML syntax. Extra
credit if you use injection.
 */
public class Ex8 extends Application implements Initializable {
    @FXML private TextField firstName;
    @FXML private TextField lastName;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            VBox root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/example.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assertNotNull(firstName);
        assertNotNull(lastName);
        assertEquals(firstName.getText(), "John");
        System.out.println(firstName.getText());
        assertEquals(lastName.getText(), "Lee");
        System.out.println(lastName.getText());
    }
}
