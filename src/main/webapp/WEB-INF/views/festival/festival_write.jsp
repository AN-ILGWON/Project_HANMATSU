<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/header.jsp" %>

<div class="container">
    <div class="form_container">
        <h2>新しい祭りの登録</h2>
        
        <form action="${pageContext.request.contextPath}/festival/writepro.do" method="post" enctype="multipart/form-data">
            <div class="form_group">
                <label for="region"><i class="fas fa-map-marker-alt"></i> 地域</label>
                <input type="text" id="region" name="region" placeholder="例: 東京, 大阪, 京都など" required>
            </div>
            
            <div class="form_group">
                <label for="name"><i class="fas fa-flag"></i> 祭り名</label>
                <input type="text" id="name" name="name" placeholder="祭りの名前を入力してください" required>
            </div>
            
            <div class="form_row">
                <div class="form_group">
                    <label for="startDate"><i class="far fa-calendar-alt"></i> 開始日</label>
                    <input type="date" id="startDate" name="startDate" required>
                </div>
                
                <div class="form_group">
                    <label for="endDate"><i class="far fa-calendar-alt"></i> 終了日</label>
                    <input type="date" id="endDate" name="endDate" required>
                </div>
            </div>
            
            <div class="form_group">
                <label for="location"><i class="fas fa-map-marker-alt"></i> 場所</label>
                <input type="text" id="location" name="location" required>
            </div>

            <div class="form_group">
                <label for="homepage"><i class="fas fa-globe"></i> 公式サイト URL</label>
                <input type="text" id="homepage" name="homepage" placeholder="例: https://www.festival.com">
            </div>

            <div class="form_group">
                <label for="instagram"><i class="fa-brands fa-instagram"></i> インスタグラム URL</label>
                <input type="text" id="instagram" name="instagram" placeholder="例: https://www.instagram.com/account">
            </div>

            <div class="form_group">
                <label for="mapUrl"><i class="fas fa-map-marker-alt"></i> Google マップ URL</label>
                <input type="text" id="mapUrl" name="mapUrl" placeholder="Googleマップの共有リンクを貼り付けてください">
            </div>

            <div class="form_group">
                <label for="likes"><i class="fas fa-heart"></i> お気に入り数 (管理者専用)</label>
                <input type="number" id="likes" name="likes" value="0" min="0">
            </div>
            
            <div class="form_group">
                <label for="description"><i class="fas fa-align-left"></i> 紹介文</label>
                <textarea id="description" name="description"></textarea>
            </div>
            
            <div class="form_group">
                <label for="imgfile"><i class="far fa-image"></i> イメージ画像</label>
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
$(document).ready(function() {
    $('#description').summernote({
        placeholder: '祭りの紹介文を入力してください',
        tabsize: 2,
        height: 300,
        lang: 'ja-JP',
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ]
    });
});

function previewImage(input) {
    const preview = document.getElementById('imagePreview');
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.innerHTML = `<img src="${e.target.result}">`;
        }
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.innerHTML = '<span>画像を選択してください</span>';
    }
}
</script>

<%@ include file="/footer.jsp" %>
