<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<div class="container">
	<div class="login_form">
		<h2>ログイン</h2>
		
		<form id="loginForm" method="post">
			<div class="form_group">
				<label for="userid">ユーザーID</label>
				<input type="text" id="userid" name="userid" placeholder="ユーザーIDを入力してください" required>
			</div>
			
			<div class="form_group">
				<label for="password">パスワード</label>
				<input type="password" id="password" name="password" required>
			</div>
			
			<div class="form_actions">
				<button type="submit">ログイン</button>
				<a href="${pageContext.request.contextPath}/member/join.do">新規登録はこちら</a>
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
