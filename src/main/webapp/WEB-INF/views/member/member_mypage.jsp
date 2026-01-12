<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/header.jsp" %>

<div class="container">
	<div class="mypage_container">
		<div class="section_title">
			<h2>マイページ</h2>
			<p>ようこそ、${sessionScope.nickname}さん！</p>
		</div>

		<div class="mypage_grid">
			<!-- お気に入りした祭り -->
			<div class="mypage_section full_width">
				<h3>お気に入りした祭り</h3>
				<div class="wishlist_container">
					<c:choose>
						<c:when test="${not empty myWishlist}">
							<div class="wishlist_grid">
								<c:forEach var="festival" items="${myWishlist}">
									<div class="wish_item">
										<a href="${pageContext.request.contextPath}/festival/view.do?fno=${festival.fno}">
											<div class="wish_img">
												<c:choose>
													<c:when test="${not empty festival.imgfile}">
														<img src="${pageContext.request.contextPath}/display.do?name=${festival.imgfile}" alt="${festival.name}">
													</c:when>
													<c:otherwise>
														<div class="no_image">画像がありません</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="wish_info">
												<span class="wish_region">${festival.region}</span>
												<span class="wish_name">${festival.name}</span>
											</div>
										</a>
									</div>
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<p class="empty_msg">お気に入りした祭りがありません。</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!-- 最近チェックした祭り -->
			<div class="mypage_section">
				<h3>最近チェックした祭り</h3>
				<div class="visited_list">
					<c:choose>
						<c:when test="${not empty recentVisited}">
							<ul>
								<c:forEach var="visited" items="${recentVisited}">
									<li>
										<a href="${pageContext.request.contextPath}/festival/${fn:contains(visited.fno, 'API') ? 'apiView.do?contentId=' : 'view.do?fno='}${visited.fno}">
											<span class="visited_name">${visited.fname}</span>
											<span class="visited_date">${fn:substring(visited.regdate, 0, 10)}</span>
										</a>
									</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<p class="empty_msg">最近チェックした祭りがありません。</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!-- 自分のコメント -->
			<div class="mypage_section">
				<h3>私のコメント</h3>
				<div class="my_replies">
					<c:choose>
						<c:when test="${not empty myReplies}">
							<c:forEach var="reply" items="${myReplies}">
								<div class="mypage_reply_item">
									<p class="reply_target">
										<a href="${pageContext.request.contextPath}/board/view.do?bno=${reply.bno}">
											[${reply.boardTitle}]
										</a>
									</p>
									<p class="reply_text">${reply.content}</p>
									<span class="reply_date">${fn:substring(reply.regdate, 0, 10)}</span>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p class="empty_msg">作成したコメントがありません。</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>

<style>
.mypage_section.full_width {
	grid-column: 1 / -1;
}
.wishlist_grid {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
	gap: 20px;
}
.wish_item {
	border: 1px solid #eee;
	border-radius: 10px;
	overflow: hidden;
	transition: transform 0.2s;
}
.wish_item:hover {
	transform: translateY(-5px);
}
.wish_item a {
	text-decoration: none;
	color: inherit;
}
.wish_img {
	height: 120px;
	background: #f5f5f5;
	display: flex;
	align-items: center;
	justify-content: center;
}
.wish_img img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}
.wish_info {
	padding: 10px;
}
.wish_region {
	display: block;
	font-size: 12px;
	color: #d32f2f;
	margin-bottom: 4px;
}
.wish_name {
	font-weight: 600;
	font-size: 14px;
}
.no_image {
	font-size: 12px;
	color: #999;
}
.mypage_container {
	padding: 40px 0;
}
.mypage_grid {
	display: grid;
	grid-template-columns: 1fr 1fr;
	gap: 30px;
	margin-top: 30px;
}
.mypage_section {
	background: #fff;
	padding: 25px;
	border-radius: 12px;
	box-shadow: 0 4px 15px rgba(0,0,0,0.05);
}
.mypage_section h3 {
	margin-bottom: 20px;
	padding-bottom: 10px;
	border-bottom: 2px solid #d32f2f;
	font-size: 20px;
}
.visited_list ul {
	list-style: none;
}
.visited_list li {
	margin-bottom: 15px;
}
.visited_list li a {
	display: flex;
	justify-content: space-between;
	text-decoration: none;
	color: #333;
	padding: 10px;
	border-radius: 6px;
	background: #f9f9f9;
	transition: background 0.2s;
}
.visited_list li a:hover {
	background: #f0f0f0;
}
.visited_name {
	font-weight: 500;
}
.visited_date {
	font-size: 12px;
	color: #999;
}
.mypage_reply_item {
	padding: 15px;
	border-bottom: 1px solid #eee;
}
.mypage_reply_item:last-child {
	border-bottom: none;
}
.reply_target a {
	font-size: 13px;
	color: #d32f2f;
	text-decoration: none;
	font-weight: bold;
}
.reply_text {
	margin: 5px 0;
	font-size: 14px;
	color: #555;
}
.empty_msg {
	text-align: center;
	color: #999;
	padding: 40px 0;
}
@media (max-width: 768px) {
	.mypage_grid {
		grid-template-columns: 1fr;
	}
}
</style>

<%@ include file="/footer.jsp" %>
