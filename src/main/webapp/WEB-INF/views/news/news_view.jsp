<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>

<div class="news_view_header">
    <div class="container">
        <div class="news_view_meta">
            <span class="news_category_badge">${news.category}</span>
            <span class="news_date_text">
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
        <h1 class="news_view_title">${news.title}</h1>
    </div>
</div>

<div class="container news_view_container">
    <div class="news_content_wrapper">
        <c:if test="${not empty news.imgfile}">
            <div class="news_main_img">
                <img src="${pageContext.request.contextPath}/display.do?name=${news.imgfile}" alt="${news.title}">
            </div>
        </c:if>
        
        <div class="news_main_content">
            <c:choose>
                <c:when test="${not empty news.content}">
                    <c:out value="${news.content}" />
                </c:when>
                <c:otherwise>
                    <p class="no_content_msg">詳細内容がありません。</p>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${not empty news.linkUrl}">
            <div class="news_external_link">
                <p>より詳細な情報は外部サイトでもご確認いただけます。</p>
                <a href="${news.linkUrl}" target="_blank" class="btn_external">
                    <i class="fas fa-external-link-alt"></i> 外部サイトへ移動
                </a>
            </div>
        </c:if>

        <div class="news_view_footer">
            <a href="${pageContext.request.contextPath}/news/list.do" class="btn_back_list">
                <i class="fas fa-list"></i> ニュース一覧に戻る
            </a>
        </div>
    </div>
</div>

<style>
.news_view_header {
    background: #f8f9fa;
    padding: 60px 0;
    border-bottom: 1px solid #eee;
    text-align: center;
}
.news_view_meta {
    margin-bottom: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 15px;
}
.news_category_badge {
    background: var(--secondary-color);
    color: white;
    padding: 5px 15px;
    border-radius: 20px;
    font-size: 14px;
    font-weight: 700;
}
.news_date_text {
    color: #888;
    font-size: 14px;
}
.news_view_title {
    font-size: 32px;
    font-weight: 800;
    color: var(--text-dark);
    line-height: 1.4;
    max-width: 800px;
    margin: 0 auto;
}
.news_view_container {
    padding: 60px 0 100px;
}
.news_content_wrapper {
    max-width: 800px;
    margin: 0 auto;
}
.news_main_img {
    margin-bottom: 40px;
    border-radius: 20px;
    overflow: hidden;
    box-shadow: var(--shadow-md);
}
.news_main_img img {
    width: 100%;
    display: block;
}
.news_main_content {
    font-size: 18px;
    line-height: 1.8;
    color: #444;
    margin-bottom: 60px;
    white-space: pre-wrap;
}
.news_external_link {
    background: #f1f3f5;
    padding: 30px;
    border-radius: 15px;
    text-align: center;
    margin-bottom: 60px;
}
.news_external_link p {
    margin-bottom: 15px;
    color: #666;
}
.btn_external {
    display: inline-block;
    background: var(--primary-color);
    color: white;
    padding: 10px 25px;
    border-radius: 10px;
    text-decoration: none;
    font-weight: 700;
    transition: 0.3s;
}
.btn_external:hover {
    background: #ff4a4f;
    transform: translateY(-3px);
    box-shadow: 0 5px 15px rgba(255, 90, 95, 0.3);
}
.news_view_footer {
    text-align: center;
    border-top: 1px solid #eee;
    padding-top: 40px;
}
.btn_back_list {
    display: inline-block;
    color: #888;
    text-decoration: none;
    font-weight: 600;
    transition: 0.3s;
}
.btn_back_list:hover {
    color: var(--primary-color);
}
.btn_back_list i {
    margin-right: 8px;
}

@media (max-width: 768px) {
    .news_view_header {
        padding: 40px 20px;
    }
    .news_view_title {
        font-size: 24px;
    }
    .news_view_container {
        padding: 40px 20px;
    }
}
</style>

<%@ include file="/footer.jsp" %>
