package Date;

import Backend.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class itemsRepository {

    DataBaseConnection dbl = new DataBaseConnection();
    int t;

    List<item> listItems;
   List<categories> listCategories = FXCollections.observableArrayList();

    public itemsRepository(){

   }

    public List<item> getItems(){
        listItems = new ArrayList<>();
        String sql ="SELECT id, name, price, description, images FROM " + cons.ITEMS_TABLE;
        DataBaseConnection dbll = new DataBaseConnection();
       try {
           PreparedStatement prST = dbll.getDbConnection().prepareStatement(sql);

           ResultSet rs = prST.executeQuery();
           while(rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("name");
               String price = rs.getString("price");
               String description = rs.getString("description");
               String image = rs.getString("images");
               listItems.add(new item(id,name,price,description,image));
           }
           this.listItems = listItems;
       }catch (SQLException exc){
           JOptionPane.showMessageDialog(null,exc);
       }catch (Exception exc){
           JOptionPane.showMessageDialog(null,exc);
       }finally {
           return listItems;
       }
    }
    public List<categories> getCategories(){
         DataBaseConnection dbl = new DataBaseConnection();
            String sql ="SELECT id, title FROM " + cons.CATEGORIES_TABLE;
            try {
                PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

                ResultSet rs = prST.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("title");
                    listCategories.add(new categories(id,name));
                }
            }catch (SQLException exc){
                JOptionPane.showMessageDialog(null,exc);
            }catch (Exception exc){
                JOptionPane.showMessageDialog(null,exc);
            }finally {
                for(categories c : listCategories){
                    c.setItemList(getItems(c.getCategoriesId()));
                }
                return listCategories;
            }
        }


    public void SetUserIMG(User user, File i) throws SQLException, ClassNotFoundException {
        //IMGuser = i;
        user.SetImgURL(i);

        String sql = "UPDATE oop . " + cons.USER_TABLE + " SET " + cons.USER_IMAGES
                + " =? WHERE (" + cons.USER_ID + " = " + user.getID() + ")";

        PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);
        prST.setString(1,String.valueOf(i));
        prST.executeUpdate();
    }

    public void setItemDb(item i,int categoryID) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO oop. " + cons.ITEMS_TABLE +
                " (" + cons.ITEMS_NAME + " , " + cons.ITEMS_PRICE +
                " , " + cons.ITEMS_DESCRIPTION + " , " + cons.ITEMS_CATEGORIESID +
                " , " + cons.ITEMS_IMAGES + ") VALUES (?,?,?,?,?)";

        PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

        prST.setString(1 , i.getName());
        prST.setString(2,i.getPrice());
        prST.setString(3,i.getDescription());
        prST.setString(4,String.valueOf(categoryID));
        prST.setString(5,i.getImages());

        prST.executeUpdate();

    }

    public List<item> getItems(int categories) {
            List<item> itemList = new ArrayList<>();
            DataBaseConnection dbll = new DataBaseConnection();
            try {
                String sql = "SELECT id, name, price, description, images FROM " + cons.ITEMS_TABLE + " WHERE categoriesID =?";

                PreparedStatement pr = dbll.getDbConnection().prepareStatement(sql);
                pr.setInt(1, categories);

                ResultSet rs = pr.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String price = rs.getString("price");
                    String description = rs.getString("description");
                    String image = rs.getString("images");
                    itemList.add(new item(id, name, price, description, image));
                }
            } catch (SQLException exc) {
                JOptionPane.showMessageDialog(null, exc);
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null, exc);
            } finally {
                return itemList;
            }
        }
    public List<coment> getListCom() throws SQLException, ClassNotFoundException {
        List<coment> listCom = new ArrayList<>();
        try {

            String sql = "SELECT id, userID, itemID, date, text FROM " + cons.COMMENT_TABLE;
            PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

            ResultSet res = prST.executeQuery();

            while (res.next()) {
                int Id = res.getInt("id");
                int userId = res.getInt("userID");
                int itemID = res.getInt("itemID");
                LocalDate date = LocalDate.parse(res.getString("date"));
                String text = res.getString("text");
                listCom.add(new coment(Id, userId, itemID, date, text));
            }

        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, exc);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
        } finally {
            return listCom;
        }
    }

    public void delItem(item item) throws Exception {
        String sql = "DELETE FROM oop." + cons.ITEMS_TABLE + " WHERE (" +
                cons.ITEMS_ID + " =?)";
        List<coment> comList = new ArrayList<>();
        comList = getListCom();
        for(coment com: comList){
            if(com.getItemId() == item.getId()){
                delComent(com);
            }
        }
        try {
            PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);
            prST.setString(1, String.valueOf(item.getId()));
            prST.executeUpdate();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null,exc.getMessage());
        }
    }



    public void delComent(coment c) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM oop.comment WHERE ("+cons.COMMENT_ID+" = "+c.getID()+")";
        PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

        prST.executeUpdate();
    }

    public void addNewComent(coment c) throws Exception{

        String sql = "INSERT INTO oop.comment (" + cons.COMMENT_USERID + ", "
                + cons.COMMENT_ITEMID + ", " + cons.COMMNET_DATE + "," + cons.COMMENT_TEXT +
                ")  VALUES (?,?,?,?)";

        PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

        try {
            prST.setInt(1,c.getUserId());
            prST.setInt(2,c.getItemId());
            prST.setString(3,String.valueOf(c.getDateComent()));
            prST.setString(4,c.getComentText());

            prST.executeUpdate();
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, exc);
        }
    }

    public List<SaveItem> getSavedItemList(){
        List<SaveItem> savedList = new ArrayList<>();
        try {

            String sql = "SELECT id, userId, itemId FROM " + cons.SAVEDITEM_TABLE;
            PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

            ResultSet res = prST.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                int userId = res.getInt("userID");
                int itemID = res.getInt("itemID");
                savedList.add(new SaveItem(id,userId, itemID));
            }

        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, exc);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
        } finally {
            return savedList;
        }
    }

    public void delSavedItem(SaveItem c) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM oop.saveditem WHERE ("+cons.SAVEDITEM_ID+" = " + c.getID() + ")";
        PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

        prST.executeUpdate();
    }

    public void addNewSavedItem(SaveItem c) throws Exception{

        String sql = "INSERT INTO oop.saveditem (" + cons.SAVEDITEM_USERID + ", "
                + cons.SAVEDITEM_ITEMID + ")  VALUES (?,?)";

        PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

        try {
            prST.setInt(1,c.getUserId());
            prST.setInt(2,c.getItemId());

            prST.executeUpdate();
        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, exc);
        }
    }

    public categories getCategoryID(int Index){

        List<categories> catList = new ArrayList<>();
        catList = getCategories();
        return catList.get(Index);
    }

    public item getItemsID(int i){ // id item

        t = 0;
        item Items;
        List<item> itemList1 = new ArrayList<>();
        itemList1 = getItems();
        for(item o : itemList1){
            if(o.getId() == i)
                break;
            else
                t++;
        }
        Items = listItems.get(t);

        return Items;
    }

    public void addNewCategory(String title) throws Exception {
        String sql = "INSERT INTO oop." + cons.CATEGORIES_TABLE + " (" + cons.CATEGORIES_TITLE
                + ") VALUES (?)";

        PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);
        try {
            prST.setString(1,title);

            prST.executeUpdate();

        }catch (Exception exc){
            JOptionPane.showMessageDialog(null,exc);
        }
    }
}
