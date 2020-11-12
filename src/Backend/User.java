package Backend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

import java.io.File;
import java.time.LocalDate;

public class User extends Person {
    protected String username;
    protected String password;
    private int type =2 ;
    private File IMG;
    protected int ID;

    public User (String name, String surname, LocalDate birthdate,String gender,String login,String password,int type,File IMG,int ID) throws Exception {
        super(name,surname,birthdate,gender);

        if (login == null || login.isEmpty())
            throw new Exception("You didn't write username");
        else
            this.username = login;
        if (password == null || password.isEmpty())
            throw new Exception("You didn't write password");
        else
            this.password = password;


        this.type = type;
        this.IMG = IMG;
        this.ID = ID;

    }


    public LocalDate GetBirthday(){return birthDate;}
    public String GetUsername(){
        return username;
    }
    public String GetPassword(){
        return password;
    }
    public int GetType(){return type;}
    public File GetIMG(){return IMG;}
    public void SetImgURL(File i){
        this.IMG = i;
    }
    

    public void SetPassword(String password){
        this.password = password;
    }

//    @Override
//    public String toString() {
//        return name + " " + surname + "             User Type: " + type;
//    }
}
