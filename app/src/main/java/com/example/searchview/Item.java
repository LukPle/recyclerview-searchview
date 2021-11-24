package com.example.searchview;

public class Item {

    private String item;
    private String quantity;

    public Item(String item, String quantity) {

        this.item = item;
        this.quantity = quantity;
    }

    public String getItem() {

        return item;
    }

    public String getQuantity() {

        return quantity;
    }
}
