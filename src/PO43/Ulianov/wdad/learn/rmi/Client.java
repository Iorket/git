package PO43.Ulianov.wdad.learn.rmi;

import PO43.Ulianov.wdad.data.managers.PreferencesManager;
import PO43.Ulianov.wdad.utils.PreferencesConstantManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public class Client implements PreferencesConstantManager {
    private Client(){};
    public  static  void  main(String args[]) throws RemoteException, NotBoundException, ParseException {
        User temp=new User("mr.Templ","mr.Templ@gmail.com");
        PreferencesManager clienPm=PreferencesManager.getInstance();
        XmlDataManager remoteObj;
            Registry registry= LocateRegistry.getRegistry(clienPm.getProperty(registryAddress), Integer.parseInt(clienPm.getProperty(registryPort)));
        remoteObj = (XmlDataManager) registry.lookup(clienPm.getBindedObjectName("XmlDataManagerImpl"));
        System.out.println("Conect");
        System.out.println("Note with title Praise: "+ remoteObj.getNote(temp,"Praise"));
            User peperoni=new User("Peperoni","Peperoni@mail.ru");
            StringBuilder someBeautifullText=new StringBuilder("vzhuh");
            remoteObj.updateNote(peperoni,"Third note",someBeautifullText);
            remoteObj.setPriveleges("Third note",new User("Volt","Volt@mail.ru"),2);
            System.out.println(remoteObj.getNotes(peperoni).size());

    }
}
