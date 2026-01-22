<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/header.jsp" %>

<div class="container admin_py_60">
    <div class="admin_container">
        <div class="admin_page_header">
            <div class="admin_page_title">
                <h1>ニュース管理</h1>
                <p class="admin_page_subtitle">最新ニュースの登録と管理を行います。</p>
            </div>
            <button onclick="openModal()" class="btn_admin btn_admin_primary">
                <i class="fas fa-plus admin_mr_8"></i>新規登録
            </button>
        </div>

        <div class="admin_table_wrap">
            <table class="admin_table">
                <thead>
                    <tr>
                        <th style="width: 80px;" class="admin_text_center">No</th>
                        <th>ニュース詳細</th>
                        <th style="width: 120px;" class="admin_text_center">カテゴリ</th>
                        <th style="width: 150px;" class="admin_text_center">登録日</th>
                        <th style="width: 180px;" class="admin_text_center">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="news" items="${newsList}">
                        <tr>
                            <td class="admin_text_center admin_td_gray admin_fw_semibold">${news.nno}</td>
                            <td>
                                <div class="admin_flex_row admin_align_center">
                                    <div class="admin_mr_12" style="position: relative;">
                                        <c:choose>
                                            <c:when test="${not empty news.imgfile}">
                                                <img src="${pageContext.request.contextPath}/display.do?name=${news.imgfile}" alt="" class="admin_thumb_mini">
                                            </c:when>
                                            <c:otherwise>
                                                <div class="admin_thumb_mini admin_flex_row admin_flex_center admin_align_center" style="background: #f8f9fa; color: #ccc;">
                                                    <i class="fas fa-image fa-lg"></i>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div style="flex: 1; min-width: 0;">
                                        <div class="admin_fw_semibold" style="font-size: 16px; margin-bottom: 4px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${news.title}</div>
                                        <div class="admin_td_gray admin_fs_xs admin_flex_row admin_align_center">
                                            <i class="fas fa-link admin_mr_8" style="font-size: 10px;"></i>
                                            <span style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                                                <c:choose>
                                                    <c:when test="${not empty news.linkUrl}">${news.linkUrl}</c:when>
                                                    <c:otherwise>内部コンテンツあり</c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td class="admin_text_center">
                                <span class="admin_role_badge user" style="background: rgba(255, 143, 163, 0.1); color: #ff8fa3; border: 1px solid rgba(255, 143, 163, 0.2);">
                                    <i class="fas fa-tag admin_mr_8" style="font-size: 10px;"></i>${news.category}
                                </span>
                            </td>
                            <td class="admin_text_center admin_td_gray admin_fs_sm">
                                <i class="far fa-calendar-alt admin_mr_8"></i>${fn:substring(news.regdate, 0, 10)}
                            </td>
                            <td class="admin_text_center">
                                <div class="admin_flex_row admin_flex_center admin_gap_8">
                                    <button onclick="editNews(this)" 
                                            data-nno="${news.nno}" 
                                            data-title="${fn:escapeXml(news.title)}" 
                                            data-category="${news.category}" 
                                            data-linkurl="${news.linkUrl}" 
                                            data-content="${fn:escapeXml(news.content)}"
                                            data-imgfile="${news.imgfile}"
                                            class="btn_admin btn_admin_secondary admin_fs_sm admin_py_6 admin_px_12"
                                            style="border: 1px solid #ddd;">
                                        <i class="fas fa-edit admin_mr_8"></i>編集
                                    </button>
                                    <button onclick="deleteNews(${news.nno})" 
                                            class="btn_admin btn_admin_danger admin_fs_sm admin_py_6 admin_px_12">
                                        <i class="fas fa-trash-alt admin_mr_8"></i>削除
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty newsList}">
                        <tr>
                            <td colspan="5" class="admin_p_40 admin_text_center admin_td_gray" style="padding: 100px 0;">
                                <div class="admin_mb_10"><i class="fas fa-folder-open fa-3x" style="opacity: 0.2;"></i></div>
                                登録されたニュースがありません。
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Modal -->
<div id="newsModal" class="admin_modal_overlay">
    <div class="admin_modal_content" style="max-width: 700px; border: 2px solid #ffeff2;">
        <h2 id="modalTitle" class="admin_modal_title" style="display: flex; align-items: center; gap: 10px;">
            <i class="fas fa-edit" style="color: #ff8fa3;"></i>
            <span>新規ニュース登録</span>
        </h2>
        <form id="newsForm" enctype="multipart/form-data">
            <input type="hidden" name="nno" id="m_nno">
            <input type="hidden" name="imgfile" id="m_imgfile">
            
            <div class="admin_form_group">
                <label class="admin_label"><i class="fas fa-heading admin_mr_8"></i>タイトル</label>
                <input type="text" name="title" id="m_title" required class="admin_input" placeholder="ニュースのタイトルを入力してください">
            </div>
            
            <div class="admin_flex_row admin_gap_20">
                <div class="admin_form_group admin_flex_1">
                    <label class="admin_label"><i class="fas fa-th-large admin_mr_8"></i>カテゴリ</label>
                    <select name="category" id="m_category" required class="admin_select">
                        <option value="祭典">祭典</option>
                        <option value="旅行">旅行</option>
                        <option value="料理">料理</option>
                        <option value="イベント">イベント</option>
                    </select>
                </div>
                <div class="admin_form_group admin_flex_1">
                    <label class="admin_label"><i class="fas fa-link admin_mr_8"></i>外部リンクURL (任意)</label>
                    <input type="url" name="linkUrl" id="m_linkUrl" class="admin_input" placeholder="https://...">
                </div>
            </div>

            <div class="admin_form_group">
                <label class="admin_label"><i class="fas fa-image admin_mr_8"></i>ニュース画像</label>
                <div id="current_img_box" class="admin_mb_10" style="display:none; padding: 10px; background: #fff9fa; border-radius: 12px; border: 1px dashed #ff8fa3;">
                    <img id="m_img_preview" src="" alt="preview" style="height: 120px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1);">
                    <p class="admin_fs_xs admin_td_gray" style="margin-top: 8px;"><i class="fas fa-info-circle admin_mr_8"></i>現在の画像</p>
                </div>
                <input type="file" name="uploadFile" id="m_uploadFile" class="admin_input" accept="image/*" style="padding: 8px;">
            </div>

            <div class="admin_form_group admin_mb_30">
                <label class="admin_label"><i class="fas fa-align-left admin_mr_8"></i>内容 (内部ニュースの場合)</label>
                <textarea name="content" id="m_content" class="admin_textarea" placeholder="ニュースの内容を入力してください。" style="height: 180px;"></textarea>
            </div>

            <div class="admin_flex_row admin_gap_8">
                <button type="button" onclick="closeModal()" class="btn_admin btn_admin_secondary admin_flex_1" style="border: 1px solid #ddd;">
                    <i class="fas fa-times admin_mr_8"></i>キャンセル
                </button>
                <button type="submit" class="btn_admin btn_admin_primary admin_flex_1">
                    <i class="fas fa-save admin_mr_8"></i>保存する
                </button>
            </div>
        </form>
    </div>
</div>

<script>
let isEdit = false;

function openModal() {
    isEdit = false;
    document.getElementById('modalTitle').innerText = '新規ニュース登録';
    document.getElementById('newsForm').reset();
    document.getElementById('m_nno').value = '';
    document.getElementById('m_imgfile').value = '';
    document.getElementById('current_img_box').style.display = 'none';
    document.getElementById('newsModal').style.display = 'flex';
}

function editNews(btn) {
    const $btn = $(btn);
    isEdit = true;
    document.getElementById('modalTitle').innerText = 'ニュース編集';
    document.getElementById('m_nno').value = $btn.data('nno');
    document.getElementById('m_title').value = $btn.data('title');
    document.getElementById('m_category').value = $btn.data('category');
    document.getElementById('m_linkUrl').value = $btn.data('linkurl') || '';
    document.getElementById('m_content').value = $btn.data('content') || '';
    
    const imgFile = $btn.data('imgfile');
    document.getElementById('m_imgfile').value = imgFile || '';
    if(imgFile) {
        document.getElementById('m_img_preview').src = '${pageContext.request.contextPath}/display.do?name=' + imgFile;
        document.getElementById('current_img_box').style.display = 'block';
    } else {
        document.getElementById('current_img_box').style.display = 'none';
    }
    
    document.getElementById('newsModal').style.display = 'flex';
}

function closeModal() {
    document.getElementById('newsModal').style.display = 'none';
}

document.getElementById('newsForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const formData = new FormData(this);
    
    // Debug: 폼 데이터 확인
    for (let pair of formData.entries()) {
        console.log(pair[0] + ': ' + pair[1]);
    }

    const url = isEdit ? '${pageContext.request.contextPath}/admin/newsUpdate.do' : '${pageContext.request.contextPath}/admin/newsInsert.do';

    fetch(url, {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(result => {
        console.log('Result:', result);
        if(result == "1") {
            alert('正常に保存されました。');
            location.reload();
        } else if(result == "-1") {
            alert('データベースエラーが発生しました。管理者に問い合わせてください。');
        } else {
            alert('保存に失敗しました。入力内容を確認してください。');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('通信中にエラーが発生しました。');
    });
});

function deleteNews(nno) {
    if(confirm('このニュースを削除しますか？')) {
        fetch('${pageContext.request.contextPath}/admin/newsDelete.do', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'nno=' + nno
        })
        .then(response => response.text())
        .then(result => {
            if(result == "1") {
                alert('削除されました。');
                location.reload();
            } else {
                alert('削除に失敗しました。');
            }
        });
    }
}
</script>

<%@ include file="/footer.jsp" %>
