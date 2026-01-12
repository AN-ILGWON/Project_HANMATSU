<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/header.jsp" %>

<div class="container">
    <div class="form_container">
        <h2>新しい祭りの登録</h2>
        
        <form action="${pageContext.request.contextPath}/festival/writepro.do" method="post" enctype="multipart/form-data">
            <div class="form_group">
                <label for="region">地域</label>
                <input type="text" id="region" name="region" placeholder="例: 東京, 大阪, 京都など" required>
            </div>
            
            <div class="form_group">
                <label for="name">祭り名</label>
                <input type="text" id="name" name="name" placeholder="祭りの名前を入力してください" required>
            </div>
            
            <div class="form_row">
                <div class="form_group">
                    <label for="startDate">開始日</label>
                    <input type="date" id="startDate" name="startDate" required>
                </div>
                
                <div class="form_group">
                    <label for="endDate">終了日</label>
                    <input type="date" id="endDate" name="endDate" required>
                </div>
            </div>
            
            <div class="form_group">
                <label for="location">場所</label>
                <input type="text" id="location" name="location" required>
            </div>

            <div class="form_group">
                <label for="homepage">公式サイト URL</label>
                <input type="text" id="homepage" name="homepage" placeholder="例: https://www.festival.com">
            </div>

            <div class="form_group">
                <label for="mapUrl">Google マップ URL (埋め込み用または共有用)</label>
                <input type="text" id="mapUrl" name="mapUrl" placeholder="Googleマップの共有リンクを貼り付けてください">
            </div>
            
            <div class="form_group">
                <label for="description">紹介文</label>
                <textarea id="description" name="description" rows="10" required></textarea>
            </div>
            
            <div class="form_group">
                <label for="imgfile">イメージ画像</label>
                <div class="file_upload_wrapper">
                    <input type="file" id="imgfile" name="imgfile" accept="image/*" onchange="previewImage(this)" required>
                    <div class="file_preview" id="imagePreview">
                        <span>画像を選択してください</span>
                    </div>
                </div>
            </div>
            
            <div class="form_buttons">
                <button type="submit" class="btn_submit">登録する</button>
                <a href="${pageContext.request.contextPath}/main.do" class="btn_cancel">キャンセル</a>
            </div>
        </form>
    </div>
</div>

<script>
function previewImage(input) {
    const preview = document.getElementById('imagePreview');
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.innerHTML = `<img src="${e.target.result}" style="max-width:100%; border-radius:12px; box-shadow: 0 5px 15px rgba(0,0,0,0.1);">`;
        }
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.innerHTML = '<span>画像を選択してください</span>';
    }
}
</script>

<style>
/* 既存スタイル削除 - style.cssで共通管理 */
</style>

<%@ include file="/footer.jsp" %>
