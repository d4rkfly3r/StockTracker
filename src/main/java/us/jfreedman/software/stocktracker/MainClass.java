package us.jfreedman.software.stocktracker;


import us.jfreedman.software.stocktracker.stocks.Stock;

class MainClass {

    private MainClass() {
        System.out.println(Data.updateData(new Stock().setStockSymbol("INTC")));
        System.out.println(Data.updateData(new Stock().setStockSymbol("GOOG")));

    }


    public static void main(String[] args) {
        new MainClass();
    }
}
