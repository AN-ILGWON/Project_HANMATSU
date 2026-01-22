<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/header.jsp" %>

<div class="container">
	<div class="mypage_container">
		<div class="admin_text_center admin_mb_40">
			<div class="profile_img_large_wrap">
				<c:choose>
					<c:when test="${not empty member.profileImg}">
						<img src="${pageContext.request.contextPath}/upload/profile/${member.profileImg}" alt="Profile">
					</c:when>
					<c:otherwise>
						<i class="fas fa-user"></i>
					</c:otherwise>
				</c:choose>
			</div>
			<h2 class="admin_mb_20">マイページ</h2>
			<p class="admin_text_dark">ようこそ、<span class="admin_fw_bold admin_text_primary">${sessionScope.nickname}</span>さん！</p>
			<div class="admin_flex_row admin_flex_center admin_mb_20 gap_15 mt_20">
				<a href="${pageContext.request.contextPath}/member/update.do" class="btn_pill btn_pill_secondary">プロフィール編集</a>
				<a href="javascript:void(0);" onclick="confirmWithdrawal()" class="btn_pill btn_pill_gray">退会する</a>
			</div>
		</div>

		<script>
		function confirmWithdrawal() {
			if (confirm('本当に退会しますか？退会するとこれまでの情報はすべて削除されます。')) {
				location.href = '${pageContext.request.contextPath}/member/delete.do';
			}
		}
		</script>

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
										<a href="${pageContext.request.contextPath}/festival/view.do?fno=${visited.fno}">
											<span class="visited_name">${visited.fname}</span>
											<span class="visited_date">
												<c:choose>
													<c:when test="${not empty visited.regdate}">
														${fn:substring(visited.regdate, 0, 10)}
													</c:when>
													<c:otherwise>
														-
													</c:otherwise>
												</c:choose>
											</span>
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
									<span class="reply_date">
										<c:choose>
											<c:when test="${not empty reply.regdate}">
												${fn:substring(reply.regdate, 0, 10)}
											</c:when>
											<c:otherwise>
												-
											</c:otherwise>
										</c:choose>
									</span>
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

<%@ include file="/footer.jsp" %>
