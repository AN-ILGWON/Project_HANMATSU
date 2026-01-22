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
			<span><i class="fas fa-user"></i> ${board.username} <small class="info_supplement">(投稿者)</small></span>
			<span><i class="fas fa-calendar-alt"></i> 
				<c:choose>
					<c:when test="${not empty board.regdate}">
						${fn:substring(board.regdate, 0, 19)}
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>
				<small class="info_supplement">(投稿日)</small></span>
			<span><i class="fas fa-eye"></i> ${board.views} <small class="info_supplement">(閲覧数)</small></span>
		</div>
		
		<div class="board_content">
			<c:if test="${not empty board.imgfile}">
				<div class="board_img">
					<img src="${pageContext.request.contextPath}/display.do?name=${board.imgfile}">
				</div>
			</c:if>
			<div class="content_text">
				${board.content}
			</div>
		</div>
		
		<div class="board_actions">
			<button type="button" class="btn_like ${isLiked ? 'liked' : ''}" data-bno="${board.bno}">
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
					<div class="reply_card">
						<div class="reply_header">
							<div class="reply_user_info">
								<div class="reply_avatar">
									<c:choose>
										<c:when test="${not empty reply.profileImg}">
											<img src="${pageContext.request.contextPath}/upload/profile/${reply.profileImg}" alt="avatar">
										</c:when>
										<c:otherwise>
											<i class="fas fa-user"></i>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="reply_meta">
									<span class="reply_author">${reply.username}</span>
									<span class="reply_date">
										<c:choose>
											<c:when test="${not empty reply.regdate}">
												${fn:substring(reply.regdate, 0, 16)}
											</c:when>
											<c:otherwise>
												-
											</c:otherwise>
										</c:choose>
									</span>
								</div>
							</div>
							<c:if test="${sessionScope.userid == reply.userid || sessionScope.role == 'ADMIN'}">
								<div class="reply_actions_new">
									<button type="button" class="btn_reply_edit" onclick="editReply(${reply.rno}, '${reply.content}')" title="編集">
										<i class="fas fa-edit"></i>
									</button>
									<a href="${pageContext.request.contextPath}/board/replyDelete.do?rno=${reply.rno}&bno=${board.bno}" 
									   class="btn_reply_delete" title="削除" onclick="return confirm('コメントを削除してもよろしいですか？')">
										<i class="fas fa-trash-alt"></i>
									</a>
								</div>
							</c:if>
						</div>
						<div class="reply_body">
							<div class="reply_content_text">${reply.content}</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<script>
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

<%@ include file="/footer.jsp" %>
