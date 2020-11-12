package sample;

import Controller.ControllerGuestWin;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/guestWin.fxml"));
        Parent root = loader.load();
        ControllerGuestWin controller = loader.getController();
        controller.showCategories();
        controller.displayOnStartup();
        primaryStage.setTitle("Program Name");
        primaryStage.setScene(new Scene(root, 600, 425));
        primaryStage.centerOnScreen();
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
