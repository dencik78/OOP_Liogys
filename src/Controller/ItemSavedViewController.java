package Controller;

import Backend.SaveItem;
import Backend.item;
import Date.itemsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class ItemSavedViewController {

    @FXML
    private Pane contentPane;

    @FXML
    private Button deletebutton;

    private SaveItem saveitems;
    private item i;

    @FXML
    private Button info;
    private Pane p;

    public void initialize(){

        info.setVisible(false);
    }

    @FXML
    void InfoClickButton(ActionEvent event) throws Exception {


//        contentPane.setVisible(false);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/shopItemInfo.fxml"));
//        Parent root = loader.load();
//        shopitemInfoController controller = loader.getController();
//        controller.displayInfo(i,p,0);
//        controller.displayComent(i);
//        p.getChildren().add(root);
    }

    @FXML
    void deleteButtonClick(ActionEvent event) throws Exception {
        itemsRepository iRP = new itemsRepository();
        iRP.delSavedItem(saveitems);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/savedItemClient.fxml"));
//        Parent root = loader.load();
//        SavedItemClient controller = loader.getController();
//        controller.clearPane();
//        controller.displaySavedItem();
        JOptionPane.showMessageDialog(null,"Delete Item");
    }


    public void displaySave(item items,SaveItem s) throws Exception{
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/itemPublicView.fxml"));
        Parent root = loader.load();
        ItemPublicViewController controller = loader.getController();
        controller.displayItem(items);
        contentPane.getChildren().add(root);

        this.saveitems = s;
        this.i = items;
    }
}
