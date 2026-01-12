<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<div class="container">
	<div class="festival_detail">
		<div class="festival_header">
			<div class="header_top">
				<span class="region">${festival.region}</span>
				<c:if test="${not empty sessionScope.userid}">
					<button type="button" class="btn_wish ${isWished ? 'active' : ''}" onclick="toggleWish(${festival.fno})">
						<i class="wish_icon">${isWished ? '❤️' : '🤍'}</i> お気に入り
					</button>
				</c:if>
			</div>
			<h2>${festival.name}</h2>
			<p class="date">${fn:substring(festival.startDate, 0, 10)} ～ ${fn:substring(festival.endDate, 0, 10)}</p>
			
			<!-- SNS共有ボタン -->
			<div class="sns_share">
				<a href="javascript:shareX()" class="btn_x" title="X(Twitter)で共有">
					<img src="${pageContext.request.contextPath}/images/icon_x.png" alt="X" onerror="this.src='https://abs.twimg.com/favicons/twitter.2.ico'">
				</a>
				<a href="javascript:shareLine()" class="btn_line" title="LINEで送る">
					<img src="${pageContext.request.contextPath}/images/icon_line.png" alt="LINE" onerror="this.src='https://line.me/favicon.ico'">
				</a>
			</div>
		</div>
		
		<div class="festival_body">
			<div class="festival_img">
				<c:choose>
					<c:when test="${not empty festival.imgfile}">
						<img src="${pageContext.request.contextPath}/display.do?name=${festival.imgfile}" alt="${festival.name}">
					</c:when>
					<c:otherwise>
						<div class="no_image_placeholder" style="height: 400px; border-radius: 15px;">画像がありません</div>
					</c:otherwise>
				</c:choose>
			</div>
			
			<div class="festival_info_detail">
				<div class="info_item">
					<strong>場所</strong>
					<span>${festival.location}</span>
				</div>
				<c:if test="${not empty festival.homepage}">
					<div class="info_item">
						<strong>公式サイト</strong>
						<span><a href="${festival.homepage}" target="_blank" class="link_homepage">公式サイトを表示</a></span>
					</div>
				</c:if>
				<c:if test="${not empty festival.mapUrl}">
					<div class="info_item">
						<strong>位置情報</strong>
						<span><a href="${festival.mapUrl}" target="_blank" class="link_map">Google マップで表示</a></span>
					</div>
				</c:if>
				<div class="info_item">
					<strong>閲覧数</strong>
					<span>${festival.views}</span>
				</div>
				<div class="info_item">
					<strong>登録日</strong>
					<span>${fn:substring(festival.regdate, 0, 10)}</span>
				</div>
			</div>
			
			<div class="festival_description">
				<h3>祭り詳細情報</h3>
				<div class="desc_content">
					${festival.description}
				</div>
			</div>
		</div>

		<!-- 地図セクション -->
		<div class="festival_extra_info">
			<div class="map_container" style="flex: 1;">
				<h3>アクセス情報</h3>
				<div id="map" style="width:100%; height:350px; background:#eee; border-radius:10px;"></div>
			</div>
		</div>
		
		<div class="festival_footer">
			<a href="${pageContext.request.contextPath}/main.do" class="btn_back">祭り一覧へ戻る</a>
			<c:if test="${sessionScope.role == 'ADMIN'}">
				<a href="${pageContext.request.contextPath}/festival/update.do?fno=${festival.fno}" class="btn_update">編集</a>
				<a href="${pageContext.request.contextPath}/festival/delete.do?fno=${festival.fno}" class="btn_delete" onclick="return confirm('本当に削除しますか？')">削除</a>
			</c:if>
		</div>
	</div>
</div>

<%@ include file="/footer.jsp" %>

<!-- Google Maps API & Scripts -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC9uAHexbNz8TFf7_amBOKeCIUMjEQm26g&callback=initMap" async defer></script>
<script>
function toggleWish(fno) {
	$.ajax({
		url: '${pageContext.request.contextPath}/festival/wish.do',
		type: 'POST',
		data: { fno: fno },
		success: function(result) {
			if(result === 'login') {
				alert('ログインが必要です。');
				location.href = '${pageContext.request.contextPath}/member/login.do';
			} else {
				location.reload();
			}
		}
	});
}

    function initMap() {
        var address = "${festival.location}";
        var geocoder = new google.maps.Geocoder();
        
        geocoder.geocode({'address': address}, function(results, status) {
            if (status === 'OK') {
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 15,
                    center: results[0].geometry.location
                });
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location,
                    title: "${festival.name}"
                });
            } else {
                document.getElementById('map').innerHTML = "<p style='padding:20px;'>地図情報を読み込めませんでした。住所を確認してください。</p>";
            }
        });
    }

    // SNS共有機能
    function shareX() {
        var text = encodeURIComponent("【韓まつ】" + "${festival.name}" + " 祭りの情報です。");
        var url = encodeURIComponent(window.location.href);
        window.open("https://twitter.com/intent/tweet?text=" + text + "&url=" + url, "_blank");
    }

    function shareLine() {
        var text = encodeURIComponent("【韓まつ】" + "${festival.name}" + " \n" + window.location.href);
        window.open("https://social-plugins.line.me/lineit/share?url=" + encodeURIComponent(window.location.href) + "&text=" + text, "_blank");
    }
</script>
