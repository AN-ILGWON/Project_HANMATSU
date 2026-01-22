package test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import util.DBManager;

public class DBVerify {
    public static void main(String[] args) {
        Connection conn = DBManager.getInstance();
        if (conn == null) return;
        
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("db_verify_output.txt"), StandardCharsets.UTF_8))) {
            Statement stmt = conn.createStatement();
            
            writer.write("--- Festivals ---\n");
            ResultSet rs = stmt.executeQuery("SELECT name, description FROM hm_festival");
            while (rs.next()) {
                writer.write("Name: " + rs.getString("name") + "\n");
                writer.write("Desc: " + rs.getString("description") + "\n");
            }
            
            writer.write("\n--- Board ---\n");
            rs = stmt.executeQuery("SELECT title FROM hm_board");
            while (rs.next()) {
                writer.write("Title: " + rs.getString("title") + "\n");
            }
            
            System.out.println("Verification file written to db_verify_output.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
