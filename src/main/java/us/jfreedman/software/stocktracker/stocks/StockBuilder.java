package us.jfreedman.software.stocktracker.stocks;

import java.net.URI;

/**
 * Created by Joshua on 12/12/2015.
 */
public class StockBuilder {

    private String shortName;
    private String fullName;
    private int amountOfStocks;
    private float oldValuePerShare;
    private float newValuePerShare;
    private float oldValue;
    private float newValue;
    private URI website;
    private StockType stockType;

    public StockBuilder setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public StockBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public StockBuilder setAmountOfStocks(int amountOfStocks) {
        this.amountOfStocks = amountOfStocks;
        return this;
    }

    public StockBuilder setOldValuePerShare(float oldValuePerShare) {
        this.oldValuePerShare = oldValuePerShare;
        return this;
    }

    public StockBuilder setNewValuePerShare(float newValuePerShare) {
        this.newValuePerShare = newValuePerShare;
        return this;
    }

    public StockBuilder setOldValue(float oldValue) {
        this.oldValue = oldValue;
        return this;
    }

    public StockBuilder setNewValue(float newValue) {
        this.newValue = newValue;
        return this;
    }

    public StockBuilder setWebsite(URI website) {
        this.website = website;
        return this;
    }

    public StockBuilder setStockType(StockType stockType) {
        this.stockType = stockType;
        return this;
    }

    public Stock createStock() {
        return new Stock(shortName, fullName, amountOfStocks, oldValuePerShare, newValuePerShare, oldValue, newValue, website, stockType);
    }
}