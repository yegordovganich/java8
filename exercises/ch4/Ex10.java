package exercises.ch4;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by y.dovganich on 16.03.2017.
 */
/*
10. Using the web viewer, implement a browser with a URL bar and a back
button. Hint: WebEngine.getHistory().
 */
public class Ex10 extends Application {
    private static final String HTTP_PREFIX = "http://";
    @Override
    public void start(Stage stage) throws Exception {
        WebView browser = new WebView();
        WebEngine engine = browser.getEngine();

        Button back = new Button("Back");
        back.setOnAction(__ -> engine.getHistory().go(-1));
        back.disableProperty().bind(Bindings.equal(engine.getHistory().currentIndexProperty(), 0));

        TextField url = new TextField("http://google.com");
        url.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                String str = url.getText();
                if (!str.startsWith(HTTP_PREFIX)) {
                    str = HTTP_PREFIX.concat(str);
                    url.setText(str);
                }
                engine.load(str);
            }
        });

        BorderPane pane = new BorderPane();
        pane.setCenter(browser);
        HBox buttons = new HBox(back, url);
        buttons.setPadding(new Insets(10));
        buttons.setSpacing(10);
        pane.setTop(buttons);

        engine.load(url.getText());

        stage.setScene(new Scene(pane, 800, 600));
        stage.show();
    }
}
