package com.javaprogram.capstone.observer;

/**
 * Observer interface for price change events.
 */
public interface PriceObserver {
    void onPriceChanged(String productId, String productName, double oldPrice, double newPrice);
}
