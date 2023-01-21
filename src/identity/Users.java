package identity;

import models.GlobalRequestManager;

import java.sql.Connection;

public class Users extends Switch{
    String id;
    String firstName;
    String lastName;
    String age;
    String mail;

    public Users() {}

    public Users(String id, String firstName, String lastName, String age, String mail) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.mail = mail;
    }

    //setters
    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    //getters
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public void setPrefixe(String prefixe) {
        super.setPrefixe(prefixe);
    }
}
