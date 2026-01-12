<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container">
    <div class="festival_detail">
        <div class="detail_header">
            <h2>${festival.title}</h2>
            <p class="addr"><i class="icon_addr"></i> ${festival.addr1}</p>
            
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

        <div class="detail_content">
            <div class="detail_image">
                <c:choose>
                    <c:when test="${not empty festival.firstImage}">
                        <img src="${festival.firstImage}" alt="${festival.title}">
                    </c:when>
                    <c:otherwise>
                        <div class="no_image_placeholder" style="height: 400px; border-radius: 15px;">画像がありません</div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="detail_info_box">
                <h3>基本情報</h3>
                <ul>
                    <li><strong>所在地:</strong> ${festival.addr1}</li>
                    <c:if test="${not empty festival.tel}">
                        <li><strong>お問い合わせ:</strong> ${festival.tel}</li>
                    </c:if>
                </ul>
                
                <div class="overview">
                    <h3>概要</h3>
                    <p>${festival.overview}</p>
                </div>
            </div>
        </div>

        <!-- 地図および天気セクション -->
        <div class="festival_extra_info">
            <div class="map_section">
                <h3>位置案内</h3>
                <div id="map" style="width:100%; height:400px; background:#eee; border-radius:10px;"></div>
            </div>
            <div class="weather_section">
                <h3>今日の天気</h3>
                <div id="weather" class="weather_box">
                    <p>天気情報を読み込み中です...</p>
                </div>
            </div>
        </div>

        <div class="detail_actions">
            <a href="javascript:history.back();" class="btn_back">戻る</a>
            <a href="${pageContext.request.contextPath}/festival/apiList.do" class="btn_list">一覧へ</a>
        </div>
    </div>
</div>

<!-- Google Maps API -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC9uAHexbNz8TFf7_amBOKeCIUMjEQm26g&callback=initMap" async defer></script>
<script>
    function initMap() {
        var lat = parseFloat("${festival.mapY}");
        var lng = parseFloat("${festival.mapX}");
        
        if (isNaN(lat) || isNaN(lng)) {
            document.getElementById('map').innerHTML = "<p style='padding:20px; text-align:center;'>地図情報が表示できません。</p>";
            return;
        }

        var location = {lat: lat, lng: lng};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 15,
            center: location
        });
        var marker = new google.maps.Marker({
            position: location,
            map: map,
            title: "${festival.title}"
        });
    }

    // SNS共有機能
    function shareX() {
        var text = encodeURIComponent("【韓まつ】" + "${festival.title}" + " 祭りの情報です。");
        var url = encodeURIComponent(window.location.href);
        window.open("https://twitter.com/intent/tweet?text=" + text + "&url=" + url, "_blank");
    }

    function shareLine() {
        var text = encodeURIComponent("【韓まつ】" + "${festival.title}" + " \n" + window.location.href);
        window.open("https://social-plugins.line.me/lineit/share?url=" + encodeURIComponent(window.location.href) + "&text=" + text, "_blank");
    }
</script>
<script>
    // Google Maps APIがロードされなかった場合
    window.onload = function() {
        if (typeof google === 'undefined') {
            document.getElementById('map').innerHTML = 
                "<div style='padding:100px 0; text-align:center; color:#888;'>" +
                "<p>Google Maps APIを正しく設定すると、ここに地図が表示されます。</p>" +
                "<p>座標: 北緯 " + "${festival.mapY}" + " / 東経 " + "${festival.mapX}" + "</p>" +
                "</div>";
        } else {
            initMap();
        }
    };
</script>

<%@ include file="/footer.jsp" %>
