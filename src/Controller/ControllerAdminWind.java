/*
Denis Martinkevic PI19B 3 Darbas OOP
Destytojas Mindaugas Liogys
*/

package Controller;


import Backend.User;
import Date.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;


public class ControllerAdminWind {

    @FXML
    private ListView<User> userListWin = new ListView<User>();

    @FXML
    private Button adminSingOutButton;

    @FXML
    private AnchorPane adminMainPane;

    @FXML
    private AnchorPane adminPropertiesPane;

    @FXML
    private TextField repeatPasswordField;

    @FXML
    private TextField oldPasswordField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private ImageView ImageAvatar;

    @FXML
    public void initialize() throws Exception {
        UserRepository rp = new UserRepository();
        if (rp.GetUserIMG(rp.GetUserLogIN()) != null) {
            System.out.println(true);
            BufferedImage bf;
            bf = ImageIO.read(rp.GetUserIMG(rp.GetUserLogIN()));
            Image image = SwingFXUtils.toFXImage(bf, null);
            ImageAvatar.setImage(image);
        }
    }

        public void clickUserListButton (ActionEvent actionEvent) throws Exception {
            if (adminPropertiesPane.isVisible())
                adminPropertiesPane.setVisible(false);
            adminMainPane.setVisible(true);
            UserRepository rp = new UserRepository();
            ObservableList<User> items = FXCollections.observableArrayList(rp.GetListUser());
            userListWin.setItems(items);

        }
        @FXML
        public void clickDeleteButton (ActionEvent event) throws Exception {
            ObservableList<Integer> SelIndexList = userListWin.getSelectionModel().getSelectedIndices();
            UserRepository rpp = new UserRepository();
            if (!rpp.GetUserLogIN().equals(rpp.GetUserLogInIndex(SelIndexList.get(0)))) {

                rpp.delUser(SelIndexList.get(0));
                ObservableList<User> items = FXCollections.observableArrayList(rpp.GetListUser());
                userListWin.setItems(items);
            } else {
                JOptionPane.showMessageDialog(null,"You can't delete yourself");
            }
        }
        @FXML
        public void clickPropertiesButton (javafx.event.ActionEvent actionEvent) throws Exception {
            if (adminMainPane.isVisible())
                adminMainPane.setVisible(false);
            adminPropertiesPane.setVisible(true);

        }

        @FXML
        public void ClickAdminSingOutButton(ActionEvent actionEvent) throws Exception{
            UserRepository user = new UserRepository();
            user.SetUserLogIN(null);

            //Close Windows
            adminSingOutButton.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../Frontend/guestWin.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Guest window");
            dialogStage.setScene(new Scene(root, 600, 450));
            dialogStage.centerOnScreen();
            dialogStage.show();
        }


        @FXML
        public void adminSavePropertiesButton (javafx.event.ActionEvent actionEvent) throws Exception {
            try {
                UserRepository user = new UserRepository();
                user.PasswordChange(oldPasswordField.getText(), newPasswordField.getText(), repeatPasswordField.getText());
                JOptionPane.showMessageDialog(null, "Pasikete");
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null, exc.getMessage());
            }
        }

        @FXML
        void clickImageUnpButton (ActionEvent event) throws Exception {
            UserRepository u = new UserRepository();
            FileChooser fl = new FileChooser();
            FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
            fl.getExtensionFilters().addAll(ext1, ext2);

            File selFile = fl.showOpenDialog(null);
            BufferedImage bf;
            if (selFile != null) {
                bf = ImageIO.read(selFile);
                Image image = SwingFXUtils.toFXImage(bf, null);
                ImageAvatar.setImage(image);
                u.SetUserIMG(u.GetUserLogIN(), selFile);
            }
        }
}


