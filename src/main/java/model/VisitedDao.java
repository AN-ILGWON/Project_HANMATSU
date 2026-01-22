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
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return;
            
            // 既に訪問記録があるか確認
            String checkSql = "SELECT vno FROM hm_visited WHERE userid = ? AND fno = ?";
            int vno = -1;
            try (PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
                pstmt.setString(1, userid);
                pstmt.setString(2, fno);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        vno = rs.getInt("vno");
                    }
                }
            }

            if (vno != -1) {
                // 既にある場合は日付を現在に更新
                String updateSql = "UPDATE hm_visited SET regdate = SYSDATE WHERE vno = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                    pstmt.setInt(1, vno);
                    pstmt.executeUpdate();
                }
            } else {
                // ない場合は新しく挿入
                String insertSql = "INSERT INTO hm_visited (vno, userid, fno, fname, regdate) " +
                                 "VALUES (hm_visited_seq.NEXTVAL, ?, ?, ?, SYSDATE)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    pstmt.setString(1, userid);
                    pstmt.setString(2, fno);
                    pstmt.setString(3, fname);
                    pstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // ユーザーの最近の訪問記録を取得 (最新5件)
    public List<VisitedDto> getRecentVisited(String userid) {
        List<VisitedDto> list = new ArrayList<>();
        String sql = "SELECT * FROM (SELECT * FROM hm_visited WHERE userid = ? ORDER BY regdate DESC) WHERE ROWNUM <= 5";
        
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return list;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userid);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        VisitedDto dto = new VisitedDto();
                        dto.setVno(rs.getInt("vno"));
                        dto.setUserid(rs.getString("userid"));
                        dto.setFno(rs.getString("fno"));
                        dto.setFname(rs.getString("fname"));
                        dto.setRegdate(rs.getString("regdate"));
                        list.add(dto);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
