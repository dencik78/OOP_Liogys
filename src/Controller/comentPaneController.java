package Controller;

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
import java.io.IOException;
import java.sql.SQLException;

public class comentPaneController {

    @FXML
    private Pane mainPane;

    @FXML
    private Pane contentMainPaninComment;

    @FXML
    private Button delelteButtonComment;

    private coment saveComent;

    private item it;

    @FXML
    void deleteClickButton(ActionEvent event) throws Exception {

        itemsRepository iRP = new itemsRepository();
        iRP.delComent(saveComent);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/shopItemInfo.fxml"));
        Parent root = loader.load();
        shopitemInfoController controller = loader.getController();
        controller.displayComent(it);

    }


    public void displayCom(coment c, item it) throws Exception {
        contentMainPaninComment.getChildren().clear();
        UserRepository rp = new UserRepository();
            if (c.getUserId() == rp.GetUserLogIN().getID()) {
                delelteButtonComment.setVisible(true);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/comentViewPane.fxml"));
                Parent root = loader.load();
                comentViewPaneController controller = loader.getController();
                controller.dysplayComent(c, rp.GetUserLogIN().GetUsername());
                contentMainPaninComment.getChildren().add(root);
                this.saveComent = c;
                this.it = it;
            } else {
                if(rp.GetUserLogIN().getType() == 1){
                    delelteButtonComment.setVisible(true);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/comentViewPane.fxml"));
                    Parent root = loader.load();
                    comentViewPaneController controller = loader.getController();
                    controller.dysplayComent(c, rp.GetUserCom(c.getUserId()).GetUsername());
                    contentMainPaninComment.getChildren().add(root);
                    this.saveComent = c;
                    this.it = it;
                }
                else {
                delelteButtonComment.setVisible(false);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/comentViewPane.fxml"));
                Parent root = loader.load();
                comentViewPaneController controller = loader.getController();
                controller.dysplayComent(c, rp.GetUserCom(c.getUserId()).GetUsername());
                contentMainPaninComment.getChildren().add(root);
            }
        }



    }
}