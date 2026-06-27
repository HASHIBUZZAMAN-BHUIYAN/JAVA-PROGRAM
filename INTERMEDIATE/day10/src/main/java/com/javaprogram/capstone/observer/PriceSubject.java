package com.javaprogram.capstone.observer;

import java.util.*;

/**
 * Manages price change observers. Classes that track prices extend this.
 */
public class PriceSubject {
    private List<PriceObserver> observers = new ArrayList<>();

    public void addObserver(PriceObserver o)    { observers.add(o); }
    public void removeObserver(PriceObserver o) { observers.remove(o); }

    protected void notifyPriceChange(String productId, String name, double old, double newPrice) {
        observers.forEach(o -> o.onPriceChanged(productId, name, old, newPrice));
    }
}
