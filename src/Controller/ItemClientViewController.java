package Controller;

import Backend.SaveItem;
import Backend.User;
import Backend.coment;
import Backend.item;
import Date.UserRepository;
import Date.itemsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemClientViewController {

    @FXML
    private Pane contentPane;

    @FXML
    private Button addToCardButton;

    @FXML
    private Button saveItemButton;

    @FXML
    private Button InfoButton;

    private item itemSave;

    private Pane fl;

    //main func
    public void displayItem(item items, Pane fl) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/itemPublicView.fxml"));
        Parent root = loader.load();
        ItemPublicViewController controller = loader.getController();
        controller.displayItem(items);
        itemSave = items;
        contentPane.getChildren().add(root);
        this.fl = fl;
    }


    @FXML
   public void infobuttonClick(ActionEvent event) throws Exception {
       fl.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/shopItemInfo.fxml"));
        Parent root = loader.load();
        shopitemInfoController controller = loader.getController();
       controller.displayInfo(itemSave,fl,0);
       controller.displayComent(itemSave);
        fl.getChildren().add(root);
    }

    @FXML
    void saveItemButtonClick(ActionEvent event) throws Exception {
        itemsRepository iRP = new itemsRepository();
        UserRepository rp = new UserRepository();
        User user = rp.GetUserLogIN();
        SaveItem itSave= new SaveItem(user.getID(),itemSave.getId());
        List<SaveItem>saveList = iRP.getSavedItemList();
        boolean isTrue = false;
        for(SaveItem s : saveList) {
            if(s.getUserId() == itSave.getUserId()){
               if(s.getItemId() == itSave.getItemId()) {
                   isTrue = true;
                   break;
               }else {
                    isTrue = false;
                }
               }
            else{
                isTrue = false;
            }
        }
        if(isTrue == false) {
            iRP.addNewSavedItem(itSave);
            JOptionPane.showMessageDialog(null,"Items Add in you Saved Items list");
        }
        else
            JOptionPane.showMessageDialog(null, "This product is already on the list");
    }
}
