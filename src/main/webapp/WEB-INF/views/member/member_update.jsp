<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container auth_container">
	<div class="auth_card">
		<h2 class="auth_title">プロフィール編集</h2>
		
		<form id="updateForm" action="${pageContext.request.contextPath}/member/updatepro.do" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
			<div class="profile_upload_wrap">
				<div class="profile_preview_wrap">
					<img id="profilePreview" src="${not empty member.profileImg ? pageContext.request.contextPath.concat('/upload/profile/').concat(member.profileImg) : pageContext.request.contextPath.concat('/img/default_profile.png')}" 
						 class="profile_preview_img">
					<label for="profileImg" class="profile_upload_label">
						<i class="fas fa-camera"></i>
					</label>
					<input type="file" id="profileImg" name="profileImg" accept="image/*" class="d_none" onchange="previewImage(this)">
				</div>
				<p class="profile_upload_hint">プロフィール写真を変更するにはカメラアイコンをクリックしてください</p>
			</div>

			<div class="auth_form_group">
				<label for="userid" class="auth_label">ユーザーID</label>
				<input type="text" id="userid" name="userid" value="${member.userid}" readonly class="auth_input">
			</div>

			<div class="auth_form_group">
				<label for="email" class="auth_label">メールアドレス <span class="required">*</span></label>
				<input type="email" id="email" name="email" value="${member.email}" required class="auth_input">
				<span id="emailError" class="auth_error_msg"></span>
			</div>
			
			<div class="auth_form_group">
				<label for="phone" class="auth_label">電話番号</label>
				<input type="text" id="phone" name="phone" value="${member.phone}" class="auth_input">
			</div>
			
			<div class="auth_form_row">
				<div class="auth_form_group">
					<label for="lastNameKanji" class="auth_label">姓（漢字） <span class="required">*</span></label>
					<input type="text" id="lastNameKanji" name="lastNameKanji" value="${member.lastNameKanji}" required class="auth_input">
				</div>
				<div class="auth_form_group">
					<label for="firstNameKanji" class="auth_label">名（漢字） <span class="required">*</span></label>
					<input type="text" id="firstNameKanji" name="firstNameKanji" value="${member.firstNameKanji}" required class="auth_input">
				</div>
			</div>
			
			<div class="auth_form_row">
				<div class="auth_form_group">
					<label for="lastNameKana" class="auth_label">姓（カタカナ） <span class="required">*</span></label>
					<input type="text" id="lastNameKana" name="lastNameKana" value="${member.lastNameKana}" required class="auth_input">
				</div>
				<div class="auth_form_group">
					<label for="firstNameKana" class="auth_label">名（カタカナ） <span class="required">*</span></label>
					<input type="text" id="firstNameKana" name="firstNameKana" value="${member.firstNameKana}" required class="auth_input">
				</div>
			</div>
			
			<div class="auth_form_group">
				<label for="nickname" class="auth_label">ニックネーム <span class="required">*</span></label>
				<div class="auth_input_btn_group">
					<input type="text" id="nickname" name="nickname" value="${member.nickname}" required class="auth_input">
					<button type="button" class="btn_check" onclick="checkNickname()">重複確認</button>
				</div>
				<span id="nicknameCheckMsg"></span>
			</div>
			
			<div class="auth_actions">
				<button type="button" class="auth_btn_cancel" onclick="history.back()">キャンセル</button>
				<button type="submit" class="auth_btn_submit">修正を保存する</button>
			</div>
		</form>
	</div>
</div>

<script>
let isNicknameChecked = true; 
const originalNickname = "${member.nickname}";

function previewImage(input) {
	if (input.files && input.files[0]) {
		const reader = new FileReader();
		reader.onload = function(e) {
			$('#profilePreview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function checkNickname() {
	const nickname = $('#nickname').val();
	if(!nickname) {
		alert('ニックネームを入力してください');
		return;
	}
	
	if(nickname === originalNickname) {
		$('#nicknameCheckMsg').text('現在のニックネームと同じです。').attr('class', 'auth_success_msg');
		isNicknameChecked = true;
		return;
	}
	
	$.ajax({
		url: '${pageContext.request.contextPath}/member/nicknameCheck.do',
		type: 'POST',
		data: { nickname: nickname },
		success: function(result) {
			if(result.trim() == "1") {
				$('#nicknameCheckMsg').text('既に使用されているニックネームです。').attr('class', 'auth_error_msg');
				isNicknameChecked = false;
			} else {
				$('#nicknameCheckMsg').text('使用可能なニックネームです。').attr('class', 'auth_success_msg');
				isNicknameChecked = true;
			}
		}
	});
}

function validateForm() {
	let isValid = true;
	
	const email = $('#email').val();
	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	if (!emailRegex.test(email)) {
		$('#emailError').text('正しいメールアドレスの形式で入力してください。');
		isValid = false;
	}
	
	if (!isNicknameChecked) {
		alert('ニックネームの重複確認を行ってください。');
		isValid = false;
	}
	
	return isValid;
}

$('#nickname').on('input', function() {
	if($(this).val() === originalNickname) {
		isNicknameChecked = true;
		$('#nicknameCheckMsg').text('').attr('class', '');
	} else {
		isNicknameChecked = false;
		$('#nicknameCheckMsg').text('重複確認が必要です。').attr('class', 'auth_warning_msg');
	}
});
</script>

<%@ include file="/footer.jsp" %>
