<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<!-- MainVisual - Carousel Slider -->
<div class="main_hero_carousel">
    <div class="swiper heroSwiper">
        <div class="swiper-wrapper">
            <div class="swiper-slide hero_slide slide1">
                <div class="hero_overlay">
                    <div class="hero_text_wrap">
                        <h2>ソウルを越えて、<br>まだ見ぬ韓国の心に触れる旅。</h2>
                        <p>旬のグルメ、伝統の灯り、そして人々の温かさ。</p>
                    </div>
                </div>
            </div>
            <div class="swiper-slide hero_slide slide2">
                <div class="hero_overlay">
                    <div class="hero_text_wrap">
                        <h2>忘れられない感動の瞬間を</h2>
                        <p>伝統と現代が融合する韓国の祭りで、特別な思い出を。</p>
                    </div>
                </div>
            </div>
            <div class="swiper-slide hero_slide slide3">
                <div class="hero_overlay">
                    <div class="hero_text_wrap">
                        <h2>冬の温もりを感じる温泉の旅</h2>
                        <p>心と体を整える、最高のリラックスタイム。</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modern Pagination & Navigation -->
        <div class="hero_controls_wrap">
            <div class="container">
                <div class="hero_status_inner">
                    <div class="hero_pagination_box">
                        <span class="curr_idx">01</span>
                        <div class="hero_progress_wrap">
                            <div class="hero_progress_bar"></div>
                        </div>
                        <span class="total_idx">03</span>
                    </div>
                    <div class="hero_nav_btns">
                        <button class="hero_btn hero-prev"><i class="fas fa-chevron-left"></i></button>
                        <button class="hero_btn hero-next"><i class="fas fa-chevron-right"></i></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Quick Menu (Icon Circle Menu) -->
<div class="container quick_service_section" style="margin-top: 80px; margin-bottom: 80px;">
    <div style="text-align: center; margin-bottom: 50px;">
        <span style="color: #ff8fa3; font-weight: 800; font-size: 14px; letter-spacing: 2px; text-transform: uppercase;">SERVICE MENU</span>
        <h3 class="quick_service_title" style="font-family: 'Mochiy Pop One', sans-serif; font-size: 28px; color: #333; margin-top: 10px;">旅の準備をサポートする <span style="color: #ff8fa3;">🌸</span></h3>
    </div>
    <div class="quick_circle_list" style="display: flex; justify-content: center; gap: 40px; flex-wrap: wrap;">
        <a href="${pageContext.request.contextPath}/info/course.do" class="quick_circle_item_new">
            <div class="circle_icon_new" style="background: #fff5f6; color: #ff8fa3;">
                <i class="fas fa-map-marked-alt"></i>
            </div>
            <span>旅行コース</span>
        </a>
        <a href="${pageContext.request.contextPath}/info/gourmet.do" class="quick_circle_item_new">
            <div class="circle_icon_new" style="background: #f0fff4; color: #48bb78;">
                <i class="fas fa-utensils"></i>
            </div>
            <span>グルメチャート</span>
        </a>
        <a href="${pageContext.request.contextPath}/festival/list.do" class="quick_circle_item_new">
            <div class="circle_icon_new" style="background: #fffaf0; color: #ed8936;">
                <i class="fas fa-republican"></i>
            </div>
            <span>地域祭り</span>
        </a>
        <a href="${pageContext.request.contextPath}/info/guide.do" class="quick_circle_item_new">
            <div class="circle_icon_new" style="background: #ebf8ff; color: #4299e1;">
                <i class="fas fa-info-circle"></i>
            </div>
            <span>利用ガイド</span>
        </a>
    </div>
</div>

<style>
.quick_circle_item_new {
    text-decoration: none;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 15px;
    transition: 0.3s;
}
.circle_icon_new {
    width: 90px;
    height: 90px;
    border-radius: 35px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 32px;
    transition: 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
    box-shadow: 0 10px 20px rgba(0,0,0,0.03);
}
.quick_circle_item_new span {
    font-size: 15px;
    font-weight: 700;
    color: #555;
    transition: 0.3s;
}
.quick_circle_item_new:hover .circle_icon_new {
    transform: translateY(-10px) rotate(8deg);
    box-shadow: 0 15px 30px rgba(0,0,0,0.08);
}
.quick_circle_item_new:hover span {
    color: #ff8fa3;
}
</style>

<!-- Back to Top -->
<div class="back_to_top" onclick="window.scrollTo({top: 0, behavior: 'smooth'})">
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"><polyline points="18 15 12 9 6 15"></polyline></svg>
</div>

<!-- Intro Section (Moved below Quick Menu for better flow like the image) -->
<div class="intro_section" style="background: #fff9fa; padding: 100px 0; border-top: 1px solid #ffeff2; border-bottom: 1px solid #ffeff2; position: relative; overflow: hidden;">
    <div style="position: absolute; top: -20px; right: -20px; font-size: 150px; color: #fff0f3; z-index: 0; transform: rotate(-15deg);"><i class="fas fa-heart"></i></div>
    <div class="container" style="position: relative; z-index: 1;">
        <div class="intro_content_wrap" style="text-align: center; max-width: 800px; margin: 0 auto;">
            <h3 class="intro_title" style="font-family: 'Mochiy Pop One', sans-serif; font-size: 28px; color: #333; margin-bottom: 30px; line-height: 1.4;">
                韓国旅行、いつも同じ場所ばかりではありませんか？ <span style="color: #ff8fa3;">🤔</span>
            </h3>
            <div class="intro_desc" style="background: white; padding: 40px; border-radius: 30px; box-shadow: 0 10px 30px rgba(255, 143, 163, 0.08); margin-bottom: 30px;">
                <p style="color: #666; font-size: 17px; margin-bottom: 15px; font-weight: 500;">「地方のお祭りに行ってみたいけど、アクセスが不安…」</p>
                <p style="color: #666; font-size: 17px; margin-bottom: 15px; font-weight: 500;">「日本語の正確な情報がなくて諦めていた…」</p>
                <p style="color: #666; font-size: 17px; font-weight: 500;">「ガイドブックにはない地元の旬を味わいたい！」</p>
            </div>
            <p class="intro_summary" style="font-size: 20px; color: #ff8fa3; font-weight: 800; letter-spacing: -0.5px;">
                韓国の祭り情報を専門に提供する「韓まつ」が、<br>皆様の旅をサポートいたします。 ✨
            </p>
        </div>
    </div>
</div>

<!-- Recommended Festival Section -->
<section class="container main_section" id="festival" style="padding: 100px 0;">
    <div class="section_title_new" style="text-align: center; margin-bottom: 60px;">
        <span style="color: #ff8fa3; font-weight: 800; font-size: 14px; letter-spacing: 2px;">PICK UP FESTIVAL</span>
        <h2 style="font-family: 'Mochiy Pop One', sans-serif; font-size: 32px; color: #333; margin-top: 10px;">注目の祭り情報 <span style="color: #ff8fa3;">🌟</span></h2>
        <p style="color: #888; margin-top: 10px;">AIによる最適な韓国体験のご提案</p>
    </div>
    
    <div class="swiper festival_grid" style="padding: 20px;">
        <div class="swiper-wrapper">
            <c:choose>
                <c:when test="${not empty festivalList}">
                    <c:forEach var="festival" items="${festivalList}">
                        <div class="swiper-slide festival_slide_item">
                            <div class="festival_card_modern" onclick="location.href='${pageContext.request.contextPath}/festival/view.do?fno=${festival.fno}'"
                                 style="background: #fff; border-radius: 25px; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.05); transition: 0.4s; cursor: pointer; border: 1px solid #f0f0f0;">
                                <div class="card_image" style="height: 250px; overflow: hidden; position: relative;">
                                    <c:choose>
                                        <c:when test="${not empty festival.imgfile}">
                                            <img src="${pageContext.request.contextPath}/display.do?name=${festival.imgfile}" alt="${festival.name}" style="width: 100%; height: 100%; object-fit: cover; transition: 0.6s;">
                                        </c:when>
                                        <c:otherwise>
                                            <div class="no_image_placeholder" style="width: 100%; height: 100%; background: #fff5f6; display: flex; align-items: center; justify-content: center; color: #ff8fa3;">画像がありません</div>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="card_tag_pill" style="position: absolute; top: 20px; left: 20px; background: #ff8fa3; color: white; padding: 5px 15px; border-radius: 20px; font-size: 12px; font-weight: 700; box-shadow: 0 4px 10px rgba(255, 143, 163, 0.3);">${festival.region}</div>
                                </div>
                                <div class="card_info" style="padding: 25px;">
                                    <h3 style="font-size: 20px; color: #333; font-weight: 800; margin-bottom: 12px; transition: 0.3s;">${festival.name}</h3>
                                    <p class="card_date" style="color: #ff8fa3; font-size: 14px; font-weight: 700; margin-bottom: 8px;">
                                        <i class="far fa-calendar-alt"></i> ${fn:substring(festival.startDate, 0, 10)} ~ ${fn:substring(festival.endDate, 0, 10)}
                                    </p>
                                    <p class="card_loc" style="color: #888; font-size: 14px;">
                                        <i class="fas fa-map-marker-alt"></i> ${festival.location}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="swiper-slide">
                        <p class="empty_msg">現在、登録されている祭りがありません。</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="swiper-pagination"></div>
    </div>
</section>

<!-- AI Recommended Section -->
<div class="ai_recommend_section" style="padding: 80px 0; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); position: relative; overflow: hidden;">
    <div style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background: url('https://www.transparenttextures.com/patterns/cubes.png'); opacity: 0.1;"></div>
    <div class="container" style="position: relative; z-index: 1;">
        <div class="ai_box" style="background: rgba(255, 255, 255, 0.1); backdrop-filter: blur(15px); border-radius: 40px; padding: 60px; border: 1px solid rgba(255, 255, 255, 0.2); box-shadow: 0 25px 50px rgba(0,0,0,0.15);">
            <div class="ai_header" style="text-align: center; margin-bottom: 40px;">
                <div class="ai_icon_wrap" style="width: 70px; height: 70px; background: white; border-radius: 20px; display: flex; align-items: center; justify-content: center; margin: 0 auto 20px; font-size: 30px; color: #764ba2; box-shadow: 0 10px 20px rgba(0,0,0,0.1);">
                    <i class="fas fa-robot"></i>
                </div>
                <h3 style="color: white; font-family: 'Mochiy Pop One', sans-serif; font-size: 26px;">AIが分析した今月の注目祭り ✨</h3>
            </div>
            <div class="ai_content" style="text-align: center;">
                <div class="ai_loading_bar" id="ai_loading">
                    <div class="bar_inner" style="background: white; height: 4px; border-radius: 2px; margin-bottom: 20px; animation: loading 2s infinite;"></div>
                    <span style="color: rgba(255, 255, 255, 0.8); font-weight: 600;">AIが最新データを分析中です...</span>
                </div>
                <div class="ai_result" id="ai_result" style="display:none;">
                    <div style="background: white; border-radius: 30px; padding: 40px; display: inline-block; max-width: 700px; box-shadow: 0 15px 35px rgba(0,0,0,0.1);">
                        <p class="ai_text" style="font-size: 22px; color: #333; line-height: 1.6; margin-bottom: 15px;">
                            「2026年1月、現在最も人気のある祭りは<br><strong style="color: #764ba2; font-size: 26px;">江原道華川のヤマメ祭り</strong>です」
                        </p>
                        <p class="ai_sub_text" style="color: #666; font-size: 16px; margin-bottom: 30px;">氷の上で楽しむ特別な体験。家族や恋人と一緒に、忘れられない冬の思い出を作りませんか。 ❄️</p>
                        <div class="ai_links" style="display: flex; gap: 15px; justify-content: center;">
                            <a href="${pageContext.request.contextPath}/festival/list.do?keyword=ヤマメ" class="btn_ai_more" style="background: #764ba2; color: white; padding: 12px 30px; border-radius: 30px; text-decoration: none; font-weight: 700; transition: 0.3s; box-shadow: 0 5px 15px rgba(118, 75, 162, 0.3);">祭り詳細を見る</a>
                            <a href="https://japanese.visitkorea.or.kr/svc/main/index.do" target="_blank" class="btn_ai_visit" style="background: #f8f9fa; color: #764ba2; padding: 12px 30px; border-radius: 30px; text-decoration: none; font-weight: 700; transition: 0.3s; border: 1px solid #764ba2;">Visit Korea 公式情報</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
@keyframes loading {
    0% { width: 0; margin-left: 0; }
    50% { width: 100%; margin-left: 0; }
    100% { width: 0; margin-left: 100%; }
}
.festival_card_modern:hover {
    transform: translateY(-12px);
    box-shadow: 0 20px 40px rgba(255, 143, 163, 0.12) !important;
    border-color: #ffeff2 !important;
}
.festival_card_modern:hover img {
    transform: scale(1.1);
}
.festival_card_modern:hover h3 {
    color: #ff8fa3;
}
.btn_ai_more:hover {
    transform: translateY(-3px);
    box-shadow: 0 8px 20px rgba(118, 75, 162, 0.4);
}
.btn_ai_visit:hover {
    background: #764ba2;
    color: white;
}
</style>

<!-- Weather & Utility Section -->
<div class="container utility_section" style="padding: 100px 0;">
    <div class="utility_grid" style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 30px;">
        <div class="utility_item weather_widget" style="background: #ebf8ff; border-radius: 35px; padding: 40px; box-shadow: 0 10px 25px rgba(66, 153, 225, 0.05); border: 1px solid #e1f5fe;">
            <div class="util_header" style="display: flex; align-items: center; gap: 12px; margin-bottom: 25px;">
                <div style="width: 45px; height: 45px; background: white; border-radius: 15px; display: flex; align-items: center; justify-content: center; color: #4299e1; font-size: 20px; box-shadow: 0 5px 15px rgba(0,0,0,0.05);">
                    <i class="fas fa-cloud-sun"></i>
                </div>
                <span style="font-size: 18px; font-weight: 800; color: #2b6cb0;">韓国の天気予報</span>
            </div>
            <div class="weather_content" style="text-align: center;">
                <img src="https://wttr.in/Seoul_2tqp_lang=ja.png" alt="Seoul Weather" style="max-width: 100%; border-radius: 20px; margin-bottom: 20px;">
                <p style="color: #4a5568; font-weight: 600; font-size: 15px;">現在のソウルは、お祭りを楽しむのに最適な気候です。 ☀️</p>
            </div>
        </div>
        <div class="utility_item travel_tips" style="background: #fffaf0; border-radius: 35px; padding: 40px; box-shadow: 0 10px 25px rgba(237, 137, 54, 0.05); border: 1px solid #fff5e6;">
            <div class="util_header" style="display: flex; align-items: center; gap: 12px; margin-bottom: 25px;">
                <div style="width: 45px; height: 45px; background: white; border-radius: 15px; display: flex; align-items: center; justify-content: center; color: #ed8936; font-size: 20px; box-shadow: 0 5px 15px rgba(0,0,0,0.05);">
                    <i class="fas fa-lightbulb"></i>
                </div>
                <span style="font-size: 18px; font-weight: 800; color: #c05621;">今月の旅行のヒント</span>
            </div>
            <ul class="tip_list" style="list-style: none; padding: 0;">
                <li style="background: white; padding: 15px 20px; border-radius: 20px; margin-bottom: 12px; display: flex; align-items: center; gap: 12px; color: #7b341e; font-weight: 600; box-shadow: 0 4px 10px rgba(0,0,0,0.02);">
                    <span style="font-size: 20px;">🧣</span> 1月の韓国は氷点下に。防寒を！
                </li>
                <li style="background: white; padding: 15px 20px; border-radius: 20px; margin-bottom: 12px; display: flex; align-items: center; gap: 12px; color: #7b341e; font-weight: 600; box-shadow: 0 4px 10px rgba(0,0,0,0.02);">
                    <span style="font-size: 20px;">🍢</span> 屋台の「おでん」は冬の定番。
                </li>
                <li style="background: white; padding: 15px 20px; border-radius: 20px; display: flex; align-items: center; gap: 12px; color: #7b341e; font-weight: 600; box-shadow: 0 4px 10px rgba(0,0,0,0.02);">
                    <span style="font-size: 20px;">🚌</span> 地方へは「K-バス」が便利！
                </li>
            </ul>
        </div>
    </div>
</div>

<script>
$(document).ready(function() {
    // AI Section Interaction
    setTimeout(function() {
        $('#ai_loading').fadeOut(500, function() {
            $('#ai_result').fadeIn(800);
        });
    }, 2000);
});
</script>

<!-- 人気コミュニティ投稿セクション -->
<div class="container board_section" style="padding-bottom: 100px;">
	<div class="section_title_new" style="text-align: center; margin-bottom: 60px;">
        <span style="color: #ff8fa3; font-weight: 800; font-size: 14px; letter-spacing: 2px;">COMMUNITY TOPICS</span>
		<h2 style="font-family: 'Mochiy Pop One', sans-serif; font-size: 32px; color: #333; margin-top: 10px;">人気のコミュニティ投稿 <span style="color: #ff8fa3;">💖</span></h2>
		<p style="color: #888; margin-top: 10px;">みんなの旅行記をチェックしてみましょう！</p>
	</div>
	
	<div class="top_boards_new" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 30px;">
		<c:forEach var="board" items="${topBoards}">
			<div class="board_card_modern" onclick="location.href='${pageContext.request.contextPath}/board/view.do?bno=${board.bno}'"
                 style="background: #fff; border-radius: 25px; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.03); transition: 0.4s; cursor: pointer; border: 1px solid #f0f0f0;">
				<c:if test="${not empty board.imgfile}">
					<div class="board_img" style="height: 200px; overflow: hidden;">
						<img src="${pageContext.request.contextPath}/display.do?name=${board.imgfile}" alt="${board.title}" style="width: 100%; height: 100%; object-fit: cover; transition: 0.6s;">
					</div>
				</c:if>
				<div class="board_body" style="padding: 25px;">
                    <div style="display: flex; align-items: center; gap: 8px; margin-bottom: 15px;">
                        <div style="width: 30px; height: 30px; background: #fff5f6; border-radius: 50%; display: flex; align-items: center; justify-content: center; color: #ff8fa3; font-size: 12px;">
                            <i class="fas fa-user"></i>
                        </div>
                        <span class="user_badge" style="color: #ff8fa3; font-weight: 700; font-size: 13px;">@${board.username}</span>
                    </div>
					<h3 style="font-size: 18px; color: #333; font-weight: 800; margin-bottom: 12px; line-height: 1.4;">${board.title}</h3>
					<p style="color: #888; font-size: 14px; line-height: 1.6; margin-bottom: 20px; height: 45px; overflow: hidden;">${fn:substring(board.content, 0, 50)}...</p>
					<div class="board_footer" style="display: flex; align-items: center; gap: 15px; padding-top: 15px; border-top: 1px solid #fdf0f2;">
						<span style="font-size: 13px; color: #ffb7c5;"><i class="fas fa-heart"></i> ${board.likes}</span>
						<span style="font-size: 13px; color: #ccb3b7;"><i class="fas fa-eye"></i> ${board.views}</span>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
    <div style="text-align: center; margin-top: 50px;">
        <a href="${pageContext.request.contextPath}/board/list.do" class="more_link_btn" style="display: inline-block; background: #fff; color: #ff8fa3; border: 2px solid #ffeff2; padding: 12px 40px; border-radius: 30px; text-decoration: none; font-weight: 700; transition: 0.3s;">
            コミュニティへもっと見る <i class="fas fa-chevron-right" style="font-size: 12px; margin-left: 5px;"></i>
        </a>
    </div>
</div>

<style>
.board_card_modern:hover {
    transform: translateY(-10px);
    box-shadow: 0 15px 35px rgba(255, 143, 163, 0.1) !important;
    border-color: #ffeff2 !important;
}
.board_card_modern:hover img {
    transform: scale(1.1);
}
.board_card_modern:hover h3 {
    color: #ff8fa3;
}
.more_link_btn:hover {
    background: #ff8fa3;
    color: white;
    border-color: #ff8fa3;
    transform: translateY(-3px);
    box-shadow: 0 10px 20px rgba(255, 143, 163, 0.2);
}
</style>

<!-- 旅行ニュースセクション -->
<div class="container news_section_new" style="padding: 80px 0;">
    <div class="section_title_new" style="display: flex; justify-content: space-between; align-items: flex-end; margin-bottom: 40px;">
        <div>
            <h2 style="font-family: 'Mochiy Pop One', sans-serif; font-size: 32px; color: #333; margin-bottom: 10px;">旅行ニュース ✨</h2>
            <p style="color: #888; font-size: 16px;">韓国の最新トレンドをいち早くお届けします！</p>
        </div>
        <a href="${pageContext.request.contextPath}/news/list.do" class="more_link" style="color: #ff8fa3; text-decoration: none; font-weight: 700; font-size: 15px; border: 2px solid #ffeff2; padding: 8px 20px; border-radius: 20px; transition: 0.3s;">
            もっと見る <i class="fas fa-chevron-right" style="font-size: 12px; margin-left: 5px;"></i>
        </a>
    </div>
    
    <div class="news_grid_modern" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(350px, 1fr)); gap: 30px;">
        <c:forEach var="news" items="${newsList}" varStatus="status">
            <c:if test="${status.index < 3}">
                <div class="news_card_modern" onclick="if('${news.linkUrl}') window.open('${news.linkUrl}', '_blank')" 
                     style="background: #fff; border-radius: 20px; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.03); border: 1px solid #fdf0f2; transition: 0.3s; cursor: pointer; position: relative;">
                    <div class="news_thumb_box" style="height: 200px; overflow: hidden; background: #fff9fa; position: relative;">
                        <c:choose>
                            <c:when test="${not empty news.imgfile}">
                                <img src="${pageContext.request.contextPath}/upload/${news.imgfile}" alt="${news.title}" style="width: 100%; height: 100%; object-fit: cover; transition: 0.5s;">
                            </c:when>
                            <c:otherwise>
                                <div style="width: 100%; height: 100%; display: flex; align-items: center; justify-content: center; color: #ffb7c5; font-size: 40px;">
                                    <i class="far fa-newspaper"></i>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div class="news_tag_pill" style="position: absolute; top: 15px; left: 15px; background: #ff8fa3; color: white; padding: 5px 15px; border-radius: 15px; font-size: 12px; font-weight: 700; box-shadow: 0 4px 10px rgba(255, 143, 163, 0.3);">
                            ${news.category}
                        </div>
                    </div>
                    <div class="news_info_box" style="padding: 25px;">
                        <h3 style="font-size: 18px; color: #333; margin-bottom: 15px; line-height: 1.5; font-weight: 700; height: 54px; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;">
                            ${news.title}
                        </h3>
                        <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 20px; padding-top: 15px; border-top: 1px solid #fff0f3;">
                            <span style="color: #ccb3b7; font-size: 13px; font-weight: 500;">
                                <i class="far fa-calendar-alt" style="margin-right: 5px;"></i> ${fn:substring(news.regdate, 0, 10)}
                            </span>
                            <span style="color: #ff8fa3; font-size: 13px; font-weight: 700;">
                                READ MORE <i class="fas fa-heart" style="margin-left: 3px; font-size: 10px;"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>

<style>
.news_card_modern:hover { transform: translateY(-10px); box-shadow: 0 15px 35px rgba(255, 143, 163, 0.1); border-color: #ffeff2; }
.news_card_modern:hover img { transform: scale(1.1); }
.news_card_modern:hover h3 { color: #ff8fa3; }
.more_link:hover { background: #ffeff2; color: #ff8fa3; transform: scale(1.05); }
</style>

<script>
function addNews() {
    const title = prompt('ニュースのタイトル:');
    if(!title) return;
    const category = prompt('カテゴリー (例: 江原観光財団):');
    const linkUrl = prompt('リンクURL (任意):');
    
    $.ajax({
        url: '${pageContext.request.contextPath}/admin/newsInsert.do',
        type: 'POST',
        data: { title: title, category: category, linkUrl: linkUrl },
        success: function() { location.reload(); }
    });
}

function deleteNews(nno) {
    if(!confirm('このニュースを削除しますか？')) return;
    $.ajax({
        url: '${pageContext.request.contextPath}/admin/newsDelete.do',
        type: 'POST',
        data: { nno: nno },
        success: function() {
            location.reload();
        }
    });
}

function editNews(nno, oldTitle, oldCategory, oldLink) {
    const title = prompt('ニュースのタイトル:', oldTitle);
    if(!title) return;
    const category = prompt('カテゴリー (例: 江原観光財団):', oldCategory);
    const linkUrl = prompt('リンクURL (任意):', oldLink);
    
    $.ajax({
        url: '${pageContext.request.contextPath}/admin/newsUpdate.do',
        type: 'POST',
        data: { nno: nno, title: title, category: category, linkUrl: linkUrl },
        success: function() {
            location.reload();
        }
    });
}
</script>

<%@ include file="/footer.jsp" %>

<script>
$(document).ready(function() {
    // Hero Carousel Swiper
    var heroSwiper = new Swiper(".heroSwiper", {
        slidesPerView: 1,
        centeredSlides: true,
        spaceBetween: 0,
        loop: true,
        speed: 1000,
        grabCursor: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false,
        },
        navigation: {
            nextEl: ".hero-next",
            prevEl: ".hero-prev",
        },
        on: {
            init: function () {
                updateHeroPagination(this);
            },
            slideChange: function () {
                updateHeroPagination(this);
            }
        }
    });

    function updateHeroPagination(swiper) {
        var realIndex = swiper.realIndex + 1;
        var total = swiper.slides.length - (swiper.params.loop ? 2 : 0);
        
        // Update numbers
        $('.curr_idx').text(realIndex < 10 ? '0' + realIndex : realIndex);
        $('.total_idx').text(total < 10 ? '0' + total : total);
        
        // Update progress bar
        var progress = (realIndex / total) * 100;
        $('.hero_progress_bar').css('width', progress + '%');
    }

    // Festival Slider
    var festivalSwiper = new Swiper(".festival_grid", {
        slidesPerView: 1,
        spaceBetween: 25,
        loop: true,
        autoplay: {
            delay: 3500,
            disableOnInteraction: false,
        },
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        breakpoints: {
            640: { slidesPerView: 2 },
            1024: { slidesPerView: 3 },
            1200: { slidesPerView: 4 },
        },
    });
});
</script>

