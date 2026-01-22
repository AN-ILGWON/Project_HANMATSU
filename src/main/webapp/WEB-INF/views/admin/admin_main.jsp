<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container admin_py_60">
    <div class="admin_container_1000">
        <div class="admin_mb_40 admin_text_center">
            <h1 class="admin_dashboard_title">管理者ダッシュボード</h1>
            <p class="admin_page_subtitle">サイトのコンテンツとユーザーを管理します。</p>
        </div>

        <!-- Stats Section -->
        <div class="admin_stats_grid">
            <div class="admin_stat_card primary">
                <div class="admin_stat_label">総会員数</div>
                <div class="admin_stat_value">${memberCount}</div>
            </div>
            <div class="admin_stat_card secondary">
                <div class="admin_stat_label">総投稿数</div>
                <div class="admin_stat_value">${boardCount}</div>
            </div>
            <div class="admin_stat_card teal">
                <div class="admin_stat_label">お祭り数</div>
                <div class="admin_stat_value">${festivalCount}</div>
            </div>
            <div class="admin_stat_card orange">
                <div class="admin_stat_label">ニュース数</div>
                <div class="admin_stat_value">${newsCount}</div>
            </div>
        </div>

        <div class="admin_menu_grid">
            <!-- Festival Management -->
            <div class="admin_menu_card">
                <div class="admin_menu_icon primary">
                    <i class="fas fa-calendar-alt"></i>
                </div>
                <h3 class="admin_menu_title">お祭り管理</h3>
                <p class="admin_menu_text">新しいお祭りの登録や、既存情報の修正・削除を行います。「リスト確認」から<strong>今月の注目のお祭り</strong>を設定できます。</p>
                <div class="admin_menu_btns">
                    <a href="${pageContext.request.contextPath}/festival/write.do" class="btn_admin_full btn_admin_primary">新規登録</a>
                    <a href="${pageContext.request.contextPath}/festival/list.do" class="btn_admin_full btn_admin_secondary">リスト確認</a>
                </div>
            </div>

            <!-- News Management -->
            <div class="admin_menu_card">
                <div class="admin_menu_icon secondary">
                    <i class="fas fa-newspaper"></i>
                </div>
                <h3 class="admin_menu_title">ニュース管理</h3>
                <p class="admin_menu_text">最新の旅行ニュースやトピックスを管理します。</p>
                <div class="admin_menu_btns">
                    <a href="${pageContext.request.contextPath}/admin/newsManage.do" class="btn_admin_full btn_admin_secondary_color">ニュース管理</a>
                    <a href="${pageContext.request.contextPath}/news/list.do" class="btn_admin_full btn_admin_secondary">リスト確認</a>
                </div>
            </div>

            <!-- User Management -->
            <div class="admin_menu_card">
                <div class="admin_menu_icon teal">
                    <i class="fas fa-users"></i>
                </div>
                <h3 class="admin_menu_title">ユーザー管理</h3>
                <p class="admin_menu_text">会員リストの閲覧や権限の管理を行います。</p>
                <div class="admin_menu_btns">
                    <a href="${pageContext.request.contextPath}/admin/memberList.do" class="btn_admin_full btn_admin_teal">会員リスト</a>
                </div>
            </div>

            <!-- Board Management -->
            <div class="admin_menu_card">
                <div class="admin_menu_icon orange">
                    <i class="fas fa-clipboard-list"></i>
                </div>
                <h3 class="admin_menu_title">掲示板・カテゴリ管理</h3>
                <p class="admin_menu_text">不適切な投稿の削除やカテゴリ構成の変更を行います。</p>
                <div class="admin_menu_btns">
                    <a href="${pageContext.request.contextPath}/admin/categoryManage.do" class="btn_admin_full btn_admin_orange">カテゴリ管理</a>
                    <a href="${pageContext.request.contextPath}/admin/boardManage.do" class="btn_admin_full btn_admin_secondary">全投稿管理</a>
                </div>
            </div>

            <!-- Slide Management -->
            <div class="admin_menu_card">
                <div class="admin_menu_icon purple">
                    <i class="fas fa-images"></i>
                </div>
                <h3 class="admin_menu_title">サイト全体・画像管理</h3>
                <p class="admin_menu_text">メインスライドの画像やテキスト、リンク先を管理します。</p>
                <div class="admin_menu_btns">
                    <a href="${pageContext.request.contextPath}/admin/bannerManage.do" class="btn_admin_full btn_admin_purple">スライド管理</a>
                </div>
            </div>

            <!-- Site Info Management -->
            <div class="admin_menu_card">
                <div class="admin_menu_icon blue">
                    <i class="fas fa-info-circle"></i>
                </div>
                <h3 class="admin_menu_title">サイト情報管理</h3>
                <p class="admin_menu_text">FAQ、利用規約、会社紹介などの固定コンテンツを管理します。</p>
                <div class="admin_menu_btns">
                    <a href="${pageContext.request.contextPath}/admin/siteInfoManage.do" class="btn_admin_full btn_admin_blue">コンテンツ管理</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/footer.jsp" %>
