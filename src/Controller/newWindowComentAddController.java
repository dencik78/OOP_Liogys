package Controller;

import Backend.coment;
import Backend.item;
import Date.UserRepository;
import Date.itemsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import  javafx.scene.control.Button;
import Backend.item;

import java.awt.*;
import java.time.LocalDate;

public class newWindowComentAddController {

    @FXML
    private TextArea comenttextField;

    @FXML
    private Button buttonSave;

    private item item;

public void setItem(item i){
    this.item = i;
}

    @FXML
    void buttonClickSave(ActionEvent event) throws Exception {
        UserRepository pr = new UserRepository();
        itemsRepository Irp = new itemsRepository();

        Irp.addNewComent(new coment(pr.GetUserLogIN().getID(),item.getId(), LocalDate.now(),comenttextField.getText()));
        buttonSave.getScene().getWindow().hide();
    }
}
