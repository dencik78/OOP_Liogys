package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class Main extends Application {

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Frontend/guestWin.fxml"));
        primaryStage.setTitle("Program Name");
        primaryStage.setScene(new Scene(root, 600, 425));
        primaryStage.centerOnScreen();
        primaryStage.show();

        //ImageView imgViev = new ImageView(null);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
