package Controller;

import Backend.SaveItem;
import Backend.User;
import Backend.item;
import Date.UserRepository;
import Date.itemsRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SavedItemClient {

    @FXML
    private FlowPane SavedItemPanelMain;

    public void clearPane(){
        SavedItemPanelMain.getChildren().clear();
    }

    public void displaySavedItem() throws Exception{
        UserRepository rp = new UserRepository();
        itemsRepository iRP = new itemsRepository();

        User user = rp.GetUserLogIN();
        List<SaveItem> saveList = new ArrayList<>();
        saveList = iRP.getSavedItemList();

        for(SaveItem s : saveList){
            if(user.getID() == s.getUserId()){
                item Item = iRP.getItemsID(s.getItemId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/itemSavedView.fxml"));
                Parent root = loader.load();
                ItemSavedViewController controller = loader.getController();
                controller.displaySave(Item,s);
                SavedItemPanelMain.getChildren().add(root);
            }
        }
    }

}
