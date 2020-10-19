/*
Denis Martinkevic PI19B 3 Darbas OOP
Destytojas Mindaugas Liogys
*/

package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class ControllerGuestWin {

    @FXML
    private Button regButton;

    @FXML
    private Button loginButton;

    @FXML
    public void clickLoginButton(ActionEvent actionEvent) throws IOException {
        newWindow("../Frontend/LoginWin.fxml","Login",loginButton);
    }
    @FXML
    public void clickReristrButton(ActionEvent actionEvent) throws IOException {
        newWindow("../Frontend/RegisterWin.fxml","Registration",regButton);

    }
    public void newWindow(String windURL,String WindName,Button butName) throws IOException {
        butName.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource(windURL));
        Stage dialogStage = new Stage();
        dialogStage.setTitle(WindName +" " + "window");
        dialogStage.setScene(new Scene(root, 600, 450));
        dialogStage.centerOnScreen();
        dialogStage.show();
    }

}
