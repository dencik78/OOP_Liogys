package Controller;

import Backend.coment;
import Backend.item;
import Date.itemsRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class shopitemInfoController {

        @FXML
        private Label priceItemInfo;

        @FXML
        private Label discriptionItemInfo;

    @FXML
    private Button deleteButtonShopItem;

        @FXML
        private Label titleItemInfo;

        @FXML
        private ImageView imagesItemInfo;

        @FXML
        private FlowPane flowItemInfo;

    @FXML
    private Button commentAddButton;

    @FXML
    private Button returnButtonInfoItem;

    private shopitemInfoController clw;

    private Pane p;
    private int type;
    private item itemm;

    private int i = 0;

    @FXML
    public void initialize(){
        if(type == 0){
           deleteButtonShopItem.setVisible(false);
        }else if(type == 1){
            deleteButtonShopItem.setVisible(true);
        }
    }

    @FXML
    void comentAddButtunClick(ActionEvent event) throws IOException {
        FXMLLoader loder = new FXMLLoader(getClass().getResource("../Frontend/newWindowComentAdd.fxml"));
        Parent root = loder.load();
        newWindowComentAddController controller = loder.getController();
        controller.setItem(itemm);
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root,264,174));
        newStage.show();
    }

    @FXML
    void buttonClickReturn(ActionEvent event) throws Exception {
            p.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/clientContentView.fxml"));
            Parent root = loader.load();
            clientContentViewController controller = loader.getController();
            controller.showCategories();
            if(type == 0){
                controller.displayItems();
            }
            else if(type == 1){
                controller.displayItemsAdmin();
            }
            p.getChildren().add(root);
    }
      public void displayInfo(item item) {
            titleItemInfo.setText(item.getName());
            priceItemInfo.setText(String.valueOf(item.getPrice()));
            discriptionItemInfo.setText(item.getDescription());
            File file = new File("src/View/" + item.getImages());
            imagesItemInfo.setImage(new Image(file.toURI().toString()));
        }

    public void displayInfo(item item,Pane p,int type) {
        titleItemInfo.setText(item.getName());
        priceItemInfo.setText(String.valueOf(item.getPrice()));
        discriptionItemInfo.setText(item.getDescription());
        File file = new File("src/View/" + item.getImages());
        imagesItemInfo.setImage(new Image(file.toURI().toString()));

        if(type == 0){
            deleteButtonShopItem.setVisible(false);
        }else if(type == 1){
            deleteButtonShopItem.setVisible(true);
        }

        this.p = p;
        this.type = type;
        }

        public void displayComent(item item) throws Exception {
        flowItemInfo.getChildren().clear();
        itemsRepository RP = new itemsRepository();
            List<coment> comList = new ArrayList<>();
            comList = RP.getListCom();
        for(coment c : comList) {
            if (c.getItemId() == item.getId()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/commentPane.fxml"));
                Parent root = loader.load();
                comentPaneController controller = loader.getController();
                controller.displayCom(c,item);
                i++;
                flowItemInfo.getChildren().add(root);
            }
        }
        this.itemm = item;
    }

    @FXML
    void deletebuttonItem(ActionEvent event) throws Exception{
        itemsRepository iRP = new itemsRepository();
        iRP.delItem(itemm);
        p.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/clientContentView.fxml"));
        Parent root = loader.load();
        clientContentViewController controller = loader.getController();
        controller.showCategories();
        if(type == 0){
            controller.displayItems();
        }
        else if(type == 1){
            controller.displayItemsAdmin();
        }
        p.getChildren().add(root);
    }
}


