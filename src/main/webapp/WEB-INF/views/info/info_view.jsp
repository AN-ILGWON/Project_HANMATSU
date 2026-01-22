<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="page_header">
    <div class="container">
        <h1>${title}</h1>
        <p>韓まつ(Hanmatsu)の${title}についてご案内いたします。</p>
    </div>
</div>

<div class="container info_page mt_60 mb_100">
    <div class="info_layout">
        <!-- Sidebar Navigation -->
        <aside class="info_sidebar">
            <h3 class="info_sidebar_title">サポート</h3>
            <ul class="info_sidebar_nav">
                <li><a href="${pageContext.request.contextPath}/info/about.do" class="${requestScope['javax.servlet.forward.path_info'] == '/about.do' ? 'active' : ''}">韓まつ(Hanmatsu)について</a></li>
                <li><a href="${pageContext.request.contextPath}/info/guide.do" class="${requestScope['javax.servlet.forward.path_info'] == '/guide.do' ? 'active' : ''}">利用ガイド</a></li>
                <li><a href="${pageContext.request.contextPath}/info/faq.do" class="${requestScope['javax.servlet.forward.path_info'] == '/faq.do' ? 'active' : ''}">よくある質問</a></li>
                <li><a href="${pageContext.request.contextPath}/info/contact.do" class="${requestScope['javax.servlet.forward.path_info'] == '/contact.do' ? 'active' : ''}">お問い合わせ</a></li>
                <li><a href="${pageContext.request.contextPath}/info/partnership.do" class="${requestScope['javax.servlet.forward.path_info'] == '/partnership.do' ? 'active' : ''}">パートナーシップのご案内</a></li>
                <li class="info_sidebar_divider">
                    <h3 class="info_sidebar_subtitle">法的情報</h3>
                    <ul class="info_sidebar_nav">
                        <li><a href="${pageContext.request.contextPath}/info/privacy.do" class="${requestScope['javax.servlet.forward.path_info'] == '/privacy.do' ? 'active' : ''}">プライバシーポリシー</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/terms.do" class="${requestScope['javax.servlet.forward.path_info'] == '/terms.do' ? 'active' : ''}">利用規約</a></li>
                    </ul>
                </li>
            </ul>
        </aside>

        <!-- Main Content -->
        <div class="info_content_card">
            <div class="info_content_header">
                <h2 class="info_content_title">${title}</h2>
            </div>
            <div class="info_content_body">
                ${content}
            </div>
            
            <div class="info_footer_actions">
                <a href="${pageContext.request.contextPath}/main.do" class="btn_back_home">ホームへ戻る</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="/footer.jsp" %>
