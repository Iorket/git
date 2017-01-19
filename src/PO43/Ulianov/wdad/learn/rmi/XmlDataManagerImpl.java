package PO43.Ulianov.wdad.learn.rmi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public class XmlDataManagerImpl implements XmlDataManager,Serializable{
    Document doc;
    String fullXmlFileName;//не факт ,что нужно
    public XmlDataManagerImpl(String fullXmlFileName){
        try {
            //вжух,и код появился
            this.fullXmlFileName = fullXmlFileName;
            File inputFile=new File(fullXmlFileName);
            DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
        }
        catch (Exception e){}

    }
    @Override
    public String getNote(User owner, String title) throws ParseException {
        Element noteAfterTitleFiltr=noteWithTitle(title);
        if(isNoteOwner(noteAfterTitleFiltr,owner)) return noteAfterTitleFiltr.getElementsByTagName("text").item(0).getTextContent();
        return null;
    }

    @Override
    public void updateNote(User owner, String title, StringBuilder newText) {
        Element noteAfterTitleFiltr=noteWithTitle(title);
        if(isNoteOwner(noteAfterTitleFiltr,owner)) noteAfterTitleFiltr.getElementsByTagName("text").item(0).setTextContent(newText.toString());
    }
    @Override
    public void setPriveleges(String noteTitle, User user, int newRights) {
        boolean foundedUser = false;
        Element noteAfterTitleFiltr = noteWithTitle(noteTitle);
        NodeList privUsers = ((Element) noteAfterTitleFiltr.getElementsByTagName("privileges").item(0)).getElementsByTagName("user");
        for (int i = 0; i < privUsers.getLength(); i++) {
            Element privUser = (Element) privUsers.item(i);
            if (privUser.getAttribute("mail").equals(user.getMail())) {
                foundedUser = true;
                switch (newRights) {
                    case 0:
                        privUser.getParentNode().removeChild(privUser);
                    case 1:
                        privUser.setAttribute("rights", "R");
                    case 2:
                        privUser.setAttribute("rights", "RW");
                        break;
                }
            }
        }
        if (!foundedUser && newRights != 0) {
            Element newUser = doc.createElement("user");
            newUser.setAttribute("name", user.getName());
            newUser.setAttribute("mail", user.getMail());
            switch (newRights) {
                case 1:
                    newUser.setAttribute("rights", "R");
                case 2:
                    newUser.setAttribute("rights", "RW");
                    ((Element)noteAfterTitleFiltr.getElementsByTagName("privileges")).appendChild(newUser);
                    //easy add after find
            }
        }
    }
    @Override
    public List<Notes> getNotes(User owner) throws ParseException {
        List<Notes> notesOfCurrentOwner=new ArrayList<Notes>();
        NodeList noteList=doc.getElementsByTagName("note");
        int ownerNoteCounter=0;
        for(int i=0;i<noteList.getLength();i++)
        {
            Element currentNote=(Element)noteList.item(i);
            if(isNoteOwner(currentNote,owner)) {
                notesOfCurrentOwner.add(convertToNotesObj(currentNote));
            }
            }

        return notesOfCurrentOwner;
    }
    //delete?title dublicate trouble
    private Element noteWithTitle(String title) throws  NullPointerException{
        NodeList noteList=doc.getElementsByTagName("note");
        for(int noteCounter=0;noteCounter<noteList.getLength();noteCounter++) {
            Element note = (Element) noteList.item(noteCounter);
            if (note.getElementsByTagName("title").item(0).getTextContent().equals(title)) {
                return note;}
        }
        return null;
    }
    private Notes convertToNotesObj(Element elem) throws ParseException,NullPointerException {
        //cdate
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date cdate= formatter.parse(elem.getElementsByTagName("cdate").item(0).getTextContent());
        //owner
        Element ownerElem=(Element) elem.getElementsByTagName("owner").item(0);
        User owner=new User(ownerElem.getAttribute("name"),ownerElem.getAttribute("mail"));
        //title and text
        String title=elem.getElementsByTagName("title").item(0).getTextContent();
        StringBuilder text=new StringBuilder();
        text.append(elem.getElementsByTagName("text").item(0).getTextContent());
        //privelegies
         NodeList userListWithRights=((Element)elem.getElementsByTagName("privileges").item(0)).getElementsByTagName("user");
        HashMap<User,Integer> privelegeUsers=new HashMap<User,Integer>();
        int rights=0;
        for(int i=0;i<userListWithRights.getLength();i++)
        {
            Element currentUser=(Element) userListWithRights.item(i);
            switch (currentUser.getAttribute("rights")) {
                case "RW":rights=2;
                case "R":rights=1;
            }
            privelegeUsers.put(new User(currentUser.getAttribute("name"),currentUser.getAttribute("mail")),rights);
        }
        return new Notes(owner,title,text,cdate,privelegeUsers);
    }
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
            StreamResult file= new StreamResult(new File(fullXmlFileName));
            tr.transform(new DOMSource(doc),file);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
