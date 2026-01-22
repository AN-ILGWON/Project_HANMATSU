package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBManager;

public class BannerDao {
    public List<BannerDto> getActiveBanners() {
        List<BannerDto> list = new ArrayList<>();
        String sql = "SELECT * FROM hm_banner WHERE is_active = 'Y' ORDER BY order_no ASC";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return list;
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BannerDto dto = new BannerDto();
                    dto.setBano(rs.getInt("bano"));
                    dto.setTitle(rs.getString("title"));
                    dto.setSubtitle(rs.getString("subtitle"));
                    dto.setImgfile(rs.getString("imgfile"));
                    dto.setLinkUrl(rs.getString("link_url"));
                    dto.setOrderNo(rs.getInt("order_no"));
                    dto.setIsActive(rs.getString("is_active"));
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<BannerDto> getAllBanners() {
        List<BannerDto> list = new ArrayList<>();
        String sql = "SELECT * FROM hm_banner ORDER BY order_no ASC";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return list;
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BannerDto dto = new BannerDto();
                    dto.setBano(rs.getInt("bano"));
                    dto.setTitle(rs.getString("title"));
                    dto.setSubtitle(rs.getString("subtitle"));
                    dto.setImgfile(rs.getString("imgfile"));
                    dto.setLinkUrl(rs.getString("link_url"));
                    dto.setOrderNo(rs.getInt("order_no"));
                    dto.setIsActive(rs.getString("is_active"));
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insertBanner(BannerDto dto) {
        String sql = "INSERT INTO hm_banner (bano, title, subtitle, imgfile, link_url, order_no, is_active) " +
                     "VALUES (hm_banner_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        System.out.println("[BannerDao] insertBanner SQL: " + sql);
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dto.getTitle());
                pstmt.setString(2, dto.getSubtitle());
                pstmt.setString(3, dto.getImgfile());
                pstmt.setString(4, dto.getLinkUrl());
                pstmt.setInt(5, dto.getOrderNo());
                pstmt.setString(6, dto.getIsActive());
                int res = pstmt.executeUpdate();
                System.out.println("[BannerDao] insert result: " + res);
                return res;
            }
        } catch (Exception e) {
            System.err.println("[BannerDao] insert error: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteBanner(int bano) {
        String sql = "DELETE FROM hm_banner WHERE bano = ?";
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, bano);
                return pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateBanner(BannerDto dto) {
        String sql = "UPDATE hm_banner SET title=?, subtitle=?, imgfile=?, link_url=?, order_no=?, is_active=? WHERE bano=?";
        System.out.println("[BannerDao] updateBanner SQL: " + sql + " for bano=" + dto.getBano());
        try (Connection conn = DBManager.getInstance()) {
            if (conn == null) return 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dto.getTitle());
                pstmt.setString(2, dto.getSubtitle());
                pstmt.setString(3, dto.getImgfile());
                pstmt.setString(4, dto.getLinkUrl());
                pstmt.setInt(5, dto.getOrderNo());
                pstmt.setString(6, dto.getIsActive());
                pstmt.setInt(7, dto.getBano());
                int res = pstmt.executeUpdate();
                System.out.println("[BannerDao] update result: " + res);
                return res;
            }
        } catch (Exception e) {
            System.err.println("[BannerDao] update error: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}
