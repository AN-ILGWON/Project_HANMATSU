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
        var $btn = $(this);
        
        if (!bno) {
            console.error('bno is missing');
            return;
        }
        
        $.ajax({
            url: contextPath + '/board/like.do',
            type: 'POST',
            data: { bno: bno },
            success: function(result) {
                var res = result.trim();
                if(res === 'login') {
                    alert('ログインが必要です');
                    location.href = contextPath + '/member/login.do';
                } else if(res === 'liked') {
                    $btn.addClass('liked');
                    var $count = $btn.find('#like_count');
                    var count = parseInt($count.text());
                    $count.text(count + 1);
                } else if(res === 'unliked') {
                    $btn.removeClass('liked');
                    var $count = $btn.find('#like_count');
                    var count = parseInt($count.text());
                    $count.text(count - 1);
                }
            },
            error: function(xhr, status, error) {
                console.error('Like AJAX error:', status, error);
                alert('エラーが発生しました');
            }
        });
    });
});

