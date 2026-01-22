package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SiteInfoDao;
import model.SiteInfoDto;

@WebServlet("/info/*")
public class InfoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String title = "情報";
        String content = "現在、このページの内容を準備中です。";
        String key = "";

        if ("/privacy.do".equals(pathInfo)) {
            title = "個人情報保護方針";
            content = "<div class='info_intro_box'>" +
                      "  <h3>🛡️ お客様の個人情報を大切に保護します</h3>" +
                      "  <p>韓まつ（以下「当サイト」）は、お客様の個人情報を保護し、関連法規を遵守するために、以下の通り個人情報保護方針を定めています。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>1. 収集する個人情報</h4>" +
                      "<p>当サイトでは、サービスの提供にあたり、以下の情報を収集することがあります。</p>" +
                      "<ul class='info_list_styled'>" +
                      "  <li><strong>会員登録時:</strong> ID、パスワード、ニックネーム、メールアドレス、電話番号、お名前</li>" +
                      "  <li><strong>サービス利用時:</strong> IPアドレス、クッキー、訪問記録、位置情報（同意時のみ）</li>" +
                      "</ul>" +

                      "<h4 class='info_section_title' style='margin-top: 30px;'>2. 利用目的</h4>" +
                      "<ul class='info_list_styled'>" +
                      "  <li>会員制サービスの提供および本人確認</li>" +
                      "  <li>お祭り情報の厳選推薦（管理者機能）</li>" +
                      "  <li>お問い合わせへの回答および重要なお知らせの通知</li>" +
                      "  <li>サービス改善のための統計分析</li>" +
                      "</ul>" +

                      "<h4 class='info_section_title' style='margin-top: 30px;'>3. 第三者への提供</h4>" +
                      "<p>当サイトは、法令に基づく場合やお客様の同意がある場合を除き、個人情報を第三者に提供することはありません。</p>" +

                      "<h4 class='info_section_title' style='margin-top: 30px;'>4. 安全管理措置</h4>" +
                      "<p>個人情報の漏洩、滅失、毀損を防ぐため、最新のセキュリティ技術を用いた暗号化通信（SSL）やアクセス制限などの措置を講じています。</p>";
        } else if ("/terms.do".equals(pathInfo)) {
            title = "利用規約";
            content = "<div class='info_intro_box'>" +
                      "  <h3>📜 サービス利用に関するルール</h3>" +
                      "  <p>この規約は、韓まつ（以下「当サイト」）が提供するすべてのサービスの利用条件を定めるものです。サービスをご利用いただくことで、本規約に同意したものとみなされます。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>1. 利用者の責任</h4>" +
                      "<ul class='info_list_styled'>" +
                      "  <li>利用者は、本規約および関連法令を遵守し、健全なコミュニティの維持に努めるものとします。</li>" +
                      "  <li>アカウントの管理責任は利用者にあり、第三者による不正利用について当サイトは責任を負いません。</li>" +
                      "</ul>" +

                      "<h4 class='info_section_title' style='margin-top: 30px;'>2. 禁止事項</h4>" +
                      "<ul class='info_list_styled'>" +
                      "  <li>他人の情報を盗用すること</li>" +
                      "  <li>公序良俗に反する内容を投稿すること</li>" +
                      "  <li>当サイトの運営を妨害する行為</li>" +
                      "  <li>営利目的の無断広告やスパム行為</li>" +
                      "</ul>" +

                      "<h4 class='info_section_title' style='margin-top: 30px;'>3. コンテンツの著作権</h4>" +
                      "<p>利用者が投稿したコンテンツ（レビュー、写真など）の著作権は利用者に帰属しますが、当サイトの広報目的で無償利用することを許諾するものとします。</p>" +

                      "<h4 class='info_section_title' style='margin-top: 30px;'>4. 免責事項</h4>" +
                      "<p>お祭りの日程や内容は主催者の都合により変更される場合があります。当サイトの情報に基づいた行動により生じた損害について、当サイトは一切の責任を負いかねます。</p>";
        } else if ("/faq.do".equals(pathInfo)) {
            title = "よくあるご質問 (FAQ)";
            content = "<div class='info_intro_box'>" +
                      "  <h3>❓ お困りのことはありませんか？</h3>" +
                      "  <p>利用者様から多く寄せられる質問をまとめました。解決しない場合は、お問い合わせフォームよりお気軽にご相談ください。</p>" +
                      "</div>" +
                      
                      "<div class='faq_list'>" +
                      "  <div class='faq_item' style='margin-bottom: 25px; padding-bottom: 20px; border-bottom: 1px solid #eee;'>" +
                      "    <h4 style='color: var(--primary-color); margin-bottom: 10px;'>Q. 会員登録をしないと利用できませんか？</h4>" +
                      "    <p>A. お祭りの検索や情報の閲覧は、登録なしでどなたでもご利用いただけます。掲示板への投稿や「いいね」機能、管理者による厳選推薦情報の詳細閲覧などは会員登録（無料）が必要です。</p>" +
                      "  </div>" +
                      "  <div class='faq_item' style='margin-bottom: 25px; padding-bottom: 20px; border-bottom: 1px solid #eee;'>" +
                      "    <h4 style='color: var(--primary-color); margin-bottom: 10px;'>Q. お祭りの予約は当サイトでできますか？</h4>" +
                      "    <p>A. 現在、当サイトでは情報の提供のみを行っており、直接のチケット予約機能はございません。各お祭りの詳細ページにある公式サイトへのリンクからお手続きをお願いします。</p>" +
                      "  </div>" +
                      "  <div class='faq_item' style='margin-bottom: 25px; padding-bottom: 20px; border-bottom: 1px solid #eee;'>" +
                      "    <h4 style='color: var(--primary-color); margin-bottom: 10px;'>Q. 掲載されている情報は正確ですか？</h4>" +
                      "    <p>A. 可能な限り最新かつ正確な情報を掲載するよう努めておりますが、主催者の事情により急遽変更される場合があります。出発前に、当サイトの「交通情報」や各公式サイトを再確認されることを強くおすすめします。</p>" +
                      "  </div>" +
                      "</div>";
        } else if ("/about.do".equals(pathInfo)) {
            title = "韓まつ（Hanmatsu）について";
            content = "<div class='info_intro_box'>" +
                      "  <h3>🌟 韓国の祭りとあなたを繋ぐ、韓まつ</h3>" +
                      "  <p>「韓まつ」は、韓国各地で開催される数千もの祭りを、国内外の旅行者に分かりやすく伝えるために誕生しました。伝統と現代が共存する韓国の躍動感を、お祭りを通じて体験してください。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>🚀 私たちのミッション</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>📍 正確な情報提供</h4>" +
                      "    <p>刻々と変わるお祭りの日程やプログラムを、正確かつ迅速にアップデートします。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🤝 地域の活性化</h4>" +
                      "    <p>あまり知られていない地方の小さなお祭りにもスポットを当て、地域経済の活性化に貢献します。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🌎 多言語対応</h4>" +
                      "    <p>世界中から訪れる観光客が言葉の壁を感じることなく、韓国の文化を楽しめる環境を作ります。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<h4 class='info_section_title' style='margin-top: 50px;'>💡 韓まつが提供する価値</h4>" +
                      "<ul class='info_list_styled'>" +
                      "  <li><strong>リアルタイム更新:</strong> 主催者との連携により、最新の情報をいち早くお届けします。</li>" +
                      "  <li><strong>管理者おすすめ:</strong> 現地の最新情報をもとに、管理者が厳選したお祭りをご紹介します。</li>" +
                      "  <li><strong>ユーザー参加型:</strong> レビューや写真を通じて、旅行者同士がリアルな体験を共有できます。</li>" +
                      "</ul>";
        } else if ("/guide.do".equals(pathInfo)) {
            title = "利用ガイド";
            content = "<div class='info_intro_box'>" +
                      "  <h3>📖 韓まつ（Hanmatsu）を100%活用する方法</h3>" +
                      "  <p>韓まつへようこそ！当サイトは、韓国各地で開催される魅力的なお祭りを簡単に見つけ、楽しむための情報プラットフォームです。より快適にご利用いただくためのヒントをご紹介します。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>🔍 1. 自分にぴったりの祭りを探す</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>✨ スマート検索</h4>" +
                      "    <p>トップページの検索バーにキーワードを入力して、興味のあるお祭りを瞬時に見つけられます。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>📍 地域・カテゴリ検索</h4>" +
                      "    <p>「お祭り紹介」メニューでは、ソウル、釜山、済州などの地域別や、伝統文化、現代芸術などのカテゴリ別に絞り込みが可能です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>📅 カレンダー機能</h4>" +
                      "    <p>お祭りカレンダーを使えば、旅行日程に合わせて開催中のお祭りを一目で確認できます。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<h4 class='info_section_title' style='margin-top: 50px;'>🌟 2. 韓まつのおすすめ活用術</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>✨ 今月の注目のお祭り</h4>" +
                      "    <p>トップページでは、管理者が自信を持って選んだ「今月一番のおすすめ」を詳しくご紹介しています。迷ったらまずはここをチェック！</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>💡 旬の旅行チップス</h4>" +
                      "    <p>季節や月ごとに合わせた韓国旅行のコツを提案。現地の雰囲気を感じられるアドバイスが満載です。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<h4 class='info_section_title' style='margin-top: 50px;'>✍️ 3. コミュニティで交流する</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>📸 祭りレビュー・写真</h4>" +
                      "    <p>実際に行ったお祭りの感想や素敵な写真を共有しましょう。あなたの投稿が他の誰かの旅のきっかけになります。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>💬 自由掲示板</h4>" +
                      "    <p>旅の仲間を募集したり、現地の最新情報を交換したりできるオープンなスペースです。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>⭐ お気に入り登録</h4>" +
                      "    <p>気になるお祭りは「いいね」でお気に入り登録。マイページからいつでもすぐに確認できます。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<div class='info_footer_tip' style='margin-top: 40px;'>" +
                      "  <p>💡 <strong>旅行のヒント:</strong> 韓国のお祭りは天候や現地の状況により日程が変更される場合があります。出発前に「ニュース」セクションで最新の情報をチェックすることをおすすめします！</p>" +
                      "</div>";
        } else if ("/traffic.do".equals(pathInfo)) {
            title = "交通情報・アクセスガイド";
            content = "<div class='info_intro_box'>" +
                      "  <h3>🚌 祭り会場までのスムーズな移動のために</h3>" +
                      "  <p>韓国の公共交通機関は非常に発達しており、全国どこへでも便利に移動できます。お祭り会場へ向かうための主な手段と、知っておくと便利なヒントをご紹介します。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>🚅 都市間移動（長距離）</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🚄 KTX / SRT</h4>" +
                      "    <p>韓国の高速鉄道。ソウルから釜山まで約2時間半で移動可能です。人気のお祭りの時期は早めの予約が必須です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🚌 高速・市外バス</h4>" +
                      "    <p>鉄道が通っていない地域のお祭りへ行くのに最適です。路線が非常に豊富で、リーズナブルに移動できます。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<h4 class='info_section_title' style='margin-top: 50px;'>🚇 都市内移動（近距離）</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🚇 地下鉄</h4>" +
                      "    <p>ソウル、釜山、大邱、光州、大田などの主要都市で利用可能。正確で清潔、かつ安価です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>💳 交通カード (T-money)</h4>" +
                      "    <p>コンビニや駅で購入可能。地下鉄やバスの乗り換え割引が適用されるため、旅行者の必須アイテムです。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🚕 タクシー</h4>" +
                      "    <p>日本に比べて料金が安く、グループでの移動に便利です。配車アプリ「Kakao T」の利用をおすすめします。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<div class='info_footer_tip' style='margin-top: 40px;'>" +
                      "  <p>💡 <strong>お祭り会場での注意:</strong> 大規模な祭りの日は、周辺道路が非常に混雑し、通行規制が行われることがあります。可能な限り公共交通機関を利用し、時間に余裕を持って出発しましょう！</p>" +
                      "</div>";
        } else if ("/partnership.do".equals(pathInfo)) {
            title = "パートナーシップのご案内";
            content = "<div class='info_intro_box'>" +
                      "  <h3>🤝 共に韓国の祭りを盛り上げませんか？</h3>" +
                      "  <p>韓まつでは、地方自治体、祭り主催団体、旅行関連企業など、韓国の観光文化を共に発展させていくパートナーを募集しています。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>💼 提携のメリット</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>📢 お祭りの広報最大化</h4>" +
                      "    <p>当サイトのユーザー層（国内外の旅行者）に向けて、効率的にお祭りをアピールできます。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>📊 データ分析</h4>" +
                      "    <p>ユーザーの関心度やトレンドデータを共有し、より魅力的な祭りプログラムの企画を支援します。</p>" +
                      "  </div>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title' style='margin-top: 50px;'>📩 お問い合わせ先</h4>" +
                      "<p>提携に関する具体的なご提案やご質問は、以下のメールアドレス、またはお問い合わせフォームよりご連絡ください。</p>" +
                      "<ul class='info_list_styled'>" +
                      "  <li><strong>メール:</strong> partners@hanmatsu.com</li>" +
                      "  <li><strong>電話:</strong> +82-2-1234-5678 (平日 09:00 - 18:00)</li>" +
                      "</ul>" +
                      "<div style='margin-top: 30px;'>" +
                      "  <a href='/hanmatsu/info/contact.do' class='btn_admin btn_admin_primary'>お問い合わせフォームへ</a>" +
                      "</div>";
        } else if ("/recruit.do".equals(pathInfo)) {
            title = "採用情報";
            content = "<div class='info_intro_box'>" +
                      "  <h3>🚀 私たちと一緒に、韓国の魅力を世界へ届けませんか？</h3>" +
                      "  <p>韓まつでは、情熱を持って韓国の文化と観光を盛り上げてくれる仲間を募集しています。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>🔍 現在募集中の職種</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>💻 Webエンジニア</h4>" +
                      "    <p>祭りの情報プラットフォームの開発・運用。新しい技術に挑戦したい方を歓迎します。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>✍️ コンテンツプランナー</h4>" +
                      "    <p>韓国各地の祭りを取材し、魅力的なコンテンツを企画・制作します。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🌎 海外マーケティング</h4>" +
                      "    <p>世界中の旅行者に韓まつを広めるための戦略立案と実行を担当します。</p>" +
                      "  </div>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title' style='margin-top: 50px;'>📩 応募方法</h4>" +
                      "<p>履歴書と職務経歴書（形式自由）を以下のメールアドレスまでお送りください。</p>" +
                      "<p><strong>Email:</strong> recruit@hanmatsu.com</p>";
        } else if ("/copyright.do".equals(pathInfo)) {
            title = "著作権ポリシー";
            content = "<div class='info_intro_box'>" +
                      "  <h3>⚖️ 著作権に関するご案内</h3>" +
                      "  <p>韓まつ（以下「当サイト」）に掲載されているすべてのコンテンツは、著作権法により保護されています。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>1. コンテンツの所有権</h4>" +
                      "<p>当サイトが作成した記事、デザイン、画像、動画などの著作権は、当サイトまたは原著作者に帰属します。</p>" +
                      
                      "<h4 class='info_section_title' style='margin-top: 30px;'>2. 無断転載の禁止</h4>" +
                      "<p>当サイトの許可なく、コンテンツを複製、転載、改変、配布することは固く禁じられています。</p>" +
                      
                      "<h4 class='info_section_title' style='margin-top: 30px;'>3. 引用について</h4>" +
                      "<p>非営利目的かつ適切な出典明記（リンク等）がある場合に限り、一部の引用を認めます。ただし、全文転載や商用利用は別途許諾が必要です。</p>";
        } else if ("/cookie.do".equals(pathInfo)) {
            title = "クッキー設定・ポリシー";
            content = "<div class='info_intro_box'>" +
                      "  <h3>🍪 クッキー（Cookie）の利用について</h3>" +
                      "  <p>当サイトでは、お客様に最適な体験を提供し、サービスを向上させるためにクッキーを利用しています。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>1. クッキーとは？</h4>" +
                      "<p>クッキーは、ウェブサイトを訪問した際にブラウザに保存される小さなテキストファイルです。これにより、お客様の好みを記憶したり、統計データを収集したりすることが可能になります。</p>" +
                      
                      "<h4 class='info_section_title' style='margin-top: 30px;'>2. 利用目的</h4>" +
                      "<ul class='info_list_styled'>" +
                      "  <li>ログイン状態の維持と本人確認</li>" +
                      "  <li>サイトの利用状況の分析と改善</li>" +
                      "  <li>お客様の関心に合わせたパーソナライズされた情報の提供</li>" +
                      "</ul>" +
                      
                      "<h4 class='info_section_title' style='margin-top: 30px;'>3. 設定の変更</h4>" +
                      "<p>お客様はブラウザの設定により、クッキーの受け入れを拒否したり、削除したりすることができます。ただし、その場合、一部のサービスが正常に利用できなくなることがあります。</p>";
        } else if (false) {
            // Logic for key matching from DB if needed in future
        } else if ("/location.do".equals(pathInfo)) {
            title = "位置情報サービス利用規約";
            content = "<div class='info_intro_box'>" +
                      "  <h3>📍 位置情報の利用について</h3>" +
                      "  <p>韓まつ（以下「当サイト」）は、お客様に最適な祭り情報を提供するために、位置情報を利用することがあります。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>1. 利用目的</h4>" +
                      "<p>お客様の現在地周辺で開催されているお祭りを検索し、道案内（ルート検索）サービスを提供するためにのみ利用します。</p>" +
                      
                      "<h4 class='info_section_title' style='margin-top: 30px;'>2. 収集と保存</h4>" +
                      "<p>位置情報は、お客様の同意がある場合のみ収集されます。サービス提供後、特定の個人を識別できない形で統計的に処理されるか、速やかに破棄されます。</p>" +
                      
                      "<h4 class='info_section_title' style='margin-top: 30px;'>3. 同意の撤回</h4>" +
                      "<p>お客様は、ブラウザやデバイスの設定により、いつでも位置情報の提供を停止することができます。</p>";
        } else if ("/customer.do".equals(pathInfo)) {
            title = "顧客サービス憲章";
            content = "<div class='info_intro_box'>" +
                      "  <h3>🤝 お客様第一のサービスをお約束します</h3>" +
                      "  <p>私たちは、すべての利用者の皆様が韓国の祭りを心から楽しめるよう、誠実かつ迅速な対応を心がけています。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>✨ 私たちの行動指針</h4>" +
                      "<ul class='info_list_styled'>" +
                      "  <li><strong>正確な情報:</strong> 常に最新かつ信頼性の高い情報を精査して提供します。</li>" +
                      "  <li><strong>迅速な対応:</strong> お問い合わせには原則24時間以内に回答するよう努めます。</li>" +
                      "  <li><strong>継続的な改善:</strong> お客様の声を真摯に受け止め、サイトの機能向上に活かします。</li>" +
                      "  <li><strong>文化の尊重:</strong> 韓国の伝統文化を正しく伝え、地域社会に貢献します。</li>" +
                      "</ul>";
        } else if ("/email.do".equals(pathInfo)) {
            title = "メールアドレスの無断収集拒否";
            content = "<div class='info_intro_box'>" +
                      "  <h3>🚫 メールアドレスの無断収集はお断りします</h3>" +
                      "  <p>当サイトに掲載されているメールアドレスを、許可なく収集・利用することを固く禁じています。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>1. 無断収集の禁止</h4>" +
                      "<p>自動収集プログラムやその他の技術的手段を用いて、当サイト上のメールアドレスを収集することを拒否します。</p>" +
                      
                      "<h4 class='info_section_title' style='margin-top: 30px;'>2. 法的措置</h4>" +
                      "<p>これに違反し、営利目的の広告性情報を送信した場合、情報通信網法に基づき処罰の対象となることがあります。</p>";
        } else if ("/sitemap.do".equals(pathInfo)) {
            title = "サイトマップ";
            content = "<div class='sitemap_container'>" +
                      "  <div class='sitemap_grid'>" +
                      "    <div>" +
                      "      <h4 class='info_section_title'>お祭り情報</h4>" +
                      "      <ul class='info_list_unstyled'> " +
                      "        <li><a href='/hanmatsu/festival/list.do?status=ongoing'>おすすめ祭り</a></li>" +
                      "        <li><a href='/hanmatsu/festival/list.do?status=this_month'>今月の祭り</a></li>" +
                      "        <li><a href='/hanmatsu/info/traffic.do'>交通情報</a></li>" +
                      "        <li><a href='/hanmatsu/festival/calendar.do'>お祭りカレンダー</a></li>" +
                      "      </ul>" +
                      "    </div>" +
                      "    <div>" +
                      "      <h4 class='info_section_title'>コミュニティ</h4>" +
                      "      <ul class='info_list_unstyled'> " +
                      "        <li><a href='/hanmatsu/board/list.do'>自由掲示板</a></li>" +
                      "        <li><a href='/hanmatsu/board/list.do?category=review'>祭りレビュー</a></li>" +
                      "        <li><a href='/hanmatsu/board/list.do?category=photo'>フォトギャラリー</a></li>" +
                      "      </ul>" +
                      "    </div>" +
                      "    <div>" +
                      "      <h4 class='info_section_title'>韓まつについて</h4>" +
                      "      <ul class='info_list_unstyled'> " +
                      "        <li><a href='/hanmatsu/info/about.do'>韓まつ紹介</a></li>" +
                      "        <li><a href='/hanmatsu/info/contact.do'>お問い合わせ</a></li>" +
                      "        <li><a href='/hanmatsu/info/partnership.do'>提携のご案内</a></li>" +
                      "      </ul>" +
                      "    </div>" +
                      "    <div>" +
                      "      <h4 class='info_section_title'>サポート</h4>" +
                      "      <ul class='info_list_unstyled'> " +
                      "        <li><a href='/hanmatsu/info/faq.do'>よくあるご質問</a></li>" +
                      "        <li><a href='/hanmatsu/info/guide.do'>利用ガイド</a></li>" +
                      "        <li><a href='/hanmatsu/info/privacy.do'>個人情報保護方針</a></li>" +
                      "      </ul>" +
                      "    </div>" +
                      "  </div>" +
                      "</div>";
        } else if ("/food_info.do".equals(pathInfo)) {
            title = "韓国料理情報";
            content = "<div class='info_intro_box'>" +
                      "  <h3>🍲 祭りと共に楽しむ韓国の美食</h3>" +
                      "  <p>韓国の祭りにおいて、屋台料理や地域独自の特産品は祭りを彩る欠かせない要素です。五感を刺激する韓国料理の世界へご案内します。</p>" +
                      "</div>" +
                      
                      "<h4 class='info_section_title'>✨ 代表的な韓国料理</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🥗 ビビンバ (Bibimbap)</h4>" +
                      "    <p>彩り豊かな野菜とご飯を混ぜて食べる健康食。全州(チョンジュ)のビビンバが特に有名です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🥩 プルコギ (Bulgogi)</h4>" +
                      "    <p>甘辛いタレに漬け込んだ牛肉を焼いた、韓国を代表する肉料理。老若男女に愛される味です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🌶️ キムチ (Kimchi)</h4>" +
                      "    <p>韓国の食卓に欠かせない伝統的な発酵食品。地域によって多様な種類があります。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<h4 class='info_section_title' style='margin-top: 50px;'>🎪 お祭りの屋台・定番グルメ</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🥘 トッポギ (Tteokbokki)</h4>" +
                      "    <p>お祭りの屋台で一番人気のピリ辛餅料理. 一度食べたら止まらない美味しさです。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🥞 ジョン/チヂミ (Jeon)</h4>" +
                      "    <p>お祭り気分を盛り上げる韓国風お好み焼き. 雨の日や祭りにはマッコリとの相性が最高です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🍯 ホットク (Hotteok)</h4>" +
                      "    <p>甘い蜜が入ったパンケーキのようなスイーツ. 冬の祭りに欠かせない定番おやつです。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🍙 キンパ (Gimbap)</h4>" +
                      "    <p>手軽に食べられる韓国風海苔巻き. 色とりどりの具材が詰まった、ピクニックの定番です。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<h4 class='info_section_title' style='margin-top: 50px;'>📍 地域限定の特産料理</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🥘 春川タッカルビ</h4>" +
                      "    <p>春川の祭りに訪れた際は、ぜひ本場の味を。鶏肉と野菜の旨味が凝縮されています。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🥣 釜山デジクッパ</h4>" +
                      "    <p>釜山のソウルフード. 濃厚なスープにたっぷりの豚肉が入った、心温まる一杯です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🦀 盈徳ズワイガニ</h4>" +
                      "    <p>盈徳の祭りで楽しめる最高級のカニ料理. 旬の時期の甘みは格別です。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<h4 class='info_section_title' style='margin-top: 50px;'>🍶 伝統飲料</h4>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🍶 マッコリ (Makgeolli)</h4>" +
                      "    <p>お米から作られた伝統的なにごり酒. 優しい甘みと爽やかな酸味が特徴です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🥤 シッケ (Sikhye)</h4>" +
                      "    <p>お米の甘みが広がる伝統的な冷たい飲み物. 食後のデザートとしても人気です。</p>" +
                      "  </div>" +
                      "</div>" +

                      "<div class='info_footer_tip' style='margin-top: 40px; padding: 20px; background: #f9f9f9; border-radius: 10px; border-left: 5px solid #ff5a5f;'>" +
                      "  <p>💡 <strong>Tip:</strong> 韓国の祭り会場では、その土地ならではの「旬の味」を大切にしています。ぜひ現地の屋台で、温かい雰囲気と共に美食を楽しんでください！</p>" +
                      "</div>";
        } else if ("/course.do".equals(pathInfo)) {
            title = "おすすめ旅行コース";
            content = "<h3>✨ あなたの好みに合わせた地方旅行コース</h3>" +
                      "<p>ソウルだけではもったいない！韓国の本当の魅力を探す地方の旅。あなたの旅行スタイルに合わせたおすすめのコースをご紹介します。</p>" +
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🏯 伝統と歴史の重みを感じたい方へ</h4>" +
                      "    <p>慶州や安東を巡る歴史探訪コース。伝統韓屋での宿泊体験もおすすめです。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🏔️ 大自然に癒やされたいヒーリング派へ</h4>" +
                      "    <p>江原道の壮大な山々と美しい海を巡るコース。自然の中でリフレッシュできます。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🍚 本場の味を追求するグルメ派へ</h4>" +
                      "    <p>全羅道の美食巡りコース。各地の祭りと共に、韓国最高の食文化を堪能できます。</p>" +
                      "  </div>" +
                      "</div>";
        } else if ("/weather.do".equals(pathInfo)) {
            title = "韓国の天気情報";
            content = "<div class='weather_info_box'>" +
                      "<h3>ソウルの現在の気象状況</h3>" +
                      "<img src='https://wttr.in/Seoul_3tqp_lang=ja.png' alt='Seoul Weather'>" +
                      "</div>";
        } else if ("/calendar.do".equals(pathInfo)) {
            title = "お祭りカレンダー";
            content = "<div class='info_intro_box'>" +
                      "  <h3>📅 年間の祭りスケジュールをチェック</h3>" +
                      "  <p>韓国では四季折々、一年中どこかで魅力的なお祭りが開催されています。月別の主なテーマをご紹介します。</p>" +
                      "</div>" +
                      
                      "<div class='info_content_grid'>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🌸 春 (3月-5月)</h4>" +
                      "    <p>桜、つつじ、菜の花などの花祭りが全国で開催されます。慶州や鎮海の桜祭りが有名です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🌊 夏 (6月-8月)</h4>" +
                      "    <p>泥遊び（保寧マッドフェスティバル）や海、音楽フェスティバルが盛り上がります。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>🍁 秋 (9月-11月)</h4>" +
                      "    <p>紅葉、伝統文化、収穫祭の季節。安東仮面舞祭りや晋州流灯祭りが人気です。</p>" +
                      "  </div>" +
                      "  <div class='info_content_card'>" +
                      "    <h4>❄️ 冬 (12月-2月)</h4>" +
                      "    <p>氷上釣り（華川ヤマメ祭り）や光のフェスティバル、日の出祭りが楽しめます。</p>" +
                      "  </div>" +
                      "</div>" +
                      
                      "<div class='info_footer_tip' style='margin-top: 40px;'>" +
                      "  <p>💡 <strong>お知らせ:</strong> インタラクティブな詳細カレンダー機能は現在準備中です。最新の開催情報は「お祭り紹介」メニューからご確認ください。</p>" +
                      "</div>";
        } else if ("/contact.do".equals(pathInfo)) {
            title = "お問い合わせ";
            content = "<div class='contact_container'>" +
                      "  <p class='mb_30'>韓まつ（Hanmatsu）へのご意見、ご質問、広告掲載、提携に関するお問い合わせは、以下のフォームより承っております。</p>" +
                      "  <form class='contact_form'>" +
                      "    <div class='contact_form_group'>" +
                      "      <label>お名前 <span class='required_star'>*</span></label>" +
                      "      <input type='text' placeholder='例：田中 太郎' required>" +
                      "    </div>" +
                      "    <div class='contact_form_group'>" +
                      "      <label>メールアドレス <span class='required_star'>*</span></label>" +
                      "      <input type='email' placeholder='example@email.com' required>" +
                      "    </div>" +
                      "    <div class='contact_form_group'>" +
                      "      <label>お問い合わせ内容 <span class='required_star'>*</span></label>" +
                      "      <textarea placeholder='お問い合わせ内容をご記入ください' required></textarea>" +
                      "    </div>" +
                      "    <button type='submit' class='contact_submit_btn'>送信する</button>" +
                      "  </form>" +
                      "</div>";
        }

        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.getRequestDispatcher("/WEB-INF/views/info/info.jsp").forward(request, response);
    }
}
