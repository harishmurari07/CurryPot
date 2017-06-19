package com.example.harishmurari.curries.model;

import java.util.ArrayList;

/**
 * Created by harishmurari on 6/15/2017.
 */

public class CartItems {

    private static ArrayList<CurryItem> cartItems = new ArrayList<CurryItem>();

    public CurryItem getCurryItem(int position) {
        return cartItems.get(position);
    }

    public int getCartSize() {
        return cartItems.size();
    }
    public static ArrayList<CurryItem> getCartItems() {
        return cartItems;
    }

    public void addItemtoCart(CurryItem item) {
        cartItems.add(item);
    }

    public void removeItemfromCart(CurryItem item) {
        cartItems.remove(item);
    }

    public boolean checkProductInCart(CurryItem curryItem) {
        return cartItems.contains(curryItem);
    }

}
