package DAO;

import Commons.DBUtils.DBUtils;

import java.time.format.DateTimeFormatter;

public class BaseDao {

    DBUtils dbUtils = DBUtils.getInstance();

    public DateTimeFormatter getDateTimeFormatter(){
        String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
        return DateTimeFormatter.ofPattern(DATE_FORMATTER);
    }

}
