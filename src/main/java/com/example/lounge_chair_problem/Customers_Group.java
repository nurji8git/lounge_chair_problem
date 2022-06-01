package com.example.lounge_chair_problem;

import javafx.beans.property.*;

public class Customers_Group {

    // ID of customers
    private final SimpleIntegerProperty group_id;

    // Number of people in this group of customers
    private final SimpleIntegerProperty group_size;


    // This is an entity class that presents a group of customers.
    public Customers_Group(int group_id, int group_size){
        this.group_id = new SimpleIntegerProperty(group_id);
        this.group_size = new SimpleIntegerProperty(group_size);
    }

    public int getGroup_id() {
        return group_id.get();
    }

    public SimpleIntegerProperty group_idProperty() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id.set(group_id);
    }

    public int getGroup_size() {
        return group_size.get();
    }

    public SimpleIntegerProperty group_sizeProperty() {
        return group_size;
    }

    public void setGroup_size(int group_size) {
        this.group_size.set(group_size);
    }
}