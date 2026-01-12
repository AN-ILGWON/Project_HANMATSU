<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container">
    <div class="section_title">
        <h2>韓国の祭り情報</h2>
        <p>韓国観光公社の最新データに基づくお祭り情報です。</p>
    </div>

    <!-- 地域フィルター -->
    <div class="filter_bar">
        <a href="${pageContext.request.contextPath}/festival/apiList.do" class="${empty areaCode ? 'active' : ''}">全地域</a>
        <a href="${pageContext.request.contextPath}/festival/apiList.do?areaCode=1" class="${areaCode == '1' ? 'active' : ''}">ソウル</a>
        <a href="${pageContext.request.contextPath}/festival/apiList.do?areaCode=6" class="${areaCode == '6' ? 'active' : ''}">釜山</a>
        <a href="${pageContext.request.contextPath}/festival/apiList.do?areaCode=39" class="${areaCode == '39' ? 'active' : ''}">済州</a>
    </div>

    <div class="festival_list">
        <c:choose>
            <c:when test="${not empty tourFestivalList}">
                <c:forEach var="festival" items="${tourFestivalList}">
                    <div class="festival_box" onclick="location.href='${pageContext.request.contextPath}/festival/apiView.do?contentId=${festival.contentId}'" style="cursor:pointer;">
                        <div class="festival_image">
                            <c:choose>
                                <c:when test="${not empty festival.firstImage}">
                                    <img src="${festival.firstImage}" alt="${festival.title}">
                                </c:when>
                                <c:otherwise>
                                    <div class="no_image_placeholder">画像がありません</div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="festival_info">
                            <h3>${festival.title}</h3>
                            <p class="festival_region">${festival.addr1}</p>
                            <p class="festival_date">
                                ${fn:substring(festival.eventStartDate, 0, 4)}-${fn:substring(festival.eventStartDate, 4, 6)}-${fn:substring(festival.eventStartDate, 6, 8)} 
                                〜 
                                ${fn:substring(festival.eventEndDate, 0, 4)}-${fn:substring(festival.eventEndDate, 4, 6)}-${fn:substring(festival.eventEndDate, 6, 8)}
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p style="text-align:center; padding: 50px 0;">お祭り情報が見つかりませんでした。</p>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- ページネーション -->
    <div class="pagination">
        <c:if test="${totalPages > 1}">
            <c:forEach var="i" begin="1" end="${totalPages > 10 ? 10 : totalPages}">
                <a href="${pageContext.request.contextPath}/festival/apiList.do?pageNo=${i}&areaCode=${areaCode}" class="${currentPage == i ? 'active' : ''}">${i}</a>
            </c:forEach>
        </c:if>
    </div>
</div>

<style>
.filter_bar { margin-bottom: 30px; text-align: center; }
.filter_bar a { display: inline-block; padding: 8px 20px; margin: 0 5px; background: #f4f4f4; border-radius: 20px; text-decoration: none; color: #333; }
.filter_bar a.active { background: #d32f2f; color: #fff; }
.festival_list { display: grid; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 20px; }
.festival_box { border: 1px solid #eee; border-radius: 8px; overflow: hidden; background: #fff; transition: transform 0.2s; }
.festival_box:hover { transform: translateY(-5px); box-shadow: 0 5px 15px rgba(0,0,0,0.1); }
.festival_image img { width: 100%; height: 200px; object-fit: cover; }
.festival_info { padding: 15px; }
.festival_info h3 { font-size: 18px; margin-bottom: 10px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
.festival_region { color: #666; font-size: 14px; margin-bottom: 5px; }
.festival_date { color: #d32f2f; font-weight: bold; font-size: 13px; }
.pagination { margin-top: 30px; text-align: center; }
.pagination a { display: inline-block; padding: 5px 12px; margin: 0 3px; border: 1px solid #ddd; text-decoration: none; color: #333; }
.pagination a.active { background: #d32f2f; color: #fff; border-color: #d32f2f; }
</style>

<%@ include file="/footer.jsp" %>
