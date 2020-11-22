
package Date;

import Backend.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UserRepository  {

    int t = 0;
    protected static ObservableList<User> userList = FXCollections.observableArrayList();
    private static User userLogIN;
    DataBaseConnection dbl = new DataBaseConnection();



    public UserRepository() throws Exception {
// Type = 1 - Admin
// Type = 0 - user
    }
    public void Registr(User user)throws Exception {

        String select = "SELECT * FROM " + cons.USER_TABLE + " WHERE " + cons.USER_USERNAME + "=?";
            PreparedStatement prST1 = dbl.getDbConnection().prepareStatement(select);
            prST1.setString(1, user.GetUsername());

            ResultSet resSet = prST1.executeQuery(); // получить данный из БД
            int counter = 0;

            while (resSet.next()) {
                counter++;
            }
            resSet.close();
            if(counter >= 1){
                throw new Exception("User");
            }

        Period period = Period.between(user.GetBirthday(), LocalDate.now());
        if (period.getYears() >= 14) {
            String insert = "INSERT INTO " + cons.USER_TABLE + "(" +
                    cons.USER_NAME + "," + cons.USER_SURNAME + "," +
                    cons.USER_BIRTHDAY + "," + cons.USER_GENDER + "," +
                    cons.USER_USERNAME + "," + cons.USER_PASSWORD + "," + cons.USER_TYPE +","+ cons.USER_IMAGES + ")" +
                    "VALUES (?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement prST = dbl.getDbConnection().prepareStatement(insert);
                prST.setString(1, user.GetNameFull());
                prST.setString(2, user.GetSurname());
                prST.setString(3, user.GetBirthday().toString());
                prST.setString(4, user.GetUserGender());
                prST.setString(5, user.GetUsername());
                prST.setString(6, user.GetPassword());
                prST.setInt(7, user.getType());
                prST.setString(8,"null");

                prST.executeUpdate(); // закинуть в базу данных
            } catch (SQLException exc) {
                JOptionPane.showMessageDialog(null, exc);
            } catch (ClassNotFoundException exc) {
                JOptionPane.showMessageDialog(null, exc);
            }
        } else {
            throw new Exception("You are still small");
        }

    }


    public User LogON(String username, String password) throws Exception {

        DataBaseConnection dbll = new DataBaseConnection();
        User User1 = null;
        String select = "SELECT * FROM " + cons.USER_TABLE + " WHERE " + cons.USER_USERNAME + "=? AND " + cons.USER_PASSWORD + "=?";
        try {
            PreparedStatement prST = dbll.getDbConnection().prepareStatement(select);
            prST.setString(1, username);
            prST.setString(2, password);

            ResultSet resSet = prST.executeQuery(); // получить данный из БД
            int counter = 0;



            ResultSet s;
            while (resSet.next()) {
                counter++;
                String name = resSet.getString("name");
                String surname = resSet.getString("surname");
                LocalDate birthdate = LocalDate.parse(resSet.getString("birthday"));
                String gender = resSet.getString("gender");
                String surname1 = resSet.getString("username");
                String password1 = resSet.getString("password");
                int type = resSet.getInt("type");
                int ID = resSet.getInt("userId");
                String images = resSet.getString("imagesUser");
                if(images.equals(null)||images.equals("null") || images == null){
                    User1 = new User(name,surname,birthdate,gender,surname1,password1,type,null,ID);
                }else{
                    File file = new File(images);
                    User1 = new User(name,surname,birthdate,gender,surname1,password1,type,file,ID);
                }
            }
            if (counter <= 0)
                throw new Exception("Not wrong Username");
            resSet.close();

        }catch(SQLException exc) {
            JOptionPane.showMessageDialog(null,exc);
        }catch (ClassNotFoundException exc){
            JOptionPane.showMessageDialog(null,exc);
        }

        //eсли найдет вернеть пользователя если нет то null
        return User1;
    }




    public void PasswordChange(String oldPassword,String newPassword,String repeatPassword) throws Exception{


            if (userLogIN.GetPassword().equals(oldPassword))
                if (newPassword.equals(repeatPassword)) {
                    //userLogIN.SetPassword(newPassword);
                    String sql = "UPDATE oop." + cons.USER_TABLE +" SET " +
                            cons.USER_PASSWORD + " =? WHERE (" + cons.USER_ID + "=? )" ;
                    PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

                    prST.setString(1,newPassword);
                    prST.setString(2,String.valueOf(userLogIN.getID()));

                    prST.executeUpdate();
                }
                else
                    throw new Exception("Wrong password");
            else
                throw new Exception("The passwords don't match");
    }

    public static ObservableList<User> GetListUser() throws Exception {
        if(userList.isEmpty()){
        String sql = "SELECT * FROM oop.usertable";
        DataBaseConnection dbl = new DataBaseConnection();
        PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

        ResultSet resSet = prST.executeQuery();

        while (resSet.next()) {
            User User1 = null;
            String name = resSet.getString("name");
            String surname = resSet.getString("surname");
            LocalDate birthdate = LocalDate.parse(resSet.getString("birthday"));
            String gender = resSet.getString("gender");
            String surname1 = resSet.getString("username");
            String password1 = resSet.getString("password");
            int type = resSet.getInt("type");
            int ID = resSet.getInt("userId");
            String images = resSet.getString("imagesUser");

            if (images.equals(null) || images.equals("null") || images == null) {
                User1 = new User(name, surname, birthdate, gender, surname1, password1, type, null, ID);
            } else {
                File file = new File(images);
                User1 = new User(name, surname, birthdate, gender, surname1, password1, type, file, ID);
            }
            userList.add(User1);
        }
        resSet.close();
        }
            return userList;

    }
    public User GetUserLogIN(){return userLogIN;}
    public User GetUserLogInIndex(int SelIndexList){
        return userList.get(SelIndexList);
    }


    public void delUser(int i) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM oop.usertable WHERE ("+cons.USER_ID+" = "+userList.get(i).getID()+")";
        PreparedStatement prST = dbl.getDbConnection().prepareStatement(sql);

        prST.executeUpdate();
        userList.remove(i);
    }





    public void SetUserLogIN(User user){
        userLogIN = user;
    }

//    public void SetUserLogIN(ResultSet res) throws SQLException {
//        IdUser = res.getRow();
//
//    }

    public File GetUserIMG(User user){
       return user.GetIMG();
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

    public User GetUserCom(int userId) throws Exception {
        User user;
        List<User> userList1 = new ArrayList<>();
       userList1 = GetListUser();
        for(User o : userList1){
            if(o.getID() == userId)
                break;
            else
                t++;
        }
        user = userList1.get(t);
        return user;
        }

    }

