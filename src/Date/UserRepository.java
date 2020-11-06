
package Date;

import Backend.Person;
import Backend.User;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

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

    protected static List<User> userList = new ArrayList<>();
    private static User userLogIN;
    DataBaseConnection dbl = new DataBaseConnection();

    private static int IdUser;


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
                    cons.USER_USERNAME + "," + cons.USER_PASSWORD + "," + cons.USER_TYPE + ")" +
                    "VALUES (?,?,?,?,?,?,?)";
            try {
                PreparedStatement prST = dbl.getDbConnection().prepareStatement(insert);
                prST.setString(1, user.GetNameFull());
                prST.setString(2, user.GetSurname());
                prST.setString(3, user.GetBirthday().toString());
                prST.setString(4, user.GetUserGender());
                prST.setString(5, user.GetUsername());
                prST.setString(6, user.GetPassword());
                prST.setInt(7, user.GetType());

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

        User User1 = null;
        String select = "SELECT * FROM " + cons.USER_TABLE + " WHERE " + cons.USER_USERNAME + "=? AND " + cons.USER_PASSWORD + "=?";
        try {
            PreparedStatement prST = dbl.getDbConnection().prepareStatement(select);
            prST.setString(1, username);
            prST.setString(2, password);

            ResultSet resSet = prST.executeQuery(); // получить данный из БД
            int counter = 0;

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
                    
                User1 = new User(name,surname,birthdate,gender,surname1,password1,type,null,ID);
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
                    userLogIN.SetPassword(newPassword);
                }
                else
                    throw new Exception("Wrong password");
            else
                throw new Exception("The passwords don't match");
    }

    public List<User> GetListUser(){
        return userList;
    }
    public User GetUserLogIN(){return userLogIN;}
    public User GetUserLogInIndex(int SelIndexList){

        return userList.get(SelIndexList);
    }

    public void delUser(int i){
        userList.remove(i);
    }
    public void SetUserLogIN(User user){
        userLogIN = user;
    }

    public void SetUserLogIN(ResultSet res) throws SQLException {
        IdUser = res.getRow();
        System.out.println(IdUser);
    }

    public File GetUserIMG(User user){
       return user.GetIMG();
    }
    public void SetUserIMG(User user, File i){
        //IMGuser = i;
        user.SetImgURL(i);
    }
}
