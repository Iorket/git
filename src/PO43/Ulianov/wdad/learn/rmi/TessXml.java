package PO43.Ulianov.wdad.learn.rmi;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Mr.Admin on 19.01.2017.
 */
public class TessXml {
    public static void main(String args[]) throws IOException, SAXException, ParserConfigurationException {

        XmlDataManagerImpl test1 = new XmlDataManagerImpl("C:\\\\Users\\\\Iorket\\\\IdeaProjects\\\\start ing - monkey - to - human - path\\\\src\\\\PO43\\\\Ulianov\\\\wdad\\\\learn\\\\rmi\\\\XmlForTest.xml");
        System.out.print(test1.fullXmlFileName);
        System.out.println(test1.noteF());
        //plan b

    }
}
