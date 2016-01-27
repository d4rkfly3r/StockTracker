package us.jfreedman.software.stocktracker.alerts;

import us.jfreedman.software.stocktracker.stocks.Stock;

import java.util.concurrent.TimeUnit;

public class AlertBuilder {
    private Stock stock;
    private double lowerLimit;
    private double upperLimit;
    private long frequency = TimeUnit.DAYS.convert(1, TimeUnit.MILLISECONDS);
    private AlertType alertType = AlertType.SOUND;

    public AlertBuilder setStock(Stock stock) {
        this.stock = stock;
        return this;
    }

    public AlertBuilder setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
        return this;
    }

    public AlertBuilder setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
        return this;
    }

    public AlertBuilder setFrequency(TimeUnit unit, long frequency) {
        this.frequency = unit.convert(frequency, TimeUnit.MILLISECONDS);
        return this;
    }

    public AlertBuilder setAlertType(AlertType alertType) {
        this.alertType = alertType;
        return this;
    }

    public Alert createAlert() {
        return new Alert(stock, lowerLimit, upperLimit, frequency, alertType);
    }
}