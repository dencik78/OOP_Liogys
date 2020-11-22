package Backend;

public class SaveItem {
    private int ID;
    private int userId;
    private int itemId;

    public SaveItem(int id, int userID,int itemID){
        this.ID = id;
        this.userId = userID;
        this.itemId = itemID;
    }

    public SaveItem( int userID,int itemID){
        this.userId = userID;
        this.itemId = itemID;
    }

    public int getID() {
        return ID;
    }

    public int getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }
}
