<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container admin_py_60">
    <div class="admin_container">
        <div class="admin_page_header">
            <div class="admin_page_title">
                <h1>掲示板管理</h1>
                <p class="admin_page_subtitle">サイトのすべての投稿を管理します。</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/main.do" class="btn_admin btn_admin_secondary">ダッシュボードに戻る</a>
        </div>

        <div class="admin_table_wrap">
            <table class="admin_table">
                <thead>
                    <tr>
                        <th class="admin_w_80">BNO</th>
                        <th class="admin_w_120">カテゴリ</th>
                        <th>タイトル</th>
                        <th class="admin_w_150">投稿者</th>
                        <th class="admin_w_150">投稿日</th>
                        <th class="admin_w_80">閲覧数</th>
                        <th class="admin_w_150 admin_text_center">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="board" items="${boardList}">
                        <tr>
                            <td class="admin_td_gray">${board.bno}</td>
                            <td>
                                <span class="admin_role_badge user">
                                    ${board.category}
                                </span>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/board/view.do?bno=${board.bno}" target="_blank" class="admin_link_bold">
                                    ${board.title}
                                </a>
                            </td>
                            <td>${board.username} <span class="admin_td_gray admin_fs_xs">(${board.userid})</span></td>
                            <td class="admin_td_gray">
                                <c:choose>
                                    <c:when test="${not empty board.regdate}">
                                        ${fn:substring(board.regdate, 0, 10)}
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="admin_td_gray">${board.views}</td>
                            <td class="admin_text_center">
                                <div class="admin_flex_row admin_flex_center admin_gap_8">
                                    <a href="${pageContext.request.contextPath}/board/update.do?bno=${board.bno}" target="_blank" class="btn_admin btn_admin_secondary admin_fs_xs admin_py_6 admin_px_12">編集</a>
                                    <button onclick="deleteBoard(${board.bno})" class="btn_admin btn_admin_danger admin_fs_xs admin_py_6 admin_px_12">削除</button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
function deleteBoard(bno) {
    if (confirm('本当にこの投稿を削除しますか？')) {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/boardDelete.do',
            type: 'POST',
            data: { bno: bno },
            success: function(result) {
                if (result == 1) {
                    alert('投稿が削除されました。');
                    location.reload();
                } else {
                    alert('削除に失敗しました。');
                }
            }
        });
    }
}
</script>

<%@ include file="/footer.jsp" %>
