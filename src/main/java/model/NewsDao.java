package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBManager;

public class NewsDao {
    public List<NewsDto> getNewsList(int page, int pageSize) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<NewsDto> list = new ArrayList<>();
        
        String sql = "SELECT * FROM ( " +
                    "    SELECT ROWNUM rn, aaa.* FROM ( " +
                    "    SELECT * FROM hm_news ORDER BY nno DESC) aaa " +
                    "    WHERE ROWNUM <= ?) " +
                    "WHERE rn > ?";
        
        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, page * pageSize);
            pstmt.setInt(2, (page - 1) * pageSize);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                NewsDto dto = new NewsDto();
                dto.setNno(rs.getInt("nno"));
                dto.setTitle(rs.getString("title"));
                dto.setCategory(rs.getString("category"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setImgfile(rs.getString("imgfile"));
                dto.setLinkUrl(rs.getString("link_url"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    public int getNewsCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) FROM hm_news";
        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) count = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return count;
    }

    public List<NewsDto> getNewsList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<NewsDto> list = new ArrayList<>();
        
        String sql = "SELECT * FROM hm_news ORDER BY nno DESC";
        
        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                NewsDto dto = new NewsDto();
                dto.setNno(rs.getInt("nno"));
                dto.setTitle(rs.getString("title"));
                dto.setCategory(rs.getString("category"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setImgfile(rs.getString("imgfile"));
                dto.setLinkUrl(rs.getString("link_url"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (Exception e) { }
        }
        return list;
    }

    public void insertNews(NewsDto dto) {
        String sql = "INSERT INTO hm_news (nno, title, category, imgfile, link_url) VALUES (hm_news_seq.NEXTVAL, ?, ?, ?, ?)";
        try (Connection conn = DBManager.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getCategory());
            pstmt.setString(3, dto.getImgfile());
            pstmt.setString(4, dto.getLinkUrl());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNews(int nno) {
        String sql = "DELETE FROM hm_news WHERE nno = ?";
        try (Connection conn = DBManager.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nno);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNews(NewsDto dto) {
        String sql = "UPDATE hm_news SET title = ?, category = ?, link_url = ? WHERE nno = ?";
        try (Connection conn = DBManager.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getCategory());
            pstmt.setString(3, dto.getLinkUrl());
            pstmt.setInt(4, dto.getNno());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
