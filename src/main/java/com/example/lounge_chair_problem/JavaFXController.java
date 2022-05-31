package com.example.lounge_chair_problem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.List;
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
    private ObservableList<Chairs_Group> all_chairs;

    @FXML
    private ObservableList<PieChart.Data> pchart_data;

    @FXML
    private Solution solution;

    @FXML
    private void InitializeNewSituation()
    {
        solution = new Solution();
        if(!Objects.equals(num_all_chairs_input.getText(), ""))
        {
            int num_of_all_chairs = new IntegerStringConverter().fromString(num_all_chairs_input.getText());
            all_chairs = FXCollections.observableArrayList();
            pchart_data = FXCollections.observableArrayList();
            all_chairs.add(new Chairs_Group(0, true, num_of_all_chairs));
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

        List<Chairs_Group> tmp = new ArrayList<>();
        tmp.add(new Chairs_Group(1, false, 12));
        tmp.add(new Chairs_Group(2, false, 5));
        tmp.add(new Chairs_Group(3, false, 5));
        tmp.add(new Chairs_Group(0, true, 5));
        tmp.add(new Chairs_Group(4, false, 5));
        tmp.add(new Chairs_Group(0, true, 5));
        solution.clustering_of_chairs(tmp);
    }

    @FXML
    private void Restart_program()
    {
        all_chairs.clear();
        pchart_data.clear();
        state_of_chairs_PChart.setData(pchart_data);
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

    @FXML
    private void Serve_New_Group()
    {
        if(!Objects.equals(new_group_input.getText(), ""))
        {
            int new_group_size = new IntegerStringConverter().fromString(new_group_input.getText());
            if(new_group_size > 0)
            {
                //There should be method for allocating new group of customers
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
            messages_lb.setText("Field is empty!");
        }
    }

    @FXML
    private void Group_Outgoing()
    {
        if(!Objects.equals(new_group_input.getText(), ""))
        {
            if(all_chairs.size() <= 1)
            {
                messages_lb.setText("No group to remove");
                return;
            }
            int outgoing_group_index = new IntegerStringConverter().fromString(index_out_group.getText());
            if(outgoing_group_index == 0)
            {
                messages_lb.setText("Index of outgoing group should be above 0!");
            }
            else
            {
                //There should be method for removing a group of customer
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
        for(Chairs_Group chair_group: all_chairs)
        {
            pchart_data.add(new PieChart.Data("", chair_group.getGroup_size()));
        }
        state_of_chairs_PChart.setData(pchart_data);
        applyCustomColorSequence(pchart_data);
    }

    @FXML
    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData)
    {
        for (PieChart.Data data : pieChartData)
        {
            if(Objects.equals(data.getName(), ""))
            {
                data.getNode().setStyle("-fx-pie-color: #BF6;");
            }
            else
            {
                data.getNode().setStyle("-fx-pie-color: #F80;");
            }
        }
    }
}