package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TourApiService {
    private final String BASE_URL = "https://apis.data.go.kr/B551011/JpnService1"; // 日文(日本語) サービス API 使用
    
    // API サービスキー (公共データポータルで発行された一般認証キー(Decoding)の使用を推奨)
    // 'Unexpected errors' 発生時は、サービスキーの有効性や該当サービス(JpnService1)の活用申請有無を確認する必要があります。
    private final String SERVICE_KEY = "a69271873b7c27da6fa1984de34e8804df4df7920bdac93678de9fd50da39e7d";

    public String getFestivalList(String startDate, String areaCode, int pageNo, int numOfRows) {
        BufferedReader br = null;
        HttpURLConnection conn = null;
        try {
            StringBuilder urlBuilder = new StringBuilder(BASE_URL + "/searchFestival");
            // サービスキーは既にエンコードされている可能性があるため、そのまま使用するか、デコードキーの場合はエンコードが必要
            urlBuilder.append("?serviceKey=" + SERVICE_KEY); 
            urlBuilder.append("&numOfRows=" + numOfRows);
            urlBuilder.append("&pageNo=" + pageNo);
            urlBuilder.append("&MobileOS=ETC");
            urlBuilder.append("&MobileApp=Hanmatsu");
            urlBuilder.append("&_type=json");
            urlBuilder.append("&listYN=Y");
            urlBuilder.append("&arrange=A");
            urlBuilder.append("&eventStartDate=" + startDate);
            
            if (areaCode != null && !areaCode.isEmpty()) {
                urlBuilder.append("&areaCode=" + areaCode);
            }

            URL url = new URL(urlBuilder.toString());
            System.out.println("API Request URL: " + url.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("Content-type", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode >= 200 && responseCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
            String result = sb.toString();
            System.out.println("API Response (" + responseCode + "): " + result);
            
            // "Unexpected errors" または XML エラーメッセージの場合は null を返し、サービス側で例外処理を誘導
            if (result.contains("Unexpected errors") || result.contains("<") || result.isEmpty()) {
                return null;
            }
            
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) br.close();
                if (conn != null) conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getDetailCommon(String contentId) {
        BufferedReader br = null;
        HttpURLConnection conn = null;
        try {
            StringBuilder urlBuilder = new StringBuilder(BASE_URL + "/detailCommon");
            urlBuilder.append("?serviceKey=" + SERVICE_KEY);
            urlBuilder.append("&MobileOS=ETC");
            urlBuilder.append("&MobileApp=Hanmatsu");
            urlBuilder.append("&_type=json");
            urlBuilder.append("&contentId=" + contentId);
            urlBuilder.append("&defaultYN=Y");
            urlBuilder.append("&firstImageYN=Y");
            urlBuilder.append("&addrYN=Y");
            urlBuilder.append("&mapYN=Y");
            urlBuilder.append("&overviewYN=Y");

            URL url = new URL(urlBuilder.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int responseCode = conn.getResponseCode();
            if (responseCode >= 200 && responseCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
            String result = sb.toString();
            if (result.contains("Unexpected errors") || result.contains("<") || result.isEmpty()) {
                return null;
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) br.close();
                if (conn != null) conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
