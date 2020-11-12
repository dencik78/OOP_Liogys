package Backend;


import java.util.ArrayList;
import java.util.List;

public class categories {

    private int id;
    private String title;
    private List<item> itemList;

    public categories(int id,String title){
        this.id = id;
        this.title = title;
        itemList = new ArrayList<item>();

    }

    public int getCategoriesId(){
        return id;
    }
    public String getCategoriesTitle(){
        return title;
    }
    public List<item> getCategoriesList(){
        return itemList;
    }

    public void setItemList(List<item> items){
        itemList = items;
    }
}
