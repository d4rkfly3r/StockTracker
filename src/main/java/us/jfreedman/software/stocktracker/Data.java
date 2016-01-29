package us.jfreedman.software.stocktracker;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import us.jfreedman.software.stocktracker.stocks.Stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;

class Data {

    private static final String baseURL = "https://query.yahooapis.com/v1/public/yql";
    private static final String defaultQueryStr = "select * from yahoo.finance.quotes where symbol in ";

    public static Stock updateData(Stock stock) {

        try {
            String queryStr = defaultQueryStr + "(\"" + stock.getStockSymbol() + "\")";

            URI tempURI = new URI(baseURL + "?q=" + URLEncoder.encode(queryStr, "UTF-8") + "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
            tempURI = new URI(tempURI.toASCIIString());

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(tempURI.toURL().openStream()))) {

                String inputLine;
                String finPro = "";
                while ((inputLine = bufferedReader.readLine()) != null) {
                    System.out.println(inputLine);
                    finPro += inputLine;
                }

//                parseToElemFromQuery(finPro).entrySet().forEach(System.out::println);
                JsonObject stockData = parseToElemFromQuery(finPro);
                stock.migrateMarketCap(Utils.convertStrNumToNum(stockData.get("MarketCapitalization").getAsString()));
                stock.migrateAvgDailyVolume(stockData.get("AverageDailyVolume").getAsLong());
                stock.migrateDayHigh(stockData.get("DaysHigh").getAsDouble());
                stock.migrateDayLow(stockData.get("DaysLow").getAsDouble());
                stock.migrateValuePerShare(stockData.get("LastTradePriceOnly").getAsDouble());
                stock.setName(stockData.get("Name").getAsString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stock;
    }


    private static JsonObject parseToElemFromQuery(String jsonString) {
//        System.err.println("Line...\n\n\n");
        return new JsonParser().parse(jsonString).getAsJsonObject().get("query").getAsJsonObject().get("results").getAsJsonObject().get("quote").getAsJsonObject();
    }
}
