<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>

<div class="page_header" style="background: linear-gradient(135deg, #fff5f6 0%, #fff 100%); padding: 80px 0; text-align: center; border-bottom: 1px solid #ffeff2;">
    <div class="container">
        <span style="display: inline-block; background: #ff8fa3; color: white; padding: 5px 15px; border-radius: 20px; font-size: 14px; font-weight: 700; margin-bottom: 15px; box-shadow: 0 4px 10px rgba(255, 143, 163, 0.2);">NEWS & TOPICS</span>
        <h1 style="font-size: 36px; color: #333; font-weight: 800; margin-bottom: 15px;">旅行ニュース <span style="color: #ff8fa3;">🌸</span></h1>
        <p style="color: #888; font-size: 16px;">韓国旅行の最新情報と地域のトピックスをお届けします。</p>
    </div>
</div>

<div class="container" style="margin-top: 60px; margin-bottom: 100px;">
    <div class="news_grid" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 35px;">
        <c:forEach var="news" items="${newsList}">
            <div class="news_card_modern" onclick="if('${news.linkUrl}') window.open('${news.linkUrl}', '_blank')" 
                 style="background: #fff; border-radius: 24px; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.03); transition: all 0.4s cubic-bezier(0.165, 0.84, 0.44, 1); cursor: pointer; border: 1px solid #f0f0f0; position: relative;">
                <div class="news_thumb_box" style="width: 100%; height: 220px; overflow: hidden; position: relative;">
                    <c:choose>
                        <c:when test="${not empty news.imgfile}">
                            <img src="${pageContext.request.contextPath}/upload/${news.imgfile}" alt="${news.title}" 
                                 style="width: 100%; height: 100%; object-fit: cover; transition: 0.6s;">
                        </c:when>
                        <c:otherwise>
                            <div style="width: 100%; height: 100%; display: flex; flex-direction: column; align-items: center; justify-content: center; background: #fff5f6; color: #ff8fa3;">
                                <i class="far fa-image" style="font-size: 40px; margin-bottom: 10px;"></i>
                                <span style="font-size: 14px; font-weight: 600;">No Image</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="news_tag_pill" style="position: absolute; top: 20px; left: 20px; background: rgba(255, 255, 255, 0.9); color: #ff8fa3; padding: 6px 16px; border-radius: 30px; font-size: 12px; font-weight: 700; backdrop-filter: blur(5px); box-shadow: 0 4px 10px rgba(0,0,0,0.05);">
                        ${news.category}
                    </div>
                </div>
                <div class="news_info_box" style="padding: 25px;">
                    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 15px;">
                        <span style="width: 30px; height: 2px; background: #ff8fa3; border-radius: 2px;"></span>
                        <span style="color: #aaa; font-size: 13px; font-weight: 500;">${fn:substring(news.regdate, 0, 10)}</span>
                    </div>
                    <h3 style="font-size: 19px; line-height: 1.5; margin-bottom: 20px; height: 57px; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; color: #333; font-weight: 700; transition: 0.3s;">
                        ${news.title}
                    </h3>
                    <div style="display: flex; justify-content: flex-end;">
                        <span class="read_more_btn" style="color: #ff8fa3; font-size: 14px; font-weight: 700; display: flex; align-items: center; gap: 5px; transition: 0.3s;">
                            READ MORE <i class="fas fa-arrow-right" style="font-size: 12px;"></i>
                        </span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Pagination -->
    <div class="pagination_kawaii" style="display: flex; align-items: center; justify-content: center; gap: 15px; margin-top: 70px;">
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/news/list.do?page=${currentPage - 1}" class="page_nav_btn">
                <i class="fas fa-chevron-left"></i>
            </a>
        </c:if>
        
        <div class="page_numbers" style="display: flex; gap: 10px;">
            <c:forEach var="i" begin="1" end="${totalPages}">
                <a href="${pageContext.request.contextPath}/news/list.do?page=${i}" 
                   class="page_num_btn ${i == currentPage ? 'active' : ''}">
                    ${i}
                </a>
            </c:forEach>
        </div>

        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/news/list.do?page=${currentPage + 1}" class="page_nav_btn">
                <i class="fas fa-chevron-right"></i>
            </a>
        </c:if>
    </div>

    <style>
    .news_card_modern:hover {
        transform: translateY(-12px);
        box-shadow: 0 20px 40px rgba(255, 143, 163, 0.12);
        border-color: #ffeff2;
    }
    .news_card_modern:hover img {
        transform: scale(1.1);
    }
    .news_card_modern:hover h3 {
        color: #ff8fa3;
    }
    .news_card_modern:hover .read_more_btn {
        gap: 10px;
    }

    /* Pagination Styles */
    .page_nav_btn {
        width: 45px;
        height: 45px;
        display: flex;
        align-items: center;
        justify-content: center;
        background: #fff;
        border: 2px solid #ffeff2;
        color: #ff8fa3;
        border-radius: 15px;
        transition: 0.3s;
        text-decoration: none;
    }
    .page_nav_btn:hover {
        background: #ff8fa3;
        color: #fff;
        border-color: #ff8fa3;
        transform: scale(1.05);
    }
    .page_num_btn {
        width: 45px;
        height: 45px;
        display: flex;
        align-items: center;
        justify-content: center;
        background: #fff;
        border: 2px solid #ffeff2;
        color: #888;
        border-radius: 15px;
        font-weight: 700;
        transition: 0.3s;
        text-decoration: none;
    }
    .page_num_btn.active {
        background: #ff8fa3;
        color: #fff;
        border-color: #ff8fa3;
        box-shadow: 0 5px 15px rgba(255, 143, 163, 0.3);
    }
    .page_num_btn:hover:not(.active) {
        border-color: #ff8fa3;
        color: #ff8fa3;
        transform: translateY(-3px);
    }
    </style>
</div>

<%@ include file="/footer.jsp" %>
