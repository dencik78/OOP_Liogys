package Controller;

import Backend.User;
import Date.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.io.File;

public class ControllerRegisterWin {

   int type = 0;
   File IMG = null;
   int id = 0;

    @FXML
    private Button registrWinButton;

    @FXML
    private CheckBox maleCheckBox;

    @FXML
    private CheckBox femaleCheckBox;
    private String chekBoxGender;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private DatePicker birthday;

    @FXML
    private Button returnButton;

    @FXML
    void checkBoxFemaleAction(ActionEvent event) {
        if(femaleCheckBox.isSelected()){
            maleCheckBox.setSelected(false);
        }
    }

    @FXML
    void checkBoxMaleAction(ActionEvent event) {
        if(maleCheckBox.isSelected()){
            femaleCheckBox.setSelected(false);
        }
    }

    @FXML
    public void registerClick(javafx.event.ActionEvent actionEvent) throws Exception {
        try {
            if(maleCheckBox.isSelected()){
                chekBoxGender = "Men";
            }
            if(femaleCheckBox.isSelected()){
                chekBoxGender = "Women";
            }
            UserRepository repository = new UserRepository();
            repository.Registr(new User(nameField.getText(),surnameField.getText(), birthday.getValue(),chekBoxGender,loginField.getText(),passwordField.getText() ,type,IMG,id));
            JOptionPane.showMessageDialog(null,"Create new user");
             registrWinButton.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../Frontend/LoginWin.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login Windows");
            dialogStage.centerOnScreen();
            dialogStage.setScene(new Scene(root, 600, 400));
            dialogStage.show();

        }catch(Exception exc){
            JOptionPane.showMessageDialog(null,exc.getMessage());
        }
    }

    @FXML
    void clickReturnButton(ActionEvent event) throws Exception {

        returnButton.getScene().getWindow().hide();
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
