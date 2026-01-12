<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<div class="container board_list_container">
    <div class="page_header_new">
        <h2>コミュニティ</h2>
        <p>皆様の旅行記をぜひご覧ください。</p>
    </div>

    <div class="board_category_tabs">
        <a href="${pageContext.request.contextPath}/board/list.do" class="category_tab ${empty category ? 'active' : ''}">すべて</a>
        <a href="${pageContext.request.contextPath}/board/list.do?category=祭りレビュー" class="category_tab ${category == '祭りレビュー' ? 'active' : ''}">祭りレビュー</a>
        <a href="${pageContext.request.contextPath}/board/list.do?category=自由掲示板" class="category_tab ${category == '自由掲示板' ? 'active' : ''}">自由掲示板</a>
        <a href="${pageContext.request.contextPath}/board/list.do?category=Q&A" class="category_tab ${category == 'Q&A' ? 'active' : ''}">Q&A</a>
    </div>

	<div class="board_header_actions">
        <div class="board_stats">
            全 <strong>${totalCount}</strong> 件の投稿
        </div>
		<c:if test="${not empty sessionScope.userid}">
			<a href="${pageContext.request.contextPath}/board/write.do" class="btn_write_new">新しい投稿を共有する</a>
		</c:if>
	</div>
	
	<div class="board_list_card">
		<table class="custom_table">
			<thead>
				<tr>
					<th width="80">番号</th>
					<th width="120">カテゴリー</th>
					<th>タイトル</th>
					<th width="150">投稿者</th>
					<th width="80">いいね</th>
					<th width="80">閲覧</th>
					<th width="120">投稿日</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}" varStatus="status">
					<tr onclick="location.href='${pageContext.request.contextPath}/board/view.do?bno=${board.bno}'" style="cursor:pointer;">
						<td class="td_num">${totalCount - (status.index + (currentPage - 1) * pageSize)}</td>
						<td class="td_category">
							<span class="category_badge ${board.category == 'Q&A' ? 'qna' : (board.category == '自由掲示板' ? 'free' : 'review')}">
								${board.category}
							</span>
						</td>
						<td class="td_title">
							<span class="title_text">${board.title}</span>
							<c:if test="${board.replyCount > 0}">
								<span class="reply_badge">${board.replyCount}</span>
							</c:if>
						</td>
						<td class="td_user"><span class="user_pill">${board.username}</span></td>
						<td class="td_likes">${board.likes}</td>
						<td class="td_views">${board.views}</td>
						<td class="td_date">${fn:substring(board.regdate, 0, 10)}</td>
					</tr>
				</c:forEach>
                <c:if test="${empty boardList}">
                    <tr>
                        <td colspan="7" class="no_data">現在、投稿された記事はございません。</td>
                    </tr>
                </c:if>
			</tbody>
		</table>
	</div>
	
	<div class="pagination_new">
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/board/list.do?page=${currentPage - 1}${not empty category ? '&category='.concat(category) : ''}" class="page_btn prev">
                <i class="fas fa-chevron-left"></i> 前へ
            </a>
		</c:if>
        
        <div class="page_numbers">
            <c:forEach var="i" begin="1" end="${totalPages}">
                <a href="${pageContext.request.contextPath}/board/list.do?page=${i}${not empty category ? '&category='.concat(category) : ''}" 
                   class="page_num ${i == currentPage ? 'active' : ''}">${i}</a>
            </c:forEach>
        </div>

		<c:if test="${currentPage < totalPages}">
			<a href="${pageContext.request.contextPath}/board/list.do?page=${currentPage + 1}${not empty category ? '&category='.concat(category) : ''}" class="page_btn next">
                次へ <i class="fas fa-chevron-right"></i>
            </a>
		</c:if>
	</div>
</div>

<style>
.board_list_container { padding-top: 60px; padding-bottom: 80px; }
.page_header_new { text-align: center; margin-bottom: 50px; }
.page_header_new h2 { font-size: 36px; color: #333; margin-bottom: 15px; font-family: 'Mochiy Pop One', sans-serif; }
.page_header_new p { color: #888; font-size: 16px; }

.board_category_tabs { display: flex; justify-content: center; gap: 15px; margin-bottom: 40px; }
.category_tab { 
    padding: 12px 30px; background: #fff; color: #ff8fa3; text-decoration: none; 
    border-radius: 30px; font-weight: 700; font-size: 15px; transition: 0.3s;
    border: 2px solid #ffeff2; box-shadow: 0 4px 6px rgba(255, 143, 163, 0.05);
}
.category_tab:hover { background: #fff9fa; border-color: #ffb7c5; transform: translateY(-2px); }
.category_tab.active { background: #ff8fa3; color: white; border-color: #ff8fa3; box-shadow: 0 6px 15px rgba(255, 143, 163, 0.3); }

.board_header_actions { display: flex; justify-content: space-between; align-items: flex-end; margin-bottom: 25px; padding: 0 5px; }
.board_stats { font-size: 15px; color: #777; background: #fff; padding: 8px 20px; border-radius: 20px; border: 1px solid #f0f0f0; }
.board_stats strong { color: #ff8fa3; font-size: 20px; }

.btn_write_new { 
    padding: 14px 30px; background: #ff8fa3; color: white; text-decoration: none; 
    border-radius: 35px; font-weight: 700; transition: 0.3s; font-size: 15px; 
    box-shadow: 0 5px 15px rgba(255, 143, 163, 0.3); border: none;
    display: flex; align-items: center; gap: 8px;
}
.btn_write_new::before { content: '✨'; font-size: 16px; }
.btn_write_new:hover { background: #ff7a91; transform: translateY(-3px) scale(1.02); box-shadow: 0 8px 20px rgba(255, 143, 163, 0.4); }

.board_list_card { background: #fff; border-radius: 25px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); overflow: hidden; border: 1px solid #fdf0f2; }
.custom_table { width: 100%; border-collapse: collapse; }
.custom_table th { background: #fff9fa; padding: 20px 15px; font-size: 14px; color: #ff8fa3; font-weight: 800; border-bottom: 2px solid #ffeff2; }
.custom_table td { padding: 20px 15px; text-align: center; font-size: 14px; color: #666; border-bottom: 1px solid #fff0f3; transition: 0.2s; }
.custom_table tr:last-child td { border-bottom: none; }
.custom_table tr:hover td { background: #fffcfd; }

.td_title { text-align: left !important; padding-left: 30px !important; }
.title_text { font-weight: 600; color: #444; transition: 0.2s; font-size: 15px; }
.custom_table tr:hover .title_text { color: #ff8fa3; }

.category_badge { 
    display: inline-block; padding: 5px 12px; border-radius: 12px; font-size: 12px; font-weight: 700;
}
.category_badge.review { background: #f0f3ff; color: #6366f1; }
.category_badge.free { background: #f0fdf4; color: #22c55e; }
.category_badge.qna { background: #fff7ed; color: #f97316; }

.reply_badge { 
    display: inline-block; padding: 3px 10px; background: #ffeff2; color: #ff8fa3; 
    font-size: 12px; font-weight: 800; border-radius: 12px; margin-left: 10px; vertical-align: middle;
}

.user_pill { padding: 5px 15px; background: #fdf2f4; border-radius: 20px; font-size: 13px; color: #ff8fa3; font-weight: 600; }
.no_data { padding: 120px 0 !important; color: #ccb3b7 !important; font-size: 18px !important; font-weight: 500; }

.pagination_new { display: flex; align-items: center; justify-content: center; gap: 25px; margin-top: 60px; }
.page_btn { 
    padding: 12px 30px; background: #fff; border: 2px solid #ffeff2; color: #ff8fa3; 
    text-decoration: none; border-radius: 30px; font-size: 15px; font-weight: 700; transition: 0.3s;
}
.page_btn:hover { border-color: #ff8fa3; background: #fff9fa; transform: scale(1.05); }
.page_numbers { display: flex; gap: 12px; align-items: center; }
.page_num {
    width: 40px; height: 40px; display: flex; align-items: center; justify-content: center;
    border-radius: 50%; text-decoration: none; color: #ccb3b7; font-weight: 700; transition: 0.3s;
}
.page_num.active { background: #ff8fa3; color: white; box-shadow: 0 5px 15px rgba(255, 143, 163, 0.3); }
.page_num:hover:not(.active) { background: #fff0f3; color: #ff8fa3; }
</style>

<%@ include file="/footer.jsp" %>
