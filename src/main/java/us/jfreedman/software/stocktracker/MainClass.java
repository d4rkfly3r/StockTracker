package us.jfreedman.software.stocktracker;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import us.jfreedman.software.stocktracker.stocks.Stock;
import us.jfreedman.software.stocktracker.stocks.StockBuilder;
import us.jfreedman.software.stocktracker.stocks.StockType;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Joshua on 12/12/2015.
 */
public class MainClass {

    HashMap<String, Stock> listOfStocks;

    boolean dev = false;
    boolean genBaseStocks = true;
    private HashMap<String, Stock> savedStocks;

    MainClass() {

//        listOfStocks = new HashMap<>();

//        if ((listOfStocks = getSavedStocks()) == null) listOfStocks = new HashMap<>();
//
        listOfStocks = getSavedStocks();
        if (genBaseStocks) {
            Stock googleStock = new StockBuilder().setShortName("GOOGL").setFullName("Alphabet Inc.").setStockType(StockType.DIVIDEND).createStock();
            Stock yahooStock = new StockBuilder().setShortName("YHOO").setFullName("Yahoo!").setStockType(StockType.DIVIDEND).createStock();
            Stock appleStock = new StockBuilder().setShortName("AAPL").setFullName("Apple").setStockType(StockType.DIVIDEND).createStock();
            Stock microsoftStock = new StockBuilder().setShortName("MSFT").setFullName("Microsoft").setStockType(StockType.DIVIDEND).createStock();
            Stock intelStock = new StockBuilder().setShortName("INTC").setFullName("Intel Corporation").setStockType(StockType.DIVIDEND).createStock();
            Stock alcoaStock = new StockBuilder().setShortName("GLW").setFullName("Corning Inc.").setStockType(StockType.DIVIDEND).createStock();

            listOfStocks.put(googleStock.getShortName(), googleStock);
            listOfStocks.put(yahooStock.getShortName(), yahooStock);
            listOfStocks.put(appleStock.getShortName(), appleStock);
            listOfStocks.put(microsoftStock.getShortName(), microsoftStock);
            listOfStocks.put(intelStock.getShortName(), intelStock);
            listOfStocks.put(alcoaStock.getShortName(), alcoaStock);
        }

        if (!dev) {
            BufferedReader bufferedReader = null;
            try {
                String baseURL = "https://query.yahooapis.com/v1/public/yql";
                String queryStr = "select * from yahoo.finance.quotes where symbol in (";


                for (Stock stock : listOfStocks.values()) {
                    queryStr += "\"" + stock.getShortName() + "\",";
                }

                queryStr = queryStr.substring(0, queryStr.length() - 1);
                queryStr += ")";
                System.err.println(queryStr);
                URI tempURI = new URI(baseURL + "?q=" + URLEncoder.encode(queryStr, "UTF-8") + "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
                tempURI = new URI(tempURI.toASCIIString());
                System.out.println(tempURI.toASCIIString());
                bufferedReader = new BufferedReader(new InputStreamReader(tempURI.toURL().openStream()));


                String inputLine;
                String finPro = "";
                while ((inputLine = bufferedReader.readLine()) != null) {
                    System.out.println(inputLine);
                    finPro += inputLine;
                }


                parseToElemFromQuery(finPro).forEach(jsonElement -> {
                    Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
                    HashMap<String, JsonElement> data = new HashMap<>();
                    entries.stream().forEach(stringJsonElementEntry -> data.put(stringJsonElementEntry.getKey(), stringJsonElementEntry.getValue()));

//                    data.forEach((s, s2) -> System.out.println(s + " :: " + s2));

                    listOfStocks.get(data.get("Symbol").getAsString()).migrateNewValue(convertStrNumToNum(data.get("MarketCapitalization").getAsString()));
                    listOfStocks.get(data.get("Symbol").getAsString()).migrateNewValuePerShare(data.get("LastTradePriceOnly").getAsFloat());
                    listOfStocks.get(data.get("Symbol").getAsString()).updateAmountOfStocks();

                    System.out.println(listOfStocks.get(data.get("Symbol").getAsString()));

                    saveStocks();
                });
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            String query = "{\"symbol\":\"GOOGL\",\"Ask\":\"757.97\",\"AverageDailyVolume\":\"2134600\",\"Bid\":\"756.35\",\"AskRealtime\":null,\"BidRealtime\":null,\"BookValue\":\"169.03\",\"Change_PercentChange\":\"-12.98 - -1.69%\",\"Change\":\"-12.98\",\"Commission\":null,\"Currency\":\"USD\",\"ChangeRealtime\":null,\"AfterHoursChangeRealtime\":null,\"DividendShare\":null,\"LastTradeDate\":\"12/18/2015\",\"TradeDate\":null,\"EarningsShare\":\"23.72\",\"ErrorIndicationreturnedforsymbolchangedinvalid\":null,\"EPSEstimateCurrentYear\":\"28.99\",\"EPSEstimateNextYear\":\"34.14\",\"EPSEstimateNextQuarter\":\"7.83\",\"DaysLow\":\"756.59\",\"DaysHigh\":\"774.14\",\"YearLow\":\"490.91\",\"YearHigh\":\"793.05\",\"HoldingsGainPercent\":null,\"AnnualizedGain\":null,\"HoldingsGain\":null,\"HoldingsGainPercentRealtime\":null,\"HoldingsGainRealtime\":null,\"MoreInfo\":null,\"OrderBookRealtime\":null,\"MarketCapitalization\":\"520.50B\",\"MarketCapRealtime\":null,\"EBITDA\":\"23.30B\",\"ChangeFromYearLow\":\"265.94\",\"PercentChangeFromYearLow\":\"+54.17%\",\"LastTradeRealtimeWithTime\":null,\"ChangePercentRealtime\":null,\"ChangeFromYearHigh\":\"-36.20\",\"PercebtChangeFromYearHigh\":\"-4.56%\",\"LastTradeWithTime\":\"4:00pm - <b>756.85</b>\",\"LastTradePriceOnly\":\"756.85\",\"HighLimit\":null,\"LowLimit\":null,\"DaysRange\":\"756.59 - 774.14\",\"DaysRangeRealtime\":null,\"FiftydayMovingAverage\":\"762.47\",\"TwoHundreddayMovingAverage\":\"666.24\",\"ChangeFromTwoHundreddayMovingAverage\":\"90.61\",\"PercentChangeFromTwoHundreddayMovingAverage\":\"+13.60%\",\"ChangeFromFiftydayMovingAverage\":\"-5.62\",\"PercentChangeFromFiftydayMovingAverage\":\"-0.74%\",\"Name\":\"Alphabet Inc.\",\"Notes\":null,\"Open\":\"766.70\",\"PreviousClose\":\"769.83\",\"PricePaid\":null,\"ChangeinPercent\":\"-1.69%\",\"PriceSales\":\"7.38\",\"PriceBook\":\"4.55\",\"ExDividendDate\":null,\"PERatio\":\"31.90\",\"DividendPayDate\":null,\"PERatioRealtime\":null,\"PEGRatio\":\"1.56\",\"PriceEPSEstimateCurrentYear\":\"26.11\",\"PriceEPSEstimateNextYear\":\"22.17\",\"Symbol\":\"GOOGL\",\"SharesOwned\":null,\"ShortRatio\":\"2.44\",\"LastTradeTime\":\"4:00pm\",\"TickerTrend\":null,\"OneyrTargetPrice\":\"849.91\",\"Volume\":\"3389702\",\"HoldingsValue\":null,\"HoldingsValueRealtime\":null,\"YearRange\":\"490.91 - 793.05\",\"DaysValueChange\":null,\"DaysValueChangeRealtime\":null,\"StockExchange\":\"NMS\",\"DividendYield\":null,\"PercentChange\":\"-1.69%\"}";
            Set<Map.Entry<String, JsonElement>> entries = new JsonParser().parse(query).getAsJsonObject().entrySet();
            HashMap<String, JsonElement> data = new HashMap<>();
            entries.stream().forEach(stringJsonElementEntry -> data.put(stringJsonElementEntry.getKey(), stringJsonElementEntry.getValue()));

            data.forEach((s, s2) -> System.out.println(s + " :: " + s2));

            listOfStocks.get(data.get("Symbol").getAsString()).setNewValue(data.get("LastTradePriceOnly").getAsFloat());

            System.out.println(listOfStocks.get(data.get("Symbol").getAsString()));

            saveStocks();
        }
    }

    static String[] prefixes = {"k", "M", "B"};


    private float convertStrNumToNum(String stringNum) {
//        int multFactor = 1;
//        Character endChar = stringNum.charAt(stringNum.length() - 1);
//        switch (endChar) {
//            case 'M':
//                multFactor = 1000000; // 1,000,000
//                return Double.valueOf(stringNum.substring(0, stringNum.length() - 1)) * (double) multFactor;
//            case 'B':
//                multFactor = 1000000000; // 1,000,000,000
//                return Double.valueOf(stringNum.substring(0, stringNum.length() - 1)) * (double) multFactor;
//        }
//        return Double.valueOf(stringNum.substring(0, stringNum.length()));

        float result = Float.parseFloat(stringNum.substring(0, stringNum.length() - 1));
        // Retrieve the prefix index used
        int prefixIndex = Arrays.asList(prefixes).indexOf(stringNum.substring(stringNum.length() - 1)) + 1;
        // Multiply the input to the appropriate prefix used
        if (prefixIndex > 0)
            result = (float) (result * Math.pow(10, prefixIndex * 3));
        return result;

    }

    private void saveStocks() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stocks.dat"));
//            objectOutputStream.writeObject(listOfStocks);
            listOfStocks.values().forEach(obj -> {
                try {
                    objectOutputStream.writeObject(obj);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonArray parseToElemFromQuery(String jsonString) {
        System.err.println("Line...\n\n\n");
        return new JsonParser().parse(jsonString).getAsJsonObject().get("query").getAsJsonObject().get("results").getAsJsonObject().get("quote").getAsJsonArray();
    }

    public static void main(String[] args) {
        new MainClass();
    }

    public HashMap<String, Stock> getSavedStocks() {
        HashMap<String, Stock> tempStockMap = new HashMap<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("stocks.dat"));
            Object object;
            try {
                while ((object = objectInputStream.readObject()) != null) {
                    Stock stock = (Stock) object;
                    tempStockMap.put(stock.getShortName(), stock);
                }
            } catch (EOFException finishup) {
                objectInputStream.close();
            }
        } catch (IOException | ClassNotFoundException ignored) {

        }
        return tempStockMap;
    }
}
//{"symbol":"AAPL","Ask":"106.39","AverageDailyVolume":"46374100","Bid":"106.34","AskRealtime":null,"BidRealtime":null,"BookValue":"21.40","Change_PercentChange":"-2.95 - -2.71%","Change":"-2.95","Commission":null,"Currency":"USD","ChangeRealtime":null,"AfterHoursChangeRealtime":null,"DividendShare":"2.08","LastTradeDate":"12/18/2015","TradeDate":null,"EarningsShare":"9.22","ErrorIndicationreturnedforsymbolchangedinvalid":null,"EPSEstimateCurrentYear":"9.78","EPSEstimateNextYear":"10.73","EPSEstimateNextQuarter":"2.41","DaysLow":"105.81","DaysHigh":"109.52","YearLow":"92.00","YearHigh":"134.54","HoldingsGainPercent":null,"AnnualizedGain":null,"HoldingsGain":null,"HoldingsGainPercentRealtime":null,"HoldingsGainRealtime":null,"MoreInfo":null,"OrderBookRealtime":null,"MarketCapitalization":"591.15B","MarketCapRealtime":null,"EBITDA":"82.49B","ChangeFromYearLow":"14.03","PercentChangeFromYearLow":"+15.25%","LastTradeRealtimeWithTime":null,"ChangePercentRealtime":null,"ChangeFromYearHigh":"-28.51","PercebtChangeFromYearHigh":"-21.19%","LastTradeWithTime":"4:00pm - <b>106.03</b>","LastTradePriceOnly":"106.03","HighLimit":null,"LowLimit":null,"DaysRange":"105.81 - 109.52","DaysRangeRealtime":null,"FiftydayMovingAverage":"116.61","TwoHundreddayMovingAverage":"118.05","ChangeFromTwoHundreddayMovingAverage":"-12.02","PercentChangeFromTwoHundreddayMovingAverage":"-10.18%","ChangeFromFiftydayMovingAverage":"-10.58","PercentChangeFromFiftydayMovingAverage":"-9.07%","Name":"Apple Inc.","Notes":null,"Open":"108.69","PreviousClose":"108.98","PricePaid":null,"ChangeinPercent":"-2.71%","PriceSales":"2.60","PriceBook":"5.09","ExDividendDate":"11/5/2015","PERatio":"11.50","DividendPayDate":"11/12/2015","PERatioRealtime":null,"PEGRatio":"0.78","PriceEPSEstimateCurrentYear":"10.84","PriceEPSEstimateNextYear":"9.88","Symbol":"AAPL","SharesOwned":null,"ShortRatio":"2.84","LastTradeTime":"4:00pm","TickerTrend":null,"OneyrTargetPrice":"149.05","Volume":"96453327","HoldingsValue":null,"HoldingsValueRealtime":null,"YearRange":"92.00 - 134.54","DaysValueChange":null,"DaysValueChangeRealtime":null,"StockExchange":"NMS","DividendYield":"1.91","PercentChange":"-2.71%"}
