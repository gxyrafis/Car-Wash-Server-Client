package gr.uop;

import java.net.Socket;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class Client extends Application {

    @Override
    public void start(Stage stage) {
        ClientGUI.create(stage);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
