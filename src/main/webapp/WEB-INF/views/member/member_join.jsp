<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container auth_container">
	<div class="auth_card">
		<h2 class="auth_title">新規会員登録</h2>
		<div class="auth_login_link">
			既にアカウントをお持ちですか？ <a href="${pageContext.request.contextPath}/member/login.do">ログイン</a>
		</div>
		
		<form id="joinForm" action="${pageContext.request.contextPath}/member/memberSave.do" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
			<div class="profile_upload_wrap">
				<div class="profile_preview_wrap">
					<img id="profilePreview" src="${pageContext.request.contextPath}/img/default_profile.png" class="profile_preview_img">
					<label for="profileImg" class="profile_upload_label">
						<i class="fas fa-camera"></i>
					</label>
					<input type="file" id="profileImg" name="profileImg" accept="image/*" class="d_none" onchange="previewImage(this)">
				</div>
				<p class="profile_upload_hint">プロフィール写真を設定（任意）</p>
			</div>

			<div class="auth_form_group">
				<label for="userid" class="auth_label">ユーザーID <span class="required">*</span></label>
				<div class="auth_input_btn_group">
					<input type="text" id="userid" name="userid" required class="auth_input">
					<button type="button" class="btn_check" onclick="checkUserId()">重複確認</button>
				</div>
				<span id="userIdCheckMsg"></span>
			</div>

			<div class="auth_form_group">
				<label for="email" class="auth_label">メールアドレス <span class="required">*</span></label>
				<input type="email" id="email" name="email" placeholder="example@domain.com" required class="auth_input">
				<span id="emailError" class="auth_error_msg"></span>
			</div>
			
			<div class="auth_form_group">
				<label for="password" class="auth_label">パスワード <span class="required">*</span></label>
				<p class="auth_hint">10文字以上で、英字と記号を組み合わせて入力してください。</p>
				<input type="password" id="password" name="password" required class="auth_input">
				<span id="passwordError" class="auth_error_msg"></span>
			</div>
			
			<div class="auth_form_group">
				<label for="password_confirm" class="auth_label">パスワード再入力 <span class="required">*</span></label>
				<input type="password" id="password_confirm" name="password_confirm" required class="auth_input">
				<span id="passwordConfirmError" class="auth_error_msg"></span>
			</div>
			
			<div class="auth_checkbox_group">
				<input type="checkbox" id="showPassword" onclick="togglePassword()">
				<label for="showPassword">パスワードを表示する</label>
			</div>
			
			<div class="auth_form_row">
				<div class="auth_form_group">
					<label for="lastNameKanji" class="auth_label">姓（漢字） <span class="required">*</span></label>
					<input type="text" id="lastNameKanji" name="lastNameKanji" required class="auth_input">
				</div>
				<div class="auth_form_group">
					<label for="firstNameKanji" class="auth_label">名（漢字） <span class="required">*</span></label>
					<input type="text" id="firstNameKanji" name="firstNameKanji" required class="auth_input">
				</div>
			</div>
			
			<div class="auth_form_row">
				<div class="auth_form_group">
					<label for="lastNameKana" class="auth_label">姓（カタカナ） <span class="required">*</span></label>
					<input type="text" id="lastNameKana" name="lastNameKana" required class="auth_input">
				</div>
				<div class="auth_form_group">
					<label for="firstNameKana" class="auth_label">名（カタカナ） <span class="required">*</span></label>
					<input type="text" id="firstNameKana" name="firstNameKana" required class="auth_input">
				</div>
			</div>
			
			<div class="auth_form_group">
				<label for="nickname" class="auth_label">ニックネーム <span class="required">*</span></label>
				<div class="auth_input_btn_group">
					<input type="text" id="nickname" name="nickname" required class="auth_input">
					<button type="button" class="btn_check" onclick="checkNickname()">重複確認</button>
				</div>
				<span id="nicknameCheckMsg"></span>
			</div>
			
			<div class="auth_agreement_area">
				<p>以下に同意いただいた上で、会員登録をお願いいたします。</p>
				<div class="auth_checkbox_group mb_0">
					<input type="checkbox" id="agree" name="agree" required>
					<label for="agree">利用規約およびプライバシーポリシーに同意する</label>
				</div>
			</div>
			
			<div class="auth_actions">
				<button type="submit" class="auth_btn_submit flex_1">会員登録</button>
			</div>
		</form>
	</div>
</div>

<script>
let isUserIdChecked = false;
let isNicknameChecked = false;

function togglePassword() {
	const password = document.getElementById("password");
	const confirm = document.getElementById("password_confirm");
	if (password.type === "password") {
		password.type = "text";
		confirm.type = "text";
	} else {
		password.type = "password";
		confirm.type = "password";
	}
}

function previewImage(input) {
	if (input.files && input.files[0]) {
		const reader = new FileReader();
		reader.onload = function(e) {
			$('#profilePreview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function checkUserId() {
	const userid = $('#userid').val();
	if(!userid) {
		alert('ユーザーIDを入力してください');
		return;
	}
	
	$.ajax({
		url: '${pageContext.request.contextPath}/member/userIdCheck.do',
		type: 'POST',
		data: { userid: userid },
		success: function(result) {
			if(result.trim() == "1") {
				$('#userIdCheckMsg').text('このユーザーIDは既に使用されています。').attr('class', 'auth_error_msg');
				isUserIdChecked = false;
			} else {
				$('#userIdCheckMsg').text('このユーザーIDは使用可能です。').attr('class', 'auth_success_msg');
				isUserIdChecked = true;
			}
		}
	});
}

function checkNickname() {
	const nickname = $('#nickname').val();
	if(!nickname) {
		alert('ニックネームを入力してください');
		return;
	}
	
	$.ajax({
		url: '${pageContext.request.contextPath}/member/nicknameCheck.do',
		type: 'POST',
		data: { nickname: nickname },
		success: function(result) {
			if(result.trim() == "1") {
				$('#nicknameCheckMsg').text('このニックネームは既に使用されています。').attr('class', 'auth_error_msg');
				isNicknameChecked = false;
			} else {
				$('#nicknameCheckMsg').text('このニックネームは使用可能です。').attr('class', 'auth_success_msg');
				isNicknameChecked = true;
			}
		}
	});
}

function validateForm() {
	let isValid = true;
	
	// Reset errors
	$('.auth_error_msg').text('');
	
	const email = $('#email').val();
	const password = $('#password').val();
	const passwordConfirm = $('#password_confirm').val();
	
	// Email check
	const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	if (!emailRegex.test(email)) {
		$('#emailError').text('正しいメールアドレスの形式で入力してください。');
		isValid = false;
	}
	
	// Password check: 10+ chars, letters + special chars
	const passwordRegex = /^(?=.*[A-Za-z])(?=.*[!@#$%^&*(),.?":{}|<>]).{10,}$/;
	if (!passwordRegex.test(password)) {
		$('#passwordError').text('パスワードは10文字以上で、英字と記号を含む必要があります。');
		isValid = false;
	}
	
	if (password !== passwordConfirm) {
		$('#passwordConfirmError').text('パスワードが一致しません。');
		isValid = false;
	}
	
	if (!isUserIdChecked) {
		alert('ユーザーIDの重複確認を行ってください。');
		isValid = false;
	}

	if (!isNicknameChecked) {
		alert('ニックネームの重複確認を行ってください。');
		isValid = false;
	}
	
	return isValid;
}

// Reset checks on change
$('#userid').on('input', function() {
	isUserIdChecked = false;
	$('#userIdCheckMsg').text('');
});
$('#nickname').on('input', function() {
	isNicknameChecked = false;
	$('#nicknameCheckMsg').text('');
});
</script>

<%@ include file="/footer.jsp" %>
