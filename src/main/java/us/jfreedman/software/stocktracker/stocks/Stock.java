package us.jfreedman.software.stocktracker.stocks;

import java.io.Serializable;
import java.util.Currency;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

@SuppressWarnings("UnusedReturnValue")
public class Stock implements Serializable {

    private String stockSymbol;
    private long stockAvgDailyVolume;
    private double stockChange;
    private Currency currency = Currency.getInstance(Locale.US);
    @SuppressWarnings("deprecation")
    private Date lastTradeDate = new Date("12/26/2015");
    private double daysLow;
    private double daysHigh;
    private double yearsLow;
    private double yearsHigh;
    private double valuePerShare;
    private long marketCap;
    private String name;
    private String lastTradeTime;

    private final LinkedList<Double> pastLows;
    private final LinkedList<Double> pastHighs;
    private final LinkedList<Double> pastValuesPerShare;
    private final LinkedList<Long> pastStockAvgDailyVolume;
    private final LinkedList<Long> pastMarketCap;


    public Stock() {
        pastLows = new LinkedList<>();
        pastHighs = new LinkedList<>();
        pastValuesPerShare = new LinkedList<>();
        pastStockAvgDailyVolume = new LinkedList<>();
        pastMarketCap = new LinkedList<>();

        stockSymbol = name = lastTradeTime = "";
        stockChange = daysLow = daysHigh = yearsHigh = yearsLow = valuePerShare = marketCap = stockAvgDailyVolume = 0;
    }

    public Stock migrateAvgDailyVolume(long newValue) {
        pastStockAvgDailyVolume.push(newValue);
        setStockAvgDailyVolume(newValue);
        return this;
    }

    public Stock migrateMarketCap(long newValue) {
        pastMarketCap.push(newValue);
        setMarketCap(newValue);
        return this;
    }

    public Stock migrateDayHigh(double newValue) {
        pastHighs.push(newValue);
        setDaysHigh(newValue);
        return this;
    }

    public Stock migrateDayLow(double newValue) {
        pastLows.push(newValue);
        setDaysLow(newValue);
        return this;
    }

    public Stock migrateValuePerShare(double newValue) {
        pastValuesPerShare.push(newValue);
        setValuePerShare(newValue);
        return this;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public Stock setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
        return this;
    }

    @SuppressWarnings("WeakerAccess")
    public long getStockAvgDailyVolume() {
        return stockAvgDailyVolume;
    }

    @SuppressWarnings("WeakerAccess")
    public Stock setStockAvgDailyVolume(long stockAvgDailyVolume) {
        this.stockAvgDailyVolume = stockAvgDailyVolume;
        return this;
    }

    @SuppressWarnings("WeakerAccess")
    public double getStockChange() {
        return stockChange;
    }

    public Stock setStockChange(double stockChange) {
        this.stockChange = stockChange;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Stock setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public Date getLastTradeDate() {
        return lastTradeDate;
    }

    public Stock setLastTradeDate(Date lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
        return this;
    }

    @SuppressWarnings("WeakerAccess")
    public double getDaysLow() {
        return daysLow;
    }

    @SuppressWarnings("WeakerAccess")
    public Stock setDaysLow(double daysLow) {
        this.daysLow = daysLow;
        return this;
    }

    @SuppressWarnings("WeakerAccess")
    public double getDaysHigh() {
        return daysHigh;
    }

    @SuppressWarnings("WeakerAccess")
    public Stock setDaysHigh(double daysHigh) {
        this.daysHigh = daysHigh;
        return this;
    }

    public double getYearsLow() {
        return yearsLow;
    }

    public Stock setYearsLow(double yearsLow) {
        this.yearsLow = yearsLow;
        return this;
    }

    public double getYearsHigh() {
        return yearsHigh;
    }

    public Stock setYearsHigh(double yearsHigh) {
        this.yearsHigh = yearsHigh;
        return this;
    }

    @SuppressWarnings("WeakerAccess")
    public double getValuePerShare() {
        return valuePerShare;
    }

    @SuppressWarnings("WeakerAccess")
    public Stock setValuePerShare(double valuePerShare) {
        this.valuePerShare = valuePerShare;
        return this;
    }

    @SuppressWarnings("WeakerAccess")
    public long getMarketCap() {
        return marketCap;
    }

    @SuppressWarnings("WeakerAccess")
    public Stock setMarketCap(long marketCap) {
        this.marketCap = marketCap;
        return this;
    }

    @SuppressWarnings("WeakerAccess")
    public String getName() {
        return name;
    }

    public Stock setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastTradeTime() {
        return lastTradeTime;
    }

    public Stock setLastTradeTime(String lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
        return this;
    }


    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + getStockSymbol() + '\'' +
                ", fullName='" + getName() + '\'' +
                ", oldValuePerShare=" + pastValuesPerShare.peekFirst() +
                ", newValuePerShare=" + getValuePerShare() +
                ", oldMarketCap=" + pastMarketCap.peekFirst() +
                ", newMarketCap=" + getMarketCap() +
                ", oldLow=" + pastLows.peekFirst() +
                ", newLow=" + getDaysLow() +
                ", oldHigh=" + pastHighs.peekFirst() +
                ", newHigh=" + getDaysHigh() +
                ", oldAvgDailyVol=" + pastStockAvgDailyVolume.peekFirst() +
                ", newAvgDailyVol=" + getStockAvgDailyVolume() +
                ", newChange=" + getStockChange() +
                '}';
    }
}
