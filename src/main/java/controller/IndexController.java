package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FestivalDao;
import model.FestivalDto;
import model.BoardDao;
import model.BoardDto;
import model.NewsDao;
import model.NewsDto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet("/main.do")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 今月の祭りリスト (DB)
			FestivalDao fDao = new FestivalDao();
			List<FestivalDto> festivalList = fDao.getThisMonthFestivals();
			request.setAttribute("festivalList", festivalList);
			
			// 今月の注目のお祭り (管理者が選定)
			FestivalDto recommendedFestival = fDao.getRecommendedFestival();
			
			request.setAttribute("recommendedFestival", recommendedFestival);
			
			// いいねが多い投稿
			BoardDao bDao = new BoardDao();
			List<BoardDto> topBoards = bDao.getTopLikedBoards(4);
			request.setAttribute("topBoards", topBoards);
			
			// 旅行ニュースの取得
			NewsDao nDao = new NewsDao();
			request.setAttribute("newsList", nDao.getNewsList());
			
			// バナー（スライド）の取得
			model.BannerDao bannerDao = new model.BannerDao();
			request.setAttribute("bannerList", bannerDao.getActiveBanners());
			
			// 旅行のコツ (月別動的コンテンツ)
			int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
			List<String[]> travelTips = new ArrayList<>();
			
			switch (currentMonth) {
				case 1:
					travelTips.add(new String[]{"🧣", "1月の韓国は氷点下！暖かくしてお過ごしください。"});
					travelTips.add(new String[]{"🍢", "屋台のおでんは冬の必須コース！"});
					travelTips.add(new String[]{"🚌", "地方旅行は市外バスが便利です！"});
					break;
				case 2:
					travelTips.add(new String[]{"🧤", "2月もまだ寒いです。マフラーと手袋を忘れずに！"});
					travelTips.add(new String[]{"🍓", "冬のイチゴ狩り体験が旬の時期です。"});
					travelTips.add(new String[]{"🛍️", "室内モールや地下商店街での観光がおすすめです。"});
					break;
				case 3:
					travelTips.add(new String[]{"🌸", "春の訪れ！慶州や鎮海の桜まつりの準備をしましょう。"});
					travelTips.add(new String[]{"🧥", "朝晩の寒暖差が激しいので、薄手のコートが必要です。"});
					travelTips.add(new String[]{"🥗", "春の山菜を使ったビビンバが美味しい季節です。"});
					break;
				case 4:
					travelTips.add(new String[]{"🌷", "各地でチューリップや菜の花まつりが開催されます。"});
					travelTips.add(new String[]{"🚲", "漢江でのサイクリングやピクニックに最適な天気です。"});
					travelTips.add(new String[]{"📸", "人生ショット（最高の写真）を撮るのにぴったりの季節です。"});
					break;
				case 5:
					travelTips.add(new String[]{"🎏", "家族の月！子供から大人まで楽しめるイベントが盛りだくさんです。"});
					travelTips.add(new String[]{"🌳", "どこへ行っても新緑が美しく、散策に最適です。"});
					travelTips.add(new String[]{"🍦", "冷たいスイーツやカフェ巡りが楽しくなる季節です。"});
					break;
				case 6:
					travelTips.add(new String[]{"☔", "梅雨が始まる時期です。折りたたみ傘を持ち歩きましょう。"});
					travelTips.add(new String[]{"🌌", "漢江の夜景や夜市(ナイトマーケット)が賑わい始めます。"});
					travelTips.add(new String[]{"☕", "おしゃれなカフェで雨音を楽しみながら一休みするのも素敵です。"});
					break;
				case 7:
					travelTips.add(new String[]{"🌊", "保寧マッドフェスティバルなど、夏の海まつりが本格化します。"});
					travelTips.add(new String[]{"🧴", "紫外線が強いので、日焼け止めとサングラスは必須です。"});
					travelTips.add(new String[]{"🍜", "夏の定番、冷たい冷麺(ネンミョン)で暑さを吹き飛ばしましょう。"});
					break;
				case 8:
					travelTips.add(new String[]{"🏖️", "夏休みのピーク！人気のビーチは多くの人で賑わいます。"});
					travelTips.add(new String[]{"🥤", "こまめな水分補給を忘れずに。室内観光も取り入れましょう。"});
					travelTips.add(new String[]{"🍧", "パッピンス(韓国かき氷)を食べて涼むのが最高の贅沢です。"});
					break;
				case 9:
					travelTips.add(new String[]{"🍁", "秋の気配。安東仮面舞まつりなど文化的な祭りが増えます。"});
					travelTips.add(new String[]{"🌾", "秋夕(チュソク)の時期は休業する店もあるので事前にチェック！"});
					travelTips.add(new String[]{"👟", "歩きやすい靴で、秋の散策を楽しみましょう。"});
					break;
				case 10:
					travelTips.add(new String[]{"🍂", "紅葉のベストシーズン！雪岳山や内蔵山が赤く染まります。"});
					travelTips.add(new String[]{"🌬️", "涼しくて爽やかな、一年で最も旅行に適した気候です。"});
					travelTips.add(new String[]{"🥘", "温かいチゲや鍋料理がより一層美味しく感じられます。"});
					break;
				case 11:
					travelTips.add(new String[]{"🧣", "急に冷え込む日が増えます。厚手のジャケットを用意しましょう。"});
					travelTips.add(new String[]{"🕯️", "ソウルランタンフェスティバルなど、光のイベントが始まります。"});
					travelTips.add(new String[]{"🌰", "焼き栗や焼き芋など、冬の味覚を楽しみましょう。"});
					break;
				case 12:
					travelTips.add(new String[]{"🎄", "街中がクリスマスイルミネーションで華やかに彩られます。"});
					travelTips.add(new String[]{"🎿", "スキー場がオープン！ウィンタースポーツを楽しめます。"});
					travelTips.add(new String[]{"♨️", "寒い日はチムジルバンで温まるのが最高です。"});
					break;
			}
			request.setAttribute("travelTips", travelTips);
			request.setAttribute("currentMonth", currentMonth);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main/index_view.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

