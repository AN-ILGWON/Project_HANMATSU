<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<div class="container board_list_container">
    <div class="page_header_new">
        <h2>コミュニティ</h2>
        <p>皆様の旅行記をぜひチェックしてみてくださいね。</p>
    </div>

    <div class="board_category_tabs">
        <a href="${pageContext.request.contextPath}/board/list.do" class="category_tab ${empty category ? 'active' : ''}">すべて</a>
        <c:forEach var="cat" items="${categoryList}">
            <a href="${pageContext.request.contextPath}/board/list.do?category=${cat.name}" class="category_tab ${category == cat.name ? 'active' : ''}">${cat.name}</a>
        </c:forEach>
    </div>

	<div class="board_header_actions">
        <div class="board_stats">
            全 <strong>${totalCount}</strong> 件
        </div>
		<c:if test="${not empty sessionScope.userid}">
			<a href="${pageContext.request.contextPath}/board/write.do" class="btn_write_new">新しい投稿を共有する</a>
		</c:if>
	</div>
	
	<div class="board_list_card">
		<table class="custom_table">
			<thead>
				<tr>
					<th width="80">番号</th>
					<th width="120">カテゴリー</th>
					<th>タイトル</th>
					<th width="150">投稿者</th>
					<th width="80">いいね</th>
					<th width="80">閲覧</th>
					<th width="120">投稿日</th>
					<c:if test="${sessionScope.role == 'ADMIN'}">
						<th width="80">操作</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}" varStatus="status">
					<tr onclick="location.href='${pageContext.request.contextPath}/board/view.do?bno=${board.bno}'">
						<td class="td_num">${totalCount - (status.index + (currentPage - 1) * pageSize)}</td>
						<td class="td_category">
							<span class="category_badge ${board.category == 'Q&A' ? 'qna' : (board.category == '自由掲示板' ? 'free' : 'review')}">
								${board.category}
							</span>
						</td>
						<td class="td_title">
							<span class="title_text">${board.title}</span>
							<c:if test="${board.replyCount > 0}">
								<span class="reply_badge">${board.replyCount}</span>
							</c:if>
						</td>
						<td class="td_user"><span class="user_pill">${board.username}</span></td>
						<td class="td_likes">${board.likes}</td>
						<td class="td_views">${board.views}</td>
						<td class="td_date">
							<c:choose>
								<c:when test="${not empty board.regdate}">
									${fn:substring(board.regdate, 0, 10)}
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>
						</td>
						<c:if test="${sessionScope.role == 'ADMIN'}">
							<td onclick="event.stopPropagation();">
								<button onclick="deleteBoard(${board.bno})" class="btn_admin_del">削除</button>
							</td>
						</c:if>
					</tr>
				</c:forEach>
                <c:if test="${empty boardList}">
                    <tr>
                        <td colspan="${sessionScope.role == 'ADMIN' ? '8' : '7'}" class="no_data">現在、投稿された記事はございません。</td>
                    </tr>
                </c:if>
			</tbody>
		</table>
	</div>
	
	<div class="pagination_new">
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/board/list.do?page=${currentPage - 1}${not empty category ? '&category='.concat(category) : ''}" class="page_btn prev">
                <i class="fas fa-chevron-left"></i> 前へ
            </a>
		</c:if>
        
        <div class="page_numbers">
            <c:forEach var="i" begin="1" end="${totalPages}">
                <a href="${pageContext.request.contextPath}/board/list.do?page=${i}${not empty category ? '&category='.concat(category) : ''}" 
                   class="page_num ${i == currentPage ? 'active' : ''}">${i}</a>
            </c:forEach>
        </div>

		<c:if test="${currentPage < totalPages}">
			<a href="${pageContext.request.contextPath}/board/list.do?page=${currentPage + 1}${not empty category ? '&category='.concat(category) : ''}" class="page_btn next">
                次へ <i class="fas fa-chevron-right"></i>
            </a>
		</c:if>
	</div>
</div>

<script>
function deleteBoard(bno) {
    if (confirm('本当にこの投稿を削除しますか？')) {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/boardDelete.do',
            type: 'POST',
            data: { bno: bno },
            success: function(result) {
                if (result == 1) {
                    alert('投稿が削除されました。');
                    location.reload();
                } else {
                    alert('削除に失敗しました。');
                }
            }
        });
    }
}
</script>

<%@ include file="/footer.jsp" %>
