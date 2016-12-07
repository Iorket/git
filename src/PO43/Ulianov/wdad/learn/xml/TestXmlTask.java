package PO43.Ulianov.wdad.learn.xml;

import PO43.Ulianov.wdad.data.managers.PreferencesManager;

/**
 * Created by Iorket on 04.12.2016.
 */
public class TestXmlTask {

    public static void main(String argv[])
    {
        User tutu=new User("tutu","tutu@mail.ru");
        User peperoni=new User("Peperoni","Peperoni@mail.ru");
        XmlTask Test1= new XmlTask("C:\\Users\\Iorket\\IdeaProjects\\start ing - monkey - to - human - path\\src\\PO43\\Ulianov\\wdad\\learn\\xml\\xmlForATest.xml");
        Test1.updateNote("Third note",peperoni,"It`s working/sometimes");
        Test1.setPrivileges("Third note",peperoni,1);
         //ОШИБКИ
        //неверное название тайтла
        System.out.print(Test1.getNoteText("Thir note",peperoni));
        //отстутствие  тега text в note
        Test1.updateNote("Ta-da",tutu,"Some  new text");
        PreferencesManager test2=  PreferencesManager.getInstance();
        String provider=test2.getClassprovider();
        System.out.println("PreferencesManager TEST");
        System.out.println("--GET--");
        System.out.println("createrigistry--"+test2.getValueOfCreateRegistryField());
        System.out.println("registryadress--"+test2.getRegistryAddress());
        System.out.println("registryport--"+test2.getRegistryPort());
        System.out.println("classprovider--"+ test2.getClassprovider());
        System.out.println("policypath--"+test2.getPolicypath());
        System.out.println("usecodebaseonly"+test2.getValueOfUsecodebaseonlyField());
        //Set
        test2.setClassprovider(provider);
        test2.setPolicypath(test2.getPolicypath());
        test2.setRegistryAddress(test2.getRegistryAddress());
        test2.setRegistryPort(test2.getRegistryPort());
        test2.setValueOfCreateRegistryField(true);
        test2.setValueOfUsecodebaseonlyField(false);
        PreferencesManager test3 = PreferencesManager.getInstance();
        System.out.print(test3.getValueOfCreateRegistryField());
    }
}

