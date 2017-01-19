package PO43.Ulianov.wdad.learn.rmi;

import java.io.Serializable;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public class User implements Serializable{
    private String name,mail;
    static final  User ALL=new User("all",null);
    User(String name,String mail){
        this.name=name;
        this.mail=mail;
    }
    public String getName(){return  name;}
    public String getMail(){return mail;}

}
