package test;

import java.sql.*;
import util.DBManager;

public class TestSequence {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBManager.getInstance();
            System.out.println("Checking HM_FESTIVAL_SEQ...");
            
            // Try to get nextval
            try {
                pstmt = conn.prepareStatement("SELECT hm_festival_seq.NEXTVAL FROM DUAL");
                rs = pstmt.executeQuery();
                if(rs.next()) {
                    System.out.println("Next value: " + rs.getLong(1));
                }
            } catch (SQLException e) {
                System.out.println("Error getting nextval: " + e.getMessage());
                if(e.getErrorCode() == 2289) { // ORA-02289: sequence does not exist
                    System.out.println("Sequence DOES NOT EXIST. Creating it...");
                    Statement stmt = conn.createStatement();
                    stmt.execute("CREATE SEQUENCE hm_festival_seq START WITH 100 INCREMENT BY 1");
                    System.out.println("Sequence created successfully.");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
    }
}
