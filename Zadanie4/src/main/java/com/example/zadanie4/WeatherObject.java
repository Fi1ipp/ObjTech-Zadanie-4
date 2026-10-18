package com.example.zadanie4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

abstract class WeatherObject extends Canvas {

    Pane root;
    double Width;
    double Height;

    GraphicsContext gc = getGraphicsContext2D();
    Timeline t;

    Color color;

    WeatherObject() {
        super(50,50);

        setLayoutX(50+Math.random()*400);
        setLayoutY(50+Math.random()*400);

        t = new Timeline(new KeyFrame(Duration.millis(20), e -> move()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    protected void move(){}

    protected void draw(){}
}

class Cloud extends WeatherObject {

    boolean right;

    Cloud(){
        super();
        color = Color.GRAY;
        setLayoutY(Math.random()*200);
        draw();
    }

    @Override
    protected void draw(){
        gc.clearRect(0,0,getWidth(),getHeight());
        gc.setFill(color);
        gc.fillOval(0,10,50,30);
    }

    @Override
    protected void move(){
        root = (Pane) getParent();
        Width = root.getWidth();
        Height = root.getHeight();

        if(getLayoutX()+50 >= Width) {setLayoutX(Width-50); right=false;}
        if(getLayoutX() <= 0) {setLayoutX(0); right=true;}

        if(right)setLayoutX(getLayoutX()+0.5);
        else setLayoutX(getLayoutX()-0.5);

        draw();
    }

    protected void ImBlue() {
        color=Color.BLUE;
        draw();
    }
}

class Raindrop extends WeatherObject {

    private boolean stopped = true;

    Raindrop(){
        super();
        t.play();
        stop();

        setLayoutX(Math.random()*480);
        setLayoutY(Math.random()*480);

        color = Color.BLUE;
        draw();
    }

    @Override
    protected void draw(){
        gc.clearRect(0,0,getWidth(),getHeight());
        gc.setFill(color);
        gc.fillOval(25,15,5,15);
    }

    @Override
    protected void move(){
        root = (Pane) getParent();
        Width = root.getWidth();
        Height = root.getHeight();

        if(getLayoutY() > Height) {
            root.getChildren().remove(this);
            t.stop();
            return;
        }
        else setLayoutY(getLayoutY()+2);


        draw();
    }

    public void stop() {
        if(stopped){
            if(t!=null)t.play();
            stopped=false;
        } else {
            if(t!=null)t.stop();
            stopped=true;
        }
    }
}


class Snowflake extends WeatherObject {

    Snowflake(){
        super();

        setLayoutX(Math.random()*480);
        setLayoutY(Math.random()*480);

        color = Color.LIGHTGRAY;
        draw();
    }

    @Override
    protected void draw(){
        gc.clearRect(0,0,getWidth(),getHeight());
        gc.setFill(color);
        gc.fillOval(20,20,10,10);
    }

    @Override
    protected void move(){
        root = (Pane) getParent();
        Width = root.getWidth();
        Height = root.getHeight();

        if(getLayoutY() > Height) {
            root.getChildren().remove(this);
            t.stop();
            return;
        }
        else setLayoutY(getLayoutY()+2);

        if(getLayoutX() > Width) {
            root.getChildren().remove(this);
            t.stop();
            return;
        } else if(getLayoutX()<0) {
            root.getChildren().remove(this);
            t.stop();
            return;
        } else setLayoutX(getLayoutX()-1+Math.random()*2);


        draw();
    }
}

