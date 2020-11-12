package Controller;

import Backend.item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;

public class ItemPublicViewController {

    @FXML
    private Label titleLable;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView imagesView;

    public void displayItem(item item){
        titleLable.setText(item.getName());
        descriptionLabel.setText(item.getDescription());
        priceLabel.setText(String.valueOf(item.getPrice()));
        File file = new File("/src/View/" + item.getImages());
        imagesView.setImage(new Image(file.toURI().toString()));

    }
}
