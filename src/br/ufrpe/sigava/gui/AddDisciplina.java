/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.sigava.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author elive
 */
public class AddDisciplina extends Application{
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AddAluno.stage = stage;
    }
    
    public static void fechar(){
        getStage().close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        Parent rootADM = FXMLLoader.load(getClass().getResource("AddDisciplina.fxml"));
        Scene sceneADM = new Scene(rootADM);
        primaryStage.setScene(sceneADM);
        primaryStage.show();
        setStage(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
