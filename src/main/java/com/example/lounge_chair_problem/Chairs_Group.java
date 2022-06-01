package com.example.lounge_chair_problem;

import javafx.beans.property.*;

public class Chairs_Group {

    // The group_id field presents an id of that group which is currently rented this chairs
    private final SimpleIntegerProperty group_id;

    // If is_free field is true then it means that the chairs are free currently
    // and since there is no customers on chairs the group_id field will set to 0
    private final SimpleBooleanProperty is_free;

    // The group_size field presents the size of this group of chairs. By group of chairs I mean consecutive free or taken chairs.
    private final SimpleIntegerProperty group_size;


    // This is an entity class that presents consecutive free or taken chairs as a one group of free or taken chairs.
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
        if(is_free)
        {
            this.group_id.set(0);
        }
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