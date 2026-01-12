<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/header.jsp" %>

<div class="container">
    <div class="form_container">
        <h2>祭り情報の編集</h2>
        
        <form action="${pageContext.request.contextPath}/festival/updatepro.do" method="post" enctype="multipart/form-data">
            <input type="hidden" name="fno" value="${festival.fno}">
            
            <div class="form_group">
                <label for="region">地域</label>
                <input type="text" id="region" name="region" value="${festival.region}" required>
            </div>
            
            <div class="form_group">
                <label for="name">祭り名</label>
                <input type="text" id="name" name="name" value="${festival.name}" required>
            </div>
            
            <div class="form_row">
                <div class="form_group">
                    <label for="startDate">開始日</label>
                    <input type="date" id="startDate" name="startDate" value="${fn:substring(festival.startDate, 0, 10)}" required>
                </div>
                
                <div class="form_group">
                    <label for="endDate">終了日</label>
                    <input type="date" id="endDate" name="endDate" value="${fn:substring(festival.endDate, 0, 10)}" required>
                </div>
            </div>
            
            <div class="form_group">
                <label for="location">場所</label>
                <input type="text" id="location" name="location" value="${festival.location}" required>
            </div>

            <div class="form_group">
                <label for="homepage">公式サイト URL</label>
                <input type="text" id="homepage" name="homepage" value="${festival.homepage}" placeholder="例: https://www.festival.com">
            </div>

            <div class="form_group">
                <label for="mapUrl">Google マップ URL (埋め込み用または共有用)</label>
                <input type="text" id="mapUrl" name="mapUrl" value="${festival.mapUrl}" placeholder="Googleマップの共有リンクを貼り付けてください">
            </div>
            
            <div class="form_group">
                <label for="description">紹介文</label>
                <textarea id="description" name="description" rows="10" required>${festival.description}</textarea>
            </div>
            
            <div class="form_group">
                <label for="imgfile">イメージ画像 (変更する場合のみ選択)</label>
                <div class="file_upload_wrapper">
                    <c:if test="${not empty festival.imgfile}">
                        <div class="current_img" id="currentImageWrapper">
                            <p>現在の画像:</p>
                            <img src="${pageContext.request.contextPath}/display.do?name=${festival.imgfile}" alt="Current Image" style="max-width: 200px; border-radius: 8px;">
                        </div>
                    </c:if>
                    <input type="file" id="imgfile" name="imgfile" accept="image/*" onchange="previewImage(this)">
                    <div class="file_preview" id="imagePreview">
                        <c:if test="${empty festival.imgfile}">
                            <span>画像を選択してください</span>
                        </c:if>
                    </div>
                </div>
            </div>
            
            <div class="form_buttons">
                <button type="submit" class="btn_submit">更新する</button>
                <a href="${pageContext.request.contextPath}/festival/view.do?fno=${festival.fno}" class="btn_cancel">キャンセル</a>
            </div>
        </form>
    </div>
</div>

<script>
function previewImage(input) {
    const preview = document.getElementById('imagePreview');
    const currentImg = document.getElementById('currentImageWrapper');
    
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.innerHTML = `<p>新しい画像プレビュー:</p><img src="${e.target.result}" style="max-width:100%; border-radius:12px; box-shadow: 0 5px 15px rgba(0,0,0,0.1);">`;
            if(currentImg) currentImg.style.opacity = '0.5';
        }
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.innerHTML = '';
        if(currentImg) currentImg.style.opacity = '1';
    }
}
</script>

<style>
/* style.cssで共通管理 */
</style>

<%@ include file="/footer.jsp" %>
