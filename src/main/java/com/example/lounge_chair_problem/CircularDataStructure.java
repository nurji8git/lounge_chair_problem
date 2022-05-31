package com.example.lounge_chair_problem;

import java.util.ArrayList;
import java.util.List;

public class CircularDataStructure
{
    private final int size;

    private final List<Chairs_Group> chairs_circular_list;

    public CircularDataStructure(int size)
    {
        this.chairs_circular_list = new ArrayList<>();
        this.size = size;
        this.chairs_circular_list.add(new Chairs_Group(0, true, size));
    }

    public void addNewGroup(Chairs_Group new_group_of_customers, Chairs_Group free_chairs_group, int prev_next)
    {
        free_chairs_group.setGroup_size(free_chairs_group.getGroup_size() - new_group_of_customers.getGroup_size());
        this.chairs_circular_list.add(this.chairs_circular_list.indexOf(free_chairs_group) + prev_next, new_group_of_customers);
    }
    public void removeGroup(int index)
    {
        for(Chairs_Group chairs_group: this.chairs_circular_list)
        {
            if(chairs_group.getGroup_id() == index)
            {
                chairs_group.setGroup_id(0);
                chairs_group.setIs_free(true);
                mergeFreeGroupsOfChairs(chairs_group);
                return;
            }
        }
        this.chairs_circular_list.get(-1);
    }
    public void mergeFreeGroupsOfChairs(Chairs_Group chairs_group)
    {
        if(getPrev(chairs_group).isIs_free() && getNext(chairs_group).isIs_free())
        {
            chairs_group.setGroup_size(getPrev(chairs_group).getGroup_size() + chairs_group.getGroup_size() + getNext(chairs_group).getGroup_size());
            this.chairs_circular_list.remove(getPrev(chairs_group));
            this.chairs_circular_list.remove(getNext(chairs_group));
        }
        else if(getPrev(chairs_group).isIs_free())
        {
            chairs_group.setGroup_size(getPrev(chairs_group).getGroup_size() + chairs_group.getGroup_size());
            this.chairs_circular_list.remove(getPrev(chairs_group));
        }
        else if(getNext(chairs_group).isIs_free())
        {
            chairs_group.setGroup_size(chairs_group.getGroup_size() + getNext(chairs_group).getGroup_size());
            this.chairs_circular_list.remove(getNext(chairs_group));
        }
    }
    public Chairs_Group getNext(Chairs_Group chairs_group)
    {
        if(this.chairs_circular_list.indexOf(chairs_group) == 0)
        {
            return this.chairs_circular_list.get(1);
        }
        else if(this.chairs_circular_list.indexOf(chairs_group) == this.size - 1)
        {
            return this.chairs_circular_list.get(0);
        }
        else
        {
            return this.chairs_circular_list.get(this.chairs_circular_list.indexOf(chairs_group) + 1);
        }
    }
    public Chairs_Group getPrev(Chairs_Group chairs_group)
    {
        if(this.chairs_circular_list.indexOf(chairs_group) == 0)
        {
            return this.chairs_circular_list.get(this.size - 1);
        }
        else if(this.chairs_circular_list.indexOf(chairs_group) == this.size - 1)
        {
            return this.chairs_circular_list.get(this.size - 2);
        }
        else
        {
            return this.chairs_circular_list.get(this.chairs_circular_list.indexOf(chairs_group) - 1);
        }
    }
    public Chairs_Group getElement(int index)
    {
        for(Chairs_Group chairs_group: this.chairs_circular_list)
        {
            if(chairs_group.getGroup_id() == index)
            {
                return chairs_group;
            }
        }
        return this.chairs_circular_list.get(-1);
    }
    public int countTakenChairs()
    {
        int cnt = 0;
        for(Chairs_Group chairs_group: this.chairs_circular_list)
        {
            if(!chairs_group.isIs_free())
            {
                cnt += chairs_group.getGroup_size();
            }
        }
        return cnt;
    }
    public int countFreeChairs()
    {
        int cnt = 0;
        for(Chairs_Group chairs_group: this.chairs_circular_list)
        {
            if(chairs_group.isIs_free())
            {
                cnt += chairs_group.getGroup_size();
            }
        }
        return cnt;
    }
}
