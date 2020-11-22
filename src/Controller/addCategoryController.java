package Controller;

import Date.itemsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.*;

public class addCategoryController {

    @FXML
    private TextField newCategoryTextField;

    @FXML
    private Button buttonSaveNewCategory;

    @FXML
    void clickNewCategorySave(ActionEvent event) throws Exception {
        itemsRepository rp = new itemsRepository();
        rp.addNewCategory(newCategoryTextField.getText());
        buttonSaveNewCategory.getScene().getWindow().hide();
        JOptionPane.showMessageDialog(null,"New Category Add");
    }

}
