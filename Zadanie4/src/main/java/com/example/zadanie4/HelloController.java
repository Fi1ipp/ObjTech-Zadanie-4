package com.example.zadanie4;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane root;

    Cloud cloud;
    Snowflake snow;
    Raindrop drop;

    @FXML
    protected void Cloud() {
        cloud = new Cloud();
        root.getChildren().add(cloud);
    }

    @FXML
    protected void Snow() {
        snow = new Snowflake();
        root.getChildren().add(snow);
    }

    @FXML
    protected void Drop() {
        drop = new Raindrop();
        root.getChildren().add(drop);
    }

    @FXML
    protected void Blue() {
        for(int i = 0; i<root.getChildren().size(); i++) {
            Node d = root.getChildren().get(i);
            if (d instanceof Cloud) ((Cloud) d).ImBlue();
        }
    }

    @FXML
    protected void Left() {
        for(int i = 0; i<root.getChildren().size(); i++) {
            Node d = root.getChildren().get(i);
            if (d instanceof WeatherObject) ((WeatherObject) d).setLayoutX(((WeatherObject) d).getLayoutX()+20);
        }
    }

    @FXML
    protected void Stop() {
        for(int i = 0; i<root.getChildren().size(); i++) {
            Node d = root.getChildren().get(i);
            if (d instanceof Raindrop) ((Raindrop) d).stop();
        }
    }
}
