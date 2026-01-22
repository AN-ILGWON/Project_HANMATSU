package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBManager;

public class DataChecker {
    public static void main(String[] args) {
        try {
            Connection conn = DBManager.getInstance();
            
            // Check Festivals
            String sqlF = "SELECT count(*) FROM hm_festival";
            PreparedStatement pstmtF = conn.prepareStatement(sqlF);
            ResultSet rsF = pstmtF.executeQuery();
            if (rsF.next()) {
                System.out.println("Festivals count: " + rsF.getInt(1));
            }
            
            // Check Boards (Community)
            String sqlB = "SELECT count(*) FROM hm_board";
            PreparedStatement pstmtB = conn.prepareStatement(sqlB);
            ResultSet rsB = pstmtB.executeQuery();
            if (rsB.next()) {
                System.out.println("Community (Board) count: " + rsB.getInt(1));
            }
            
            // Check News
            String sqlN = "SELECT count(*) FROM hm_news";
            PreparedStatement pstmtN = conn.prepareStatement(sqlN);
            ResultSet rsN = pstmtN.executeQuery();
            if (rsN.next()) {
                System.out.println("News count: " + rsN.getInt(1));
            }
            
            // Check Banners
            String sqlBn = "SELECT count(*) FROM hm_banner";
            PreparedStatement pstmtBn = conn.prepareStatement(sqlBn);
            ResultSet rsBn = pstmtBn.executeQuery();
            if (rsBn.next()) {
                System.out.println("Banners count: " + rsBn.getInt(1));
            }
            
            // List some festivals to check recommended
            String sqlRec = "SELECT fno, name, is_recommended FROM hm_festival WHERE is_recommended = 'Y'";
            PreparedStatement pstmtRec = conn.prepareStatement(sqlRec);
            ResultSet rsRec = pstmtRec.executeQuery();
            while (rsRec.next()) {
                System.out.println("Recommended: " + rsRec.getInt("fno") + " - " + rsRec.getString("name"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
