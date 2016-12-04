package PO43.Ulianov.wdad.learn.xml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 * Created by Iorket on 04.12.2016.
 */
public class XmlTask {
    Document doc;
    XmlTask(String xmlFile){
        try{
            File inputFile = new File(xmlFile);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             doc = dBuilder.parse(inputFile);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void testMethod(){
        System.out.print(doc.getDocumentElement().getTagName().toString());
    }
}
/*
во
имя
порядка
 */
class User{
    private String name;
    private String mail;
    public User(String name,String mail)
    {
        this.name=name;
        this.mail=mail;
    }
     String getName(){
        return name;
    }
    String getMail(){
        return  mail;
    }
}
