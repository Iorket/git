package PO43.Ulianov.wdad.learn.rmi;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Mr.Admin on 15.01.2017.
 */
public class Notes {
    private User owner;
    private String title;
    private StringBuilder text;
    private Date cdate;
    HashMap<User,Integer> privelegeMap;

}
