<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container admin_py_60">
    <div class="admin_container_1000">
        <div class="admin_page_header">
            <div class="admin_page_title">
                <h1>メインスライド管理</h1>
                <p class="admin_page_subtitle">トップページのバナー画像を管理します。</p>
            </div>
            <div class="admin_header_btns">
                <button onclick="showInsertModal()" class="btn_admin btn_admin_primary">新規バナー登録</button>
                <a href="${pageContext.request.contextPath}/admin/main.do" class="btn_admin btn_admin_secondary">ダッシュボードに戻る</a>
            </div>
        </div>

        <div class="admin_grid">
            <c:forEach var="banner" items="${bannerList}">
                <div class="admin_card">
                    <div class="admin_card_img_wrap">
                        <c:choose>
                            <c:when test="${empty banner.imgfile}">
                                <div class="admin_no_img_box">
                                    画像なし
                                </div>
                            </c:when>
                            <c:when test="${fn:startsWith(banner.imgfile, 'http')}">
                                <img src="${banner.imgfile}" class="admin_card_img">
                            </c:when>
                            <c:when test="${fn:startsWith(banner.imgfile, 'slide')}">
                                <!-- slide1, slide2 etc are CSS classes -->
                                <div class="${banner.imgfile} admin_slide_preview">
                                    <div class="admin_slide_overlay">
                                        CSS Class: ${banner.imgfile}
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/display.do?name=${banner.imgfile}" class="admin_card_img">
                            </c:otherwise>
                        </c:choose>
                        <div class="admin_card_badge_wrap">
                            <span class="admin_badge order">順序: ${banner.orderNo}</span>
                            <span class="admin_badge ${banner.isActive == 'Y' ? 'active' : 'inactive'}">
                                ${banner.isActive == 'Y' ? '有効' : '無効'}
                            </span>
                        </div>
                    </div>
                    <div class="admin_card_content">
                        <h3 class="admin_card_title">${banner.title}</h3>
                        <p class="admin_card_text">${banner.subtitle}</p>
                        <div class="admin_card_btns">
                            <button onclick="editBanner(this)" 
                                    data-bano="${banner.bano}" 
                                    data-title="${fn:escapeXml(banner.title)}" 
                                    data-subtitle="${fn:escapeXml(banner.subtitle)}" 
                                    data-imgfile="${banner.imgfile}" 
                                    data-linkurl="${banner.linkUrl}" 
                                    data-orderno="${banner.orderNo}" 
                                    data-isactive="${banner.isActive}" 
                                    class="btn_admin btn_admin_secondary admin_flex_1">編集</button>
                            <button onclick="deleteBanner(${banner.bano})" class="btn_admin btn_admin_danger">削除</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<!-- Insert Modal -->
<div id="bannerModal" class="admin_modal_overlay">
    <div class="admin_modal_content">
        <h2 id="modalTitle" class="admin_modal_title">バナー登録</h2>
        <form id="bannerForm" enctype="multipart/form-data">
            <input type="hidden" name="bano" id="m_bano">
            <div class="admin_form_group">
                <label class="admin_label">タイトル</label>
                <input type="text" name="title" id="m_title" class="admin_input" required>
            </div>
            <div class="admin_form_group">
                <label class="admin_label">サブタイトル</label>
                <input type="text" name="subtitle" id="m_subtitle" class="admin_input">
            </div>
            <div class="admin_form_group">
                <label class="admin_label">画像ファイル</label>
                <input type="file" name="uploadFile" id="m_uploadFile" class="admin_input admin_mb_10">
                <input type="text" name="imgfile" id="m_imgfile" placeholder="または既存のファイル名/クラス(slide1など)" class="admin_input">
            </div>
            <div class="admin_form_group">
                <label class="admin_label">リンクURL</label>
                <input type="text" name="link_url" id="m_linkUrl" class="admin_input">
            </div>
            <div class="admin_grid_2 admin_mb_30">
                <div class="admin_form_group admin_mb_0">
                    <label class="admin_label">順序</label>
                    <input type="number" name="order_no" id="m_orderNo" value="1" class="admin_input">
                </div>
                <div id="activeGroup" class="admin_form_group admin_d_none admin_mb_0">
                    <label class="admin_label">状態</label>
                    <select name="is_active" id="m_isActive" class="admin_select">
                        <option value="Y">有効</option>
                        <option value="N">無効</option>
                    </select>
                </div>
            </div>
            <div class="admin_flex_row admin_gap_10">
                <button type="submit" class="btn_admin btn_admin_primary admin_flex_2 admin_p_15">保存</button>
                <button type="button" onclick="closeModal()" class="btn_admin btn_admin_secondary admin_flex_1 admin_p_15">キャンセル</button>
            </div>
        </form>
    </div>
</div>

<script>
let isEdit = false;

function showInsertModal() {
    isEdit = false;
    $('#modalTitle').text('新規バナー登録');
    $('#bannerForm')[0].reset();
    $('#m_bano').val('');
    $('#activeGroup').hide();
    $('#bannerModal').css('display', 'flex');
}

function editBanner(btn) {
    const $btn = $(btn);
    isEdit = true;
    $('#modalTitle').text('バナー編集');
    $('#m_bano').val($btn.data('bano'));
    $('#m_title').val($btn.data('title'));
    $('#m_subtitle').val($btn.data('subtitle'));
    $('#m_imgfile').val($btn.data('imgfile'));
    $('#m_linkUrl').val($btn.data('linkurl'));
    $('#m_orderNo').val($btn.data('orderno'));
    $('#m_isActive').val($btn.data('isactive'));
    $('#activeGroup').show();
    $('#bannerModal').css('display', 'flex');
}

function closeModal() {
    $('#bannerModal').hide();
}

$('#bannerForm').on('submit', function(e) {
    e.preventDefault();
    const url = isEdit ? '${pageContext.request.contextPath}/admin/bannerUpdate.do' : '${pageContext.request.contextPath}/admin/bannerInsert.do';
    
    let formData = new FormData(this);
    
    console.log('Submitting to:', url);
    for (let pair of formData.entries()) {
        console.log(pair[0]+ ': ' + pair[1]); 
    }
    
    $.ajax({
                url: url,
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(result) {
                    console.log('Server response raw:', result);
                    // 空白を除去して比較
                    const cleanResult = String(result).trim();
                    if (cleanResult == "1") {
                        alert('保存されました！');
                        location.reload();
                    } else {
                        console.error('Save failed with result:', cleanResult);
                        alert('保存に失敗しました。結果: ' + cleanResult);
                    }
                },
                error: function(xhr, status, error) {
                    console.error('AJAX Error details:', {
                        status: status,
                        error: error,
                        responseText: xhr.responseText
                    });
                    alert('通信エラーが発生しました。詳細はコンソールを確認してください。');
                }
            });
});

function deleteBanner(bano) {
    if (confirm('本当にこのバナーを削除しますか？')) {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/bannerDelete.do',
            type: 'POST',
            data: { bano: bano },
            success: function(result) {
                if (result == 1) {
                    alert('削除されました。');
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
