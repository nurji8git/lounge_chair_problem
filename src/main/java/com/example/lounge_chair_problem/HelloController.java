package com.example.lounge_chair_problem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;

import java.util.Objects;

public class HelloController {

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
    private int current_group_id;

    @FXML
    private Solution solution;

    @FXML
    private void Set_N_all_chairs()
    {
        solution = new Solution();
        int num_of_all_chairs = new IntegerStringConverter().fromString(num_all_chairs_input.getText());
        all_chairs = FXCollections.observableArrayList();
        pchart_data = FXCollections.observableArrayList();
        all_chairs.add(new Chairs_Group(0, "Free", num_of_all_chairs));
        for(Chairs_Group chair_group: all_chairs)
        {
            pchart_data.add(new PieChart.Data(chair_group.getIs_free(), chair_group.getGroup_size()));
        }
        state_of_chairs_PChart.setData(pchart_data);
        applyCustomColorSequence(pchart_data);

        num_all_chairs_input.setDisable(true);
        num_all_chairs_Submit.setDisable(true);

        new_group_input.clear();
        new_group_input.setDisable(false);
        new_group_submit.setDisable(false);
        index_out_group.clear();
        index_out_group.setDisable(false);
        out_group_submit.setDisable(false);
    }

    @FXML
    private void Restart_program()
    {
        all_chairs = FXCollections.observableArrayList();
        pchart_data = FXCollections.observableArrayList();
        state_of_chairs_PChart.setData(pchart_data);
        current_group_id = 0;
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
        current_group_id += 1;
        int new_group_size = new IntegerStringConverter().fromString(new_group_input.getText());
        if(new_group_size > 0)
        {
            pchart_data.clear();
            solution.Place_New_Group(all_chairs, new Chairs_Group(current_group_id, "Group: " + current_group_id + "\nSize: " + new_group_size, new_group_size));
            for(Chairs_Group chairs_group: all_chairs)
            {
                pchart_data.add(new PieChart.Data(chairs_group.getIs_free(), chairs_group.getGroup_size()));
            }

            state_of_chairs_PChart.setData(pchart_data);
            applyCustomColorSequence(pchart_data);

            messages_lb.setText("Last action: Group with index " + current_group_id + " came");
            System.out.println("#######");
            for(Chairs_Group chairs_group: all_chairs)
            {
                System.out.println(chairs_group.getGroup_size());
            }
            System.out.println("#######");
        }
    }

    @FXML
    private void Group_Outgoing()
    {
        int outgoing_group_index = new IntegerStringConverter().fromString(index_out_group.getText());
        if(outgoing_group_index == 0)
        {
            messages_lb.setText("Index of outgoing group should be above 0!");
        }
        else
        {
            solution.Remove_Group(all_chairs, outgoing_group_index);
            pchart_data.clear();
            for(Chairs_Group chairs_group: all_chairs)
            {
                pchart_data.add(new PieChart.Data(chairs_group.getIs_free(), chairs_group.getGroup_size()));
            }
            state_of_chairs_PChart.setData(pchart_data);
            applyCustomColorSequence(pchart_data);
            messages_lb.setText("Last action: Group with index " + outgoing_group_index + " was gone");
        }

        System.out.println("#######");
        for(Chairs_Group chairs_group: all_chairs)
        {
            System.out.println(chairs_group.getGroup_size());
        }
        System.out.println("#######");
    }

    @FXML
    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData)
    {
        for (PieChart.Data data : pieChartData)
        {
            if(Objects.equals(data.getName(), "Free"))
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