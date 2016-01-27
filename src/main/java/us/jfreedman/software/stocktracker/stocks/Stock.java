package us.jfreedman.software.stocktracker.stocks;

import java.io.Serializable;
import java.net.URI;

/**
 * Created by Joshua on 12/12/2015.
 */
public class Stock implements Serializable {

    private String shortName, fullName;
    private long amountOfStocks;
    private float oldValuePerShare;
    private float newValuePerShare;
    private float oldValue;
    private float newValue;
    private URI website;
    private StockType stockType;

    protected Stock(String shortName, String fullName, int amountOfStocks, float oldValuePerShare, float newValuePerShare, float oldValue, float newValue, URI website, StockType stockType) {

        this.shortName = shortName;
        this.fullName = fullName;
        this.amountOfStocks = amountOfStocks;
        this.oldValuePerShare = oldValuePerShare;
        this.newValuePerShare = newValuePerShare;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.website = website;
        this.stockType = stockType;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getAmountOfStocks() {
        return amountOfStocks;
    }

    public void setAmountOfStocks(long amountOfStocks) {
        this.amountOfStocks = amountOfStocks;
    }

    public double getOldValuePerShare() {
        return oldValuePerShare;
    }

    public void setOldValuePerShare(float oldValuePerShare) {
        this.oldValuePerShare = oldValuePerShare;
    }

    public float getNewValuePerShare() {
        return newValuePerShare;
    }

    public void setNewValuePerShare(float newValuePerShare) {
        this.newValuePerShare = newValuePerShare;
    }

    public double getOldValue() {
        return oldValue;
    }

    public void setOldValue(float oldValue) {
        this.oldValue = oldValue;
    }

    public float getNewValue() {
        return newValue;
    }

    public void setNewValue(float newValue) {
        this.newValue = newValue;
    }

    public URI getWebsite() {
        return website;
    }

    public void setWebsite(URI website) {
        this.website = website;
    }

    public StockType getStockType() {
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", amountOfStocks=" + amountOfStocks +
                ", oldValuePerShare=" + oldValuePerShare +
                ", newValuePerShare=" + newValuePerShare +
                ", oldValue=" + oldValue +
                ", newValue=" + newValue +
                ", website=" + website +
                ", stockType=" + stockType +
                '}';
    }

    public void migrateNewValue(float nValue) {
        setOldValue(getNewValue());
        setNewValue(nValue);
    }

    public void migrateNewValuePerShare(float nValue) {
        setOldValuePerShare(getNewValuePerShare());
        setNewValuePerShare(nValue);
    }

    public void updateAmountOfStocks() {
        setAmountOfStocks(((long) getNewValue()) / ((long) getNewValuePerShare()));
    }
}
