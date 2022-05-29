package com.example.lounge_chair_problem;
import javafx.collections.ObservableList;
import java.util.Objects;

public class Solution {
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
