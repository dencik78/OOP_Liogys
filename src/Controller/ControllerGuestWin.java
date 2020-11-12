/*
Denis Martinkevic PI19B 3 Darbas OOP
Destytojas Mindaugas Liogys
*/

package Controller;


import Backend.categories;
import Backend.item;
import Date.itemsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.List;

public class ControllerGuestWin {

    private itemsRepository repository;
    private List<item> itemsList ;
    private List<categories> categoriesList;

    @FXML
    private VBox VBoxMenu;

    @FXML
    private FlowPane content;

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

    public ControllerGuestWin(){
        repository = new itemsRepository();
        itemsList = repository.getItems();
        categoriesList = repository.getCategories();

    }

    public void showCategories() throws Exception {
        for(categories c :categoriesList){
            Button btn = new Button(c.getCategoriesTitle());
            btn.setPrefWidth(110);
            CategoryButtonEventHandler hendler = new CategoryButtonEventHandler(c,this);
            btn.setOnAction(hendler);
            VBoxMenu.getChildren().add(btn);
        }

    }
    public void displayOnStartup() throws IOException {
        content.getChildren().clear();
        for(item i : itemsList){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/itemPublicView.fxml"));
            Parent root = loader.load();
            ItemPublicViewController controller = loader.getController();
            controller.displayItem(i);
            content.getChildren().add(root);
        }
    }

    public void displayItems(List<item> items, int userType) throws IOException {
        content.getChildren().clear();
        if(userType == 2){
            for (item i : items) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/itemPublicView.fxml"));
                Parent root = loader.load();
                ItemPublicViewController controller = loader.getController();
                controller.displayItem(i);
                content.getChildren().add(root);
            }
        }
    }
}