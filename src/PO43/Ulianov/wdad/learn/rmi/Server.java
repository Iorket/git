package PO43.Ulianov.wdad.learn.rmi;

import PO43.Ulianov.wdad.data.managers.PreferencesManager;
import PO43.Ulianov.wdad.utils.PreferencesConstantManager;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public class Server implements PreferencesConstantManager {
    public Server() {};
    public static void main(String args[]) throws RemoteException {
        try {
            PreferencesManager prefManagServ = PreferencesManager.getInstance();
            if (prefManagServ.getProperty(createRegistry).equals("yes")) {
                Registry registry = LocateRegistry.createRegistry(Integer.parseInt(prefManagServ.getProperty(registryPort)));
                registry.rebind("XmlDataManager", new XmlDataManagerImpl("C:\\Users\\Iorket\\IdeaProjects\\start ing - monkey - to - human - path\\src\\PO43\\Ulianov\\wdad\\learn\\rmi\\XmlFORTestAg.xml"));
                prefManagServ.addBindedObject("XmlDataManager","XmlDataManagerImpl");
            } else System.out.println(cry());
        }
        catch (Exception e){
            System.exit(1);
        };
        }
    private static String cry(){return "tears";}
}
