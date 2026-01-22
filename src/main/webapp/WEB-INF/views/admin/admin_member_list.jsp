<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container admin_py_60">
    <div class="admin_container">
        <div class="admin_page_header">
            <div class="admin_page_title">
                <h1>会員管理</h1>
                <p class="admin_page_subtitle">サイトのすべての会員を管理します。</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/main.do" class="btn_admin btn_admin_secondary">ダッシュボードに戻る</a>
        </div>

        <div class="admin_table_wrap">
            <table class="admin_table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>ニックネーム</th>
                        <th>名前</th>
                        <th>メール</th>
                        <th>権限</th>
                        <th>登録日</th>
                        <th class="admin_text_center">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="member" items="${memberList}">
                        <tr>
                            <td class="admin_td_id">${member.userid}</td>
                            <td>${member.nickname}</td>
                            <td>${member.lastNameKanji} ${member.firstNameKanji}</td>
                            <td class="admin_td_gray">${member.email}</td>
                            <td>
                                <span class="admin_role_badge ${member.role == 'ADMIN' ? 'admin' : 'user'}">
                                    ${member.role}
                                </span>
                            </td>
                            <td class="admin_td_gray">
                                <c:choose>
                                    <c:when test="${not empty member.regdate}">
                                        ${fn:substring(member.regdate, 0, 10)}
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="admin_text_center">
                                <c:if test="${member.role != 'ADMIN'}">
                                    <button onclick="deleteMember('${member.userid}')" class="btn_admin btn_admin_danger admin_fs_xs admin_py_6 admin_px_12">削除</button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
function deleteMember(userid) {
    if (confirm('本当にこの会員を削除しますか？関連するデータもすべて削除される可能性があります。')) {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/memberDelete.do',
            type: 'POST',
            data: { userid: userid },
            success: function(result) {
                if (result == 1) {
                    alert('会員が正常に削除されました。');
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
