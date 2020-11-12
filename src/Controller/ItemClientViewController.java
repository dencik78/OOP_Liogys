package Controller;

import Backend.item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

public class ItemClientViewController {

    @FXML
    private Pane contentPane;

    @FXML
    private Button addToCardButton;

    @FXML
    private Button InfoButton;

    public void displayItem(item items) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/itemPublicView.fxml"));
        Parent root = loader.load();
        ItemPublicViewController controller = loader.getController();
        controller.displayItem(items);
        contentPane.getChildren().add(root);
    }

}
