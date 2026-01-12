// メインJavaScript

$(document).ready(function() {
    // スムーススクロール
    $('a[href^="#"]').on('click', function(e) {
        e.preventDefault();
        var target = $(this.getAttribute('href'));
        if(target.length) {
            $('html, body').stop().animate({
                scrollTop: target.offset().top - 100
            }, 1000);
        }
    });
    
    // いいね機能
    $('.btn_like').on('click', function() {
        var bno = $(this).data('bno');
        toggleLike(bno);
    });
});

function toggleLike(bno) {
    $.ajax({
        url: contextPath + '/board/like.do',
        type: 'POST',
        data: { bno: bno },
        success: function(result) {
            if(result === 'login') {
                alert('ログインが必要です');
                location.href = contextPath + '/member/login.do';
            } else {
                location.reload();
            }
        },
        error: function() {
            alert('エラーが発生しました');
        }
    });
}

function editReply(rno, content) {
    var newContent = prompt('コメントを編集してください:', content);
    if(newContent && newContent !== content) {
        // 編集処理
        // ここでAJAXで更新処理を行う
    }
}

