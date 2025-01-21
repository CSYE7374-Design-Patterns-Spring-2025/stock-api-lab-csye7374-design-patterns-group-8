package edu.neu.csye7374;

public interface Tradeable1 {
    void setBid(String bid);  // No default implementation
    
    default String getMetric() {
        // Default implementation
        return "Default metric";
    }
} 