package PO43.Ulianov.wdad.learn.rmi;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public interface XmlDataManger extends Remote {

    public String getNote(User owner,String title);
    public void updateNote(User owner,String title,StringBuilder newText);
    public void setPriveleges(String noteTitle,User user,int newRights);
    public List<Notes> getNotes(User owner);

}
