package Controller;


import Backend.User;
import Backend.categories;
import Date.UserRepository;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.IOException;

public class CategoryButtonEventHandler<ActionEvent extends Event> implements EventHandler<ActionEvent> {

   private categories categories;
   private ControllerGuestWin cgw;
    private clientContentViewController cgw1;

   private UserRepository rep = new UserRepository();
   private User user = rep.GetUserLogIN();

   public CategoryButtonEventHandler(categories categories,ControllerGuestWin cgw) throws Exception {
       this.categories = categories;
       this.cgw = cgw;
   }

    public CategoryButtonEventHandler(categories categories,clientContentViewController cgw) throws Exception {
        this.categories = categories;
        this.cgw1 = cgw;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if(cgw==null) {
                if (user == null)
                    cgw1.displayItems(categories.getCategoriesList());
                else if (user.getType() == 1)
                    cgw1.displayItemsAdmin(categories.getCategoriesList());
                else
                    cgw1.displayItems(categories.getCategoriesList());
            }else {
                if (user == null)
                    cgw.displayItems(categories.getCategoriesList(), 2);
                else if (user.getType() == 1)
                    cgw.displayItems(categories.getCategoriesList(), 1);
                else
                    cgw.displayItems(categories.getCategoriesList(), 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
