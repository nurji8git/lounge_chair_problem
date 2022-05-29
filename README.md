# Solution for lounge chair allocation
Pool has n lounge chairs arranged in a circle that are temporarily rented to bathers for a fee.
When a group of k guests arrives, you must find k free sunbeds next to each other.
When the group leaves after bathing, all k beds simultaneously become free and can be redistributed.
If there are not enough sunbeds nearby, the group leaves again, and the lifeguard makes no money.
The goal is to keep the sunbeds occupied as efficiently as possible so that the lifeguard earns a lot of money.
Write a Java program to help the lifeguard occupy the sun loungers in the best way possible.

The main algorithms is in the Solution class.
1. Clustering_of_chairs method. In this method I tried to implement a clustering algorithm for 1 dimensional lists. The goal that I want to achieve with this method is to find a biggest cluster of chairs that are already taken. There you can ask a question why? Because the optimal way to allocate chairs for a new group of customers so we can earn more money is to give them a chairs that are near the biggest cluster or inside of biggest cluster the second case is the best for us.
The size of a range to search for a cluster of non free chairs is equal to a sum of all non free chairs.
2. Circlize. Since the chairs located around a pool like a circle, data structure that presents all chairs should be also like a circle. What it means? Imagine the case that the first and the last element in our list of chairs present a free chairs, since chairs located around a pool like a circle, we need to circlize the list, by circlize I mean appending of the first element to last element so we can get a list wich starts by an element that presents a already taken chair and ends by an element which presents a free chair.
3. Place_new_group. Pls dont give much attention to names of methods. This method is used to find and locate a new group of customers into right chairs. Inside of this method I used a method for clustering, to know where we should look for chairs for a new group of customers. Then algorithm at first looks for sequence of chairs that the size of sequence will match to size of group of customers. If it finds it locates the group on this chairs. If there is no such a sequence of chairs algorithm will look for the sequences of chairs that will return true for the next condition current_group.size() < seqence_of_chairs.size()
4. Remove_Group. This method is for cases when the group wanted to return to home. The chairs that they used after calling this method will be free. Method removes the group by their unique id.
