package gr.uop;

import java.net.Socket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientGUI {

    public static void create(Stage stage)
    {
        //
        //
        //Scene 1
        //
        //
        VBox mainwrapper = new VBox();
        HBox keyboard = new HBox();
        GridPane letterskeyboard = new GridPane();
        GridPane numberskeyboard = new GridPane();
        Label preview = new Label("");
        Button[] letters = new Button[26];
        Button[] numbers = new Button[10];
        Button[] special = new Button[3];
        var scene = new Scene(mainwrapper, 1024, 768);

        //
        //
        //Scene 2
        //
        //
        Button back = new Button("Cancel");
        Button ok = new Button("OK");
        Label price = new Label("Total: 0 €");
        BorderPane mainwrapper2 = new BorderPane();
        var scene2 = new Scene(mainwrapper2, 1024, 768);
        GridPane serviceChoices = new GridPane();
        HBox confirm = new HBox();
        VBox choicewrapper = new VBox(price, serviceChoices);


        Keyboardhandler keyboardhandler = new Keyboardhandler(preview, price, stage, serviceChoices, scene, scene2, back, ok);

        //Button setup
        {
            char c = 'A';
            for(int i = 0 ; i < 26 ; i++, c++)
            {
                String tmp = c + "";
                letters[i] = new Button(tmp);
                letters[i].setMinSize(60, 60);
                letters[i].setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
                letters[i].setOnAction(keyboardhandler);
            }
            for(int i = 0 ; i < 10 ; i++)
            {
                String tmp = i + "";
                numbers[i] = new Button(tmp);
                numbers[i].setMinSize(60, 60);
                numbers[i].setStyle("-fx-font-size: 25px;-fx-font-weight: bold;");
                numbers[i].setOnAction(keyboardhandler);

            }
            for(int i = 0 ; i < 3 ; i++)
            {
                special[i] = new Button();
                special[i].setMinSize(125, 60);
            }
            special[0].setGraphic(new ImageView(new Image("file:Images/enter.png", 40, 40, false, false)));
            special[0].setId("Enter");
            special[1].setGraphic(new ImageView(new Image("file:Images/backspace.png", 40, 40, false, false)));
            special[1].setId("Backspace");
            special[2].setGraphic(new ImageView(new Image("file:Images/spacebar.png", 100, 50, false, false)));
            special[2].setId("Space");
            special[2].setPadding(new Insets(5, 207, 5, 207));
        }

        //Adding buttons to keyboard.
        for(int i = 0, k = 0 ; i < 2 ; i++)
        {
            for(int j = 0 ; j < 8 ; j++, k++)
            {
                letterskeyboard.add(letters[k], j, i);
            }
        }
        for(int i = 0 ; i < 10 ; i++)
        {
            letterskeyboard.add(letters[16+i], i, 2);
        }

        for(int i = 2, k = 1 ; i > -1 ; i--)
        {
            for(int j = 0 ; j < 3 ; j++, k++)
            {
                numberskeyboard.add(numbers[k], j, i);
            }
        }
        numberskeyboard.add(numbers[0], 1, 3);

        letterskeyboard.add(special[1], 8, 0);
        letterskeyboard.add(special[0], 8, 1);
        letterskeyboard.add(special[2], 1, 3);

        //Visuals improvement.
        GridPane.setColumnSpan(special[0], 2);
        GridPane.setColumnSpan(special[1], 2);
        GridPane.setColumnSpan(special[2], 8);

        letterskeyboard.setVgap(5);
        letterskeyboard.setHgap(5);
        letterskeyboard.setPadding(new Insets(100, 0, 50, 0));

        numberskeyboard.setVgap(5);
        numberskeyboard.setHgap(5);
        numberskeyboard.setPadding(new Insets(100, 0, 50, 0));

        keyboard.setAlignment(Pos.BOTTOM_CENTER);
        keyboard.setSpacing(30);
        keyboard.getChildren().addAll(letterskeyboard, numberskeyboard);

        preview.setPadding(new Insets(100, 0, 0, 0));
        preview.setStyle("-fx-font-size: 60px;-fx-font-weight: bold;");

        mainwrapper.setAlignment(Pos.CENTER);
        mainwrapper.setSpacing(50);
        mainwrapper.getChildren().addAll(preview, keyboard);

        special[0].setOnAction(keyboardhandler);
        special[1].setOnAction(keyboardhandler);
        special[2].setOnAction(keyboardhandler);


        choicewrapper.setSpacing(60);

        price.setStyle("-fx-font-size: 60px;-fx-font-weight: bold;");

        confirm.getChildren().addAll(ok, back);
        confirm.setAlignment(Pos.BOTTOM_RIGHT);
        confirm.setPadding(new Insets(0, 30, 30, 0));
        confirm.setSpacing(10);

        ok.setMinSize(100, 40);
        ok.setStyle("-fx-font-size: 25px;");
        back.setMinSize(100, 40);
        back.setStyle("-fx-font-size: 25px;");
        
        serviceChoices.setAlignment(Pos.CENTER);
        choicewrapper.setAlignment(Pos.CENTER);

        mainwrapper2.setBottom(confirm);
        mainwrapper2.setCenter(choicewrapper);

        stage.setScene(scene);
        stage.setMinHeight(768);
        stage.setMinWidth(1024);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920);
        stage.setTitle("CarWash Client");
        stage.show();
    }
    
}
