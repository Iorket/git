package PO43.Ulianov.wdad.learn.rmi;

import PO43.Ulianov.wdad.data.managers.PreferencesManager;
import PO43.Ulianov.wdad.utils.PreferencesConstantManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public class Client implements PreferencesConstantManager{
    Client(){};
    public  static  void  main(String args[])
    {
        PreferencesManager clienPm=PreferencesManager.getInstance();
        XmlDataManagerImpl remoteObj;
        try {
            Registry registry= LocateRegistry.getRegistry(clienPm.getProperty(registryAddress), Integer.parseInt(clienPm.getProperty(registryPort)));
            Object object=registry.lookup(clienPm.getBindedObjectName(XmlDataManagerImpl.class.getSimpleName()));
            remoteObj=(XmlDataManagerImpl)object;
            System.out.println("Note with title Praise: "+ remoteObj.getNote(new User("mr.Templ","mr.Templ@gmail.com"),"Praise"));
            User peperoni=new User("Peperoni","Peperoni@mail.ru");
            StringBuilder someBeautifullText=new StringBuilder("vzhuh");
            remoteObj.updateNote(peperoni,"Third note",someBeautifullText);
            remoteObj.setPriveleges("Third note",new User("Volt","Volt@mail.ru"),2);
            System.out.println(remoteObj.getNotes(peperoni).size());
        }
        catch (Exception e){
            e.printStackTrace();

        };
    }
}
