package PO43.Ulianov.wdad.learn.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public interface XmlDataManager extends Remote {

    public String getNote(User owner,String title) throws ParseException,RemoteException;
    public void updateNote(User owner,String title,StringBuilder newText) throws RemoteException;
    public void setPriveleges(String noteTitle,User user,int newRights) throws RemoteException;
    public List<Notes> getNotes(User owner) throws ParseException,RemoteException;

}
