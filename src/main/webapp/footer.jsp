<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- footer -->
<footer class="footer_renewal">
    <div class="footer_container">
        <div class="footer_top_section">
            <div class="footer_brand_area">
                <div class="footer_logo">
                    <a href="${pageContext.request.contextPath}/main.do">
                        <span class="hanmatsu_footer">韓まつ(Hanmatsu)</span>
                    </a>
                </div>
                <p class="footer_slogan">「韓まつ(Hanmatsu)」は、韓国の「韓（ハン）」と日本の「まつり」を合わせた名前です。<br>韓国と日本を繋ぐ架け橋として、最新の情報を正確にお伝えいたします。</p>
                <div class="footer_partner">
                    <a href="https://japanese.visitkorea.or.kr/svc/main/index.do" target="_blank" class="footer_partner_link">
                        Visit Korea Partner
                    </a>
                </div>
            </div>
            
            <div class="footer_nav_grid">
                <div class="footer_nav_col">
                    <h3>お祭り情報</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/festival/list.do?status=ongoing">おすすめのお祭り</a></li>
                        <li><a href="${pageContext.request.contextPath}/festival/list.do?status=this_month">今月のお祭り</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/traffic.do">交通情報</a></li>
                        <li><a href="${pageContext.request.contextPath}/festival/calendar.do">お祭りカレンダー</a></li>
                    </ul>
                </div>
                <div class="footer_nav_col">
                    <h3>コミュニティ</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/board/list.do">掲示板</a></li>
                        <li><a href="${pageContext.request.contextPath}/board/list.do?category=review">お祭りレビュー</a></li>
                        <li><a href="${pageContext.request.contextPath}/board/list.do?category=photo">フォトギャラリー</a></li>
                        <li><a href="${pageContext.request.contextPath}/board/list.do?category=notice">お知らせ</a></li>
                    </ul>
                </div>
                <div class="footer_nav_col">
                    <h3>韓まつ紹介</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/info/about.do">韓まつ紹介</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/contact.do">お問い合わせ</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/partnership.do">提携のご案内</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/recruit.do">採用情報</a></li>
                    </ul>
                </div>
                <div class="footer_nav_col">
                    <h3>サポート</h3>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/info/faq.do">よくある質問</a></li>
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
                <a href="${pageContext.request.contextPath}/info/copyright.do">著作権ポリシー</a>
                <a href="${pageContext.request.contextPath}/info/cookie.do">クッキー設定</a>
            </div>
            <div class="footer_copyright">
                <p>韓国観光公社 (KTO) 提携モデル | © 2026 Hanmatsu. All Rights Reserved.</p>
                <p class="address">ソウル特別市中区茶洞10 | 代表番号: 02-1234-5678</p>
            </div>
        </div>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/js/jquery.cookie.min.js"></script>	
<script src="${pageContext.request.contextPath}/js/main.js"></script>
	
</body>
</html>
