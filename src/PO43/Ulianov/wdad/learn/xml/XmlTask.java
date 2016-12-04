package PO43.Ulianov.wdad.learn.xml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import java.io.*;

/**
 * Created by Iorket on 04.12.2016.
 */
public class XmlTask {
    Document doc;
    String xmlFile;
    XmlTask(String xmlFile){
        try{
            this.xmlFile=xmlFile;
            File inputFile = new File(xmlFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             doc = dBuilder.parse(inputFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public String getNoteText(String title,User owner) {
        return getTextElement(title, owner).getTextContent();
    }
    public void updateNote(String title,User owner,String text){
        getTextElement(title,owner).setTextContent(text);
        System.out.print("Well dobe!");
    }
    public void setPrivileges(String noteTitle,User user,int newRigths) {
        NodeList noteList = doc.getElementsByTagName("note");
        for (int noteCounter = 0; noteCounter < noteList.getLength(); noteCounter++) {
            Element note = (Element) noteList.item(noteCounter);
            if (note.getElementsByTagName("title").item(0).getTextContent().equals(noteTitle)) {
                Element notePrivileges=(Element)note.getElementsByTagName("privileges").item(0);
                NodeList users= notePrivileges.getElementsByTagName("user");
                for(int usersCount=0;usersCount<users.getLength();usersCount++)
                {
                    Element privilegeUser=(Element)users.item(usersCount);
                    if(user.getMail().equals(privilegeUser.getAttribute("mail")))
                    {
                        switch (newRigths){
                            case 0:privilegeUser.getParentNode().removeChild(privilegeUser);
                            case 1:privilegeUser.setAttribute("rights","R");
                            case 2:privilegeUser.setAttribute("rights","RW");
                        }
                    }
                }
            }
        }
    }
    private Element getTextElement(String title, User owner){
         NodeList noteList=doc.getElementsByTagName("note");
        for(int noteCounter=0;noteCounter<noteList.getLength();noteCounter++) {
            Element note = (Element) noteList.item(noteCounter);
            if (note.getElementsByTagName("title").item(0).getTextContent().equals(title)) {
                Element noteOwner = (Element) note.getElementsByTagName("owner").item(0);
                if (noteOwner.getAttribute("name").equals(owner.getName()) && noteOwner.getAttribute("mail").equals(owner.getMail())) {
                    return (Element) note.getElementsByTagName("text").item(0);
                }
            }
        }
        return null;
    }
    private void writeToFile(){
        

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
