package edu.neu.csye7374;

/**
 * 
 * @author Preyash Mehta
 * 
 */

public class Driver {
    public static void main(String[] args) {
        System.out.println("============Main Execution Start===================\n\n");

        // Create StockMarket instance
        StockMarket market = StockMarket.getInstance();
        
        // Create and add Google stock
        Stock googleStock = new GoogleStock("GOOGL", 2800.0, "Alphabet Inc. Class A Common Stock");
        market.addStock(googleStock);
        
        // Show initial state
        System.out.println("Initial Stock State:");
        market.showAllStocks();
        
        // Perform trades for Google stock with 6 bids
        System.out.println("\nTrading Google Stock:");
        String[] googleBids = {"2850.0", "2900.0", "2875.0", "2925.0", "2910.0", "2950.0"};
        
        for (int i = 0; i < googleBids.length; i++) {
            System.out.println("\nTrade #" + (i + 1) + ":");
            market.tradeStock(googleStock, googleBids[i]);
            market.showAllStocks();
        }

        System.out.println("\n============Main Execution End===================");
    }
}



