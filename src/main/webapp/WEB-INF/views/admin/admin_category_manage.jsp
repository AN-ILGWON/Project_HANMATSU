<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container admin_py_60">
    <div class="admin_container_sm">
        <div class="admin_page_header">
            <div class="admin_page_title">
                <h1>掲示板カテゴリ管理</h1>
                <p class="admin_page_subtitle">コミュニティで使用するカテゴリを管理します。</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/main.do" class="btn_admin btn_admin_secondary">ダッシュボードに戻る</a>
        </div>

        <div class="admin_card admin_p_40 admin_mb_40">
            <h3 class="admin_card_title admin_mb_20">新規カテゴリ追加</h3>
            <form id="categoryForm" class="admin_flex_row">
                <input type="hidden" name="type" value="BOARD">
                <input type="text" name="name" placeholder="カテゴリ名 (例: 料理, 交通, 宿泊)" class="admin_input admin_flex_1" required>
                <button type="submit" class="btn_admin btn_admin_primary admin_py_12 admin_px_30">追加する</button>
            </form>
        </div>

        <div class="admin_table_wrap">
            <table class="admin_table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>カテゴリ名</th>
                        <th>タイプ</th>
                        <th class="admin_text_center">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cat" items="${categoryList}">
                        <tr>
                            <td class="admin_td_gray">${cat.cno}</td>
                            <td class="admin_fw_bold admin_text_dark">${cat.name}</td>
                            <td class="admin_td_gray admin_fs_sm">${cat.type}</td>
                            <td class="admin_text_center">
                                <button onclick="deleteCategory(${cat.cno})" class="btn_admin btn_admin_danger admin_fs_xs admin_py_6 admin_px_12">削除</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
$('#categoryForm').on('submit', function(e) {
    e.preventDefault();
    $.ajax({
        url: '${pageContext.request.contextPath}/admin/categoryInsert.do',
        type: 'POST',
        data: $(this).serialize(),
        success: function(result) {
            if (result == 1) {
                alert('カテゴリが追加されました。');
                location.reload();
            } else {
                alert('追加に失敗しました。名前が重複している可能性があります。');
            }
        }
    });
});

function deleteCategory(cno) {
    if (confirm('本当にこのカテゴリを削除しますか？')) {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/categoryDelete.do',
            type: 'POST',
            data: { cno: cno },
            success: function(result) {
                if (result == 1) {
                    alert('カテゴリが削除されました。');
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
