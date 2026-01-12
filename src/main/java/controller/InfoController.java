package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String title = "情報";
        String content = "現在、このページの内容を準備中です。";

        if ("/privacy.do".equals(pathInfo)) {
            title = "個人情報処理方針";
            content = "<h3>1. 個人情報の収集</h3><p>韓まつは、お客様の個人情報の保護を最も重要な責務と考え、以下の通り個人情報保護方針を定めます。会員登録やお問い合わせの際に、必要最小限の個人情報を収集します。</p>" +
                      "<h3>2. 利用目的</h3><p>収集した情報は、サービスの提供、本人確認、お問い合わせへの対応にのみ利用します。お客様の大切な情報は、法令を遵守し、厳重に管理いたします。</p>" +
                      "<h3>3. 第三者提供</h3><p>法令に基づく場合を除き、お客様の同意なく個人情報を第三者に提供することはありません。</p>";
        } else if ("/terms.do".equals(pathInfo)) {
            title = "利用規約";
            content = "<h3>第1条（会員登録）</h3><p>この規約は、韓まつが提供するサービスの利用条件を定めるものです。利用者は、本規約に同意した上で、所定の手続きに従って会員登録を行うものとします。</p>" +
                      "<h3>2. 禁止事項</h3><p>他者の権利を侵害する行為、公序良俗に反する行為、サイトの運営を妨害する行為を禁止します。良識あるご利用をお願いいたします。</p>" +
                      "<h3>第3条（免責事項）</h3><p>当サイトは、提供する情報の正確性について最善を尽くしますが、その内容を保証するものではありません。</p>";
        } else if ("/location.do".equals(pathInfo)) {
            title = "位置ベースサービス利用規約";
            content = "<p>当サイトでは、現在地に基づいた祭り情報の提供のために位置情報を利用する場合があります。</p>" +
                      "<p>位置情報の利用に同意された場合のみ、お近くの祭り情報を表示します。この情報はサービス提供後、直ちに破棄されます。</p>";
        } else if ("/copyright.do".equals(pathInfo)) {
            title = "著作権政策";
            content = "<p>当サイトに掲載されているすべてのコンテンツ（テキスト、画像、ロゴなど）の著作権は、韓まつまたは情報提供者に帰属します。</p>" +
                      "<p>無断での転載、複製、配布を固く禁じます。</p>";
        } else if ("/customer.do".equals(pathInfo)) {
            title = "顧客サービス憲章";
            content = "<p>私たちは、お客様に最高の韓国旅行体験を提供するため、親切かつ迅速なサービスを約束します。</p>" +
                      "<p>1. 常に正確な情報を提供します。<br>2. お客様の声に耳を傾け、サービスの改善に努めます。</p>";
        } else if ("/email.do".equals(pathInfo)) {
            title = "電子メール無断収集拒否";
            content = "<p>当サイトに掲載されている電子メールアドレスが、自動収集プログラム等を用いて無断で収集されることを拒否します。</p>" +
                      "<p>これに違反した場合、情報通信網法等に基づき処罰されることがあります。</p>";
        } else if ("/faq.do".equals(pathInfo)) {
            title = "よくある質問 (FAQ)";
            content = "<div class='faq_container'>" +
                      "  <div class='faq_item' style='margin-bottom:15px; border:1px solid #eee; border-radius:10px; overflow:hidden;'>" +
                      "    <div style='padding:18px 25px; background:#fcfcfc; font-weight:600; cursor:pointer; display:flex; justify-content:space-between; align-items:center;' onclick='$(this).next().slideToggle()'>" +
                      "      <span>Q. 韓まつとはどのようなサイトですか？</span>" +
                      "      <i class='fas fa-chevron-down' style='font-size:12px; color:#999;'></i>" +
                      "    </div>" +
                      "    <div style='padding:20px 25px; border-top:1px solid #eee; line-height:1.7; display:none;'>" +
                      "      A. 韓国各地で開催される魅力的な地域祭りを日本の皆様にごご紹介する情報サイトです。公共データを利用した正確なスケジュールや、AIによるおすすめ情報の提供、コミュニティ機能などを備えています。" +
                      "    </div>" +
                      "  </div>" +
                      "  <div class='faq_item' style='margin-bottom:15px; border:1px solid #eee; border-radius:10px; overflow:hidden;'>" +
                      "    <div style='padding:18px 25px; background:#fcfcfc; font-weight:600; cursor:pointer; display:flex; justify-content:space-between; align-items:center;' onclick='$(this).next().slideToggle()'>" +
                      "      <span>Q. 会員登録をするとどのようなメリットがありますか？</span>" +
                      "      <i class='fas fa-chevron-down' style='font-size:12px; color:#999;'></i>" +
                      "    </div>" +
                      "    <div style='padding:20px 25px; border-top:1px solid #eee; line-height:1.7; display:none;'>" +
                      "      A. 気に入ったお祭りを「マイページ」に保存（お気に入り登録）したり、コミュニティに写真や感想を投稿して他のユーザーと交流したりすることができます。登録は無料です。" +
                      "    </div>" +
                      "  </div>" +
                      "  <div class='faq_item' style='margin-bottom:15px; border:1px solid #eee; border-radius:10px; overflow:hidden;'>" +
                      "    <div style='padding:18px 25px; background:#fcfcfc; font-weight:600; cursor:pointer; display:flex; justify-content:space-between; align-items:center;' onclick='$(this).next().slideToggle()'>" +
                      "      <span>Q. 掲載されている祭り情報は正確ですか？</span>" +
                      "      <i class='fas fa-chevron-down' style='font-size:12px; color:#999;'></i>" +
                      "    </div>" +
                      "    <div style='padding:20px 25px; border-top:1px solid #eee; line-height:1.7; display:none;'>" +
                      "      A. 韓国の公共データポータル(data.go.kr)等の公的機関の情報を基に作成しておりますが、現地の事情により急遽変更・中止になる場合がございます。お出かけ前に必ず各祭りの公式サイト等をご確認ください。" +
                      "    </div>" +
                      "  </div>" +
                      "  <div class='faq_item' style='margin-bottom:15px; border:1px solid #eee; border-radius:10px; overflow:hidden;'>" +
                      "    <div style='padding:18px 25px; background:#fcfcfc; font-weight:600; cursor:pointer; display:flex; justify-content:space-between; align-items:center;' onclick='$(this).next().slideToggle()'>" +
                      "      <span>Q. ログインできません。どうすればよいですか？</span>" +
                      "      <i class='fas fa-chevron-down' style='font-size:12px; color:#999;'></i>" +
                      "    </div>" +
                      "    <div style='padding:20px 25px; border-top:1px solid #eee; line-height:1.7; display:none;'>" +
                      "      A. IDまたはパスワードが正しいかご確認ください。解決しない場合は、お問い合わせフォームより詳細（発生日時、エラー内容等）を添えてご連絡ください。" +
                      "    </div>" +
                      "  </div>" +
                      "</div>";
        } else if ("/about.do".equals(pathInfo)) {
            title = "韓まつについて";
            content = "<div class='about_section'>" +
                      "  <h3 style='color:var(--primary-color);'>韓まつ(Hanmatsu)の目的</h3>" +
                      "  <p>「韓まつ」は、韓国の魅力あふれる地域祭りを日本の皆様にお届けするために設立されました。<br>" +
                      "  伝統的な祭事から現代的なフェスティバルまで、韓国の文化を体験できる機会をサポートいたします。</p>" +
                      "  <h3 style='color:var(--primary-color);'>主な機能</h3>" +
                      "  <ul style='list-style:none; padding-left:0;'>" +
                      "    <li>✅ <strong>リアルタイム祭り情報:</strong> 公共データを利用した最新のスケジュールを確認できます。</li>" +
                      "    <li>✅ <strong>パーソナライズ推薦:</strong> ユーザーの好みに合わせたお祭りをAIが提案いたします。</li>" +
                      "    <li>✅ <strong>コミュニティ:</strong> 旅行の思い出を共有したり、他のユーザーと情報交換が可能です。</li>" +
                      "  </ul>" +
                      "  <h3 style='color:var(--primary-color);'>私たちの想い</h3>" +
                      "  <p>お祭りを通じて、日韓の文化交流がより深まることを願っております。<br>" +
                      "  韓まつを活用して、皆様だけの特別な韓国旅行をお楽しみください。</p>" +
                      "</div>";
        } else if ("/guide.do".equals(pathInfo)) {
            title = "利用ガイド";
            content = "<div class='guide_section'>" +
                      "  <h3 style='color:var(--primary-color);'>韓まつの楽しみ方</h3>" +
                      "  <div style='margin-bottom:30px;'>" +
                      "    <h4 style='border-bottom:2px solid #eee; padding-bottom:5px;'>1. お祭りを探す</h4>" +
                      "    <p>検索バーや「おすすめ祭り」メニューから、気になるお祭りを見つけることができます。<br>" +
                      "    地域別、日付別に簡単に検索が可能です。</p>" +
                      "  </div>" +
                      "  <div style='margin-bottom:30px;'>" +
                      "    <h4 style='border-bottom:2px solid #eee; padding-bottom:5px;'>2. AI推薦の活用</h4>" +
                      "    <p>メインページの「AI推薦セクション」では、現在のトレンドに基づいたお祭りをAIが分析して紹介しています。</p>" +
                      "  </div>" +
                      "  <div style='margin-bottom:30px;'>" +
                      "    <h4 style='border-bottom:2px solid #eee; padding-bottom:5px;'>3. コミュニティへの参加</h4>" +
                      "    <p>会員登録後、ご自身が参加されたお祭りの写真や感想を投稿することができます。<br>" +
                      "    他のユーザーの投稿に「いいね」をしたり、コメントでの交流も可能です。</p>" +
                      "  </div>" +
                      "  <div style='margin-bottom:30px;'>" +
                      "    <h4 style='border-bottom:2px solid #eee; padding-bottom:5px;'>4. 旅行の計画</h4>" +
                      "    <p>各祭りの詳細ページには、地図や公式サイトへのリンクが掲載されています。<br>" +
                      "    これらの情報を活用して、最適な旅行プランをご検討ください。</p>" +
                      "  </div>" +
                      "</div>";
        } else if ("/sitemap.do".equals(pathInfo)) {
            title = "サイトマップ";
            content = "<div class='sitemap_container'>" +
                      "  <div style='display:grid; grid-template-columns:1fr 1fr; gap:40px;'>" +
                      "    <div>" +
                      "      <h4 style='border-left:4px solid var(--primary-color); padding-left:10px; margin-bottom:15px;'>お祭り情報</h4>" +
                      "      <ul style='line-height:2;'> " +
                      "        <li><a href='/hanmatsu/festival/list.do'>おすすめ祭り</a></li>" +
                      "        <li><a href='/hanmatsu/main.do#festival'>今月の祭り</a></li>" +
                      "        <li><a href='/hanmatsu/festival/apiList.do'>全国お祭りMAP</a></li>" +
                      "        <li><a href='/hanmatsu/info/calendar.do'>お祭りカレンダー</a></li>" +
                      "      </ul>" +
                      "    </div>" +
                      "    <div>" +
                      "      <h4 style='border-left:4px solid var(--primary-color); padding-left:10px; margin-bottom:15px;'>コミュニティ</h4>" +
                      "      <ul style='line-height:2;'> " +
                      "        <li><a href='/hanmatsu/board/list.do'>自由掲示板</a></li>" +
                      "        <li><a href='/hanmatsu/board/list.do?category=review'>祭りレビュー</a></li>" +
                      "        <li><a href='/hanmatsu/board/list.do?category=photo'>フォトギャラリー</a></li>" +
                      "      </ul>" +
                      "    </div>" +
                      "    <div>" +
                      "      <h4 style='border-left:4px solid var(--primary-color); padding-left:10px; margin-bottom:15px;'>韓まつについて</h4>" +
                      "      <ul style='line-height:2;'> " +
                      "        <li><a href='/hanmatsu/info/about.do'>韓まつ紹介</a></li>" +
                      "        <li><a href='/hanmatsu/info/contact.do'>お問い合わせ</a></li>" +
                      "        <li><a href='/hanmatsu/info/partnership.do'>提携のご案内</a></li>" +
                      "      </ul>" +
                      "    </div>" +
                      "    <div>" +
                      "      <h4 style='border-left:4px solid var(--primary-color); padding-left:10px; margin-bottom:15px;'>サポート</h4>" +
                      "      <ul style='line-height:2;'> " +
                      "        <li><a href='/hanmatsu/info/faq.do'>よくあるご質問</a></li>" +
                      "        <li><a href='/hanmatsu/info/guide.do'>利用ガイド</a></li>" +
                      "        <li><a href='/hanmatsu/info/privacy.do'>個人情報保護方針</a></li>" +
                      "      </ul>" +
                      "    </div>" +
                      "  </div>" +
                      "</div>";
        } else if ("/gourmet.do".equals(pathInfo)) {
            title = "韓国グルメ情報";
            content = "<h3>祭りと共に楽しむ絶品グルメ</h3>" +
                      "<p>韓国の祭りには、屋台料理やその土地ならではの特産品が欠かせません。</p>" +
                      "<div style='display:grid; grid-template-columns:1fr 1fr; gap:20px; margin-top:30px;'>" +
                      "  <div style='background:#fff5f5; padding:20px; border-radius:10px;'>" +
                      "    <h4 style='color:var(--primary-color);'>🍲 タッカルビ (春川)</h4><p>春川の祭りに訪れた際は、ぜひお召し上がりください。</p>" +
                      "  </div>" +
                      "  <div style='background:#fff5f5; padding:20px; border-radius:10px;'>" +
                      "    <h4 style='color:var(--primary-color);'>🥞 ジョン (各地)</h4><p>お祭り気分を盛り上げる韓国風お好み焼き。伝統酒とも好相性です。</p>" +
                      "  </div>" +
                      "</div>" +
                      "<p style='margin-top:30px;'>その他、様々なグルメ情報を順次追加予定です。</p>";
        } else if ("/course.do".equals(pathInfo)) {
            title = "おすすめ旅行コース";
            content = "<h3>あなたにぴったりの韓国旅行プラン</h3>" +
                      "<h3>1. 伝統満喫コース (2泊3日)</h3><p>ソウルで王宮を巡り、地方の伝統祭りを楽しむ充実のコースです。</p>" +
                      "<h3>2. トレンドスポット巡りコース (1泊2日)</h3><p>話題のカフェと、華やかなお祭りを組み合わせた人気のコースです。</p>" +
                      "<p>季節やテーマに合わせたコースを今後も追加してまいります。</p>";
        } else if ("/weather.do".equals(pathInfo)) {
            title = "韓国の天気情報";
            content = "<div id='weather_wrap' style='text-align:center; padding:30px; background:linear-gradient(135deg, #f0f8ff, #e6f3ff); border-radius:20px;'>" +
                      "<h3 style='margin-bottom:20px;'>ソウルの現在の気象状況</h3>" +
                      "<img src='https://wttr.in/Seoul_3tqp_lang=ja.png' alt='Seoul Weather' style='max-width:100%; border-radius:15px; box-shadow:0 5px 15px rgba(0,0,0,0.1);'>" +
                      "</div>";
        } else if ("/calendar.do".equals(pathInfo)) {
            title = "お祭りカレンダー";
            content = "<p>月別・日付別の詳細な祭りスケジュールは、現在システムを構築中です。まもなく公開予定ですので、今しばらくお待ちください。</p>";
        } else if ("/contact.do".equals(pathInfo)) {
            title = "お問い合わせ";
            content = "<div class='contact_container'>" +
                      "  <p style='margin-bottom:30px;'>韓まつ(Hanmatsu)へのご意見、ご質問、広告掲載、提携に関するお問い合わせは、以下のフォームよりお気軽にご連絡ください。</p>" +
                      "  <form class='contact_form' style='background:#f9f9f9; padding:40px; border-radius:15px;'>" +
                      "    <div style='margin-bottom:20px;'>" +
                      "      <label style='display:block; margin-bottom:8px; font-weight:600;'>お名前 <span style='color:red;'>*</span></label>" +
                      "      <input type='text' style='width:100%; padding:12px; border:1px solid #ddd; border-radius:5px;' placeholder='例：田中 太郎' required>" +
                      "    </div>" +
                      "    <div style='margin-bottom:20px;'>" +
                      "      <label style='display:block; margin-bottom:8px; font-weight:600;'>メールアドレス <span style='color:red;'>*</span></label>" +
                      "      <input type='email' style='width:100%; padding:12px; border:1px solid #ddd; border-radius:5px;' placeholder='example@mail.com' required>" +
                      "    </div>" +
                      "    <div style='margin-bottom:20px;'>" +
                      "      <label style='display:block; margin-bottom:8px; font-weight:600;'>お問い合わせ項目 <span style='color:red;'>*</span></label>" +
                      "      <select style='width:100%; padding:12px; border:1px solid #ddd; border-radius:5px;'>" +
                      "        <option>サービスに関するお問い合わせ</option>" +
                      "        <option>広告掲載・提携について</option>" +
                      "        <option>不具合報告</option>" +
                      "        <option>その他</option>" +
                      "      </select>" +
                      "    </div>" +
                      "    <div style='margin-bottom:30px;'>" +
                      "      <label style='display:block; margin-bottom:8px; font-weight:600;'>お問い合わせ内容 <span style='color:red;'>*</span></label>" +
                      "      <textarea style='width:100%; height:150px; padding:12px; border:1px solid #ddd; border-radius:5px;' placeholder='お問い合わせ内容をご入力ください' required></textarea>" +
                      "    </div>" +
                      "    <button type='button' onclick='alert(\"お問い合わせを受け付けました。\")' style='width:100%; padding:15px; background:var(--primary-color); color:#fff; border:none; border-radius:5px; font-size:16px; font-weight:600; cursor:pointer;'>送信する</button>" +
                      "  </form>" +
                      "  <div style='margin-top:40px; padding:20px; border:1px solid #eee; border-radius:10px;'>" +
                      "    <p>📧 <strong>直通メール:</strong> support@hanmatsu.jp</p>" +
                      "    <p>📞 <strong>お電話:</strong> 03-1234-5678 (平日 10:00 - 18:00)</p>" +
                      "  </div>" +
                      "</div>";
        } else if ("/partnership.do".equals(pathInfo)) {
            title = "提携のご案内";
            content = "<h3>ビジネスパートナーの皆様へ</h3>" +
                      "<p>韓まつでは、韓国の観光コンテンツを共に広めていくパートナー企業様を募集しております。自治体、旅行代理店、メディア関係者様からのご連絡をお待ちしております。</p>" +
                      "<p>提携に関するお問い合わせ: biz@hanmatsu.jp</p>";
        } else if ("/recruit.do".equals(pathInfo)) {
            title = "採用情報";
            content = "<h3>韓まつと共に成長する仲間を募集しています</h3>" +
                      "<p>現在、積極的な採用活動は行っておりませんが、私たちのビジョンに共感いただける方のオープンポジションへの応募は随時受け付けております。</p>";
        } else if ("/cookie.do".equals(pathInfo)) {
            title = "クッキーポリシー";
            content = "<p>当サイトでは、利便性の向上やアクセス解析のためにクッキー(Cookie)を使用しています。ブラウザの設定により、クッキーの受け取りを拒否することも可能です。</p>";
        }

        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.getRequestDispatcher("/WEB-INF/views/info/info_view.jsp").forward(request, response);
    }
}
