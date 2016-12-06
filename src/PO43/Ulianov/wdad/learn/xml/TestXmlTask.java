package PO43.Ulianov.wdad.learn.xml;

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
        //надо ли это прописывать проверку для других тегов?


    }
}

