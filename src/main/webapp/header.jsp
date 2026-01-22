<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="theme-color" content="#ff5a5f">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<title>韓まつ - 韓国地域祭り情報ポータル</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300;400;500;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=1.6">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<!-- Summernote Lite CSS -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<!-- Summernote Lite JS -->
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/lang/summernote-ja-JP.min.js"></script>
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
						<div class="logo_icon">
							<svg viewBox="0 0 100 100" class="flower_svg">
								<circle cx="50" cy="30" r="18" fill="#ff5a5f" /> <!-- Top: Red -->
								<circle cx="80" cy="50" r="18" fill="#2ecc71" /> <!-- Right: Green -->
								<circle cx="68" cy="80" r="18" fill="#f1c40f" /> <!-- Bottom Right: Yellow -->
								<circle cx="32" cy="80" r="18" fill="#a2d149" /> <!-- Bottom Left: Light Green -->
								<circle cx="20" cy="50" r="18" fill="#3498db" /> <!-- Left: Blue -->
								<circle cx="50" cy="58" r="10" fill="white" /> <!-- Center -->
							</svg>
						</div>
						<div class="logo_text">
							<span class="hanmatsu">韓まつ</span>
							<span class="hanmatsu_sub">HANMATSU</span>
						</div>
					</a>
				</div>
			</div>
			
			<div class="header_mobile_btns">
				<button type="button" class="mobile_search_btn" onclick="toggleSearch()">
					<i class="fas fa-search"></i>
				</button>
				<button type="button" class="mobile_menu_btn" onclick="toggleMobileMenu()">
					<i class="fas fa-bars"></i>
				</button>
			</div>
			
			<div class="header_center">
				<nav class="gnb">
					<ul class="nav_1depth">
						<li>
							<a href="${pageContext.request.contextPath}/festival/list.do">
								<i class="fas fa-bullhorn nav_icon"></i>
								<span class="nav_main_text">お祭り紹介</span>
								<span class="nav_en_text">/ Festival</span>
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/festival/calendar.do">
								<i class="fas fa-calendar-alt nav_icon"></i>
								<span class="nav_main_text">お祭りカレンダー</span>
								<span class="nav_en_text">/ Calendar</span>
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/festival/list.do?status=ongoing">
								<i class="fas fa-star nav_icon"></i>
								<span class="nav_main_text">おすすめのお祭り</span>
								<span class="nav_en_text">/ Pick Up</span>
								<span class="nav_badge hot">HOT</span>
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/board/list.do">
								<i class="fas fa-comments nav_icon"></i>
								<span class="nav_main_text">コミュニティ</span>
								<span class="nav_en_text">/ Community</span>
								<span class="nav_badge new">NEW</span>
							</a>
						</li>
					</ul>
				</nav>
			</div>

			<div class="header_right">
				<div class="utility_nav">
					<ul>
						<li class="search_item">
							<a href="javascript:void(0);" onclick="toggleSearch()" class="nav_search_btn" title="検索">
								<i class="fas fa-search"></i>
							</a>
						</li>
						<li class="nav_divider"></li>
						<c:if test="${sessionScope.role eq 'ADMIN'}">
							<li><a href="${pageContext.request.contextPath}/admin/main.do" class="nav_admin_link"><i class="fas fa-cog"></i> 管理者</a></li>
						</c:if>
						<c:choose>
							<c:when test="${empty sessionScope.userid}">
								<li><a href="${pageContext.request.contextPath}/member/login.do" class="btn_login_nav">ログイン / 会員登録</a></li>
							</c:when>
							<c:otherwise>
								<li class="user_welcome">
									<a href="${pageContext.request.contextPath}/member/mypage.do">
										<div class="header_profile_img">
											<c:choose>
												<c:when test="${not empty sessionScope.profileImg}">
													<img src="${pageContext.request.contextPath}/upload/profile/${sessionScope.profileImg}" 
														 alt="P">
												</c:when>
												<c:otherwise>
													<i class="fas fa-user header_user_icon"></i>
												</c:otherwise>
											</c:choose>
										</div>
										<strong>${sessionScope.nickname}</strong><span> 様</span>
									</a>
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
					<input type="text" name="keyword" placeholder="祭りの名前やキーワードを入力してください">
					<button type="submit">検索</button>
				</form>
				<button type="button" class="search_close" onclick="toggleSearch()">×</button>
			</div>
		</div>
	</header>

	<!-- モバイルメニューオーバーレイ -->
	<div id="mobile_menu_overlay" class="mobile_menu_overlay" onclick="toggleMobileMenu()"></div>
	<div id="mobile_menu_panel" class="mobile_menu_panel">
		<div class="mobile_menu_header">
			<div class="mobile_logo">
				<span class="hanmatsu">韓まつ</span>
			</div>
			<button type="button" class="mobile_menu_close" onclick="toggleMobileMenu()">×</button>
		</div>
		<div class="mobile_menu_body">
			<nav class="mobile_gnb">
				<ul>
					<li><a href="${pageContext.request.contextPath}/festival/list.do"><i class="fas fa-bullhorn"></i> お祭り紹介</a></li>
					<li><a href="${pageContext.request.contextPath}/festival/calendar.do"><i class="fas fa-calendar-alt"></i> お祭りカレンダー</a></li>
					<li><a href="${pageContext.request.contextPath}/festival/list.do?status=ongoing"><i class="fas fa-star"></i> おすすめのお祭り</a></li>
					<li><a href="${pageContext.request.contextPath}/board/list.do"><i class="fas fa-comments"></i> コミュニティ</a></li>
				</ul>
			</nav>
			<div class="mobile_utility">
				<c:choose>
					<c:when test="${empty sessionScope.userid}">
						<a href="${pageContext.request.contextPath}/member/login.do" class="btn_mobile_login">ログイン / 会員登録</a>
					</c:when>
					<c:otherwise>
						<div class="mobile_user_info">
							<strong>${sessionScope.nickname}</strong> 様、こんにちは！
						</div>
						<div class="mobile_user_links">
							<a href="${pageContext.request.contextPath}/member/mypage.do">マイページ</a>
							<a href="${pageContext.request.contextPath}/member/logout.do">ログアウト</a>
							<c:if test="${sessionScope.role eq 'ADMIN'}">
								<a href="${pageContext.request.contextPath}/admin/main.do" class="admin_link">管理者ページ</a>
							</c:if>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

	<script>
		function toggleSearch() {
			const searchBar = document.getElementById('search_bar');
			searchBar.classList.toggle('active');
			if(searchBar.classList.contains('active')) {
				searchBar.querySelector('input').focus();
			}
		}
		
		function toggleMobileMenu() {
			const panel = document.getElementById('mobile_menu_panel');
			const overlay = document.getElementById('mobile_menu_overlay');
			panel.classList.toggle('active');
			overlay.classList.toggle('active');
			document.body.style.overflow = panel.classList.contains('active') ? 'hidden' : '';
		}
	</script>

