<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container">
    <div class="festival_section_title">
        <c:choose>
            <c:when test="${not empty keyword}">
                <h2>「${keyword}」の検索結果</h2>
                <p>${totalCount}件のお祭りが検索されました。</p>
            </c:when>
            <c:when test="${status == 'ongoing'}">
                <h2>開催中のお祭り</h2>
                <p>今すぐ楽しめる活気あふれるお祭り情報です。</p>
            </c:when>
            <c:when test="${status == 'upcoming'}">
                <h2>開催予定のお祭り</h2>
                <p>まもなく始まるワクワクするお祭りを一足先にチェック！</p>
            </c:when>
            <c:when test="${status == 'past'}">
                <h2>過去のお祭り</h2>
                <p>惜しくも逃した、過去のお祭りの記録です。</p>
            </c:when>
            <c:when test="${status == 'this_month'}">
                <h2>今月のお祭り</h2>
                <p>今月開催中、または開催予定の注目のお祭り情報です。</p>
            </c:when>
            <c:otherwise>
                <h2>すべてのお祭り情報</h2>
                <p>韓国各地の魅力あふれるお祭り情報を一目で確認しましょう。</p>
            </c:otherwise>
        </c:choose>
        
        <div class="festival_tabs">
            <a href="${pageContext.request.contextPath}/festival/list.do" class="tab_item ${empty status ? 'active' : ''}">
                <span class="tab_jp">すべて</span>
                <span class="tab_ko">전체</span>
            </a>
            <a href="${pageContext.request.contextPath}/festival/list.do?status=ongoing" class="tab_item ${status == 'ongoing' ? 'active' : ''}">
                <span class="tab_jp">開催中</span>
                <span class="tab_ko">개최중</span>
            </a>
            <a href="${pageContext.request.contextPath}/festival/list.do?status=upcoming" class="tab_item ${status == 'upcoming' ? 'active' : ''}">
                <span class="tab_jp">開催予定</span>
                <span class="tab_ko">개최예정</span>
            </a>
            <a href="${pageContext.request.contextPath}/festival/list.do?status=past" class="tab_item ${status == 'past' ? 'active' : ''}">
                <span class="tab_jp">過去のお祭り</span>
                <span class="tab_ko">과거축제</span>
            </a>
            <a href="${pageContext.request.contextPath}/festival/list.do?status=this_month" class="tab_item ${status == 'this_month' ? 'active' : ''}">
                <span class="tab_jp">今月のお祭り</span>
                <span class="tab_ko">이달의 축제</span>
            </a>
        </div>

        <c:if test="${sessionScope.role == 'ADMIN'}">
            <div class="admin_action_wrap">
                <a href="${pageContext.request.contextPath}/festival/write.do" class="btn_admin_add">+ お祭り情報の登録</a>
            </div>
        </c:if>
    </div>

    <div class="festival_list">
        <c:choose>
            <c:when test="${not empty festivalList}">
                <c:forEach var="festival" items="${festivalList}">
                    <div class="festival_box" onclick="location.href='${pageContext.request.contextPath}/festival/view.do?fno=${festival.fno}'">
                        <div class="festival_image">
                            <c:choose>
                                <c:when test="${not empty festival.imgfile}">
                                    <img src="${pageContext.request.contextPath}/display.do?name=${festival.imgfile}" alt="${festival.name}">
                                </c:when>
                                <c:otherwise>
                                    <div class="no_image_placeholder"><i class="far fa-image"></i></div>
                                </c:otherwise>
                            </c:choose>
                            <div class="card_tag">${festival.region}</div>
                        </div>
                        <div class="festival_info">
                            <h3>${festival.name}</h3>
                            <c:if test="${festival.isRecommended == 'Y'}">
                                <span class="badge_recommended"><i class="fas fa-star"></i> 今月の注目</span>
                            </c:if>
                            <p class="festival_region"><i class="fas fa-map-pin"></i> ${festival.location} <small class="info_supplement">(開催場所)</small></p>
                            <p class="festival_date">
                                <i class="far fa-calendar-alt"></i>
                                <c:choose>
                                    <c:when test="${not empty festival.startDate && not empty festival.endDate}">
                                        ${festival.startDate} 〜 ${festival.endDate}
                                    </c:when>
                                    <c:otherwise>
                                        期間情報なし
                                    </c:otherwise>
                                </c:choose>
                                <small class="info_supplement">(開催期間)</small>
                            </p>
                            <div class="festival_stats">
                                <span><i class="fas fa-eye"></i> ${festival.views} <small class="info_supplement">(閲覧数)</small></span>
                            </div>
                            
                            <c:if test="${sessionScope.role == 'ADMIN'}">
                                <div class="admin_card_actions" onclick="event.stopPropagation();">
                                    <button type="button" class="btn_set_recommended ${festival.isRecommended == 'Y' ? 'active' : ''}" 
                                            onclick="setRecommended(${festival.fno})">
                                        <i class="fas fa-star"></i> ${festival.isRecommended == 'Y' ? '注目解除' : '注目に設定'}
                                    </button>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="no_results_box">
                    <p>現在、登録されている祭り情報はございません。</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- ページネーション -->
    <c:if test="${totalPages > 1}">
        <div class="pagination_new">
            <c:if test="${currentPage > 1}">
                <a href="${pageContext.request.contextPath}/festival/list.do?page=${currentPage - 1}&status=${status}&keyword=${keyword}" class="page_btn prev">
                    <i class="fas fa-chevron-left"></i>
                </a>
            </c:if>
            
            <div class="page_numbers">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="${pageContext.request.contextPath}/festival/list.do?page=${i}&status=${status}&keyword=${keyword}" 
                       class="page_num ${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
            </div>

            <c:if test="${currentPage < totalPages}">
                <a href="${pageContext.request.contextPath}/festival/list.do?page=${currentPage + 1}&status=${status}&keyword=${keyword}" class="page_btn next">
                    <i class="fas fa-chevron-right"></i>
                </a>
            </c:if>
        </div>
    </c:if>
</div>

<style>
.badge_recommended {
    display: inline-block;
    background-color: #ff9800;
    color: white;
    padding: 2px 8px;
    border-radius: 4px;
    font-size: 0.8rem;
    font-weight: bold;
    margin-bottom: 8px;
}
.admin_card_actions {
    margin-top: 15px;
    padding-top: 10px;
    border-top: 1px dashed #eee;
    text-align: right;
}
.btn_set_recommended {
    background-color: white;
    color: #ff9800;
    border: 1px solid #ff9800;
    padding: 5px 10px;
    border-radius: 4px;
    cursor: pointer;
    font-size: 0.85rem;
    transition: all 0.2s;
}
.btn_set_recommended:hover {
    background-color: #fff3e0;
}
.btn_set_recommended.active {
    background-color: #ff9800;
    color: white;
}
</style>

<script>
function setRecommended(fno) {
    if (!confirm('このお祭りを「今月の注目のお祭り」に設定しますか？\n(他のお祭りの設定は解除されます)')) {
        return;
    }
    
    fetch('${pageContext.request.contextPath}/festival/setRecommended.do?fno=' + fno)
        .then(response => response.text())
        .then(result => {
            if (result === '1') {
                alert('設定されました！');
                location.reload();
            } else {
                alert('設定に失敗しました。');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('通信エラーが発生しました。');
        });
}
</script>

<%@ include file="/footer.jsp" %>
