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
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ControllerAdminWind {


    @FXML
    private Pane paneAddNewItem;

    @FXML
    private Button saveItemAdd;

    @FXML
    private TextField titleItemsAdd;

    @FXML
    private TextArea discriptionsItemsAdd;

    @FXML
    private TextField TextFielPriceAdd;

    @FXML
    private Button UploadImageItemAdd;

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
    private TableView<User> mainTable;

    @FXML
    private TableColumn<User,Integer> id_coll;

    @FXML
    private TableColumn<User,String> name_coll;

    @FXML
    private TableColumn<User,String> surname_coll;

    @FXML
    private TableColumn<User,Integer> type_coll;

    @FXML
    private Button shopAdminButton;

    @FXML
    private Pane contentAdminPane;

    @FXML
    private Pane contentShopAdminPane;

    @FXML
    private FlowPane FlowPaneAdmin;

    @FXML
    private TableView<categories> categoriosSelect;

    @FXML
    private TableColumn<categories, String> CategoriesTitle;

    @FXML
    private Button newcategoryButton;


    private List<categories> categoriesList;
    private List<item> itemsList;

    File imageAdd;

    @FXML
    public void initialize() throws Exception {
            categoryUpdatetable();

            id_coll.setCellValueFactory(new PropertyValueFactory<User, Integer>("ID"));
            name_coll.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
            surname_coll.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
            type_coll.setCellValueFactory(new PropertyValueFactory<User, Integer>("type"));
            mainTable.setItems(UserRepository.GetListUser());

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

        public void clickUserListButton (ActionEvent actionEvent) throws Exception {
                adminPropertiesPane.setVisible(false);
                contentShopAdminPane.setVisible(false);
                paneAddNewItem.setVisible(false);
            adminMainPane.setVisible(true);
        }

        public void categoryUpdatetable(){

            itemsRepository iRP = new itemsRepository();
            CategoriesTitle.setCellValueFactory(new PropertyValueFactory<categories, String>("title"));
            categoriosSelect.setItems(FXCollections.observableArrayList(iRP.getCategories()));
        }

    @FXML
    void addClickNewItem(ActionEvent event) {
        contentShopAdminPane.setVisible(false);
        paneAddNewItem.setVisible(true);
        adminPropertiesPane.setVisible(false);
        adminMainPane.setVisible(false);

    }

        @FXML
        public void clickDeleteButton (ActionEvent event) throws Exception {
            ObservableList<Integer> SelIndexList = mainTable.getSelectionModel().getSelectedIndices();
            UserRepository rpp = new UserRepository();
            System.out.println(rpp.GetUserLogIN().GetUsername());
            System.out.println(rpp.GetUserLogInIndex(SelIndexList.get(0)).GetUsername());
            if (rpp.GetUserLogIN().getID()  != rpp.GetUserLogInIndex(SelIndexList.get(0)).getID()) {
                rpp.delUser(SelIndexList.get(0));
//                ObservableList<User> items = FXCollections.observableArrayList(rpp.GetListUser());
//                mainTable.setItems(items);
            } else {
                JOptionPane.showMessageDialog(null,"You can't delete yourself");
            }
        }
        @FXML
        public void clickPropertiesButton (javafx.event.ActionEvent actionEvent) throws Exception {

                adminMainPane.setVisible(false);
                paneAddNewItem.setVisible(false);
                contentShopAdminPane.setVisible(false);
            adminPropertiesPane.setVisible(true);

        }

        @FXML
        public void ClickAdminSingOutButton(ActionEvent actionEvent) throws Exception {
            UserRepository user = new UserRepository();
            user.SetUserLogIN((User) null);


            //Close Windows
            adminSingOutButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/guestWin.fxml"));
            Parent root = loader.load();

            ControllerGuestWin controller = loader.getController();
            controller.showCategories();
            controller.displayOnStartup();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Guest" + " " + "window");
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
                System.out.println();
                Image image = SwingFXUtils.toFXImage(bf, null);
                ImageAvatar.setImage(image);
                u.SetUserIMG(u.GetUserLogIN(), selFile);
            }
        }

    @FXML
    void clickNewCategoryButton(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/AddCategory.fxml"));
        Parent root = loader.load();
        addCategoryController controller = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root,249,152));
        stage.show();
        categoryUpdatetable();

    }

    void updateContent(){

    }

    @FXML
    void saveItemaddClick(ActionEvent event) throws Exception {
        ObservableList<Integer> SelIndexList = categoriosSelect.getSelectionModel().getSelectedIndices();
        itemsRepository rp = new itemsRepository();
        item item = new item(titleItemsAdd.getText(),TextFielPriceAdd.getText(),discriptionsItemsAdd.getText(),String.valueOf(imageAdd));
       rp.setItemDb(item,rp.getCategoryID(SelIndexList.get(0)).getCategoriesId());

        titleItemsAdd.clear();
        TextFielPriceAdd.clear();
        discriptionsItemsAdd.clear();
        SelIndexList = null;
    }

    @FXML
    void UploadImageAdd(ActionEvent event) throws Exception {
        itemsRepository item = new itemsRepository();
        FileChooser fl = new FileChooser();
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        fl.getExtensionFilters().addAll(ext1, ext2);

        File selFile = fl.showOpenDialog(null);
        BufferedImage bf;
        if (selFile != null) {
            this.imageAdd = selFile;
        }
    }

    @FXML
    void shopAdminButtonClick(ActionEvent event) throws Exception {
        contentShopAdminPane.setVisible(true);
        adminMainPane.setVisible(false);
        adminPropertiesPane.setVisible(false);
       // contentAdminPane.setVisible(false);
        paneAddNewItem.setVisible(false);
        contentShopAdminPane.getChildren().clear();

       categoryUpdatetable();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/clientContentView.fxml"));
        Parent root = loader.load();
        clientContentViewController controller = loader.getController();
        controller.showCategories();
        controller.displayItemsAdmin();
        contentShopAdminPane.getChildren().add(root);

    }

    public void display() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/clientContentView.fxml"));
        Parent root = loader.load();
        clientContentViewController controller = loader.getController();
        controller.setPane(contentShopAdminPane);
        controller.showCategories();
        controller.displayItemsAdmin();
        contentShopAdminPane.getChildren().add(root);
    }
}


