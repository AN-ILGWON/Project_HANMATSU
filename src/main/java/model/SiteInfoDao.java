package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBManager;

public class SiteInfoDao {
    private static SiteInfoDao instance = new SiteInfoDao();
    private SiteInfoDao() {}
    public static SiteInfoDao getInstance() { return instance; }

    public SiteInfoDto getSiteInfo(String infoKey) {
        SiteInfoDto dto = null;
        String sql = "SELECT * FROM hm_site_info WHERE info_key = ?";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, infoKey);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        dto = new SiteInfoDto();
                        dto.setInfoKey(rs.getString("info_key"));
                        dto.setTitle(rs.getString("title"));
                        dto.setContent(rs.getString("content"));
                        dto.setUpdatedDate(rs.getTimestamp("updated_date"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<SiteInfoDto> getAllSiteInfo() {
        List<SiteInfoDto> list = new ArrayList<>();
        String sql = "SELECT * FROM hm_site_info ORDER BY info_key ASC";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return list;
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SiteInfoDto dto = new SiteInfoDto();
                    dto.setInfoKey(rs.getString("info_key"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    dto.setUpdatedDate(rs.getTimestamp("updated_date"));
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int updateSiteInfo(SiteInfoDto dto) {
        int result = 0;
        String sql = "UPDATE hm_site_info SET title = ?, content = ?, updated_date = SYSDATE WHERE info_key = ?";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dto.getTitle());
                pstmt.setString(2, dto.getContent());
                pstmt.setString(3, dto.getInfoKey());
                result = pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insertSiteInfo(SiteInfoDto dto) {
        int result = 0;
        String sql = "INSERT INTO hm_site_info (info_key, title, content) VALUES (?, ?, ?)";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dto.getInfoKey());
                pstmt.setString(2, dto.getTitle());
                pstmt.setString(3, dto.getContent());
                result = pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
