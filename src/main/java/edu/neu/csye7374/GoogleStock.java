package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class GoogleStock extends Stock {
    private List<Double> priceHistory;
    private static final int HISTORY_SIZE = 5; // Keep track of last 5 prices
    
    public GoogleStock(String name, double price, String description) {
        super(name, price, description);
        this.priceHistory = new ArrayList<>();
        this.priceHistory.add(price);
    }
    
    @Override
    public void setBid(String bid) {
        double bidValue = Double.parseDouble(bid);
        
        // Add new price to history
        if (priceHistory.size() >= HISTORY_SIZE) {
            priceHistory.remove(0);
        }
        priceHistory.add(bidValue);
        
        setPrice(bidValue);
    }
    
    @Override
    public String getMetric() {
        if (priceHistory.size() < 2) {
            return "0.0";
        }
        
        // Calculate price momentum (rate of change)
        double momentum = (priceHistory.get(priceHistory.size() - 1) - priceHistory.get(0)) 
                         / priceHistory.get(0) * 100;
        
        // Calculate volatility (standard deviation of price changes)
        double volatility = calculateVolatility();
        
        // Combine momentum and volatility for final metric
        // Positive momentum with low volatility = good
        // Negative momentum or high volatility = bad
        double metric = momentum * (1 - volatility/100);
        
        return String.format("%.2f", metric);
    }
    
    private double calculateVolatility() {
        if (priceHistory.size() < 2) {
            return 0.0;
        }
        
        // Calculate mean of price changes
        double sum = 0.0;
        List<Double> changes = new ArrayList<>();
        
        for (int i = 1; i < priceHistory.size(); i++) {
            double change = ((priceHistory.get(i) - priceHistory.get(i-1)) / priceHistory.get(i-1)) * 100;
            changes.add(change);
            sum += change;
        }
        
        double mean = sum / changes.size();
        
        // Calculate standard deviation
        double sumSquaredDiff = 0.0;
        for (double change : changes) {
            sumSquaredDiff += Math.pow(change - mean, 2);
        }
        
        return Math.sqrt(sumSquaredDiff / changes.size());
    }
} 