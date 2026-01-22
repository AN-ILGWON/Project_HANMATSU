<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/header.jsp" %>

<div class="page_header_news">
    <div class="container">
        <span class="page_header_pill">NEWS & TOPICS</span>
        <h1 class="page_header_h1">旅行ニュース <span>🏮</span></h1>
        <p class="page_header_p">韓国旅行の最新情報と地域のトピックスをお届けします。</p>
    </div>
</div>

<div class="container news_list_container">
    <div class="news_grid_list">
        <c:forEach var="news" items="${newsList}">
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
                    <div class="news_tag_pill">
                        ${news.category}
                    </div>
                </div>
                <div class="news_info_box">
                    <div class="news_meta_mini">
                        <span class="meta_bar"></span>
                        <span class="meta_date">
                            <c:choose>
                                <c:when test="${not empty news.regdate}">
                                    ${fn:substring(news.regdate, 0, 10)}
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                            <small class="info_supplement">(登録日)</small>
                        </span>
                    </div>
                    <h3>${news.title}</h3>
                    <div class="news_footer_list">
                        <span class="read_more_btn">
                            READ MORE <i class="fas fa-arrow-right"></i>
                            <small class="info_supplement">(詳細を見る)</small>
                        </span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Pagination -->
    <div class="pagination_kawaii">
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/news/list.do?page=${currentPage - 1}" class="page_nav_btn">
                <i class="fas fa-chevron-left"></i>
            </a>
        </c:if>
        
        <div class="page_numbers">
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
</div>

<%@ include file="/footer.jsp" %>
