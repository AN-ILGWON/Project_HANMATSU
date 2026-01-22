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
                    "    SELECT nno, title, category, TO_CHAR(regdate, 'YYYY-MM-DD') as regdate, imgfile, link_url, content FROM hm_news ORDER BY nno DESC) aaa " +
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
                dto.setContent(rs.getString("content"));
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
        
        String sql = "SELECT nno, title, category, TO_CHAR(regdate, 'YYYY-MM-DD') as regdate, imgfile, link_url, content FROM hm_news ORDER BY nno DESC";
        
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
                dto.setContent(rs.getString("content"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    public int insertNews(NewsDto dto) {
        String sql = "INSERT INTO hm_news (nno, title, category, imgfile, link_url, content) VALUES (hm_news_seq.NEXTVAL, ?, ?, ?, ?, ?)";
        System.out.println("[NewsDao] Attempting to insert news: " + dto.getTitle());
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) {
                System.err.println("[NewsDao] Error: Database connection is null");
                return 0;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dto.getTitle());
                pstmt.setString(2, dto.getCategory());
                pstmt.setString(3, dto.getImgfile());
                pstmt.setString(4, dto.getLinkUrl());
                pstmt.setString(5, dto.getContent());
                
                int result = pstmt.executeUpdate();
                System.out.println("[NewsDao] Insert successful. Rows affected: " + result);
                return result;
            }
        } catch (Exception e) {
            System.err.println("[NewsDao] Error during insertNews: " + e.getMessage());
            e.printStackTrace();
            return -1; // 에러 발생 시 -1 반환하여 구분
        }
    }

    public int deleteNews(int nno) {
        String sql = "DELETE FROM hm_news WHERE nno = ?";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, nno);
                return pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateNews(NewsDto dto) {
        String sql = "UPDATE hm_news SET title = ?, category = ?, link_url = ?, content = ?, imgfile = ? WHERE nno = ?";
        System.out.println("[NewsDao] Attempting to update news nno: " + dto.getNno());
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) {
                System.err.println("[NewsDao] Error: Database connection is null during update");
                return 0;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dto.getTitle());
                pstmt.setString(2, dto.getCategory());
                pstmt.setString(3, dto.getLinkUrl());
                pstmt.setString(4, dto.getContent());
                pstmt.setString(5, dto.getImgfile());
                pstmt.setInt(6, dto.getNno());
                
                int result = pstmt.executeUpdate();
                System.out.println("[NewsDao] Update successful. Rows affected: " + result);
                return result;
            }
        } catch (Exception e) {
            System.err.println("[NewsDao] Error during updateNews: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public NewsDto getNewsByNno(int nno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NewsDto dto = null;
        String sql = "SELECT nno, title, category, TO_CHAR(regdate, 'YYYY-MM-DD') as regdate, imgfile, link_url, content FROM hm_news WHERE nno = ?";
        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nno);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                dto = new NewsDto();
                dto.setNno(rs.getInt("nno"));
                dto.setTitle(rs.getString("title"));
                dto.setCategory(rs.getString("category"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setImgfile(rs.getString("imgfile"));
                dto.setLinkUrl(rs.getString("link_url"));
                dto.setContent(rs.getString("content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return dto;
    }
}
