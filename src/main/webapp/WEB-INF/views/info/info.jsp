<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<div class="container">
    <div class="info_page">
        <h2>${title}</h2>
        <div class="info_content">
            ${content}
        </div>
        <div style="text-align: center;">
            <a href="javascript:history.back();" class="btn_back">戻る</a>
        </div>
    </div>
</div>

<%@ include file="/footer.jsp" %>
