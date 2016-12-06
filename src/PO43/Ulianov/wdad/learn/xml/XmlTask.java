package PO43.Ulianov.wdad.learn.xml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
/**
 * Created by Iorket on 04.12.2016.
 */
public class XmlTask {
    Document doc;
    String xmlFileName;
    XmlTask(String xmlFile){
        try{
            this.xmlFileName =xmlFile;
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
        Element currentNote= noteWithTitle(title);
        if(isNoteOwner(currentNote,owner)) return currentNote.getElementsByTagName("text").item(0).getTextContent();
        return "";
    }
    public void updateNote(String title,User owner,String text){
        Element currentNote= noteWithTitle(title);
        if(isNoteOwner(currentNote,owner)) {
            try {
                currentNote.getElementsByTagName("text").item(0).setTextContent(text);
            }
            catch (NullPointerException e) { Element noteTextTag=doc.createElement("text");
                currentNote.appendChild(noteTextTag).setTextContent(text);}
            writeToFile();
            System.out.println("Well done!+-");
        }
        else System.out.println("something wrong,I suppose");
    }
    public void setPrivileges(String noteTitle,User user,int newRigths) {
                Element notePrivileges=(Element) noteWithTitle(noteTitle).getElementsByTagName("privileges").item(0);
                NodeList users= notePrivileges.getElementsByTagName("user");
                for(int usersCount=0;usersCount<users.getLength();usersCount++)
                {
                    Element privilegeUser=(Element)users.item(usersCount);
                    if(user.getMail().equals(privilegeUser.getAttribute("mail").toString()))
                    {
                        privilegeUser.getParentNode().removeChild(privilegeUser);
                    }
                }
                //надо написать проверку на имя,навеерн
                if( newRigths!=0 ) {
                    //Вопросец:setAttribute и setTextContent не работают при смене прав RW на R
                    Element newUser = doc.createElement("user");
                    newUser.setAttribute("mail",user.getMail());
                    newUser.setAttribute("name",user.getName());//может же быть ситуация когда имя в записи отстутсвует?добавить конструктор там на его отстутствие,проверку добавить
                    if(newRigths==2) newUser.setAttribute("rights","RW");
                    else if(newRigths==1) newUser.setAttribute("rights","R");
                    else System.out.println("Incorect rights,I removed this user HAHA(It`s bad,I know)");
                    notePrivileges.appendChild(newUser);
                }
        writeToFile();
    }
    private Element noteWithTitle(String title){
         NodeList noteList=doc.getElementsByTagName("note");
        for(int noteCounter=0;noteCounter<noteList.getLength();noteCounter++) {
            Element note = (Element) noteList.item(noteCounter);
            if (note.getElementsByTagName("title").item(0).getTextContent().equals(title)) {
               return note;}
        }
        return null;
    }
   /*
    private Element elementWithOwner(Element verifiableElement,User owner)
    {try {
        Element noteOwner = (Element) verifiableElement.getElementsByTagName("owner").item(0);
        if (owner.getMail().equals(noteOwner.getAttribute("mail")) && owner.getName().equals(noteOwner.getAttribute("name")))
            return verifiableElement;}
    catch (NullPointerException e)
    { }
    return null;
    }
    */
    private boolean isNoteOwner(Element verifiableElement, User owner)
    {
        try {
            Element noteOwner = (Element) verifiableElement.getElementsByTagName("owner").item(0);
            if (owner.getMail().equals(noteOwner.getAttribute("mail")) && owner.getName().equals(noteOwner.getAttribute("name"))) return true;
        }
        catch (NullPointerException e)
        {}
        return false;
    }
    private void writeToFile(){
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            StreamResult file= new StreamResult(new File(xmlFileName));
            tr.transform(new DOMSource(doc),file);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
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