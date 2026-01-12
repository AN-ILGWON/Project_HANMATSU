package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBManager;

public class VisitedDao {
    
    // 訪問記録を保存 (既に訪問している場合は日付のみ更新、または重複防止)
    public void insertVisited(String userid, String fno, String fname) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBManager.getInstance();
            
            // 既に訪問記録があるか確認
            String checkSql = "SELECT vno FROM hm_visited WHERE userid = ? AND fno = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setString(1, userid);
            pstmt.setString(2, fno);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // 既にある場合は日付を現在に更新
                pstmt.close();
                String updateSql = "UPDATE hm_visited SET regdate = SYSDATE WHERE vno = ?";
                pstmt = conn.prepareStatement(updateSql);
                pstmt.setInt(1, rs.getInt("vno"));
                pstmt.executeUpdate();
            } else {
                // ない場合は新しく挿入
                pstmt.close();
                String insertSql = "INSERT INTO hm_visited (vno, userid, fno, fname, regdate) " +
                                 "VALUES (hm_visited_seq.NEXTVAL, ?, ?, ?, SYSDATE)";
                pstmt = conn.prepareStatement(insertSql);
                pstmt.setString(1, userid);
                pstmt.setString(2, fno);
                pstmt.setString(3, fname);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {}
        }
    }
    
    // ユーザーの最近の訪問記録を取得 (最新5件)
    public List<VisitedDto> getRecentVisited(String userid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<VisitedDto> list = new ArrayList<>();
        
        String sql = "SELECT * FROM (SELECT * FROM hm_visited WHERE userid = ? ORDER BY regdate DESC) WHERE ROWNUM <= 5";
        
        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                VisitedDto dto = new VisitedDto();
                dto.setVno(rs.getInt("vno"));
                dto.setUserid(rs.getString("userid"));
                dto.setFno(rs.getString("fno"));
                dto.setFname(rs.getString("fname"));
                dto.setRegdate(rs.getString("regdate"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {}
        }
        return list;
    }
}
