package PO43.Ulianov.wdad.learn.rmi;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public class Notes implements Serializable{
    public Notes(User owner,String title,StringBuilder text,Date cdate,HashMap<User,Integer> privelegeMap)
    {
        this.cdate=cdate;
        this.owner=owner;
        this.privelegeMap=privelegeMap;
        this.text=text;
        this.title=title;
    }
    private User owner;
    private String title;
    private StringBuilder text;
    private Date cdate;
    HashMap<User,Integer> privelegeMap;

    public String getTitle() {
        return title;
    }

    public StringBuilder getText() {
        return text;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
