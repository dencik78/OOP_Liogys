package Date;

import Backend.categories;
import Backend.item;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class itemsRepository {

    DataBaseConnection dbl = new DataBaseConnection();

    public itemsRepository(){
//        try {
//                dbl.getDbConnection();
//        }catch (Exception exc){
//            JOptionPane.showMessageDialog(null,exc);
//        }
   }

    public List<item> getItems(){
        List<item> listItems = new ArrayList<>();
        String sql ="SELECT id, name, price, description, images FROM " + cons.ITEMS_TABLE;
       try {
           PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

           ResultSet rs = prST.executeQuery();
           while(rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("name");
               double price = rs.getDouble("price");
               String description = rs.getString("description");
               String image = rs.getString("images");
               listItems.add(new item(id,name,price,description,image));
           }
       }catch (SQLException exc){
           JOptionPane.showMessageDialog(null,exc);
       }catch (Exception exc){
           JOptionPane.showMessageDialog(null,exc);
       }finally {
           return listItems;
       }
    }
        public List<categories> getCategories(){
            List<categories> listCategories = new ArrayList<>();
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

        private List<item> getItems(int categories) {
            List<item> itemList = new ArrayList<>();
            try {
                String sql = "SELECT id, name, price, description, images FROM " + cons.ITEMS_TABLE + " WHERE categoriesID =?";

                PreparedStatement pr = dbl.dbConnection.prepareStatement(sql);
                pr.setInt(1, categories);

                ResultSet rs = pr.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
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

}
