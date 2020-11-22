package Controller;

import Backend.User;
import Backend.coment;
import Date.UserRepository;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class comentViewPaneController {
    @FXML
    private Label nicknameCommentLabel;

    @FXML
    private Label dateCommentPane;

    @FXML
    private Label commentTextPane;

    public void dysplayComent(coment c,String name) throws Exception {
//        UserRepository rp = new UserRepository();
//       ObservableList<User> listCom = rp.GetListUser();
//        User IDuser = rp.GetUserCom();
//        if(c.getUserId() == IDuser.getID()){
//            nicknameCommentLabel.setText(name);
//        } else
//            {
//                nicknameCommentLabel.setText(name);
//            }
        nicknameCommentLabel.setText(name);

        dateCommentPane.setText(String.valueOf(c.getDateComent()));
        commentTextPane.setText(c.getComentText());
    }

}
