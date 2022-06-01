package com.example.lounge_chair_problem;

import java.util.ArrayList;
import java.util.List;

public class CircularDataStructure
{
    private final List<Chairs_Group> chairs_circular_list;

    public CircularDataStructure(int size)
    {
        this.chairs_circular_list = new ArrayList<>();
        this.chairs_circular_list.add(new Chairs_Group(0, true, size));
    }

    public List<Chairs_Group> getChairs_circular_list()
    {
        return chairs_circular_list;
    }

    // This method places a new group of customers on the given consecutive free chairs.
    public boolean addNewGroup(Customers_Group new_group_of_customers)
    {
        Chairs_Group free_chairs_group = findChairsForGroup(new_group_of_customers);
        if(new_group_of_customers.getGroup_size() > free_chairs_group.getGroup_size())
        {
            return false;
        }
        free_chairs_group.setGroup_size(free_chairs_group.getGroup_size() - new_group_of_customers.getGroup_size());
        this.chairs_circular_list.add(this.chairs_circular_list.indexOf(free_chairs_group) + before_or_after(free_chairs_group, new_group_of_customers), new Chairs_Group(new_group_of_customers.getGroup_id(), false, new_group_of_customers.getGroup_size()));
        return true;
    }

    // This method finds an optimal consecutive free chairs for new group of customers.
    private Chairs_Group findChairsForGroup(Customers_Group new_group)
    {
        for(Chairs_Group chairs_group: chairs_circular_list)
        {
            if(chairs_group.getGroup_size() == new_group.getGroup_size() && chairs_group.isIs_free())
            {
                return chairs_group;
            }
        }
        int res_index = 0;
        int cond_size = countFreeChairs();

        for(Chairs_Group chairs_group: chairs_circular_list)
        {
            if(chairs_group.getGroup_size() > new_group.getGroup_size() && chairs_group.isIs_free())
            {
                if(chairs_group.getGroup_size() <= cond_size)
                {
                    res_index = chairs_circular_list.indexOf(chairs_group);
                    cond_size = chairs_group.getGroup_size();
                }
            }
        }
        return chairs_circular_list.get(res_index);
    }

    // This method decides where the new group of customers has to be placed, on the left side or right side of consecutive free chairs.
    // Is it matter?
    private int before_or_after(Chairs_Group free_chairs_group, Customers_Group new_group_of_customers)
    {
        if(getPrev(free_chairs_group).getGroup_size() <= new_group_of_customers.getGroup_size() && getNext(free_chairs_group).getGroup_size() <= new_group_of_customers.getGroup_size())
        {
            if(getPrev(free_chairs_group).getGroup_size() <= getNext(free_chairs_group).getGroup_size())
            {
                return 1;
            }
            return 0;
        }
        else if(getPrev(free_chairs_group).getGroup_size() >= new_group_of_customers.getGroup_size() && getNext(free_chairs_group).getGroup_size() >= new_group_of_customers.getGroup_size())
        {
            if(getPrev(free_chairs_group).getGroup_size() <= getNext(free_chairs_group).getGroup_size())
            {
                return 0;
            }
            return 1;
        }
        else if(getPrev(free_chairs_group).getGroup_size() <= getNext(free_chairs_group).getGroup_size())
        {
            if(new_group_of_customers.getGroup_size() - getPrev(free_chairs_group).getGroup_size() < getNext(free_chairs_group).getGroup_size() - new_group_of_customers.getGroup_size())
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else
        {
            if(getPrev(free_chairs_group).getGroup_size() - new_group_of_customers.getGroup_size() < new_group_of_customers.getGroup_size() - getNext(free_chairs_group).getGroup_size())
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
    }

    // This method removes group of customers from the chairs. Imagine that the customers going home.
    public void removeGroup(int index_of_group)
    {
        for(Chairs_Group chairs_group: this.chairs_circular_list)
        {
            if(chairs_group.getGroup_id() == index_of_group)
            {
                chairs_group.setGroup_id(0);
                chairs_group.setIs_free(true);
                mergeFreeGroupsOfChairs(chairs_group);
                return;
            }
        }
        this.chairs_circular_list.get(-1);
    }

    // This method merges groups of free chairs.
    // After removing a group of customers from chairs we need to merge newly vacated chairs with neighbour consecutive free chairs and get a one solid group of free chairs.
    private void mergeFreeGroupsOfChairs(Chairs_Group free_chairs_group)
    {
        if(getPrev(free_chairs_group).isIs_free() && getNext(free_chairs_group).isIs_free() && !getPrev(free_chairs_group).equals(getNext(free_chairs_group)))
        {
            free_chairs_group.setGroup_size(getPrev(free_chairs_group).getGroup_size() + free_chairs_group.getGroup_size() + getNext(free_chairs_group).getGroup_size());
            this.chairs_circular_list.remove(getPrev(free_chairs_group));
            this.chairs_circular_list.remove(getNext(free_chairs_group));
        }
        else if(getPrev(free_chairs_group).isIs_free())
        {
            free_chairs_group.setGroup_size(getPrev(free_chairs_group).getGroup_size() + free_chairs_group.getGroup_size());
            this.chairs_circular_list.remove(getPrev(free_chairs_group));
        }
        else if(getNext(free_chairs_group).isIs_free())
        {
            free_chairs_group.setGroup_size(free_chairs_group.getGroup_size() + getNext(free_chairs_group).getGroup_size());
            this.chairs_circular_list.remove(getNext(free_chairs_group));
        }
    }

    // This method returns a next element of parameter element clockwise.
    private Chairs_Group getNext(Chairs_Group chairs_group)
    {
        if(chairs_circular_list.size() == 1)
        {
            return this.chairs_circular_list.get(0);
        }
        if(this.chairs_circular_list.indexOf(chairs_group) == chairs_circular_list.size() - 1)
        {
            return this.chairs_circular_list.get(0);
        }
        else
        {
            return this.chairs_circular_list.get(this.chairs_circular_list.indexOf(chairs_group) + 1);
        }
    }

    // This method returns a previous element of parameter element counterclockwise.
    private Chairs_Group getPrev(Chairs_Group chairs_group)
    {
        if(chairs_circular_list.size() == 1)
        {
            return this.chairs_circular_list.get(0);
        }
        if(this.chairs_circular_list.indexOf(chairs_group) == 0)
        {
            return this.chairs_circular_list.get(chairs_circular_list.size() - 1);
        }
        else if(this.chairs_circular_list.indexOf(chairs_group) == chairs_circular_list.size() - 1)
        {
            return this.chairs_circular_list.get(chairs_circular_list.size() - 2);
        }
        else
        {
            return this.chairs_circular_list.get(this.chairs_circular_list.indexOf(chairs_group) - 1);
        }
    }

    // This method returns the number of all taken chairs.
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

    // This method returns the number of all free chairs.
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
