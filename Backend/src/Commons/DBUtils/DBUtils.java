package Commons.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static final String USER = "root";
    private static final String PASSWORD = "coderslab";
    private static final String DB_NAME = "it_school";
    private static final String DB_HOST = "jdbc:mysql://localhost:3306/";
    private static final String DB_OPTIONS = "?useSSL=false&characterEncoding=utf8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static DBUtils dbUtils = null;

    private DBUtils() {
    }

    public static DBUtils getInstance() {
        if (dbUtils == null)
            dbUtils = new DBUtils();

        return dbUtils;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_HOST + DB_NAME + DB_OPTIONS,
                USER, PASSWORD);
    }

}
