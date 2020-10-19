package Backend;

import java.time.LocalDate;

public class Person {
    protected String name;
    protected String surname;
    protected LocalDate birthDate;
    protected String gender;

    public Person(String name, String surname, LocalDate birthDate,String gender) throws Exception {

        if (name == null || name.isEmpty())
            throw new Exception("You didn't write name");
        else
            this.name = name;
        if (surname == null || surname.isEmpty())
            throw new Exception("You didn't write surname");
        else
            this.surname = surname;
        if (birthDate == null || birthDate.toString().isEmpty() || birthDate.isAfter(LocalDate.now()))
            throw new Exception("Invalid date value");
        else
            this.birthDate = birthDate;
        if (gender == null || gender.isEmpty())
            throw new Exception("You have not chosen gender");
        else
            this.gender = gender;
    }


    public String GetNameFull(){
        return name;
    }

}
