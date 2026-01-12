<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="page_header">
    <div class="container">
        <h1>${title}</h1>
        <p>韓まつ(Hanmatsu)の${title}についてご案内いたします。</p>
    </div>
</div>

<div class="container info_page" style="margin-top: 60px; margin-bottom: 100px;">
    <div style="display: flex; gap: 40px; align-items: flex-start;">
        <!-- Sidebar Navigation -->
        <aside style="width: 280px; flex-shrink: 0; background: #fff; border: 1px solid #eee; border-radius: 12px; padding: 25px; position: sticky; top: 100px;">
            <h3 style="font-size: 18px; margin-bottom: 20px; color: #333; border-bottom: 2px solid var(--primary-color); padding-bottom: 10px;">カスタマーサポート</h3>
            <ul class="info_sidebar_nav" style="list-style: none; padding: 0;">
                <li><a href="${pageContext.request.contextPath}/info/about.do" class="${requestScope['javax.servlet.forward.path_info'] == '/about.do' ? 'active' : ''}">韓まつについて</a></li>
                <li><a href="${pageContext.request.contextPath}/info/guide.do" class="${requestScope['javax.servlet.forward.path_info'] == '/guide.do' ? 'active' : ''}">利用ガイド</a></li>
                <li><a href="${pageContext.request.contextPath}/info/faq.do" class="${requestScope['javax.servlet.forward.path_info'] == '/faq.do' ? 'active' : ''}">よくある質問</a></li>
                <li><a href="${pageContext.request.contextPath}/info/contact.do" class="${requestScope['javax.servlet.forward.path_info'] == '/contact.do' ? 'active' : ''}">お問い合わせ</a></li>
                <li><a href="${pageContext.request.contextPath}/info/partnership.do" class="${requestScope['javax.servlet.forward.path_info'] == '/partnership.do' ? 'active' : ''}">提携のご案内</a></li>
                <li style="margin-top: 20px; padding-top: 20px; border-top: 1px solid #eee;">
                    <h3 style="font-size: 16px; margin-bottom: 15px; color: #333;">法的方針</h3>
                    <ul style="list-style: none; padding: 0;">
                        <li><a href="${pageContext.request.contextPath}/info/privacy.do" class="${requestScope['javax.servlet.forward.path_info'] == '/privacy.do' ? 'active' : ''}">個人情報処理方針</a></li>
                        <li><a href="${pageContext.request.contextPath}/info/terms.do" class="${requestScope['javax.servlet.forward.path_info'] == '/terms.do' ? 'active' : ''}">利用規約</a></li>
                    </ul>
                </li>
            </ul>
        </aside>

        <!-- Main Content -->
        <div class="info_content_wrap" style="flex-grow: 1; background: #fff; border-radius: 12px; padding: 40px; box-shadow: 0 4px 15px rgba(0,0,0,0.05);">
            <div class="info_content_header" style="margin-bottom: 30px; border-bottom: 1px solid #eee; padding-bottom: 20px;">
                <h2 style="font-size: 28px; color: #333;">${title}</h2>
            </div>
            <div class="info_content_body" style="line-height: 1.8; color: #555;">
                ${content}
            </div>
            
            <div style="text-align: center; margin-top: 60px; padding-top: 30px; border-top: 1px solid #eee;">
                <a href="${pageContext.request.contextPath}/main.do" class="btn_back" style="display: inline-block; padding: 12px 30px; background: #333; color: #fff; border-radius: 5px; text-decoration: none; transition: background 0.3s;">ホームへ戻る</a>
            </div>
        </div>
    </div>
</div>

<style>
.info_sidebar_nav li a {
    display: block;
    padding: 12px 15px;
    color: #666;
    text-decoration: none;
    border-radius: 8px;
    transition: all 0.3s;
    font-size: 15px;
}
.info_sidebar_nav li a:hover {
    background: #f8f9fa;
    color: var(--primary-color);
}
.info_sidebar_nav li a.active {
    background: var(--primary-color);
    color: #fff;
    font-weight: 500;
}
.info_content_body h3 {
    font-size: 22px;
    color: #333;
    margin: 40px 0 20px;
    border-left: 5px solid var(--primary-color);
    padding-left: 15px;
}
.info_content_body p {
    margin-bottom: 15px;
}
.info_content_body ul {
    margin-bottom: 20px;
}
.info_content_body li {
    margin-bottom: 10px;
}
</style>

<%@ include file="/footer.jsp" %>
