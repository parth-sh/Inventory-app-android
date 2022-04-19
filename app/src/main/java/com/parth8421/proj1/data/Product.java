package com.parth8421.proj1.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int cost;
    private int count;

    public Product(@NonNull String name, int cost, int count) {
        this.name = name;
        this.cost = cost;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }
}
