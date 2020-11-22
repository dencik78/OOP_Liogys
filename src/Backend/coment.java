package Backend;

import java.time.LocalDate;

public class coment {
    private int ID;
    private String comentText;
    private LocalDate dateComent;
    private int itemId;
    private int userId;

    public coment(int ID,int userId,int itemId,LocalDate dateComent,String comentText){
        this.ID = ID;
        this.userId = userId;
        this.itemId = itemId;
        this.dateComent = dateComent;
        this.comentText = comentText;
    }

    public coment(int userId,int itemId,LocalDate dateComent,String comentText){
        this.userId = userId;
        this.itemId = itemId;
        this.dateComent = dateComent;
        this.comentText = comentText;
    }

    public String getComentText() {
        return comentText;
    }

    public LocalDate getDateComent() {
        return dateComent;
    }


    public int getItemId() {
        return itemId;
    }
    public int getID() {
        return ID;
    }
    public int getUserId() {
        return userId;
    }

}
