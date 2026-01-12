<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<div class="container">
	<div class="form_container">
		<h2>コミュニティ投稿</h2>
		<form action="${pageContext.request.contextPath}/board/writepro.do" method="post" enctype="multipart/form-data">
			<div class="form_group">
				<label for="category">カテゴリー</label>
				<select id="category" name="category" required class="form_select">
					<option value="祭りレビュー" selected>祭りレビュー</option>
					<option value="自由掲示板">自由掲示板</option>
					<option value="Q&A">Q&A</option>
				</select>
			</div>

			<div class="form_group">
				<label for="title">タイトル</label>
				<input type="text" id="title" name="title" placeholder="タイトルの入力" required>
			</div>
			
			<div class="form_group">
				<label for="imgfile">思い出の写真 (任意)</label>
				<div class="file_upload_wrapper">
                    <input type="file" id="imgfile" name="imgfile" accept="image/*" onchange="previewImage(this)">
                    <div class="file_preview" id="imagePreview">
                        <span>画像を選択してください</span>
                    </div>
                </div>
			</div>
			
			<div class="form_group">
				<label for="content">内容</label>
				<textarea id="content" name="content" placeholder="楽しい思い出をお聞かせください。" required></textarea>
			</div>
			
			<div class="form_actions">
				<button type="submit" class="btn_submit">投稿を完了する</button>
				<a href="${pageContext.request.contextPath}/board/list.do" class="btn_cancel">キャンセル</a>
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
