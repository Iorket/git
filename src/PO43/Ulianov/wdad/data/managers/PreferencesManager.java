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
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Created by Iorket on 07.12.2016.
 */
//C:\Users\Iorket\IdeaProjects\start ing - monkey - to - human - path\src\PO43\Ulianov\wdad\resources\configuration\appconfig.xml
public class PreferencesManager {

    private Document doc;
    private final String FILE_PATH="//C:\\Users\\Iorket\\IdeaProjects\\start ing - monkey - to - human - path\\src\\PO43\\Ulianov\\wdad\\resources\\configuration\\appconfig.xml";
    private static PreferencesManager instance;
    private PreferencesManager(){
        try{
            File inputFile = new File(FILE_PATH);
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
    //HEHE
    public  void setProperty(String key,String value){
        finalElement(key).setTextContent(value);
        writeToFile();
    }
    public  String getProperty(String key){return finalElement(key).getTextContent();}
    public void setProperties(Properties prop){
        for (String propertyName:prop.stringPropertyNames()
             ) {
            setProperty(propertyName,prop.getProperty(propertyName));
        }
        writeToFile();
    };
    public Properties getProperties(){
        Properties properties=new Properties();
        xmlPropertiesAdder(properties,"",doc.getDocumentElement());
        return  properties;
    };
    public void addBindedObject(String name,String className){
        Element bindedobject = doc.createElement("bindedobject");
        bindedobject.setAttribute("name",name);
        bindedobject.setAttribute("className",className);
        doc.getElementsByTagName("rmi").item(0).appendChild(bindedobject);
        writeToFile();
    }
    public void removeBindedObject(String name){
        Element rmi= (Element)doc.getElementsByTagName("rmi").item(0);
        NodeList bindedObjects=rmi.getElementsByTagName("bindedobject");
        for(int i=0;i <bindedObjects.getLength();i++)
        {
            Element bindedObject=(Element) bindedObjects.item(i);
            if(bindedObject.getAttribute("name")==name)
            {
                bindedObject.getParentNode().removeChild(bindedObject);
            }
        }
        writeToFile();
    }
    @Deprecated
    public String getBindedObjectName(String className){
        NodeList bindedObjects;
        bindedObjects=finalElement("appconfig.rmi").getElementsByTagName("bindedobject");
        for(int i=0;i<bindedObjects.getLength();i++)
        {
            Element currentBindedObjects=(Element)bindedObjects.item(i);
            if((currentBindedObjects.getAttribute("className").equals(className)))
                return currentBindedObjects.getAttribute("name");
        }
        return  null;
    }
    @Deprecated
    private void xmlPropertiesAdder(Properties prop,String path,Node node)
    {
        boolean hasChild=false;
        path=path+node.getNodeName()+".";
        NodeList nodes=node.getChildNodes();
        for(int i=0;i<nodes.getLength();i++) {
            Node currentNode = nodes.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                xmlPropertiesAdder(prop, path, currentNode);
                hasChild=true;
            }
        }

        if (!hasChild) prop.put(path.substring(0, path.length() - 1), node.getTextContent());

    }

    @Deprecated
    private Element finalElement(String key)
    {
        Element currentElement=null;
        StringTokenizer stk = new StringTokenizer(key,".");
        String [] tagsRoute = new String[stk.countTokens()];
        for(int i = 0; i<tagsRoute.length; i++)
        {
            tagsRoute[i] = stk.nextToken();
            if(i==1) {
                currentElement=(Element)doc.getElementsByTagName(tagsRoute[i]).item(0);
            }
            else if(i>1){
                currentElement=(Element)currentElement.getElementsByTagName(tagsRoute[i]).item(0);
            }
        }
        return  currentElement;
    }
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
            StreamResult file= new StreamResult(new File(FILE_PATH));
            tr.transform(new DOMSource(doc),file);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
