package us.jfreedman.software.stocktracker;


import java.io.IOException;
import java.util.Set;

class MainClass {
    int totalNeutral = 0;
    int totalPro = 0;
    int totalCon = 0;

    private MainClass() throws IOException {
//        System.out.println(Data.updateData(new Stock().setStockSymbol("INTC")));
//        System.out.println(Data.updateData(new Stock().setStockSymbol("GOOG")));

        GoogleCrawler googleCrawler = new GoogleCrawler();
        Set<String> result = googleCrawler.getDataFromGoogle("SYMC");


        result.forEach(s -> {
            Crawler crawler = new Crawler(s);
            try {
                crawler.crawl();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            int pro = 0;
            int con = 0;
            int neutral = 0;
            neutral += getCount(crawler.getRetData(), "forecast");
            neutral += getCount(crawler.getRetData(), "stock");
            pro += getCount(crawler.getRetData(), "jumps");
            pro += getCount(crawler.getRetData(), "surges");
            pro += getCount(crawler.getRetData(), "buy");
            pro += getCount(crawler.getRetData(), "purchase");
            pro += getCount(crawler.getRetData(), "rising");
            pro += getCount(crawler.getRetData(), "rose");
            pro += getCount(crawler.getRetData(), "rise");
            pro += getCount(crawler.getRetData(), "up");
            con += getCount(crawler.getRetData(), "blow");
            con += getCount(crawler.getRetData(), "plumet");
            con += getCount(crawler.getRetData(), "fall");
            con += getCount(crawler.getRetData(), "down");
            con += getCount(crawler.getRetData(), "sell");
            con += getCount(crawler.getRetData(), "trade");
            con += getCount(crawler.getRetData(), "bad");
            con += getCount(crawler.getRetData(), "horrible");
            con += getCount(crawler.getRetData(), "fell");
            System.out.println(crawler.getUrl() + "\t|\t" + neutral + " | " + pro + " | " + con);
            totalNeutral += neutral;
            totalPro += pro;
            totalCon += con;
        });

        System.out.println(totalNeutral + " | " + totalPro + " | " + totalCon);
        System.out.println(result.size());
    }


    public int getCount(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        return count;

    }

    public static void main(String[] args) throws IOException {
        new MainClass();
    }
}
