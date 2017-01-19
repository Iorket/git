package PO43.Ulianov.wdad.learn.rmi;

import PO43.Ulianov.wdad.data.managers.PreferencesManager;
import PO43.Ulianov.wdad.utils.PreferencesConstantManager;
import sun.security.timestamp.TSRequest;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public class Server implements PreferencesConstantManager {
    public Server() {};
    public static void main(String args[]) throws RemoteException, NotBoundException, AlreadyBoundException, ParseException {

        //little test
        User mrT=new User("mr.Templ","mr.Templ@gmail.com");
          XmlDataManagerImpl TestObj=new XmlDataManagerImpl("C:\\Users\\Iorket\\IdeaProjects\\start ing - monkey - to - human - path\\src\\PO43\\Ulianov\\wdad\\learn\\rmi\\XmlForTest.xml");
          System.out.println(TestObj.getNote(mrT,"Praise"));
        PreferencesManager prefManagServ = PreferencesManager.getInstance();
            if (prefManagServ.getProperty(createRegistry).equals("yes")) {
                XmlDataManagerImpl remoteObj=new XmlDataManagerImpl("C:\\Users\\Iorket\\IdeaProjects\\start ing - monkey - to - human - path\\src\\PO43\\Ulianov\\wdad\\learn\\rmi\\XmlForTest.xml");
                UnicastRemoteObject.exportObject(remoteObj,0);
                 Registry registry = LocateRegistry.createRegistry(Integer.parseInt(prefManagServ.getProperty(registryPort)));
                registry.bind("XmlDataManager",remoteObj);
                prefManagServ.addBindedObject("XmlDataManager","XmlDataManagerImpl");
                System.out.println("It`s working....... ");
            } else System.out.println(cry());

        }
    private static String cry(){return "tears";}
}
