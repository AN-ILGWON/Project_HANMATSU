package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import util.DBManager;

public class DBEncodingCheck {
    public static void main(String[] args) {
        Connection conn = DBManager.getInstance();
        if (conn == null) return;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM nls_database_parameters WHERE parameter IN ('NLS_CHARACTERSET', 'NLS_NCHAR_CHARACTERSET')");
            while (rs.next()) {
                System.out.println(rs.getString("PARAMETER") + ": " + rs.getString("VALUE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, null);
        }
    }
}
