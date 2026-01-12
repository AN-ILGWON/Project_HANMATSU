<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- footer -->
<footer class="footer_renewal">
    <div class="footer_container">
        <div class="footer_top_section">
            <div class="footer_brand_area">
                <div class="footer_logo">
                    <a href="${pageContext.request.contextPath}/main.do">
                        <span class="hanmatsu_footer">韓まつ</span>
                    </a>
                </div>
                <p class="footer_slogan">韓国の祭りを、日本の皆様の感動に。<br>旬の情報を、正確にお届けします。</p>
                <div class="footer_partner">
                    <a href="https://japanese.visitkorea.or.kr/svc/main/index.do?utm_source=ko_visitkorea&utm_medium=landing_url&utm_campaign=vk_main_jpn" target="_blank">
                        <img src="https://korean.visitkorea.or.kr/resources/images/common/logo_footer.png" alt="Visit Korea" style="height: 30px; opacity: 0.8;">
                    </a>
                </div>
            </div>
            
            <div class="footer_nav_grid">
                <div class="footer_nav_col">
                    <h3>お祭り情報</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/festival/list.do">おすすめ祭り</a></li>
                        <li><a href="${pageContext.request.contextPath}/main.do#festival">今月の祭り</a></li>
                        <li><a href="${pageContext.request.contextPath}/festival/apiList.do">全国お祭りMAP</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/calendar.do">お祭りカレンダー</a></li>
                    </ul>
                </div>
                <div class="footer_nav_col">
                    <h3>コミュニティ</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/board/list.do">自由掲示板</a></li>
                        <li><a href="${pageContext.request.contextPath}/board/list.do?category=review">祭りレビュー</a></li>
                        <li><a href="${pageContext.request.contextPath}/board/list.do?category=photo">フォトギャラリー</a></li>
                        <li><a href="${pageContext.request.contextPath}/board/list.do?category=notice">お知らせ</a></li>
                    </ul>
                </div>
                <div class="footer_nav_col">
                    <h3>韓まつについて</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/info/about.do">韓まつ紹介</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/contact.do">お問い合わせ</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/partnership.do">提携のご案内</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/recruit.do">採用情報</a></li>
                    </ul>
                </div>
                <div class="footer_nav_col">
                    <h3>お役立ち情報</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/info/faq.do">よくあるご質問</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/sitemap.do">サイトマップ</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/guide.do">利用ガイド</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="footer_bottom_section">
            <div class="footer_utility_links">
                <a href="${pageContext.request.contextPath}/info/privacy.do">個人情報保護方針</a>
                <a href="${pageContext.request.contextPath}/info/terms.do">利用規約</a>
                <a href="${pageContext.request.contextPath}/info/copyright.do">著作権・リンクについて</a>
                <a href="${pageContext.request.contextPath}/info/cookie.do">クッキーポリシー</a>
            </div>
            <div class="footer_copyright">
                <p>日本政府観光局 (JNTO) 提携モデル | © 2026 Hanmatsu. All Rights Reserved.</p>
                <p class="address">〒160-0004 東京都新宿区四谷1-6-4 | 代表電話: 03-1234-5678</p>
            </div>
        </div>
    </div>
</footer>

<style>
/* Footer Renewal Styles */
.footer_renewal {
    background-color: #f8f8f8;
    border-top: 1px solid #e0e0e0;
    padding: 60px 0 40px;
    margin-top: 100px;
    font-family: 'Noto Sans JP', sans-serif;
}

.footer_container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}

.footer_top_section {
    display: flex;
    justify-content: space-between;
    padding-bottom: 50px;
    border-bottom: 1px solid #eee;
}

.footer_brand_area {
    flex: 0 0 250px;
}

.hanmatsu_footer {
    font-family: 'Mochiy Pop One', sans-serif;
    font-size: 28px;
    color: #333;
    font-weight: bold;
}

.footer_slogan {
    margin-top: 20px;
    font-size: 13px;
    color: #666;
    line-height: 1.8;
}

.footer_nav_grid {
    flex: 1;
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 30px;
    margin-left: 60px;
}

.footer_nav_col h3 {
    font-size: 15px;
    font-weight: 700;
    color: #333;
    margin-bottom: 20px;
}

.footer_nav_col ul {
    list-style: none;
    padding: 0;
}

.footer_nav_col ul li {
    margin-bottom: 10px;
}

.footer_nav_col ul li a {
    font-size: 13px;
    color: #666;
    text-decoration: none;
    transition: color 0.2s;
}

.footer_nav_col ul li a:hover {
    color: var(--primary-color);
    text-decoration: underline;
}

.footer_bottom_section {
    padding-top: 40px;
    text-align: center;
}

.footer_utility_links {
    display: flex;
    justify-content: center;
    gap: 25px;
    margin-bottom: 30px;
}

.footer_utility_links a {
    font-size: 12px;
    color: #666;
    text-decoration: none;
    font-weight: 500;
}

.footer_utility_links a:hover {
    color: #333;
}

.footer_copyright {
    font-size: 12px;
    color: #999;
}

.footer_copyright p {
    margin-bottom: 5px;
}

.footer_copyright .address {
    color: #bbb;
}

@media (max-width: 1024px) {
    .footer_top_section {
        flex-direction: column;
    }
    .footer_nav_grid {
        margin-left: 0;
        margin-top: 40px;
        grid-template-columns: repeat(2, 1fr);
    }
}

@media (max-width: 640px) {
    .footer_nav_grid {
        grid-template-columns: 1fr;
    }
    .footer_utility_links {
        flex-wrap: wrap;
        gap: 15px;
    }
}
</style>

<script src="${pageContext.request.contextPath}/js/jquery.cookie.min.js"></script>	
<script src="${pageContext.request.contextPath}/js/main.js"></script>
	
</body>
</html>
