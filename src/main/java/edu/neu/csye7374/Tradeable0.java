package edu.neu.csye7374;

public interface Tradeable0 {
    default void setBid(String bid) {
        // Default implementation
        System.out.println("Default bid set to: " + bid);
    }
    
    default String getMetric() {
        // Default implementation
        return "Default metric";
    }
} 