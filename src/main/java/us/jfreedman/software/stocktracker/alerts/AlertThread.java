package us.jfreedman.software.stocktracker.alerts;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Joshua on 12/12/2015.
 */
public class AlertThread implements Runnable {

    private static AlertThread instance;

    private BlockingQueue<Alert> registeredAlerts;

    private AlertThread() {
        registeredAlerts = new LinkedBlockingQueue<>();
        new Thread(this).start();
    }

    public static AlertThread getInstance() {
        if (instance == null) instance = new AlertThread();
        return instance;
    }

    public void registerAlert(Alert alert) {
        registeredAlerts.add(alert);
    }

    @Override
    public void run() {
        while (true) {
            try {
                registeredAlerts.forEach(Alert::check);
            } catch (Exception e) {
                e.printStackTrace();
//                break;
            }
        }
    }
}
