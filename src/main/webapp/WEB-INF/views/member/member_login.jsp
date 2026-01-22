<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container auth_container">
	<div class="auth_card auth_card_sm">
		<h2 class="auth_title">ログイン</h2>
		
		<form id="loginForm" method="post">
			<div class="auth_form_group">
				<label for="userid" class="auth_label">ユーザーID</label>
				<input type="text" id="userid" name="userid" class="auth_input" placeholder="ユーザーIDを入力してください" required>
			</div>
			
			<div class="auth_form_group">
				<label for="password" class="auth_label">パスワード</label>
				<input type="password" id="password" name="password" class="auth_input" placeholder="パスワードを入力してください" required>
			</div>
			
			<div class="auth_actions auth_actions_col gap_15">
				<button type="submit" class="auth_btn_submit w_100 flex_none">ログイン</button>
				<div class="auth_login_link mb_0 mt_10">
					アカウントをお持ちでないですか？ <a href="${pageContext.request.contextPath}/member/join.do">新規登録はこちら</a>
				</div>
			</div>
		</form>
	</div>
</div>

<script>
$(document).ready(function() {
	$('#loginForm').on('submit', function(e) {
		e.preventDefault();
		
		$.ajax({
			url: '${pageContext.request.contextPath}/member/loginpro.do',
			type: 'POST',
			data: $(this).serialize(),
			success: function(result) {
				if(result.trim() === 'success') {
					location.href = '${pageContext.request.contextPath}/main.do';
				} else {
					alert('ログインに失敗しました。ユーザーIDまたはパスワードが正しくありません。');
				}
			}
		});
	});
});
</script>

<%@ include file="/footer.jsp" %>
