<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<!-- FullCalendar CSS -->
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css' rel='stylesheet' />
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/locales/ja.js'></script>

<div class="container admin_mt_40 admin_mb_80">
    <div class="admin_section_title admin_mb_40">
        <h2 class="admin_text_center"><i class="fas fa-calendar-alt admin_mr_10 admin_text_primary"></i>お祭りカレンダー</h2>
        <p class="admin_text_center admin_text_dark">韓国各地で開催されるお祭りの日程をひと目で確認できます♪</p>
    </div>

    <div class="calendar_card admin_card">
        <div id='calendar'></div>
    </div>
</div>

<style>
    .calendar_card {
        padding: 30px;
        background: #fff;
        border-radius: 20px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.05);
    }
    #calendar {
        max-width: 1100px;
        margin: 0 auto;
    }
    .fc-event {
        cursor: pointer;
        padding: 2px 5px;
        border-radius: 4px;
        border: none;
        font-size: 0.85em;
    }
    .fc-toolbar-title {
        font-size: 1.5em !important;
        font-weight: 700;
        color: var(--primary-color);
    }
    .fc-button-primary {
        background-color: var(--primary-color) !important;
        border-color: var(--primary-color) !important;
    }
    .fc-button-primary:hover {
        background-color: var(--primary-hover) !important;
        border-color: var(--primary-hover) !important;
    }
    .fc-daygrid-event-dot {
        border-color: var(--primary-color) !important;
    }
    /* Region based colors */
    .event-seoul { background-color: #ff5a5f !important; }
    .event-busan { background-color: #3498db !important; }
    .event-jeju { background-color: #2ecc71 !important; }
    .event-other { background-color: #f1c40f !important; }
</style>

<script>
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ja',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,dayGridWeek,listMonth'
        },
        events: [
            <c:forEach var="f" items="${festivalList}" varStatus="status">
                {
                    id: '${f.fno}',
                    title: '[${f.region}] ${f.name}',
                    start: '${f.startDate}',
                    end: '${f.endDate}',
                    allDay: true,
                    url: '${pageContext.request.contextPath}/festival/view.do?fno=${f.fno}',
                    className: 'event-' + (getRegionClass('${f.region}')),
                    extendedProps: {
                        location: '${f.location}'
                    }
                }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ],
        eventClick: function(info) {
            if (info.event.url) {
                window.location.href = info.event.url;
                info.jsEvent.preventDefault();
            }
        },
        eventMouseEnter: function(info) {
            // Tooltip or preview logic can be added here
        }
    });
    calendar.render();
});

function getRegionClass(region) {
    if (region.includes('ソウル')) return 'seoul';
    if (region.includes('釜山')) return 'busan';
    if (region.includes('済州')) return 'jeju';
    return 'other';
}
</script>

<%@ include file="/footer.jsp" %>
