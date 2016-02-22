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

        getResult(googleCrawler.getDataFromGoogle("GOOG"));
        getResult(googleCrawler.getDataFromGoogle("TSLA"));
    }

    private void getResult(Set<String> result) {
        result.forEach(s -> {
            Crawler crawler = new Crawler(s);
            try {
                crawler.crawl();
            } catch (Exception e) {
//                System.err.println(e.getMessage());
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
            pro += getCount(crawler.getRetData(), "ascend");
            pro += getCount(crawler.getRetData(), "go up");
            pro += getCount(crawler.getRetData(), "clamber");
            pro += getCount(crawler.getRetData(), "mount");
            pro += getCount(crawler.getRetData(), "top");
            pro += getCount(crawler.getRetData(), "soar");
            pro += getCount(crawler.getRetData(), "escalate");
            pro += getCount(crawler.getRetData(), "positive");

            con += getCount(crawler.getRetData(), "blow");
            con += getCount(crawler.getRetData(), "plumet");
            con += getCount(crawler.getRetData(), "fall");
            con += getCount(crawler.getRetData(), "negative");
            con += getCount(crawler.getRetData(), "down");
            con += getCount(crawler.getRetData(), "sell");
            con += getCount(crawler.getRetData(), "trade");
            con += getCount(crawler.getRetData(), "bad");
            con += getCount(crawler.getRetData(), "horrible");
            con += getCount(crawler.getRetData(), "go down");
            con += getCount(crawler.getRetData(), "decline");
            con += getCount(crawler.getRetData(), "decrease");
            con += getCount(crawler.getRetData(), "drop");
            con += getCount(crawler.getRetData(), "lower");
            con += getCount(crawler.getRetData(), "slump");
            con += getCount(crawler.getRetData(), "fell"); // 418 | 231 |
            System.out.print(neutral + " | " + pro + " | " + con + " | ");
            totalNeutral += neutral;
            totalPro += pro;
            totalCon += con;
        });

        System.out.println();

        System.out.println("Stat: [ " + totalNeutral + " | " + totalPro + " | " + totalCon + " ]");
        System.out.println("Dif: " + Math.abs(totalPro - totalCon));
        System.out.println("Result Size: " + result.size());
        System.out.println();
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
