package us.jfreedman.software.stocktracker.alerts;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AlertThread implements Runnable {

    private static AlertThread instance;

    private final BlockingQueue<Alert> registeredAlerts;
    private boolean running = true;

    private AlertThread() {
        registeredAlerts = new LinkedBlockingQueue<>();
        new Thread(this).start();
    }

    public static AlertThread getInstance() {
        if (instance == null) instance = new AlertThread();
        return instance;
    }

    public synchronized void registerAlert(Alert alert) {
        registeredAlerts.add(alert);
    }

    @Override
    public void run() {
        while (running) {
            try {
                registeredAlerts.forEach(Alert::check);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void interrupt() {
        running = false;
    }
}
