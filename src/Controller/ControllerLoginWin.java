/*
Denis Martinkevic PI19B 3 Darbas OOP
Destytojas Mindaugas Liogys
*/

package Controller;

import Backend.User;
import Date.UserRepository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import javax.swing.*;

import java.io.IOException;

public class ControllerLoginWin {

    @FXML
    private Button buttonLoginON;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void LoginClickk(ActionEvent actionEvent) throws Exception {
        UserRepository repository = new UserRepository();
        try {
            User user = repository.LogON(loginField.getText(), passwordField.getText());
            repository.SetUserLogIN(user);
            if(user.GetType() == 1){
                newWindow("../Frontend/adminWind.fxml","Admin");
            }else{
                newWindow("../Frontend/clientLoginWin.fxml","User");
            }
        }
        catch (Exception exc){
            JOptionPane.showMessageDialog(null,exc.getMessage());
        }
    }

    @FXML
    void clickReturn(ActionEvent event) throws IOException {
        newWindow("../Frontend/guestWin.fxml","Guest");
    }

    //new windows open metod
    public void newWindow(String windURL,String WindName) throws IOException {
        buttonLoginON.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource(windURL));
        Stage dialogStage = new Stage();
        dialogStage.setTitle(WindName +" " + "window");
        dialogStage.setScene(new Scene(root, 600, 450));
        dialogStage.centerOnScreen();
        dialogStage.show();
    }
}

