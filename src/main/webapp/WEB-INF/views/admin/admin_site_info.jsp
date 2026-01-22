<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container admin_py_60">
    <div class="admin_container">
        <div class="admin_page_header">
            <div class="admin_page_title">
                <h1>サイト情報管理</h1>
                <p class="admin_page_subtitle">FAQ、会社紹介、利用規約などのコンテンツを編集できます。</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/main.do" class="btn_admin btn_admin_secondary">ダッシュボードに戻る</a>
        </div>

        <div class="admin_table_wrap">
            <table class="admin_table">
                <thead>
                    <tr>
                        <th>キー</th>
                        <th>タイトル</th>
                        <th>最終更新日</th>
                        <th class="admin_text_center">管理</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="info" items="${siteInfoList}">
                        <tr>
                            <td class="admin_td_id">${info.infoKey}</td>
                            <td class="admin_fw_bold">${info.title}</td>
                            <td class="admin_td_gray">${info.updatedDate}</td>
                            <td class="admin_text_center">
                                <button onclick="editInfo('${info.infoKey}', '${info.title}')" class="btn_admin btn_admin_primary admin_fs_xs">編集</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="editArea" class="admin_card admin_p_40 admin_mt_40 admin_d_none">
            <h3 id="editTitleHeader" class="admin_card_title admin_mb_20">コンテンツ編集</h3>
            <form id="infoForm">
                <input type="hidden" id="infoKey" name="infoKey">
                
                <div class="admin_form_group">
                    <label class="admin_label">タイトル</label>
                    <input type="text" id="infoTitle" name="title" class="admin_input" required>
                </div>
                
                <div class="admin_form_group">
                    <label class="admin_label">内容 (HTML可)</label>
                    <textarea id="infoContent" name="content" class="admin_textarea" required></textarea>
                </div>
                
                <div class="admin_flex_row admin_flex_end admin_mt_30">
                    <button type="button" onclick="$('#editArea').hide()" class="btn_admin btn_admin_secondary">キャンセル</button>
                    <button type="button" onclick="saveInfo()" class="btn_admin btn_admin_primary">保存する</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function editInfo(key, title) {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/getSiteInfo.do',
            type: 'GET',
            data: { key: key },
            success: function(data) {
                var info = JSON.parse(data);
                $('#infoKey').val(info.infoKey);
                $('#infoTitle').val(info.title);
                $('#infoContent').val(info.content);
                $('#editTitleHeader').text('「' + info.title + '」の編集');
                $('#editArea').show();
                $('html, body').animate({
                    scrollTop: $("#editArea").offset().top - 100
                }, 500);
            }
        });
    }

    function saveInfo() {
        var formData = $('#infoForm').serialize();
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/siteInfoUpdate.do',
            type: 'POST',
            data: formData,
            success: function(result) {
                if (result == 1) {
                    alert('保存されました！');
                    location.reload();
                } else {
                    alert('保存に失敗しました。');
                }
            }
        });
    }
</script>

<%@ include file="/footer.jsp" %>
