/*
Denis Martinkevic PI19B 3 Darbas OOP
Destytojas Mindaugas Liogys
*/

package Controller;

import Backend.User;
import Backend.categories;
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

import java.util.List;

public class ControllerLoginWin {


    @FXML
    private Button buttonLoginON;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    private List<categories> categoriesList;

    @FXML
    public void LoginClickk(ActionEvent actionEvent) throws Exception {
        buttonLoginON.getScene().getWindow().hide();
        UserRepository repository = new UserRepository();
        try {
            User user = repository.LogON(loginField.getText().trim(), passwordField.getText().trim());
            if(user != null) {
                repository.SetUserLogIN(user);
                if (user.getType() == 1) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/adminWind.fxml"));
                    Parent root = loader.load();
                    ControllerAdminWind controller = loader.getController();
                    controller.display();
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("window");
                    dialogStage.setScene(new Scene(root, 600, 450));
                    dialogStage.centerOnScreen();
                    dialogStage.show();
                } else if (user.getType() == 0) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/clientLoginWin.fxml"));
                    Parent root = loader.load();
                    ControllerClientWind controller = loader.getController();
                    controller.display();
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("window");
                    dialogStage.setScene(new Scene(root, 600, 450));
                    dialogStage.centerOnScreen();
                    dialogStage.show();
                }
            }else{
                throw new Exception("Excepcion");
            }
        }
        catch (Exception exc){
            JOptionPane.showMessageDialog(null,exc.getMessage());

            Parent root = FXMLLoader.load(getClass().getResource("../Frontend/LoginWin.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login window");
            dialogStage.setScene(new Scene(root, 600, 450));
            dialogStage.centerOnScreen();
            dialogStage.show();
        }
    }


    @FXML
    void clickReturn(ActionEvent event) throws Exception {
        buttonLoginON.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/guestWin.fxml"));
        Parent root = loader.load();

        ControllerGuestWin controller = loader.getController();
        controller.showCategories();
        controller.displayOnStartup();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Guest" +" " + "window");
        dialogStage.setScene(new Scene(root, 600, 450));
        dialogStage.centerOnScreen();
        dialogStage.show();
    }


}

