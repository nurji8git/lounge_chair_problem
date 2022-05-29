package com.example.lounge_chair_problem;
import javafx.collections.ObservableList;
import java.util.Objects;

public class Solution {
    /*
    * Place_new_group. Pls don't give much attention to names of methods. This method is used to find and locate a new group of customers into right chairs. Inside this method I used a method for clustering, to know where we should look for chairs for a new group of customers. Then algorithm at first looks for sequence of chairs that the size of sequence will match to size of group of customers. If it finds it locates the group on chairs. If there is no such a sequence of chairs algorithm will look for the sequences of chairs that will return true for the next condition current_group.size() < sequence_of_chairs.size()
    */
    public void Place_New_Group(ObservableList<Chairs_Group> chairs_groups, Chairs_Group new_group)
    {
        int tmp_index = Clustering_of_chairs(chairs_groups);
        for (int i = tmp_index; i >= 0; i--)
        {
            if(chairs_groups.get(i).getGroup_size() == new_group.getGroup_size() && Objects.equals(chairs_groups.get(i).getIs_free(), "Free"))
            {
                System.out.println("clustered");
                chairs_groups.get(i).setGroup_id(new_group.getGroup_id());
                chairs_groups.get(i).setGroup_size(new_group.getGroup_size());
                chairs_groups.get(i).setIs_free(new_group.getIs_free());
                Circlize(chairs_groups);
                return;
            }
        }
        for (int i = tmp_index; i >= 0; i--)
        {
            if(chairs_groups.get(i).getGroup_size() > new_group.getGroup_size() && Objects.equals(chairs_groups.get(i).getIs_free(), "Free"))
            {
                System.out.println("clustered");
                chairs_groups.add(chairs_groups.indexOf(chairs_groups.get(i)), new Chairs_Group(0, "Free", chairs_groups.get(i).getGroup_size() - new_group.getGroup_size()));
                chairs_groups.get(i).setGroup_id(new_group.getGroup_id());
                chairs_groups.get(i).setGroup_size(new_group.getGroup_size());
                chairs_groups.get(i).setIs_free(new_group.getIs_free());
                Circlize(chairs_groups);
                return;
            }
        }
    }

    /*
     * Remove_Group. This method is for cases when the group wanted to return to home. The chairs that they used after calling this method will be free. Method removes the group by their unique id.
     */
    public void Remove_Group(ObservableList<Chairs_Group> chairs_groups, int outgoing_group_index)
    {
        for(Chairs_Group ch: chairs_groups)
        {
            if(ch.getGroup_id() == outgoing_group_index)
            {
                ch.setIs_free("Free");
                ch.setGroup_id(0);
                if(chairs_groups.get(chairs_groups.indexOf(ch) + 1).getGroup_id() == 0 && chairs_groups.get(chairs_groups.indexOf(ch) - 1).getGroup_id() == 0)
                {
                    chairs_groups.get(chairs_groups.indexOf(ch) - 1).setGroup_size(chairs_groups.get(chairs_groups.indexOf(ch) - 1).getGroup_size() + ch.getGroup_size());
                    chairs_groups.get(chairs_groups.indexOf(ch) - 1).setGroup_size(chairs_groups.get(chairs_groups.indexOf(ch) - 1).getGroup_size() + chairs_groups.get(chairs_groups.indexOf(ch) + 1).getGroup_size());
                    chairs_groups.remove(chairs_groups.indexOf(ch) + 1);
                    chairs_groups.remove(ch);
                    break;
                }
                else if(chairs_groups.get(chairs_groups.indexOf(ch) + 1).getGroup_id() == 0)
                {
                    chairs_groups.get(chairs_groups.indexOf(ch) + 1).setGroup_size(chairs_groups.get(chairs_groups.indexOf(ch) + 1).getGroup_size() + ch.getGroup_size());
                    chairs_groups.remove(ch);
                    break;
                }
                else if(chairs_groups.get(chairs_groups.indexOf(ch) - 1).getGroup_id() == 0)
                {
                    chairs_groups.get(chairs_groups.indexOf(ch) - 1).setGroup_size(chairs_groups.get(chairs_groups.indexOf(ch) - 1).getGroup_size() + ch.getGroup_size());
                    chairs_groups.remove(ch);
                    break;
                }
            }
        }
        Circlize(chairs_groups);
    }

    /*
     * Circlize. Since the chairs located around a pool like a circle, data structure that presents all chairs should be also like a circle. What it means? Imagine the case that the first and the last element in our list of chairs present a free chairs, since chairs located around a pool like a circle, we need to circlize the list, by circlize I mean appending of the first element to last element, so we can get a list which starts by an element that presents already taken chair and ends by an element which presents a free chair.
     */
    public void Circlize(ObservableList<Chairs_Group> chairs_groups)
    {
        if(chairs_groups.size() > 1)
        {
            if(Objects.equals(chairs_groups.get(0).getIs_free(), chairs_groups.get(chairs_groups.size() - 1).getIs_free()) && Objects.equals(chairs_groups.get(chairs_groups.size() - 1).getIs_free(), "Free"))
            {
                chairs_groups.set(chairs_groups.size() - 1, new Chairs_Group(0, "Free", chairs_groups.get(0).getGroup_size() + chairs_groups.get(chairs_groups.size() - 1).getGroup_size()));
                chairs_groups.remove(0);
            }
        }
    }

    /*
     * Clustering_of_chairs. In this method I tried to implement a clustering algorithm for 1 dimensional lists. The goal that I want to achieve with this method is to find the biggest cluster of chairs that are already taken. There you can ask a question why? Because the optimal way to allocate chairs for a new group of customers. so we can earn more money is to give them a chairs that are near the biggest cluster or inside biggest cluster the second case is the best for us.
     * The size of a range to search for a cluster of non-free chairs is equal to a sum of all non-free chairs.
     */
    public int Clustering_of_chairs(ObservableList<Chairs_Group> chairs_groups)
    {
        int cnt = 0;
        int rind = 0;
        if(chairs_groups.size() > 1)
        {
            for(Chairs_Group ch_gr: chairs_groups)
            {
                if(ch_gr.getGroup_id() > 0)
                {
                    cnt += ch_gr.getGroup_size();
                }
            }
            Chairs_Group tmp_ch_gr = chairs_groups.get(chairs_groups.size() - 1);
            while(chairs_groups.get(0) != tmp_ch_gr)
            {
                int cnt1 = 0;
                for(Chairs_Group ch_gr1: chairs_groups)
                {
                    cnt1 += ch_gr1.getGroup_size();
                    if(cnt1 >= cnt && rind <= chairs_groups.indexOf(ch_gr1))
                    {
                        rind = chairs_groups.indexOf(ch_gr1);
                        break;
                    }
                }
                chairs_groups.add(chairs_groups.get(0));
                chairs_groups.remove(0);
            }
        }
        Circlize(chairs_groups);
        System.out.println("Rind " + rind);
        return rind;
    }
}