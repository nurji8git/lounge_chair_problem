package com.example.lounge_chair_problem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;
import java.util.Objects;

public class JavaFXController {

    @FXML
    private TextField num_all_chairs_input;

    @FXML
    private TextField new_group_input;

    @FXML
    private TextField index_out_group;

    @FXML
    private Button num_all_chairs_Submit;

    @FXML
    private Button new_group_submit;

    @FXML
    private Button out_group_submit;

    @FXML
    private Label messages_lb;

    @FXML
    private PieChart state_of_chairs_PChart;

    @FXML
    private ObservableList<PieChart.Data> pchart_data;

    @FXML
    private CircularDataStructure chairs_circular_list;

    @FXML
    private int new_group_id = 0;

    @FXML
    private void InitializeNewSituation()
    {
        if(!Objects.equals(num_all_chairs_input.getText(), ""))
        {
            int num_of_all_chairs = new IntegerStringConverter().fromString(num_all_chairs_input.getText());
            chairs_circular_list = new CircularDataStructure(num_of_all_chairs);
            pchart_data = FXCollections.observableArrayList();
            UpdateData();

            num_all_chairs_input.setDisable(true);
            num_all_chairs_Submit.setDisable(true);

            new_group_input.clear();
            new_group_input.setDisable(false);
            new_group_submit.setDisable(false);
            index_out_group.clear();
            index_out_group.setDisable(false);
            out_group_submit.setDisable(false);
        }
        else
        {
            messages_lb.setText("Field is empty!");
        }
    }

    @FXML
    private void Serve_New_Group()
    {
        if(!Objects.equals(new_group_input.getText(), ""))
        {
            int new_group_size = new IntegerStringConverter().fromString(new_group_input.getText());
            if(new_group_size > 0 && new_group_size <= chairs_circular_list.countFreeChairs())
            {
                new_group_id++;
                Customers_Group customers_group = new Customers_Group(new_group_id, new_group_size);
                if(chairs_circular_list.addNewGroup(customers_group))
                {
                    UpdateData();
                    messages_lb.setText("Last action: New group of " + new_group_size + " customers came");
                }
                else
                {
                    messages_lb.setText("Last action: No free chairs for this group");
                }
            }
            else
            {
                messages_lb.setText("Last action: No free chairs for this group");
            }
        }
        else
        {
            messages_lb.setText("Field is empty!");
        }
    }

    @FXML
    private void Group_Outgoing()
    {
        if(!Objects.equals(new_group_input.getText(), ""))
        {
            int outgoing_group_index = new IntegerStringConverter().fromString(index_out_group.getText());
            if(outgoing_group_index == 0)
            {
                messages_lb.setText("Index of outgoing group should be above 0!");
            }
            else
            {
                chairs_circular_list.removeGroup(outgoing_group_index);
                UpdateData();
                messages_lb.setText("Last action: Group with index " + outgoing_group_index + " was gone");
            }
        }
        else
        {
            messages_lb.setText("Field is empty!");
        }
    }

    @FXML
    private void UpdateData()
    {
        pchart_data.clear();
        for(Chairs_Group chair_group: chairs_circular_list.getChairs_circular_list())
        {
            if(chair_group.isIs_free())
            {
                pchart_data.add(new PieChart.Data("Free" + "\n" + chair_group.getGroup_size(), chair_group.getGroup_size()));
            }
            else
            {
                pchart_data.add(new PieChart.Data("Group " + chair_group.getGroup_id() + "\nSize " + chair_group.getGroup_size(), chair_group.getGroup_size()));
            }
        }
        state_of_chairs_PChart.setData(pchart_data);
        applyCustomColorSequence(pchart_data);
    }

    @FXML
    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData)
    {
        for (PieChart.Data data : pieChartData)
        {
            if(Objects.equals(data.getName().substring(0, 4), "Free"))
            {
                data.getNode().setStyle("-fx-pie-color: #BF6;");
            }
            else
            {
                data.getNode().setStyle("-fx-pie-color: #F80;");
            }
        }
    }

    @FXML
    private void Restart_program()
    {
        chairs_circular_list = new CircularDataStructure(0);
        pchart_data.clear();
        state_of_chairs_PChart.setData(pchart_data);
        new_group_id = 0;
        num_all_chairs_input.setDisable(false);
        num_all_chairs_input.clear();
        num_all_chairs_Submit.setDisable(false);
        new_group_input.setDisable(true);
        new_group_input.clear();
        new_group_submit.setDisable(true);
        index_out_group.setDisable(true);
        index_out_group.clear();
        out_group_submit.setDisable(true);
        messages_lb.setText("Last action: Restart");
    }

    @FXML
    private void Exit_program()
    {
        System.exit(0);
    }
}