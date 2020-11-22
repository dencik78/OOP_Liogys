package Controller;

import Backend.categories;
import Backend.item;
import Date.UserRepository;
import Date.itemsRepository;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class clientContentViewController {

    @FXML
    private Pane contentPaneView;

    @FXML
    private FlowPane flowContentClient;

    @FXML
    private VBox menuClientBox;

    private List<categories> categoriesList;
    private List<item> itemsList;

    private static Pane fl;

    @FXML
    public void initialize() throws Exception {
        itemsRepository repository = new itemsRepository();
        this.categoriesList = repository.getCategories();
        this.itemsList = repository.getItems();

    }

    public void setPane(Pane fl) {
        this.fl = fl;
    }

    public void showCategories() throws Exception {
        menuClientBox.getChildren().clear();
        for (categories c : categoriesList) {
            Button btn = new Button(c.getCategoriesTitle());
            btn.setPrefWidth(110);
            CategoryButtonEventHandler hendler = new CategoryButtonEventHandler(c, this);
            btn.setOnAction(hendler);
            menuClientBox.getChildren().add(btn);
        }
    }

    public void displayItems() throws IOException {
        flowContentClient.getChildren().clear();
        for (item i : itemsList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/ItemClientView.fxml"));
            Parent root = loader.load();
            ItemClientViewController controller = loader.getController();
            controller.displayItem(i, fl);
            flowContentClient.getChildren().add(root);
        }
    }

    public void displayItems(List<item> items) throws IOException {
        flowContentClient.getChildren().clear();
        for (item i : items) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/ItemClientView.fxml"));
            Parent root = loader.load();
            ItemClientViewController controller = loader.getController();
            controller.displayItem(i, fl);

            flowContentClient.getChildren().add(root);
        }
    }

    public void displayItemsAdmin() throws IOException {
        flowContentClient.getChildren().clear();
        for (item i : itemsList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/ItemAdminView.fxml"));
            Parent root = loader.load();
            ItemAdminViewController controller = loader.getController();
            controller.displayItemAdmin(i, fl);
            flowContentClient.getChildren().add(root);
        }
    }

    public void displayItemsAdmin(List<item> items) throws IOException {
        flowContentClient.getChildren().clear();
        for (item i : items) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Frontend/ItemAdminView.fxml"));
            Parent root = loader.load();
            ItemAdminViewController controller = loader.getController();
            controller.displayItemAdmin(i, fl);

            flowContentClient.getChildren().add(root);
        }
    }
}
