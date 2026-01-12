<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<div class="container">
	<div class="join_form">
		<h2>新規会員登録</h2>
		<p class="login_link_text">既にアカウントをお持ちですか？ <a href="${pageContext.request.contextPath}/member/login.do">ログイン</a></p>
		
		<form id="joinForm" action="${pageContext.request.contextPath}/member/memberSave.do" method="post" onsubmit="return validateForm()">
			<div class="form_group">
				<label for="userid">ユーザーID <span class="required">(必須)</span></label>
				<div class="input_with_btn">
					<input type="text" id="userid" name="userid" required>
					<button type="button" class="btn_check" onclick="checkUserId()">ID確認</button>
				</div>
				<span id="userIdCheckMsg"></span>
			</div>

			<div class="form_group">
				<label for="email">メールアドレス <span class="required">(必須)</span></label>
				<input type="email" id="email" name="email" placeholder="example@domain.com" required>
				<span id="emailError" class="error_msg"></span>
			</div>
			
			<div class="form_group">
				<label for="password">パスワード <span class="required">(必須)</span></label>
				<p class="hint">10文字以上で、英字と特殊文字を含めて入力してください。</p>
				<input type="password" id="password" name="password" required>
				<span id="passwordError" class="error_msg"></span>
			</div>
			
			<div class="form_group">
				<label for="password_confirm">パスワード再入力 <span class="required">(必須)</span></label>
				<input type="password" id="password_confirm" name="password_confirm" required>
				<span id="passwordConfirmError" class="error_msg"></span>
			</div>
			
			<div class="form_group checkbox_group">
				<input type="checkbox" id="showPassword" onclick="togglePassword()">
				<label for="showPassword">パスワードを表示する</label>
			</div>
			
			<div class="form_row">
				<div class="form_group half">
					<label for="lastNameKanji">姓（漢字） <span class="required">(必須)</span></label>
					<input type="text" id="lastNameKanji" name="lastNameKanji" required>
				</div>
				<div class="form_group half">
					<label for="firstNameKanji">名（漢字） <span class="required">(必須)</span></label>
					<input type="text" id="firstNameKanji" name="firstNameKanji" required>
				</div>
			</div>
			
			<div class="form_row">
				<div class="form_group half">
					<label for="lastNameKana">姓（カタカナ） <span class="required">(必須)</span></label>
					<input type="text" id="lastNameKana" name="lastNameKana" required>
				</div>
				<div class="form_group half">
					<label for="firstNameKana">名（カタカナ） <span class="required">(必須)</span></label>
					<input type="text" id="firstNameKana" name="firstNameKana" required>
				</div>
			</div>
			
			<div class="form_group">
				<label for="nickname">ニックネーム <span class="required">(必須)</span></label>
				<div class="input_with_btn">
					<input type="text" id="nickname" name="nickname" required>
					<button type="button" class="btn_check" onclick="checkNickname()">重複確認</button>
				</div>
				<span id="nicknameCheckMsg"></span>
			</div>
			
			<div class="agreement_area">
				<p>以下に同意いただいた上で、会員登録をお願いいたします。</p>
				<div class="checkbox_group">
					<input type="checkbox" id="agree" name="agree" required>
					<label for="agree">利用規約およびプライバシーポリシーに同意する</label>
				</div>
			</div>
			
			<div class="form_actions">
				<button type="submit" class="btn_submit">会員登録</button>
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
				$('#userIdCheckMsg').text('既に使用されているユーザーIDです。').css('color', 'red');
				isUserIdChecked = false;
			} else {
				$('#userIdCheckMsg').text('使用可能なユーザーIDです。').css('color', 'green');
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
				$('#nicknameCheckMsg').text('既に使用されているニックネームです。').css('color', 'red');
				isNicknameChecked = false;
			} else {
				$('#nicknameCheckMsg').text('使用可能なニックネームです。').css('color', 'green');
				isNicknameChecked = true;
			}
		}
	});
}

function validateForm() {
	let isValid = true;
	
	// Reset errors
	$('.error_msg').text('');
	
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
		$('#passwordError').text('10文字以上で、英字と特殊文字を含めてください。');
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
