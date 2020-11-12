/*
Denis Martinkevic PI19B 3 Darbas OOP
Destytojas Mindaugas Liogys
*/

package Controller;

import Backend.User;
import Backend.categories;
import Date.UserRepository;

import Date.itemsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

import java.io.IOException;
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
            repository.SetUserLogIN(user);
            if(user.GetType() == 1){
                //newWindow("../Frontend/adminWind.fxml","Admin");
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/clientLoginWin.fxml"));
                Parent root = loader.load();
                ControllerClientWind controller = loader.getController();
                controller.showCategories();
                controller.displayItems();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("window");
                dialogStage.setScene(new Scene(root, 600, 450));
                dialogStage.centerOnScreen();
                dialogStage.show();
            }
        }
        catch (Exception exc){
            JOptionPane.showMessageDialog(null,exc.getMessage());
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

