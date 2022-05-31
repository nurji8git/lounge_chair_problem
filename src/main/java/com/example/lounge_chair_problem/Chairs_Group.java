package com.example.lounge_chair_problem;

import javafx.beans.property.*;

public class Chairs_Group {
    private final SimpleIntegerProperty group_id;
    private final SimpleBooleanProperty is_free;
    private final SimpleIntegerProperty group_size;

    public Chairs_Group(int group_id, boolean is_free, int group_size){
        this.group_id = new SimpleIntegerProperty(group_id);
        this.is_free = new SimpleBooleanProperty(is_free);
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

    public boolean isIs_free() {
        return is_free.get();
    }

    public SimpleBooleanProperty is_freeProperty() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free.set(is_free);
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