<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<div class="container">
	<div class="festival_detail">
		<div class="festival_header">
			<div class="header_top">
				<span class="region">${festival.region}</span>
				<c:if test="${not empty sessionScope.userid}">
					<button type="button" class="btn_wish ${isWished ? 'active' : ''}" onclick="toggleWish(${festival.fno})" title="お気に入り">
						<i class="wish_icon ${isWished ? 'fas fa-heart' : 'far fa-heart'}"></i>
					</button>
				</c:if>
			</div>
			<h2>${festival.name}</h2>
			<p class="date">
				<c:choose>
					<c:when test="${not empty festival.startDate && not empty festival.endDate}">
						${festival.startDate} ～ ${festival.endDate}
					</c:when>
					<c:otherwise>
						期間情報なし
					</c:otherwise>
				</c:choose>
			</p>
			
			<!-- SNS共有ボタン -->
			<div class="sns_share">
				<a href="javascript:shareX()" class="btn_x" title="X(Twitter)で共有"><i class="fa-brands fa-x-twitter"></i></a>
				<a href="javascript:shareLine()" class="btn_line" title="LINEで共有"><i class="fa-brands fa-line"></i></a>
			</div>
		</div>
		
		<div class="festival_body">
			<div class="festival_img">
				<c:choose>
					<c:when test="${not empty festival.imgfile}">
						<img src="${pageContext.request.contextPath}/display.do?name=${festival.imgfile}" alt="${festival.name}">
					</c:when>
					<c:otherwise>
						<div class="no_image_placeholder_large"><i class="far fa-image"></i></div>
					</c:otherwise>
				</c:choose>
			</div>
			
			<div class="festival_info_detail">
				<div class="festival_info_grid">
					<div class="info_column">
						<div class="info_item">
							<div class="info_item_header">
								<i class="fas fa-map-marker-alt"></i>
								<span>所在地</span>
							</div>
							<div class="info_item_content">
								${festival.location}
							</div>
						</div>
						<div class="info_item">
							<div class="info_item_header">
								<i class="fas fa-eye"></i>
								<span>閲覧数</span>
							</div>
							<div class="info_item_content">
								${festival.views}
								<small class="info_supplement">回閲覧されました</small>
							</div>
						</div>
						<div class="info_item">
							<div class="info_item_header">
								<i class="fas fa-calendar-alt"></i>
								<span>登録日</span>
							</div>
							<div class="info_item_content">
								<c:out value="${not empty festival.regdate ? festival.regdate : '정보 없음'}" />
								<small class="info_supplement">に登録されました</small>
							</div>
						</div>
					</div>

					<div class="info_links_column">
						<div class="info_links_title">外部リンク・共有</div>
						<div class="info_links">
							<c:if test="${not empty festival.homepage}">
								<div class="btn_icon_wrapper">
									<a href="${festival.homepage}" target="_blank" class="btn_icon_link home" title="公式サイトへ移動"><i class="fas fa-home"></i></a>
									<span class="icon_label">公式サイト</span>
								</div>
							</c:if>
							
							<c:if test="${not empty festival.instagram}">
								<div class="btn_icon_wrapper">
									<a href="${festival.instagram}" target="_blank" class="btn_icon_link instagram" title="Instagramへ移動"><i class="fa-brands fa-instagram"></i></a>
									<span class="icon_label">Instagram</span>
								</div>
							</c:if>

							<div class="btn_icon_wrapper">
								<a href="https://www.google.com/maps/search/?api=1&query=${festival.location}" target="_blank" class="btn_icon_link map" title="Googleマップで表示"><i class="fas fa-map-marker-alt"></i></a>
								<span class="icon_label">地図表示</span>
							</div>

							<div class="btn_icon_wrapper">
								<a href="https://twitter.com/intent/tweet?text=${festival.name}&url=${pageContext.request.requestURL}" target="_blank" class="btn_icon_link x" title="X(Twitter)で共有"><i class="fa-brands fa-x-twitter"></i></a>
								<span class="icon_label">Xで共有</span>
							</div>

							<div class="btn_icon_wrapper">
								<a href="https://social-plugins.line.me/lineit/share?url=${pageContext.request.requestURL}" target="_blank" class="btn_icon_link line" title="LINEで共有"><i class="fa-brands fa-line"></i></a>
								<span class="icon_label">LINEで共有</span>
							</div>
						</div>
					</div>
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
			<div class="map_container">
				<h3>アクセス情報</h3>
				<div id="map" class="map_container_new"></div>
			</div>
		</div>
		
		<div class="festival_footer">
			<a href="${pageContext.request.contextPath}/main.do" class="btn_back" title="一覧へ戻る"><i class="fas fa-list"></i></a>
			<c:if test="${sessionScope.role == 'ADMIN'}">
				<a href="${pageContext.request.contextPath}/festival/update.do?fno=${festival.fno}" class="btn_update" title="編集"><i class="fas fa-edit"></i></a>
				<a href="${pageContext.request.contextPath}/festival/delete.do?fno=${festival.fno}" class="btn_delete" title="削除" onclick="return confirm('本当に削除しますか？')"><i class="fas fa-trash-alt"></i></a>
			</c:if>
		</div>
	</div>
</div>

<%@ include file="/footer.jsp" %>

<!-- Google Maps API & Scripts -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC9uAHexbNz8TFf7_amBOKeCIUMjEQm26g&callback=initMap" 
        async defer onerror="handleMapError()"></script>
<script>
    function handleMapError() {
        document.getElementById('map').innerHTML = 
            "<div class='map_error_container'>" +
            "<p>Google Mapsの読み込みに失敗しました。</p>" +
            "<p>ネットワーク接続を確認して、ページを再読み込みしてください。</p>" +
            "</div>";
    }

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
        if (!address) {
            document.getElementById('map').innerHTML = "<p class='map_msg_box'>住所情報がありません。</p>";
            return;
        }

        try {
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
                    console.error('Geocode was not successful for the following reason: ' + status);
                    document.getElementById('map').innerHTML = "<p class='map_msg_box'>地図情報を読み込めませんでした。住所を確認してください。</p>";
                }
            });
        } catch (e) {
            console.error("Map initialization failed:", e);
            handleMapError();
        }
    }

    // SNS共有機能
    function shareX() {
        var text = encodeURIComponent("【韓まつ】" + "${festival.name}" + " 祭りの情報です。 (Xで共有)");
        var url = encodeURIComponent(window.location.href);
        window.open("https://x.com/intent/tweet?text=" + text + "&url=" + url, "_blank");
    }

    function shareLine() {
        var text = encodeURIComponent("【韓まつ】" + "${festival.name}" + " \n" + window.location.href);
        window.open("https://social-plugins.line.me/lineit/share?url=" + encodeURIComponent(window.location.href) + "&text=" + text, "_blank");
    }
</script>
