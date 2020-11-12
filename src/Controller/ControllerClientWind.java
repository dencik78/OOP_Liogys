/*
Denis Martinkevic PI19B 3 Darbas OOP
Destytojas Mindaugas Liogys
*/

package Controller;

import Backend.User;
import Backend.categories;
import Backend.item;
import Date.UserRepository;
import Date.itemsRepository;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ControllerClientWind {

    @FXML
    private Pane ClientPane;

    @FXML
    private Button shopClientButton;

    @FXML
    private VBox VBoxMenu;

    @FXML
    private FlowPane contentFlowClientPane;

    @FXML
    private Button clientSingOutButton;


    @FXML
    private AnchorPane clientPropertiesPane;

    @FXML
    private TextField repeatPasswordField;

    @FXML
    private TextField oldPasswordField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private ImageView ImageAvatar;

    private List<categories> categoriesList;
    private List<item> itemsList ;


    /*
      public void initialize()   позваляет сделать первый степ не используя главный конструктор!
    */

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
        itemsRepository repository = new itemsRepository();
        this.categoriesList = repository.getCategories();
        this.itemsList = repository.getItems();

    }


    public void showCategories() throws Exception {
        for (categories c : categoriesList) {
            Button btn = new Button(c.getCategoriesTitle());
            btn.setPrefWidth(110);
            CategoryButtonEventHandler hendler = new CategoryButtonEventHandler(c, this);
            btn.setOnAction(hendler);
            VBoxMenu.getChildren().add(btn);
        }
    }
    public void displayItems() throws IOException {
        contentFlowClientPane.getChildren().clear();
            for (item i : itemsList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/ItemClientView.fxml"));
                Parent root = loader.load();
                ItemClientViewController controller = loader.getController();
                controller.displayItem(i);
                contentFlowClientPane.getChildren().add(root);
            }
    }
    public void displayItems(List<item> items) throws IOException {
        contentFlowClientPane.getChildren().clear();
        for (item i : items) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/ItemClientView.fxml"));
            Parent root = loader.load();
            ItemClientViewController controller = loader.getController();
            controller.displayItem(i);
            contentFlowClientPane.getChildren().add(root);
        }
    }

    @FXML
    void ClickClientSingOutButton(ActionEvent event) throws Exception {
        UserRepository user = new UserRepository();
        user.SetUserLogIN((User)null);

        //Close Windows
        clientSingOutButton.getScene().getWindow().hide();
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

    @FXML
    void clickPropertiesButton(ActionEvent event) throws Exception {
        if(ClientPane.isVisible())
            ClientPane.setVisible(false);
        clientPropertiesPane.setVisible(true);
    }
    @FXML
    void shopClientButton(ActionEvent event) throws Exception{
        if(clientPropertiesPane.isVisible())
            clientPropertiesPane.setVisible(false);
        ClientPane.setVisible(true);
    }


    @FXML
    void clientSavePropertiesButton(ActionEvent event) {
        try {
            UserRepository user = new UserRepository();
            user.PasswordChange(oldPasswordField.getText(),newPasswordField.getText(),repeatPasswordField.getText());
            JOptionPane.showMessageDialog(null,"Pasikete");
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }

    @FXML
    void clickImageUnpButton(ActionEvent event) throws Exception {
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
                u.SetUserIMG(u.GetUserLogIN(),selFile);
            }
        }
    }

