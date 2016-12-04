package PO43.Ulianov.wdad.learn.xml;

/**
 * Created by Iorket on 04.12.2016.
 */
public class TestXmlTask {

    public static void main(String argv[])
    {
        User peperoni=new User("Peperoni","Peperoni@mail.ru");
        XmlTask Test1= new XmlTask("C:\\Users\\Iorket\\IdeaProjects\\start ing - monkey - to - human - path\\src\\PO43\\Ulianov\\wdad\\learn\\xml\\xmlForATest.xml");
        System.out.print(Test1.getNoteText("Third note",peperoni));
        Test1.updateNote("Third note",peperoni,"It`s working");


    }
}

