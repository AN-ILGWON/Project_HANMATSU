<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>韓まつ - 韓国地域祭り情報ポータル</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Mochiy+Pop+One&family=Noto+Sans+JP:wght@300;400;500;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
<!-- HEADER -->
	<header class="main_header">
		<div class="header_container">
			<div class="header_left">
				<div class="logo">
					<a href="${pageContext.request.contextPath}/main.do">
						<span class="hanmatsu">韓まつ</span>
					</a>
				</div>
			</div>
			
			<div class="header_center">
				<nav class="gnb">
					<ul class="nav_1depth">
						<li><a href="${pageContext.request.contextPath}/main.do">ホーム</a></li>
						<li><a href="${pageContext.request.contextPath}/main.do#festival">祭り紹介</a></li>
						<li><a href="${pageContext.request.contextPath}/festival/list.do">おすすめ祭り</a></li>
						<li><a href="${pageContext.request.contextPath}/board/list.do">コミュニティ</a></li>
					</ul>
				</nav>
			</div>

			<div class="header_right">
				<div class="utility_nav">
					<ul>
						<li class="search_item">
							<a href="javascript:void(0);" onclick="toggleSearch()" class="nav_search_btn">
								<svg class="icon_svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round" style="width:18px; height:18px;"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg> 検索
							</a>
						</li>
						<li><a href="${pageContext.request.contextPath}/info/faq.do">よくあるご質問</a></li>
						<c:choose>
							<c:when test="${empty sessionScope.userid}">
								<li><a href="${pageContext.request.contextPath}/member/login.do" class="btn_login_nav">ログイン / 新規登録</a></li>
							</c:when>
							<c:otherwise>
								<li class="user_welcome">
									<a href="${pageContext.request.contextPath}/member/mypage.do"><strong>${sessionScope.nickname}</strong>さん</a>
								</li>
								<li><a href="${pageContext.request.contextPath}/member/logout.do">ログアウト</a></li>
							</c:otherwise>					
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- 検索バー (Hidden by default) -->
		<div id="search_bar" class="search_bar_wrap">
			<div class="search_inner">
				<form action="${pageContext.request.contextPath}/festival/list.do" method="get">
					<input type="text" name="keyword" placeholder="お探しの祭りを入力してください">
					<button type="submit">検索</button>
				</form>
				<button type="button" class="search_close" onclick="toggleSearch()">×</button>
			</div>
		</div>
	</header>

	<script>
		function toggleSearch() {
			const searchBar = document.getElementById('search_bar');
			searchBar.classList.toggle('active');
		}
	</script>

