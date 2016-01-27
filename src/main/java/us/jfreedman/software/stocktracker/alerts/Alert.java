package us.jfreedman.software.stocktracker.alerts;

import us.jfreedman.software.stocktracker.stocks.Stock;

/**
 * Created by Joshua on 12/12/2015.
 */
public class Alert {

    private final Stock stock;
    private final double lowerLimit, upperLimit;
    private final long frequency;
    private final AlertType alertType;

    private long lastCheck;


    protected Alert(Stock stock, double lowerLimit, double upperLimit, long frequency, AlertType alertType) {
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
        if (System.currentTimeMillis() - frequency >= 0) {
            //TODO Finish checker....
        }
    }
}
