<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<!-- MainVisual - Carousel Slider -->
<div class="main_hero_carousel">
    <div class="swiper heroSwiper">
        <div class="swiper-wrapper">
            <c:forEach var="banner" items="${bannerList}">
                <c:set var="bgStyle" value="" />
                <c:choose>
                    <c:when test="${empty banner.imgfile}">
                        <c:set var="bgStyle" value="background-color: #f0f0f0;" />
                    </c:when>
                    <c:when test="${fn:startsWith(banner.imgfile, 'http')}">
                        <c:set var="bgStyle" value="background-image: url('${banner.imgfile}');" />
                    </c:when>
                    <c:when test="${!fn:startsWith(banner.imgfile, 'slide')}">
                        <c:set var="bgStyle" value="background-image: url('${pageContext.request.contextPath}/display.do?name=${banner.imgfile}');" />
                    </c:when>
                </c:choose>
                
                <div class="swiper-slide hero_slide ${fn:startsWith(banner.imgfile, 'slide') ? banner.imgfile : ''}" 
                     style="${bgStyle}">
                    <div class="hero_overlay">
                        <div class="hero_text_wrap">
                            <h2>${banner.title}</h2>
                            <p>${banner.subtitle}</p>
                            <c:if test="${not empty banner.linkUrl}">
                                <a href="${banner.linkUrl}" class="hero_btn">詳細を見る</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- Swiper Navigation Arrows -->
        <div class="swiper-button-next hero-next"></div>
        <div class="swiper-button-prev hero-prev"></div>
    </div>
</div>

<!-- Quick Menu (Icon Circle Menu) -->
<div class="quick_service_section_wrap">
    <div class="container quick_service_section">
        <div>
            <span class="service_tag_new">SERVICE MENU</span>
            <h3>旅行の準備をお手伝いします <span class="accent_emoji">🏮</span></h3>
        </div>
        <div class="quick_circle_list">
            <a href="${pageContext.request.contextPath}/info/course.do" class="quick_circle_item_new">
                <div class="circle_icon_new">
                    <i class="fas fa-map-marked-alt"></i>
                </div>
                <span>旅行コース</span>
                <small class="info_supplement_mini">(おすすめプラン)</small>
            </a>
            <a href="${pageContext.request.contextPath}/info/food_info.do" class="quick_circle_item_new">
                <div class="circle_icon_new">
                    <i class="fas fa-utensils"></i>
                </div>
                <span>韓国料理情報</span>
                <small class="info_supplement_mini">(美食の旅)</small>
            </a>
            <a href="${pageContext.request.contextPath}/festival/list.do" class="quick_circle_item_new">
                <div class="circle_icon_new">
                    <i class="fas fa-mountain-sun"></i>
                </div>
                <span>地域のお祭り</span>
                <small class="info_supplement_mini">(地域イベント)</small>
            </a>
            <a href="${pageContext.request.contextPath}/info/guide.do" class="quick_circle_item_new">
                <div class="circle_icon_new">
                    <i class="fas fa-info-circle"></i>
                </div>
                <span>利用ガイド</span>
                <small class="info_supplement_mini">(サイトの利用方法)</small>
            </a>
        </div>
    </div>
</div>

<!-- Back to Top -->
<div class="back_to_top" onclick="window.scrollTo({top: 0, behavior: 'smooth'})">
    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"><polyline points="18 15 12 9 6 15"></polyline></svg>
</div>

<!-- Intro Section -->
<div class="intro_section_wrap">
    <div class="intro_section">
        <div class="intro_heart_bg"><i class="fas fa-heart"></i></div>
        <div class="container">
            <div class="intro_content_wrap">
                <h3 class="intro_title">
                    韓国旅行、いつも同じ場所ばかり行っていませんか？ <span class="secondary_emoji">🤔</span>
                </h3>
                <div class="intro_desc_box">
                    <p>「地方のお祭りに行ってみたいけど、行き方が心配...」</p>
                    <p>「正確なお祭り情報を探すのが大変...」</p>
                    <p>「ガイドブックにはない、現地ならではの特別感を感じたい！」</p>
                </div>
                <p class="intro_summary_text">
                    韓国お祭り情報専門サービス 韓まつ(Hanmatsu) が<br>
                    あなたの特別な旅行をお手伝いします。 ✨
                </p>
            </div>
        </div>
    </div>
</div>

<!-- Recommended Festival Section -->
<div class="festival_section_wrap">
    <section class="container main_section" id="festival">
        <div class="section_title_new">
            <span class="sub_title">MONTHLY FESTIVAL</span>
            <h2>今月の注目のお祭り情報 ✨</h2>
            <p>韓まつが自信を持っておすすめする今月の韓国体験</p>
        </div>
        
        <div class="swiper festival_grid">
            <div class="swiper-wrapper">
                <c:choose>
                    <c:when test="${not empty festivalList}">
                        <c:forEach var="festival" items="${festivalList}">
                            <div class="swiper-slide festival_slide_item">
                                <div class="festival_card_modern" onclick="location.href='${pageContext.request.contextPath}/festival/view.do?fno=${festival.fno}'">
                                    <div class="card_image">
                                        <c:choose>
                                            <c:when test="${not empty festival.imgfile}">
                                                <img src="${pageContext.request.contextPath}/display.do?name=${festival.imgfile}" alt="${festival.name}">
                                            </c:when>
                                            <c:otherwise>
                                                <div class="no_image_placeholder">画像がありません</div>
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="card_tag_pill">${festival.region}</div>
                                    </div>
                                    <div class="card_info">
                                        <h3>${festival.name}</h3>
                                        <p class="card_date">
                                            <i class="far fa-calendar-alt"></i> ${fn:substring(festival.startDate, 0, 10)} ~ ${fn:substring(festival.endDate, 0, 10)}
                                        </p>
                                        <p class="card_loc">
                                            <i class="fas fa-map-marker-alt"></i> ${festival.location}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="swiper-slide">
                            <p class="empty_msg">現在登録されているお祭り情報はありません。</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <!-- Add Navigation Arrows -->
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
            <div class="swiper-pagination"></div>
        </div>
    </section>
</div>

<!-- Recommended Section (Curated by Admin) -->
<div class="recommended_festival_section">
    <div class="recommended_bg_pattern"></div>
    <div class="container">
        <div class="recommended_box">
            <div class="recommended_header">
                <div class="recommended_icon_wrap">
                    <i class="fas fa-award"></i>
                </div>
                <h3>みんなが選んだ今月の注目のお祭り ✨</h3>
            </div>
            <div class="recommended_content">
                <div class="recommended_result" id="recommended_result">
                    <div class="recommended_result_box">
                        <c:choose>
                            <c:when test="${not empty recommendedFestival}">
                                <p class="recommended_text">
                                    今月、韓まつが自信を持っておすすめするのは...<br>
                                    <strong class="recommended_festival_name">${recommendedFestival.name}</strong>です！
                                </p>
                                <p class="recommended_sub_text">${fn:substring(recommendedFestival.description, 0, 100)}${fn:length(recommendedFestival.description) > 100 ? '...' : ''}</p>
                                <div class="recommended_links">
                                    <a href="${pageContext.request.contextPath}/festival/view.do?fno=${recommendedFestival.fno}" class="btn_recommended_more">詳細を見る <i class="fas fa-arrow-right"></i></a>
                                    <a href="https://www.google.com/maps/search/?api=1&query=${recommendedFestival.location}" target="_blank" class="btn_recommended_visit" title="地図で位置を確認">
                                        <i class="fas fa-map-marker-alt"></i>
                                    </a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <p class="recommended_text">現在、おすすめのお祭りを準備中です。</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Weather & Utility Section -->
<div class="utility_section_wrap">
    <div class="container utility_section">
        <div class="utility_grid">
            <div class="utility_item weather_link_widget">
                <div class="util_header">
                    <div class="util_icon">
                        <i class="fas fa-sun"></i>
                    </div>
                    <span>韓国の今日の天気</span>
                </div>
                <div class="weather_link_content">
                    <p class="weather_desc">最新の気象情報を確認して、快適な旅行を計画しましょう！</p>
                    <a href="https://www.weather.go.kr/w/index.do" target="_blank" class="weather_btn_new">
                        <i class="fas fa-external-link-alt"></i> 気象庁で確認する
                    </a>
                </div>
            </div>
            <div class="utility_item travel_tips">
                <div class="util_header">
                    <div class="util_icon">
                        <i class="fas fa-lightbulb"></i>
                    </div>
                    <span>${currentMonth}月の旅行のコツ</span>
                </div>
                <ul class="tip_list">
                    <c:forEach var="tip" items="${travelTips}">
                        <li class="tip_item">
                            <span>${tip[0]}</span> ${tip[1]}
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

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
        }
    });

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

<!-- Community Section (Top Liked Boards) -->
<section class="board_section">
    <div class="container">
        <div class="section_title_new">
            <span class="sub_title">COMMUNITY</span>
            <h2>みんなの旅行記 🌸</h2>
            <p>今、話題になっている投稿をチェックしてみましょう！</p>
        </div>
        
        <div class="board_grid_main">
            <c:forEach var="board" items="${topBoards}">
                <div class="board_card_modern" onclick="location.href='${pageContext.request.contextPath}/board/view.do?bno=${board.bno}'">
                    <div class="board_img">
                        <c:choose>
                            <c:when test="${not empty board.imgfile}">
                                <img src="${pageContext.request.contextPath}/display.do?name=${board.imgfile}" alt="${board.title}">
                            </c:when>
                            <c:otherwise>
                                <div class="no_image_placeholder">画像がありません</div>
                            </c:otherwise>
                        </c:choose>
                        <div class="board_category_tag">${board.category}</div>
                    </div>
                    <div class="board_body">
                        <h3>${board.title}</h3>
                        <div class="board_footer">
                            <span class="board_user"><i class="far fa-user"></i> ${board.username}</span>
                            <span class="board_likes"><i class="far fa-heart"></i> ${board.likes}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="section_more_btn_wrap">
            <a href="${pageContext.request.contextPath}/board/list.do" class="btn_view_all">すべての投稿を見る <i class="fas fa-chevron-right"></i></a>
        </div>
    </div>
</section>

<!-- News Section -->
<section class="news_section_new">
    <div class="container">
        <div class="section_title_new">
            <span class="sub_title">LATEST NEWS</span>
            <h2>韓国旅行最新ニュース 🏮</h2>
            <p>今すぐ役立つ最新の情報をお届けします。</p>
        </div>
        
        <div class="news_grid_list">
            <c:forEach var="news" items="${newsList}" end="2">
                <div class="news_card_modern" onclick="location.href='${pageContext.request.contextPath}/news/view.do?nno=${news.nno}'">
                    <div class="news_thumb_box">
                        <c:choose>
                            <c:when test="${not empty news.imgfile}">
                                <img src="${pageContext.request.contextPath}/display.do?name=${news.imgfile}" alt="${news.title}">
                            </c:when>
                            <c:otherwise>
                                <div class="news_placeholder_icon">
                                    <i class="far fa-image"></i>
                                    <span>No Image</span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div class="news_tag_pill">${news.category}</div>
                    </div>
                    <div class="news_info_box">
                        <div class="news_meta_mini">
                            <span class="meta_date">
                                <c:choose>
                                    <c:when test="${not empty news.regdate}">
                                        ${fn:substring(news.regdate, 0, 10)}
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                        <h3>${news.title}</h3>
                        <div class="news_footer_list">
                            <span class="read_more_btn">READ MORE <i class="fas fa-arrow-right"></i></span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="section_more_btn_wrap">
            <a href="${pageContext.request.contextPath}/news/list.do" class="btn_view_all">すべてのニュースを見る <i class="fas fa-chevron-right"></i></a>
        </div>
    </div>
</section>

<%@ include file="/footer.jsp" %>


