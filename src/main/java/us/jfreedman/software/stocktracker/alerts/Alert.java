package us.jfreedman.software.stocktracker.alerts;

import us.jfreedman.software.stocktracker.stocks.Stock;

public class Alert {

    private final Stock stock;
    private double lowerLimit, upperLimit;
    private long frequency;
    private AlertType alertType;

    private long lastCheck;


    protected Alert(Stock stock) {
        this.stock = stock;
        this.lowerLimit = 0;
        this.upperLimit = 0;
        this.frequency = 0L;
        this.alertType = AlertType.HIDDEN;
    }

    Alert(Stock stock, double lowerLimit, double upperLimit, long frequency, AlertType alertType) {
        this.stock = stock;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.frequency = frequency;
        this.alertType = alertType;
    }

    public void start() {
        lastCheck = System.currentTimeMillis();
        AlertThread.getInstance().registerAlert(this);
    }

    public void check() {
        if (System.currentTimeMillis() - lastCheck >= frequency) {
            //TODO Finish checker....

            lastCheck = System.currentTimeMillis();
        }
    }

    public Stock getStock() {
        return stock;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public Alert setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
        return this;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public Alert setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
        return this;
    }

    public long getFrequency() {
        return frequency;
    }

    public Alert setFrequency(long frequency) {
        this.frequency = frequency;
        return this;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public Alert setAlertType(AlertType alertType) {
        this.alertType = alertType;
        return this;
    }
}
