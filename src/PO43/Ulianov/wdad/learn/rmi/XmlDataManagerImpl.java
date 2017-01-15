package PO43.Ulianov.wdad.learn.rmi;

import java.util.List;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public class XmlDataManagerImpl implements XmlDataManger {
    public XmlDataManagerImpl(String path){
        //вжух,и код появился
    }
    @Override
    public String getNote(User owner, String title) {
        return null;
    }

    @Override
    public void updateNote(User owner, String title, StringBuilder newText) {

    }

    @Override
    public void setPriveleges(String noteTitle, User user, int newRights) {

    }

    @Override
    public List<Notes> getNotes(User owner) {
        return null;
    }
}
