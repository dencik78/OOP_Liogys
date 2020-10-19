
package Date;

import Backend.Person;
import Backend.User;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UserRepository  {

    protected static List<User> userList = new ArrayList<>();
    private static User userLogIN;



    public UserRepository() throws Exception {

// Type = 1 - Admin
// Type = 0 - user
        if (userList.size() == 0) {
            User user1 = new User("Denis", "Martinkevic", LocalDate.now(),"Men" ,"d_mart", "1234", 1,null);
          User user2 = new User("Denis", "Martinkevic", LocalDate.now(),"Men", "q", "q", 0,null);
//            User user3 = new User("Denis", "Martinkevic", LocalDate.now(), "d_ma", "1234", 0,null);
            userList.add(user1);
            userList.add(user2);
//            userList.add(user3); "C:\\Users\\Computer\\Desktop\\download.jpg
        }
    }
    public void Registr(User user)throws Exception {

        User u = null;
        for (User userChek : userList) {
            if (userChek.GetUsername().equals(user.GetUsername())) {
                u = userChek;
                break;
            }
        }
        if (u == null) {
            Period period = Period.between(user.GetBirthday(), LocalDate.now());
            if (period.getYears() >= 14)
                userList.add(user);
            else
                throw new Exception("You are still small");
            } else {
                throw new Exception("Such user exists");
            }
        }


    public User LogON(String username, String password) throws Exception {
        for (User user : userList) {
            if (user.GetUsername().equals(username) && user.GetPassword().equals(password))
                return user;
        }
        throw new Exception("Wrong username or password");
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
    public File GetUserIMG(User user){
       return user.GetIMG();
    }
    public void SetUserIMG(User user, File i){
        //IMGuser = i;
        user.SetImgURL(i);
    }
}
