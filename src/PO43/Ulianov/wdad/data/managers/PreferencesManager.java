package PO43.Ulianov.wdad.data.managers;

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
 * Created by Iorket on 07.12.2016.
 */
//C:\Users\Iorket\IdeaProjects\start ing - monkey - to - human - path\src\PO43\Ulianov\wdad\resources\configuration\appconfig.xml
public class PreferencesManager {

    private Document doc;
    private String filePath;
    private static PreferencesManager instance;
    private PreferencesManager(){
        filePath="//C:\\Users\\Iorket\\IdeaProjects\\start ing - monkey - to - human - path\\src\\PO43\\Ulianov\\wdad\\resources\\configuration\\appconfig.xml";
        try{
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static PreferencesManager getInstance() {
        if (instance == null) instance = new PreferencesManager();
        return instance;
    }
    public String getValueOfCreateRegistryField()
    {
        return neededElemByTags("registry","createregistry",rmiElement("server")).getTextContent();
    }
    public String getRegistryAddress(){
        return neededElemByTags("registry","registryaddress",rmiElement("server")).getTextContent();
    }
    public String getRegistryPort(){
        return neededElemByTags("registry","registryport",rmiElement("server")).getTextContent();

    }
    public String getClassprovider(){
        return rmiElement("classprovider").getTextContent();
    }
    public String getPolicypath(){
        return neededElemByTags("client","policypath",(Element) doc.getElementsByTagName("rmi").item(0)).getTextContent();
    }
    public String getValueOfUsecodebaseonlyField(){
        return neededElemByTags("client","usecodebaseonly",(Element) doc.getElementsByTagName("rmi").item(0)).getTextContent();
    }
    public void setValueOfCreateRegistryField(boolean value) {
        if (value) neededElemByTags("registry","createregistry",rmiElement("server")).setTextContent("yes");
        else neededElemByTags("registry","createregistry",rmiElement("server")).setTextContent("no");
        writeToFile();
    }
    public void setRegistryAddress(String registryAddress) {
        neededElemByTags("registry", "registryaddress", rmiElement("server")).setTextContent(registryAddress);
        writeToFile();
    }
    public void setRegistryPort(String registryPort){
         neededElemByTags("registry","registryport",rmiElement("server")).setTextContent(registryPort);
        writeToFile();
    }
    public void  setClassprovider(String classprovider)
    {
        rmiElement("classprovider").setTextContent(classprovider);
        writeToFile();
    }
    public void setPolicypath(String policypath){
        neededElemByTags("client","policypath",(Element) doc.getElementsByTagName("rmi").item(0)).setTextContent(policypath);
        writeToFile();

    }
    public void setValueOfUsecodebaseonlyField(boolean valueOfUsecodebaseonly)
    {
     if(valueOfUsecodebaseonly)   neededElemByTags("client","usecodebaseonly",(Element) doc.getElementsByTagName("rmi").item(0)).setTextContent("yes");
        else {neededElemByTags("client","usecodebaseonly",(Element) doc.getElementsByTagName("rmi").item(0)).setTextContent("no");}
        writeToFile();
    }
//lvl l/2/3 mb
    //косячно как то
    private Element rmiElement(String tagName){
        Element rmi=(Element)doc.getElementsByTagName("rmi").item(0);
        return (Element) rmi.getElementsByTagName(tagName).item(0);
    }
    private Element neededElemByTags(String firstTag, String secondTag, Element parsElem)
    {
    Element currentElement=(Element)parsElem.getElementsByTagName(firstTag).item(0);
    return (Element) currentElement.getElementsByTagName(secondTag).item(0);
    }
    private void writeToFile(){
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            StreamResult file= new StreamResult(new File(filePath));
            tr.transform(new DOMSource(doc),file);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
