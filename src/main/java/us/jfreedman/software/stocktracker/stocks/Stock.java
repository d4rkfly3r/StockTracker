package us.jfreedman.software.stocktracker.stocks;

import java.io.Serializable;
import java.net.URI;
import java.time.Instant;
import java.util.Currency;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Created by Joshua on 12/12/2015.
 */
public class Stock implements Serializable {

    String stockSymbol;
    long stockAvgDailyVolume;
    double stockChange;
    Currency currency = Currency.getInstance(Locale.US);
    Date lastTradeDate = new Date("12/26/2015");
    double daysLow, daysHigh, yearsLow, yearsHigh, valuePerShare;
    long marketCap;
    String name;
    String lastTradeTime;

    LinkedList<Double> pastLows, pastHighs, pastValuesPerShare;
    LinkedList<Long> pastStockAvgDailyVolume, pastMarketCap;


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
        pastValuesPerShare.push(valuePerShare);
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

    public long getStockAvgDailyVolume() {
        return stockAvgDailyVolume;
    }

    public Stock setStockAvgDailyVolume(long stockAvgDailyVolume) {
        this.stockAvgDailyVolume = stockAvgDailyVolume;
        return this;
    }

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

    public double getDaysLow() {
        return daysLow;
    }

    public Stock setDaysLow(double daysLow) {
        this.daysLow = daysLow;
        return this;
    }

    public double getDaysHigh() {
        return daysHigh;
    }

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

    public double getValuePerShare() {
        return valuePerShare;
    }

    public Stock setValuePerShare(double valuePerShare) {
        this.valuePerShare = valuePerShare;
        return this;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public Stock setMarketCap(long marketCap) {
        this.marketCap = marketCap;
        return this;
    }

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
}
