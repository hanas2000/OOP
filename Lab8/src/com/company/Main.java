package com.company;

import com.sun.javafx.application.LauncherImpl;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.sql.Time;
import java.util.Scanner;

public class Main extends Application {
    public Plan plan1;
    Button button1;
    public ObservableList<Plan> plan = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("План");
        FlowPane second_part=new FlowPane();
        GridPane rootNode = new GridPane();
        HBox root=new HBox();
        Scene scene = new Scene(rootNode, 1000, 500);
        primaryStage.setScene(scene);
        TableView<Plan> table =new TableView<Plan>(plan);
        table.setPrefWidth((scene.getWidth()/3)*2);
        table.setPrefHeight(scene.getHeight());
        TableColumn<Plan, String> nameColumn = new TableColumn<Plan, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Plan, String>("name"));
        table.getColumns().add(nameColumn);
        TableColumn<Plan,String> dateColumn=new TableColumn<Plan,String>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<Plan,String>("date"));
        table.getColumns().add(dateColumn);
        TableColumn<Plan, String> timeColumn = new TableColumn<Plan, String>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<Plan, String>("time"));
        table.getColumns().add(timeColumn);
        TableColumn<Plan,String> placeColumn=new TableColumn<Plan,String>("Place");
        placeColumn.setCellValueFactory(new PropertyValueFactory<Plan,String>("place"));
        table.getColumns().add(placeColumn);
        TableColumn<Plan, String> describeColumn = new TableColumn<Plan, String>("Description");
        describeColumn.setCellValueFactory(new PropertyValueFactory<Plan, String>("describe"));
        table.getColumns().add(describeColumn);
        TableColumn<Plan,String> peopleColumn=new TableColumn<Plan,String>("People");
        peopleColumn.setCellValueFactory(new PropertyValueFactory<Plan,String>("people"));
        table.getColumns().add(peopleColumn);
        Button button = new Button("Add");
        button1 = new Button("Delete");
        Button button2 = new Button("Read from file");
        Button button3 = new Button("Write to file");
        Button button4 = new Button("Exit");
        button.setPrefSize(100, 100);
        button1.setPrefSize(100, 100);
        button2.setPrefSize(100, 100);
        button3.setPrefSize(100, 100);
        button4.setPrefSize(100,100);
        VBox vb=new VBox();

        rootNode.getColumnConstraints().add(new ColumnConstraints(scene.getWidth()/2));
        rootNode.getColumnConstraints().add(new ColumnConstraints(scene.getWidth()/2));
        root.getChildren().addAll(button,button1,button2,button3,button4);

        second_part.getChildren().addAll(root,vb);
        rootNode.getChildren().addAll(table,second_part);


        rootNode.setColumnIndex(table, 0);
        rootNode.setColumnIndex(second_part, 1);
        button3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try (XMLEncoder encoder= new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file.toString())))){
                        Integer size = plan.size();
                        encoder.writeObject(size);
                        for(Plan i:plan)
                        {
                            encoder.writeObject(i);
                        }
                        encoder.close();
                        table.refresh();
                    } catch (Exception ex) {
                        System.out.println("Error");
                    }
                }
            }
        });
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try (XMLDecoder decoder_ = new XMLDecoder(new BufferedInputStream(new FileInputStream(file.toString())))){
                        Integer size_ = (Integer) decoder_.readObject();
                        for (int i = 0; i < size_; i++) {
                            plan.add((Plan) decoder_.readObject());
                        }
                        decoder_.close();
                        table.refresh();
                    } catch (Exception ex) {
                        System.out.println("Error");
                    }
                }

            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextField name=new TextField();
                TextField date=new TextField();
                TextField time=new TextField();
                TextField place=new TextField();
                TextField describe=new TextField();
                TextField people=new TextField();
                Button add2=new Button("Add to table");
                HBox namebox = new HBox(new Label("Name: "),name);
                HBox datebox = new HBox(new Label("Date: "),date);
                HBox timebox = new HBox(new Label("Time: "),time);
                HBox placebox = new HBox(new Label("Place: "),place);
                HBox describebox = new HBox(new Label("Description: "),describe);
                HBox peoplebox = new HBox(new Label("People: "),people);
                HBox button_add=new HBox(add2);
                vb.getChildren().clear();
                vb.getChildren().addAll(namebox,datebox,timebox,placebox,describebox,peoplebox,button_add);
                add2.setOnMouseClicked(event1 -> {
                    if(name.getText().length()>0&&date.getText().length()>0&&time.getText().length()>0&&place.getText().length()>0&&describe.getText().length()>0&&people.getText().length()>0) {
                        plan1=new Plan();
                        plan1.setName(name.getText());
                        plan1.setDate(date.getText());
                        plan1.setTime(time.getText());
                        plan1.setPlace(place.getText());
                        plan1.setDescribe(describe.getText());
                        plan1.setPeople(people.getText());
                        plan.add(plan1);
                        table.refresh();
                    }else
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.initOwner(root.getScene().getWindow());
                        alert.setTitle("No Filling");
                        alert.setHeaderText("Not filling the fields");
                        alert.setContentText("Please fill the fields.");
                        alert.showAndWait();
                    }
                    vb.getChildren().clear();
                });
            }

        });
        TableView.TableViewSelectionModel<Plan> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Plan>(){
            public void changed(ObservableValue<? extends Plan> val, Plan oldVal, Plan newVal){

                    plan1 = newVal;
                    vb.getChildren().clear();

                    getPlan(second_part,table);
                    vb.getChildren().clear();

            }
        });
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(plan1!=null)
                {
                    vb.getChildren().clear();
                    plan.remove(plan1);
                    table.refresh();
                    vb.getChildren().clear();
                }

            }
        });
        button4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               rootNode.getChildren().clear();
               second_part.getChildren().clear();
               Label exit1=new Label("Goodbye!!!");
               second_part.setMargin(exit1, new Insets(200.0, 0.0, 0.0, -60.0));
               exit1.setTextFill(Color.web("#0076a3"));
               exit1.setFont(Font.font("Cambria", 32));
               exit1.setOpacity(0);
               second_part.getChildren().add(exit1);
               rootNode.getChildren().add(second_part);
               FadeTransition ft = new FadeTransition(Duration.millis(1000), exit1);
               ft.setToValue(1);
               ft.play();
               (new Runnable()).start();
            }
        });
        vb.getChildren().clear();
        primaryStage.show();


    }

    public void getPlan(FlowPane second_part,TableView<Plan> table)
    {
        TextField name=new TextField();
        TextField date=new TextField();
        TextField time=new TextField();
        TextField place=new TextField();
        TextField describe=new TextField();
        TextField people=new TextField();
        Button add2=new Button("Change");
        name.setText(plan1.getName());
        date.setText(plan1.getDate());
        time.setText(plan1.getTime());
        place.setText(plan1.getPlace());
        describe.setText(plan1.getDescribe());
        people.setText(plan1.getPeople());
        HBox namebox = new HBox(new Label("Name: "),name);
        HBox datebox = new HBox(new Label("Date: "),date);
        HBox timebox = new HBox(new Label("Time: "),time);
        HBox placebox = new HBox(new Label("Place: "),place);
        HBox describebox = new HBox(new Label("Description: "),describe);
        HBox peoplebox = new HBox(new Label("People: "),people);
        HBox button_change=new HBox(add2);
        VBox vb=new VBox();
        vb.getChildren().clear();
        vb.getChildren().addAll(namebox,datebox,timebox,placebox,describebox,peoplebox,button_change);
        second_part.getChildren().add(vb);

        add2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(name.getText().length()>0&&date.getText().length()>0&&time.getText().length()>0&&place.getText().length()>0&&describe.getText().length()>0&&people.getText().length()>0) {
                    plan1.setName(name.getText());
                    plan1.setDate(date.getText());
                    plan1.setTime(time.getText());
                    plan1.setPlace(place.getText());
                    plan1.setDescribe(describe.getText());
                    plan1.setPeople(people.getText());
                    table.refresh();
                    vb.getChildren().clear();
                }else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(second_part.getScene().getWindow());
                    alert.setTitle("No Filling");
                    alert.setHeaderText("Not filling the fields");
                    alert.setContentText("Please fill the fields.");
                    alert.showAndWait();
                }

            }

        });
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(name.getText().length()>0&&date.getText().length()>0&&time.getText().length()>0&&place.getText().length()>0&&describe.getText().length()>0&&people.getText().length()>0) {
                    plan1.setName(name.getText());
                    plan1.setDate(date.getText());
                    plan1.setTime(time.getText());
                    plan1.setPlace(place.getText());
                    plan1.setDescribe(describe.getText());
                    plan1.setPeople(people.getText());
                    plan.remove(plan1);
                    table.refresh();
                    vb.getChildren().clear();
                }else
                {
                    System.out.println("Error");
                }


            }
        });

    }



    public static void main(String[] args)  {
        launch(args);
    }
}
