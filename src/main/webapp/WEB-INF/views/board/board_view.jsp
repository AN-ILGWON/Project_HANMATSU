<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<div class="container">
	<div class="board_view">
		<div class="board_title">
			<h2>${board.title}</h2>
		</div>
		
		<div class="board_info">
			<span class="view_category">${board.category}</span>
			<span>投稿者: ${board.username}</span>
			<span>投稿日: ${fn:substring(board.regdate, 0, 19)}</span>
			<span>閲覧数: ${board.views}</span>
		</div>
		
		<div class="board_content">
			<c:if test="${not empty board.imgfile}">
				<div class="board_img">
					<img src="${pageContext.request.contextPath}/display.do?name=${board.imgfile}" style="max-width:100%; height:auto; border-radius:10px; margin-bottom:20px;">
				</div>
			</c:if>
			<div class="content_text">
				${fn:replace(board.content, '
', '<br>')}
			</div>
		</div>
		
		<div class="board_actions">
			<button type="button" class="btn_like ${isLiked ? 'liked' : ''}" onclick="toggleLike(${board.bno})">
				いいね <span id="like_count">${board.likes}</span>
			</button>
			
			<c:if test="${sessionScope.role == 'ADMIN'}">
				<button type="button" class="btn_admin_like" onclick="editLikes(${board.bno}, ${board.likes})">
					いいね数修正
				</button>
			</c:if>
			
			<c:if test="${sessionScope.userid == board.userid || sessionScope.role == 'ADMIN'}">
				<a href="${pageContext.request.contextPath}/board/update.do?bno=${board.bno}" class="btn_update">編集</a>
				<a href="${pageContext.request.contextPath}/board/delete.do?bno=${board.bno}" class="btn_delete" onclick="return confirm('この記事を削除してもよろしいですか？')">削除</a>
			</c:if>
		</div>
		
		<!-- コメントセクション -->
		<div class="reply_section">
			<h3>コメント</h3>
			
			<c:if test="${not empty sessionScope.userid}">
				<form action="${pageContext.request.contextPath}/board/replyInsert.do" method="post" class="reply_form">
					<input type="hidden" name="bno" value="${board.bno}">
					<textarea name="content" placeholder="コメントを入力してください" required></textarea>
					<button type="submit">コメント投稿</button>
				</form>
			</c:if>
			
			<div class="reply_list">
				<c:forEach var="reply" items="${replyList}">
					<div class="reply_item">
						<div class="reply_info">
							<span class="reply_author">${reply.username}</span>
							<span class="reply_date">${fn:substring(reply.regdate, 0, 19)}</span>
						</div>
						<div class="reply_content">${reply.content}</div>
						<c:if test="${sessionScope.userid == reply.userid || sessionScope.role == 'ADMIN'}">
							<div class="reply_actions">
								<a href="#" onclick="editReply(${reply.rno}, '${reply.content}')">編集</a>
								<a href="${pageContext.request.contextPath}/board/replyDelete.do?rno=${reply.rno}&bno=${board.bno}" onclick="return confirm('コメントを削除してもよろしいですか？')">削除</a>
							</div>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<script>
function toggleLike(bno) {
	$.ajax({
		url: '${pageContext.request.contextPath}/board/like.do',
		type: 'POST',
		data: { bno: bno },
		success: function(result) {
			if(result === 'login') {
				alert('ログインが必要です。ログインページへ移動します。');
				location.href = '${pageContext.request.contextPath}/member/login.do';
			} else {
				location.reload();
			}
		}
	});
}

function editLikes(bno, currentLikes) {
    const newLikes = prompt('新しいいいね数を入力してください:', currentLikes);
    if (newLikes !== null && newLikes !== '' && !isNaN(newLikes)) {
        $.ajax({
            url: '${pageContext.request.contextPath}/board/likeUpdate.do',
            type: 'POST',
            data: { bno: bno, likes: newLikes },
            success: function() {
                location.reload();
            },
            error: function() {
                alert('エラーが発生しました。');
            }
        });
    }
}

function editReply(rno, content) {
    const newContent = prompt('コメントを編集:', content);
    if (newContent && newContent !== content) {
        $.ajax({
            url: '${pageContext.request.contextPath}/board/replyUpdate.do',
            type: 'POST',
            data: { rno: rno, content: newContent, bno: ${board.bno} },
            success: function() {
                location.reload();
            }
        });
    }
}
</script>

<style>
.board_view { max-width: 900px; margin: 40px auto; background: #fff; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); }
.board_title { margin-bottom: 20px; border-bottom: 2px solid #333; padding-bottom: 10px; }
.board_info { margin-bottom: 30px; color: #666; font-size: 14px; }
.board_info span { margin-right: 20px; }
.board_content { min-height: 300px; line-height: 1.8; margin-bottom: 40px; }
.board_actions { text-align: center; margin-bottom: 50px; padding-bottom: 30px; border-bottom: 1px solid #eee; }
.btn_like { padding: 10px 25px; border: 1px solid #ddd; background: #fff; border-radius: 25px; cursor: pointer; font-weight: bold; }
.btn_like.liked { background: #d32f2f; color: #fff; border-color: #d32f2f; }
.btn_update, .btn_delete { display: inline-block; padding: 8px 20px; margin-left: 10px; text-decoration: none; border-radius: 4px; font-size: 14px; }
.btn_update { background: #eee; color: #333; }
.btn_delete { background: #333; color: #fff; }

.reply_section h3 { margin-bottom: 20px; font-size: 20px; }
.reply_form { margin-bottom: 30px; display: flex; flex-direction: column; gap: 10px; }
.reply_form textarea { width: 100%; padding: 15px; border: 1px solid #ddd; border-radius: 4px; height: 100px; resize: none; }
.reply_form button { align-self: flex-end; padding: 10px 30px; background: #333; color: #fff; border: none; border-radius: 4px; cursor: pointer; }
.reply_item { padding: 20px; border-bottom: 1px solid #f0f0f0; }
.reply_info { margin-bottom: 10px; font-size: 13px; color: #888; }
.reply_author { font-weight: bold; color: #333; margin-right: 15px; }
.reply_content { line-height: 1.6; margin-bottom: 10px; }
.reply_actions { font-size: 12px; }
.reply_actions a { color: #888; margin-right: 10px; text-decoration: none; }
.reply_actions a:hover { text-decoration: underline; }
.view_category { 
    background: var(--primary-color); color: white; padding: 3px 12px; 
    border-radius: 15px; font-size: 12px; font-weight: 700; margin-right: 15px;
}
</style>

<%@ include file="/footer.jsp" %>
