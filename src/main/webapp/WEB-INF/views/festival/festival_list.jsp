<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container">
    <div class="section_title">
        <h2>厳選おすすめ祭り</h2>
        <p>韓国各地の魅力溢れる祭り情報を厳選してお届けします。</p>
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <div style="text-align: right; margin-bottom: 20px;">
                <a href="${pageContext.request.contextPath}/festival/write.do" class="btn_admin_add" style="background: #ff6b6b; color: #fff; padding: 10px 20px; border-radius: 25px; text-decoration: none; font-weight: bold; display: inline-block;">+ 祭り情報の登録</a>
            </div>
        </c:if>
    </div>

    <div class="festival_list">
        <c:choose>
            <c:when test="${not empty festivalList}">
                <c:forEach var="festival" items="${festivalList}">
                    <div class="festival_box" onclick="location.href='${pageContext.request.contextPath}/festival/view.do?fno=${festival.fno}'" style="cursor:pointer;">
                        <div class="festival_image">
                            <c:choose>
                                <c:when test="${not empty festival.imgfile}">
                                    <img src="${pageContext.request.contextPath}/display.do?name=${festival.imgfile}" alt="${festival.name}">
                                </c:when>
                                <c:otherwise>
                                    <div class="no_image_placeholder">画像がありません</div>
                                </c:otherwise>
                            </c:choose>
                            <div class="card_tag">${festival.region}</div>
                        </div>
                        <div class="festival_info">
                            <h3>${festival.name}</h3>
                            <p class="festival_region">${festival.location}</p>
                            <p class="festival_date">
                                ${fn:substring(festival.startDate, 0, 10)} 〜 ${fn:substring(festival.endDate, 0, 10)}
                            </p>
                            <div class="festival_stats">
                                <span>閲覧数 ${festival.views}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p style="text-align:center; padding: 100px 0; color: #888;">現在、登録されている祭り情報はございません。</p>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- ページネーション -->
    <c:if test="${totalPages > 1}">
        <div class="pagination_new">
            <c:if test="${currentPage > 1}">
                <a href="${pageContext.request.contextPath}/festival/list.do?page=${currentPage - 1}" class="page_btn prev">
                    <i class="fas fa-chevron-left"></i>
                </a>
            </c:if>
            
            <div class="page_numbers">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="${pageContext.request.contextPath}/festival/list.do?page=${i}" 
                       class="page_num ${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
            </div>

            <c:if test="${currentPage < totalPages}">
                <a href="${pageContext.request.contextPath}/festival/list.do?page=${currentPage + 1}" class="page_btn next">
                    <i class="fas fa-chevron-right"></i>
                </a>
            </c:if>
        </div>
    </c:if>
</div>

<style>
.section_title { margin-bottom: 40px; text-align: center; }
.section_title h2 { font-family: 'Mochiy Pop One', sans-serif; color: #333; font-size: 2.2rem; margin-bottom: 10px; }
.section_title p { color: #666; font-size: 1.1rem; }

.festival_list { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 30px; margin-bottom: 50px; }
.festival_box { border: 1px solid #eee; border-radius: 15px; overflow: hidden; background: #fff; transition: all 0.3s ease; box-shadow: 0 4px 15px rgba(0,0,0,0.05); }
.festival_box:hover { transform: translateY(-10px); box-shadow: 0 10px 25px rgba(0,0,0,0.1); }

.festival_image { position: relative; width: 100%; height: 220px; overflow: hidden; }
.festival_image img { width: 100%; height: 100%; object-fit: cover; transition: transform 0.3s ease; }
.festival_box:hover .festival_image img { transform: scale(1.1); }

.card_tag { position: absolute; top: 15px; left: 15px; background: rgba(255, 107, 107, 0.9); color: #fff; padding: 5px 12px; border-radius: 20px; font-size: 0.85rem; font-weight: bold; z-index: 1; }

.no_image_placeholder { width: 100%; height: 100%; background: #f8f9fa; display: flex; align-items: center; justify-content: center; color: #adb5bd; font-size: 0.9rem; font-weight: 500; }

.festival_info { padding: 20px; }
.festival_info h3 { font-size: 1.25rem; margin-bottom: 10px; color: #333; font-weight: 700; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
.festival_region { color: #666; font-size: 0.95rem; margin-bottom: 8px; display: flex; align-items: center; }
.festival_date { color: #ff6b6b; font-weight: bold; font-size: 0.9rem; margin-bottom: 15px; }
.festival_stats { display: flex; justify-content: flex-end; color: #999; font-size: 0.85rem; border-top: 1px solid #f0f0f0; pt: 10px; }
</style>

<%@ include file="/footer.jsp" %>
