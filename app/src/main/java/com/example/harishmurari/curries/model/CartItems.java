package com.example.harishmurari.curries.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by harishmurari on 6/15/2017.
 */

public class CartItems {

    private Map<CurryItem, Integer> cart = new LinkedHashMap<>();
    private double price = 0;

    public void addItemToCart(CurryItem item) {
        if (cart.containsKey(item)) {
            cart.put(item, cart.get(item) + 1);
        } else {
            cart.put(item, 1);
        }
        price += item.getCurryPrice();
    }

    public Set getCartItems() {
        return cart.keySet();
    }

    public void emptyCart() {
        cart.clear();
        price = 0;
    }

    public double getPrice() {
        return price;
    }

    public int getCartSize() {
        return cart.size();
    }

    public int getCurryItem(CurryItem curryItem) {
        return cart.get(curryItem);
    }

    public boolean checkProductInCart(CurryItem curryItem) {
        return cart.containsKey(curryItem);
    }

    public void removeItemfromCart(CurryItem curryItem) {
        cart.remove(curryItem);
    }
}
