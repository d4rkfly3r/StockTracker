package us.jfreedman.software.stocktracker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by Joshua on 2/19/2016.
 * Project: StockTracker
 */
public class Crawler {

    private String url;
    private String retData = "";

    public Crawler(String url) {
        this.url = url;
    }

    public void crawl() throws Exception {
        Document doc = Jsoup
                .connect(url)
                .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36")
                .timeout(5000).get();

        retData = doc.getElementsByTag("p").html();
    }

    public String getRetData() {
        return retData;
    }

    public String getUrl() {
        return url;
    }
}
