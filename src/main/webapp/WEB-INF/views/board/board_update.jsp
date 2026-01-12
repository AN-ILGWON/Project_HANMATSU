<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<div class="container">
	<div class="form_container">
		<h2>投稿の編集</h2>
		<form action="${pageContext.request.contextPath}/board/updatepro.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bno" value="${board.bno}">
			
			<div class="form_group">
				<label for="category">カテゴリー</label>
				<select id="category" name="category" required class="form_select">
					<option value="祭りレビュー" ${board.category == '祭りレビュー' ? 'selected' : ''}>祭りレビュー</option>
					<option value="自由掲示板" ${board.category == '自由掲示板' ? 'selected' : ''}>自由掲示板</option>
					<option value="Q&A" ${board.category == 'Q&A' ? 'selected' : ''}>Q&A</option>
				</select>
			</div>

			<div class="form_group">
				<label for="title">タイトル</label>
				<input type="text" id="title" name="title" value="${board.title}" placeholder="タイトルの入力" required>
			</div>
			
			<div class="form_group">
				<label for="imgfile">画像の変更 (任意)</label>
				<div class="file_upload_wrapper">
                    <c:if test="${not empty board.imgfile}">
                        <div class="current_img_box" id="currentImageWrapper">
                            <p>現在の画像:</p>
                            <img src="${pageContext.request.contextPath}/display.do?name=${board.imgfile}" alt="Current Image" style="max-width: 150px; border-radius: 8px;">
                        </div>
                    </c:if>
                    <input type="file" id="imgfile" name="imgfile" accept="image/*" onchange="previewImage(this)">
                    <div class="file_preview" id="imagePreview">
                        <span>新しい画像を選択するとプレビューが表示されます</span>
                    </div>
                </div>
			</div>
			
			<div class="form_group">
				<label for="content">内容</label>
				<textarea id="content" name="content" placeholder="内容を入力してください" required>${board.content}</textarea>
			</div>
			
			<div class="form_actions">
				<button type="submit" class="btn_submit">修正を完了する</button>
				<a href="${pageContext.request.contextPath}/board/view.do?bno=${board.bno}" class="btn_cancel">キャンセル</a>
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
            preview.innerHTML = `<img src="${e.target.result}" style="max-width:100%; border-radius:12px; box-shadow: 0 5px 15px rgba(0,0,0,0.1);">`;
            if(currentImg) currentImg.style.opacity = '0.5';
        }
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.innerHTML = '<span>新しい画像を選択するとプレビューが表示されます</span>';
        if(currentImg) currentImg.style.opacity = '1';
    }
}
</script>

<style>
/* style.cssで共通管理 */
</style>

<%@ include file="/footer.jsp" %>
