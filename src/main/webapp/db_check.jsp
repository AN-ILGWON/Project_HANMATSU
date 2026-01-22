<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, util.DBManager" %>
<!DOCTYPE html>
<html>
<head>
<title>DB Check</title>
</head>
<body>
    <h2>Database Connection & Data Check</h2>
    <%
        try (Connection conn = DBManager.getInstance()) {
            if (conn != null) {
                out.println("<p style='color:green'>Connection Successful!</p>");
                
                String[] tables = {"hm_member", "hm_festival", "hm_board", "hm_news", "hm_banner"};
                for (String table : tables) {
                    try (PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + table);
                         ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            out.println("<p>" + table + " count: <b>" + rs.getInt(1) + "</b></p>");
                        }
                    } catch (Exception e) {
                        out.println("<p style='color:red'>Error checking " + table + ": " + e.getMessage() + "</p>");
                    }
                }
            } else {
                out.println("<p style='color:red'>Connection Failed!</p>");
            }
        } catch (Exception e) {
            out.println("<p style='color:red'>Error: " + e.getMessage() + "</p>");
            e.printStackTrace(new java.io.PrintWriter(out));
        }
    %>
</body>
</html>
