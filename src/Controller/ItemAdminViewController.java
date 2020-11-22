package Controller;

import Backend.item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ItemAdminViewController {

    @FXML
    private Pane contentPane;

    @FXML
    private Button InfoButton;

    private Pane fl;
    private item itemSave;

    public void displayItemAdmin(item items, Pane fl) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/itemPublicView.fxml"));
        Parent root = loader.load();
        ItemPublicViewController controller = loader.getController();
        controller.displayItem(items);
        itemSave = items;
        contentPane.getChildren().add(root);
        this.fl = fl;
    }

    @FXML
    public void infobuttonClickAdmin(ActionEvent event) throws Exception {
        fl.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/shopItemInfo.fxml"));
        Parent root = loader.load();
        shopitemInfoController controller = loader.getController();
        controller.displayInfo(itemSave,fl,1);
        controller.displayComent(itemSave);
        fl.getChildren().add(root);
    }
}