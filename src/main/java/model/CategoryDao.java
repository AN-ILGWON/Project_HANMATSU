package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBManager;

public class CategoryDao {
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> list = new ArrayList<>();
        String sql = "SELECT * FROM hm_category ORDER BY type ASC, cno ASC";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return list;
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CategoryDto dto = new CategoryDto();
                    dto.setCno(rs.getInt("cno"));
                    dto.setName(rs.getString("name"));
                    dto.setType(rs.getString("type"));
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CategoryDto> getCategoriesByType(String type) {
        List<CategoryDto> list = new ArrayList<>();
        String sql = "SELECT * FROM hm_category WHERE type = ? ORDER BY cno ASC";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return list;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, type);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        CategoryDto dto = new CategoryDto();
                        dto.setCno(rs.getInt("cno"));
                        dto.setName(rs.getString("name"));
                        dto.setType(rs.getString("type"));
                        list.add(dto);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insertCategory(CategoryDto dto) {
        String sql = "INSERT INTO hm_category (cno, name, type) VALUES (hm_category_seq.NEXTVAL, ?, ?)";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dto.getName());
                pstmt.setString(2, dto.getType());
                return pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteCategory(int cno) {
        String sql = "DELETE FROM hm_category WHERE cno = ?";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, cno);
                return pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
